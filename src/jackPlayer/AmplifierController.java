package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class AmplifierController extends Controller {

    public AmplifierController() {

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        turnCount++;

    }
}
