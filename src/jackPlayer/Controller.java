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
    protected static final int[][] DIRS = new int[][]{
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

        MapLocation[] allLocations = rc.getAllLocationsWithinRadiusSquared(myLocation, rc.getType().visionRadiusSquared);
        MapLocation closestToTarget = myLocation;
        int closestDistance = closestToTarget.distanceSquaredTo(target);
        for (MapLocation location : allLocations) {
            int currDistance = location.distanceSquaredTo(target);
            if (currDistance < closestDistance) {
                closestToTarget = location;
                closestDistance = currDistance;
            }
        }

        PriorityQueue<Node> frontier = new PriorityQueue<>();
        Node start = new Node(myLocation, 0 + myLocation.distanceSquaredTo(closestToTarget));
        frontier.add(start);

        int cameFrom[][][] = new int[60][60][2];
        int costSoFar[][] = new int[60][60];

        cameFrom[myLocation.x][myLocation.y][0] = -1;
        cameFrom[myLocation.x][myLocation.y][1] = -1;
        costSoFar[myLocation.x][myLocation.y] = 0;

        while (!frontier.isEmpty()) {
            Node curr = frontier.poll();
            if (curr.location.equals(closestToTarget)) {
                break; // last location is closestToTarget so just break and follow it back in cameFrom
            }

            for (int[] pair : DIRS) {
                int x0 = curr.location.x, y0 = curr.location.y;
                int x1 = pair[0], y1 = pair[1];
                MapLocation next = new MapLocation(x0 + x1, y0 + y1);
                if (!rc.onTheMap(next)) {
                    continue;
                }

                if (rc.canSenseLocation(next)) {
                    continue;
                }

                if (rc.senseRobotAtLocation(next) != null) {
                    continue;
                }

                // Graph cost of 1 given that its adj
                int newCost = costSoFar[curr.location.x][curr.location.y] + 1;
                if (costSoFar[next.x][next.y] == 0 || newCost < costSoFar[next.x][next.y]) {

                    // Map costSoFar[next] = newCost
                    costSoFar[next.x][next.y] = newCost;

                    int priority = newCost + next.distanceSquaredTo(closestToTarget);
                    frontier.add(new Node(next, priority));

                    // Map cameFrom[next] = curr
                    cameFrom[next.x][next.y][0] = curr.location.x;
                    cameFrom[next.x][next.y][1] = curr.location.y;
                }
            }
        }

        MapLocation moveSpot = closestToTarget, curr = closestToTarget;
        while (cameFrom[curr.x][curr.y][0] > 0 && cameFrom[curr.x][curr.y][1] > 0) {
            int parentX = cameFrom[curr.x][curr.y][0];
            int parentY = cameFrom[curr.x][curr.y][1];
            moveSpot = new MapLocation(curr.x, curr.y);
            curr = new MapLocation(parentX, parentY);
        }

        Direction dir = myLocation.directionTo(moveSpot);
        if (rc.canMove(dir)) {
            rc.move(dir);
            return true;
        }
        return false;
    }
}
