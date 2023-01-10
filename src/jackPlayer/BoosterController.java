package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class BoosterController extends Controller {

    public BoosterController() {

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        turnCount++;

    }
}
