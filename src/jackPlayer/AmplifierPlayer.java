package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class AmplifierPlayer extends Player {

    public AmplifierPlayer() {

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        turnCount++;

    }
}
