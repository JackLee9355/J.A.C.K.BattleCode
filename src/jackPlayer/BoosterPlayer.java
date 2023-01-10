package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class BoosterPlayer extends Player {

    public BoosterPlayer() {

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        turnCount++;

    }
}
