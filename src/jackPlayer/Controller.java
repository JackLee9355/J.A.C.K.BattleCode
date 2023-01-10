package jackPlayer;

import battlecode.common.*;

import java.util.Random;

public abstract class Controller {
    protected int turnCount = 0;
    protected static Direction alongObstacleDir = null;
    protected static final Random rng = new Random(6147);
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

    public abstract void run(RobotController rc) throws GameActionException;

    public static MapLocation[] enemiesInVision(RobotController rc, int visionRadiusSquared, Team enemyTeam) throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(visionRadiusSquared, enemyTeam);
        MapLocation[] enemyLocations = new MapLocation[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyLocations[i] = enemies[i].location;
        }
        return enemyLocations;
    }

    public static boolean moveTowards(RobotController rc, MapLocation target) throws GameActionException {
        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return false;
        }

        // Verify it's not at target location
        MapLocation currLoc = rc.getLocation();
        if (currLoc.equals(target)) {
            return true;
        }

        // Get direction towards target
        Direction dir = currLoc.directionTo(target);

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
}
