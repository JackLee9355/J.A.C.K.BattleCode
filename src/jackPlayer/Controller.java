package jackPlayer;

import java.util.*;
import battlecode.common.*;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import java.util.Random;

public abstract class Controller {
    protected int turnCount = 0;
    protected final int mapWidth;
    protected final int mapHeight;
    protected MapLocation myLocation;
    protected final int[] sharedArray = new int[64];
    protected Direction alongObstacleDir = null;
    protected static final Random rng = new Random(6147);
    protected static final int[][] DIRS = new int[][] {
            {0, 1},
            {1, 0},
            {1, 1},
            {0, -1},
            {-1, 0},
            {-1, -1},
            {1, -1},
            {-1, 1}
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

    public Controller(RobotController rc) {
        mapWidth = rc.getMapWidth();
        mapHeight = rc.getMapHeight();
    }

    public void run(RobotController rc) throws GameActionException {
        turnCount++;
        myLocation = rc.getLocation();
    }

    protected void readEntireArray(RobotController rc) throws GameActionException {
        for (int i = 0; i < 64; i++) {
            sharedArray[i] = rc.readSharedArray(i);
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

    public MapLocation[] enemiesInVision(RobotController rc, int visionRadiusSquared, Team enemyTeam) throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(visionRadiusSquared, enemyTeam);
        MapLocation[] enemyLocations = new MapLocation[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyLocations[i] = enemies[i].location;
        }
        return enemyLocations;
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
            rc.move(dir); alongObstacleDir = null;
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

    // A Star Implementation
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
        MapLocation[] allLocations = rc.getAllLocationsWithinRadiusSquared(myLocation, rc.getType().visionRadiusSquared);
        MapLocation closestToTarget = closestLocation(allLocations, myLocation, target);

        // Frontier (nodes that we could be visiting until finding the closestToTarget location)
        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Node start = new Node(myLocation, myLocation.distanceSquaredTo(closestToTarget));
        frontier.add(start);

        // Came from array stores the shortest and least expensive path given our heuristic
        int[][][] cameFrom = new int[60][60][2];

        // Keeps track of the costs so far made in the path
        int[][] costSoFar = new int[60][60];
        for (int[] row : costSoFar) Arrays.fill(row, -1);

        // Setting defaults for the path on location (x, y) & its cost
        cameFrom[myLocation.x][myLocation.y][0] = -1;
        cameFrom[myLocation.x][myLocation.y][1] = -1;
        costSoFar[myLocation.x][myLocation.y] = 0;

        // Iterate until the frontier is completely empty or a path is found
        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();
            // Path is found, break and just flow the head of the path (closestToTarget)
            // back to start (stop right before)
            if (curr.location.equals(closestToTarget)) {
                break;
            }

            // Iterate through all 8 directions && build the path
            for (int[] dir : DIRS) {
                int currX = curr.location.x, currY = curr.location.y;
                MapLocation next = new MapLocation(currX + dir[0], currY + dir[1]);
                if (!rc.onTheMap(next)) {
                    continue;
                }

                if (!rc.canSenseLocation(next)) {
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
                    int priority = newCost + next.distanceSquaredTo(closestToTarget);
                    frontier.add(new Node(next, priority));

                    // Map cameFrom[next] = curr
                    cameFrom[next.x][next.y][0] = currX;
                    cameFrom[next.x][next.y][1] = currY;
                }
            }
        }

        // Find the moveSpot right before the start location by following the path back
        MapLocation moveSpot = closestToTarget, curr = closestToTarget;
        while (cameFrom[curr.x][curr.y][0] > 0 && cameFrom[curr.x][curr.y][1] > 0) {
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

    public void generalExplore(RobotController rc) throws GameActionException {
        if(rc.isMovementReady()){
            Direction dir = null;
            int[] nearby = new int[4]; //0-N (up) 1-E (right), 2-S (down), 3-W (left)
            // +1 for ally robot (to disperse), +1 for opposite direction if enemy robot (to avoid)
            RobotInfo[] nearbyRobots = rc.senseNearbyRobots();
            int thisX = rc.getLocation().x;
            int thisY = rc.getLocation().y;
            for (RobotInfo robot : nearbyRobots) {
                int x = robot.location.x;
                int y = robot.location.y;
                int diffX = x - thisX; //pos = enemy is N, neg = enemy = S
                int diffY = y - thisY; //pos = enemy is E, neg = W
                if (diffX > 0) {
                    nearby[0] += 1;
                }
                if (diffX < 0) {
                    nearby[2] += 1;
                }
                if (diffY > 0) {
                    nearby[1] += 1;
                }
                if (diffY < 0) {
                    nearby[3] += 1;
                }
            }
            boolean N = false;
            boolean S = false;
            boolean E = false;
            boolean W = false;
            int updown = nearby[0] - nearby[2];
            int ew = nearby[1] - nearby[3];
            if (updown > 0) {
                S = true; //enemies in N
            } else if (updown < 0) {
                N = true;
            }
            if (ew > 0) {
                W = true;
            }
            if (ew < 0) {
                E = true;
            }
            if(N && E && rc.canMove(Direction.NORTHEAST)){
                dir = Direction.NORTHEAST;
            } else if (N && W && rc.canMove((Direction.NORTHWEST))){
                dir = Direction.NORTHWEST;
            } else if (S && E && rc.canMove((Direction.SOUTHEAST))){
                dir = Direction.SOUTHEAST;
            } else if (S && W && rc.canMove(Direction.SOUTHWEST)){
                dir = Direction.SOUTHWEST;
            } else if(N && rc.canMove(Direction.NORTH)){
                dir = Direction.NORTH;
            } else if (E && rc.canMove(Direction.EAST)){
                dir = Direction.EAST;
            } else if (S && rc.canMove(Direction.SOUTH)){
                dir = Direction.SOUTH;
            } else if(W && rc.canMove(Direction.WEST)){
                dir = Direction.WEST;
            }
            if(dir != null ){
                rc.move(dir); //only moves on space
            }
        }
    }
}
