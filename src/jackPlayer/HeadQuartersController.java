package jackPlayer;

import battlecode.common.*;

public class HeadQuartersController extends Controller {

    int[][] pages;
    int headQuartersIndex;


    public HeadQuartersController(RobotController rc) {
        super(rc);
        try {
            for (int i = 2; i < 6; i++) {
                pages[0][i] = rc.readSharedArray(i);
            }
            for (headQuartersIndex = 0; headQuartersIndex < 4; headQuartersIndex++) {
                if (pages[0][headQuartersIndex + 2] == 0) {
                    break;
                }
            }
        } catch (GameActionException e) {
            System.out.println(rc.getType() + "Exception, cannot initialize");
        }
    }

    public void run(RobotController rc) throws GameActionException {
        super.run(rc); // Common actions
        if (headQuartersIndex == 0) {
            int[] array = new int[64];
            for (int i = 0; i < 64; i++) {
                array[i] = rc.readSharedArray(i);
            }
            if (turnCount != 0) {

            }
        }
    }

}
