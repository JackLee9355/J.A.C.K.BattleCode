package jackPlayer;

import jackPlayer.Communications.Communications;
import jackPlayer.Communications.EntityType;
import jackPlayer.Communications.Well;

import java.util.*;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract strictfp class Controller {
    protected int turnCount = 0;
    protected final int mapWidth;
    protected final int mapHeight;
    protected MapLocation myLocation;
    protected Direction alongObstacleDir = null;
    protected static final Random rng = new Random(6147);
    private final int[][] map; // [x][y]
    private static final int[][] DIRS = new int[][]{
            {0, 1}, {1, 0}, {1, 1},
            {0, -1}, {-1, 0}, {-1, -1},
            {1, -1}, {-1, 1}
    };
    protected static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };
    protected Map<MapLocation, WellInfo> wellCache = new HashMap<>();

    public Controller(RobotController rc) {
        mapWidth = rc.getMapWidth();
        mapHeight = rc.getMapHeight();
        map = new int[mapWidth][mapHeight];
    }

    public void run(RobotController rc) throws GameActionException {
        turnCount++;
        myLocation = rc.getLocation();
        cacheNewWells(rc);
        if (turnCount > 1 && rc.canWriteSharedArray(0, 0)) { // Worried about weird behavior on master hq
            writeWellCache(rc);
        }
    }

    protected void manageWell(RobotController rc, WellInfo wellInfo) throws GameActionException {
        List<Well> wells;
        if ((wells = Communications.getWells(rc)) == null) {
            return;
        }
        int index = -1;
        for (int i = 0; i < wells.size(); i++) {
            Well w = wells.get(i);
            if (w.getMapLocation().equals(wellInfo.getMapLocation())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Communications.input(rc, EntityType.WELL, wellInfo.getMapLocation().x, wellInfo.getMapLocation().y);
        } else {
            // TODO: update info for well
        }
    }

    protected List<MapLocation> adjacentSquares(RobotController rc) throws GameActionException {
        List<MapLocation> locations = new ArrayList<>();
        for (Direction dir : directions) {
            locations.add(rc.getLocation().add(dir));
        }
        return locations;
    }

    protected void readEntireArray(RobotController rc) throws GameActionException {
        for (int i = 0; i < 64; i++) {
            sharedArray[i] = rc.readSharedArray(i);
        }
    }

    private void writeWellCache(RobotController rc) throws GameActionException {
        Set<MapLocation> storedWells = new HashSet<>();
        List<Well> wells = Communications.getWells(rc);
        if (wells == null)
            return;

        for (Well well : wells) {
            storedWells.add(well.getMapLocation());
        }

        for (MapLocation wellLoc : wellCache.keySet()) {
            if (!storedWells.contains(wellLoc)) {
                manageWell(rc, wellCache.get(wellLoc));
                System.out.println("Managing new well at location: " + wellLoc.x + ", " + wellLoc.y);
                break; // We can't add more than one anyway
            }
        }
        // wellCache.clear(); Can be added for performance later if we didn't break out of the loop
    }

    private void cacheNewWells(RobotController rc) throws GameActionException {
        for (WellInfo wellInfo : rc.senseNearbyWells()) {
            wellCache.put(wellInfo.getMapLocation(), wellInfo);
        }
    }

    public MapLocation closestLocation(MapLocation[] locations, MapLocation relative, MapLocation target) throws GameActionException {
        MapLocation closestToTarget = relative;
        int closestDistance = closestToTarget.distanceSquaredTo(target);
        for (MapLocation location : locations) {
            int currDistance = location.distanceSquaredTo(target);
            if (currDistance < closestDistance) {
                closestToTarget = location;
                closestDistance = currDistance;
            }
        }
        return closestToTarget;
    }

    public boolean moveTowards(RobotController rc, MapLocation target) throws GameActionException {
        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return false;
        }

        // Verify it's not at target location
        if (myLocation.equals(target)) {
            return true;
        }

        // Get direction towards target
        Direction dir = myLocation.directionTo(target);

        // Move if no wall is present in the direction && bytecode is available to check
        if (rc.canMove(dir)) {
            rc.move(dir);
            alongObstacleDir = null;
        } else {
            // Set the along the obstacle direction to current direction
            if (alongObstacleDir == null) alongObstacleDir = dir;

            for (int i = 0; i < 8; i++) {

                if (rc.canMove(alongObstacleDir)) {
                    rc.move(alongObstacleDir);
                    // Turn back towards obstacle
                    alongObstacleDir = alongObstacleDir.rotateLeft();
                    return false;
                }

                // Keep rotating direction until it finds an empty space to move in
                alongObstacleDir = alongObstacleDir.rotateRight();
            }
        }

        return false;
    }

    class Node implements Comparable<Node> {
        public MapLocation location;
        public int weight;

        public Node(MapLocation location, int weight) {
            this.location = location;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.weight, n.weight);
        }
    }

    // A Star Implementation (NOT WORKING)
    // 3 costs:
    // G-cost = distance from starting node
    // H-cost = distance from end node
    // F-cost = G-cost + H-cost
    public boolean moveTowardsAStar(RobotController rc, MapLocation target) throws GameActionException {
        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return false;
        }

        // Verify it's not at target location
        if (myLocation.equals(target)) {
            return true;
        }

        // Determine all the locations that can be viewed by the robot
        MapLocation[] allLocations = rc.getAllLocationsWithinRadiusSquared(myLocation, 3);
        MapLocation closestToTarget = closestLocation(allLocations, myLocation, target);
        System.out.println("Tiles: " + allLocations.length);
        System.out.println("ByteCode after locations: " + Clock.getBytecodeNum());

        // Frontier (nodes that we could be visiting until finding the closestToTarget location)
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Node start = new Node(myLocation, Math.max(Math.abs(myLocation.x - closestToTarget.x),
                Math.abs(myLocation.y - closestToTarget.y)));
        frontier.add(start);

        System.out.println("ByteCode after priority queue: " + Clock.getBytecodeNum());
        // Came from array stores the shortest and least expensive path given our heuristic
        int[][][] cameFrom = new int[60][60][2];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 60; j++) {
                cameFrom[i][j][0] = -1;
                cameFrom[i][j][1] = -1;
            }
        }

        // Keeps track of the costs so far made in the path
        int[][] costSoFar = new int[60][60];
        for (int[] row : costSoFar) Arrays.fill(row, -1);

        // Setting defaults for the path on location (x, y) & its cost
        cameFrom[myLocation.x][myLocation.y][0] = -1;
        cameFrom[myLocation.x][myLocation.y][1] = -1;
        costSoFar[myLocation.x][myLocation.y] = 0;

        System.out.println("ByteCode after initialization: " + Clock.getBytecodeNum());

        // Iterate until the frontier is completely empty or a path is found
        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();
            System.out.println("ByteCode after poll: " + Clock.getBytecodeNum());
            // Path is found, break and just flow the head of the path (closestToTarget)
            // back to start (stop right before)
            if (curr.location.equals(closestToTarget)) {
                break;
            }

            // Iterate through all 8 directions && build the path
            for (Direction dir : directions) {
                int currX = curr.location.x, currY = curr.location.y;
                MapLocation next = new MapLocation(currX, currY).add(dir);

                if (next.distanceSquaredTo(myLocation) > 3) {
                    continue;
                }

                if (rc.senseRobotAtLocation(next) != null) {
                    continue;
                }

                // + 1 reviews to the graph cost & since they are all adjacent add 1
                int newCost = costSoFar[currX][currY] + 1;
                if (costSoFar[next.x][next.y] == -1 || newCost < costSoFar[next.x][next.y]) {

                    // Map costSoFar[next] = newCost
                    costSoFar[next.x][next.y] = newCost;

                    // Update priority and append it to the frontier
                    int priority = newCost + Math.max(Math.abs(next.x - currX), Math.abs(next.y - currY));
                    frontier.add(new Node(next, priority));

                    // Map cameFrom[next] = curr
                    cameFrom[next.x][next.y][0] = currX;
                    cameFrom[next.x][next.y][1] = currY;
                }
            }
            System.out.println("priority length: " + frontier.size());
            System.out.println("ByteCode after viewing all 8 directions of a single square: " + Clock.getBytecodeNum());
        }

        if (costSoFar[closestToTarget.x][closestToTarget.y] == -1) {
            return moveTowards(rc, target);
        }

        // Find the moveSpot right before the start location by following the path back
        MapLocation moveSpot = closestToTarget, curr = closestToTarget;
        while (cameFrom[curr.x][curr.y][0] >= 0 && cameFrom[curr.x][curr.y][1] >= 0) {
            int parentX = cameFrom[curr.x][curr.y][0];
            int parentY = cameFrom[curr.x][curr.y][1];
            moveSpot = new MapLocation(curr.x, curr.y);
            curr = new MapLocation(parentX, parentY);
        }

        // Move in that direction
        Direction dir = myLocation.directionTo(moveSpot);
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }

        return false;
    }

    public boolean moveTowardsBFS(RobotController rc, MapLocation target) throws GameActionException {

        rc.setIndicatorString("Pathing");

        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return false;
        }

        // Verify it's not at target location
        if (myLocation.equals(target)) {
            return true;
        }

        // Frontier (nodes that we could be visiting until finding the closestToTarget location)
        int[] dest = {target.x, target.y};
        int[][] queue = new int[10000][2];
        int front = 0, rear = 1;

        queue[0][0] = myLocation.x;
        queue[0][1] = myLocation.y;

        // Came from array stores the shortest and least expensive path given our heuristic
        int[][][] cameFrom = new int[60][60][2];
        for (int i = 0; i < 60; i++) {
            for (int j = 0; j < 60; j++) {
                cameFrom[i][j][0] = -1;
                cameFrom[i][j][1] = -1;
            }
        }

        int[] closest = {queue[0][0], queue[0][1]};
        int closestDistance = Math.max(Math.abs(closest[0] - dest[0]), Math.abs(closest[1] - dest[1]));

        // Iterate until the frontier is completely empty or a path is found
        while (front != rear) {
            int[] curr = {queue[front][0], queue[front][1]};

            // Iterate through all 8 directions && build the path
            for (int[] dir : DIRS) {

                MapLocation next = new MapLocation(curr[0] + dir[0], curr[1] + dir[1]);

                // Not viewable or on the map
                if (!rc.canSenseLocation(next)) {
                    continue;
                }

                // Already visited
                if (cameFrom[next.x][next.y][0] != -1) {
                    continue;
                }

                // Obstructed
                if (!rc.sensePassability(next)) {
                    continue;
                }

                // Set it as visited and add it to queue while updating rear
                cameFrom[next.x][next.y][0] = curr[0];
                cameFrom[next.x][next.y][1] = curr[1];
                queue[rear][0] = next.x;
                queue[rear][1] = next.y;

                // Compute relative distance from the current node being search to the destination
                int distance = Math.max(Math.abs(queue[rear][0] - dest[0]), Math.abs(queue[rear][1] - dest[1]));

                // Compare if it's less than the closest distance (NOTE: if distance == 0, that means it's the target)
                if (distance < closestDistance) {
                    closest[0] = queue[rear][0];
                    closest[1] = queue[rear][1];
                    closestDistance = distance;
                }

                rear++;
            }

            front++;
        }

        // Set the start to point to nothing (so there isn't a loop in the path thats followed back)
        cameFrom[queue[0][0]][queue[0][1]][0] = -1;
        cameFrom[queue[0][0]][queue[0][1]][1] = -1;

        // Find the moveSpot right before the start location by following the path back
        int[] moveSpot = {closest[0], closest[1]};
        int[] curr = {closest[0], closest[1]};
        while (cameFrom[curr[0]][curr[1]][0] >= 0) {
            int parentX = cameFrom[curr[0]][curr[1]][0];
            int parentY = cameFrom[curr[0]][curr[1]][1];

            moveSpot[0] = curr[0];
            moveSpot[1] = curr[1];

            curr[0] = parentX;
            curr[1] = parentY;
        }

        // Move in that direction
        Direction dir = myLocation.directionTo(new MapLocation(moveSpot[0], moveSpot[1]));
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }

        return false;
    }

    private static double dotProduct(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1];
    }

    public void generalExplore(RobotController rc) throws GameActionException {
        if (rc.isMovementReady()) {
            RobotInfo[] robots = rc.senseNearbyRobots(-1, rc.getTeam()); // TODO: Clouds
            Direction dir = directions[rng.nextInt(8)];
            double[] vector = new double[]{dir.dx, dir.dx};
            for (RobotInfo r : robots) {
                MapLocation loc = r.location;
                int dx = loc.x - myLocation.x;
                int dy = loc.y - myLocation.y;
                double inverse = 1.2 / (dx * dx + dy * dy);
                vector[0] -= dx * inverse;
                vector[1] -= dy * inverse;
            }
            double max = Double.MIN_VALUE;
            for (Direction d : directions) {
                MapLocation target = myLocation.add(d);
                if (rc.onTheMap(target) && rc.sensePassability(target) && !rc.isLocationOccupied(target)) {
                    double dot = dotProduct(new double[]{d.dx, d.dy}, vector);
                    if (dot > max) {
                        dir = d;
                        max = dot;
                    }
                }
            }
            if (rc.canMove(dir)) {
                rc.move(dir);
            }
        }
    }
}