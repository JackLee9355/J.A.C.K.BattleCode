package jackPlayer.Pathing;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class RobotBFS extends BFS {

    public RobotBFS(RobotController rc) {
        super(rc);
    }

    public Direction getBestDirection(MapLocation target) {
        return null;
    }
}
