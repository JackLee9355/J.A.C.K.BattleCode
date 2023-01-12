package jackPlayer.Pathing;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;
import battlecode.common.GameActionException;

public abstract class BFS {
    private static Direction alongObstacleDir = null;
    public static final int BYTECODE_REMAINING = 1000;
    public static final int GREEDY_TURNS = 4; // changable
    public RobotController rc;
    public Tracker tracker;
    public MapLocation currentTarget = null;
    public int turnsGreedy = 0;

    public BFS (RobotController rc) {
        this.rc = rc;
        this.tracker = new Tracker();
    }

    void reset() {
        turnsGreedy = 0;
        tracker.reset();
    }

    public void move(MapLocation target) throws GameActionException {
        moveTowardsBFS(target, false);
    }

    void update(MapLocation target) {
        if (currentTarget == null || target.distanceSquaredTo(currentTarget) > 0) {
            reset();
        } else --turnsGreedy;
        currentTarget = target;
        tracker.add(rc.getLocation());
    }

    public void naiveMove(RobotController rc, MapLocation target) throws GameActionException {
        // Cool down active, can't move
        if (!rc.isMovementReady()) {
            return;
        }

        // Verify it's not at target location
        if (rc.getLocation().equals(target)) {
            return;
        }

        // Get direction towards target
        Direction dir = rc.getLocation().directionTo(target);

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

    public void moveTowardsBFS(MapLocation target, boolean greedy) throws GameActionException {
        if (target == null) return;
        if (!rc.isMovementReady()) return;
        if (rc.getLocation().distanceSquaredTo(target) == 0) return;

        update(target);

        if (!greedy && turnsGreedy <= 0){
            Direction dir = getBestDirection(target);
            if (dir != null && !tracker.check(rc.getLocation().add(dir))){
                naiveMove(rc, target);
                return;
            } else activateGreedy();
        }

        if (Clock.getBytecodesLeft() >= BYTECODE_REMAINING) {
            naiveMove(rc, target);
            --turnsGreedy;
        }
    }

    void activateGreedy(){
        turnsGreedy = GREEDY_TURNS;
    }

    public abstract Direction getBestDirection(MapLocation target);
}
