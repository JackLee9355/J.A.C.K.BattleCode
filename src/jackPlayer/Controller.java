package jackPlayer;

<<<<<<< HEAD
import battlecode.common.*;
=======
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
>>>>>>> 0a0147ef05dd5b52658d5771718ff14b78e3d8eb

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

<<<<<<< HEAD
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
=======
    protected final int mapWidth;
    protected final int mapHeight;

    protected MapLocation myLocation;
    protected final int[] sharedArray = new int[64];

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


>>>>>>> 0a0147ef05dd5b52658d5771718ff14b78e3d8eb
}
