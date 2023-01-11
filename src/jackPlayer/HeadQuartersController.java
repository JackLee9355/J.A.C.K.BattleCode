package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;

import java.util.List;

public class HeadQuartersController extends Controller {

    int headQuartersIndex;


    public HeadQuartersController(RobotController rc) {
        super(rc);
        try {
            List<Headquarter> headquarters = Communications.getHeadQuarters(rc);
            if (headquarters == null) {
                headQuartersIndex = 0;
            } else {
                headQuartersIndex = headquarters.size();
            }
            if (headQuartersIndex == 0) {
                Communications.initPages(rc);
            }
        } catch (GameActionException e) {
            System.out.println(rc.getType() + "Exception, cannot initialize");
        }
    }

    public void run(RobotController rc) throws GameActionException {
        super.run(rc); // Common actions
        if (headQuartersIndex == 0) {
            WellInfo[] wellInfos = rc.senseNearbyWells();
            for (WellInfo wellInfo : wellInfos) {
                manageWell(rc, wellInfo);
            }
            if (turnCount > 2 && turnCount % 2 == 0) {
                Communications.iteratePage(rc);
            }
            Communications.processInput(rc);
        }

    }

}
