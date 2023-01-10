package jackPlayer;

import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.RobotController;

import java.util.Random;

public abstract class Player {
    protected int turnCount = 0;
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
}
