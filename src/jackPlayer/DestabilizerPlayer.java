package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class DestabilizerPlayer extends Player {

    public DestabilizerPlayer() {

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        turnCount++;

    }
}
