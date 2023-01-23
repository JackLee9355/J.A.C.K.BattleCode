package jackPlayer.Pathing;

import battlecode.common.*;

public abstract class Pathing {
    private Direction alongObstacleDir = null;
    private MapLocation currentTarget = null;
    private MapLocation myLocation = null;
    private Tracker tracker;
    public RobotController rc;

    public Pathing(RobotController rc) {
        this.rc = rc;
        this.tracker = new Tracker();
    }

    public void move(MapLocation target) throws GameActionException {
        myLocation = rc.getLocation();
        BFSMove(target);
    }

    // Reset tracker if new target has been specified else continue pathing with current tracker
    private void update(MapLocation target) {
        if (!target.equals(currentTarget)) {
            tracker.reset();
        }
        currentTarget = target;
        tracker.add(rc.getLocation());
    }

    private void bugMove(MapLocation target) throws GameActionException {
        // rc.setIndicatorString("Bug Pathing to " + target);
        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return;
        }

        // Verify it's not at target location
        if (myLocation.equals(target)) {
            return;
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
                    return;
                }

                // Keep rotating direction until it finds an empty space to move in
                alongObstacleDir = alongObstacleDir.rotateRight();
            }
        }
    }

    private void BFSMove(MapLocation target) throws GameActionException {
        if (!rc.isMovementReady()) return;
        if (myLocation.equals(target)) return;

        update(target);

        // Target is adjacent to my location & is unoccupied & is passable
        if (myLocation.distanceSquaredTo(target) <= 2) {
            Direction dir = myLocation.directionTo(target);
            if (rc.canMove(dir)) {
                rc.move(dir);
            }
            return;
        }

        // Get the best direction to move in computed by bellman ford
        Direction dir = getBestDirection(target);

        // Attempt to move if best direction was found, hasn't been seen in the tracker
        // (assuming our destination hasn't changed), and we can move else we will attempt a
        // possible not optimal bug move
        if (dir != null) {
            MapLocation optionToMove = myLocation.add(dir);
            if (!tracker.check(optionToMove) && rc.canMove(dir)) {
                rc.move(dir);
                tracker.add(optionToMove);
                alongObstacleDir = null;
                myLocation = rc.getLocation();
            }
        }

        bugMove(target);
    }

    public abstract Direction getBestDirection(MapLocation target) throws GameActionException;
}