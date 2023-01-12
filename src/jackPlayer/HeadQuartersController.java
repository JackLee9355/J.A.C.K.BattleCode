package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.Well;

import java.util.List;

public class HeadQuartersController extends Controller {

    int headQuartersIndex;
    int carriersConstructed;
    int launchersConstructed;
    int amplifiersConstructed;

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

    private void constructLauncher(RobotController rc) throws GameActionException {
        if (rc.getResourceAmount(ResourceType.MANA) < 60)
            return;

        boolean built = false;
        for (MapLocation loc : adjacentSquares(rc)) {
            if (rc.canBuildRobot(RobotType.LAUNCHER, loc)) {
                rc.buildRobot(RobotType.LAUNCHER, loc);
                built = true;
                break;
            }
        }
        if (built)
            launchersConstructed++;
    }

    private void constructCarrier(RobotController rc) throws GameActionException {
        if (rc.getResourceAmount(ResourceType.ADAMANTIUM) < 50)
            return;

        boolean built = false;
        for (MapLocation loc : adjacentSquares(rc)) {
            if (rc.canBuildRobot(RobotType.CARRIER, loc)) {
                rc.buildRobot(RobotType.CARRIER, loc);
                built = true;
                break;
            }
        }
        if (built)
            carriersConstructed++;
    }

    private void constructAmplifier(RobotController rc) throws GameActionException {
        if (rc.getResourceAmount(ResourceType.ADAMANTIUM) < 40 || rc.getResourceAmount(ResourceType.MANA) < 40)
            return;

        boolean built = false;
        for (MapLocation loc : adjacentSquares(rc)) {
            if (rc.canBuildRobot(RobotType.AMPLIFIER, loc)) {
                rc.buildRobot(RobotType.AMPLIFIER, loc);
                built = true;
                break;
            }
        }
        if (built)
            carriersConstructed = 0;
            launchersConstructed = 0;
            amplifiersConstructed++;
    }

    private void constructUnits(RobotController rc) throws GameActionException {
        if ((carriersConstructed + launchersConstructed + 1) % 20 == 0) {
            constructAmplifier(rc);
        } else {
            constructLauncher(rc);
            constructCarrier(rc);
        }
    }

    public void run(RobotController rc) throws GameActionException {
        super.run(rc); // Common actions
        rc.setIndicatorString(headQuartersIndex + "");
        if (turnCount < 3) {
            Communications.addFriendlyHeadquarters(rc, myLocation.x, myLocation.y, headQuartersIndex);
        }
        WellInfo[] wellInfos = rc.senseNearbyWells();
        for (WellInfo wellInfo : wellInfos) {
            manageWell(rc, wellInfo);
        }
        if (headQuartersIndex == 0) {
            Communications.processInput(rc);
            if (turnCount > 10) {
                Communications.iteratePage(rc);
            }
//            List<Well> wells = Communications.getWells(rc);
//            if (wells != null) {
//                StringBuilder sb = new StringBuilder();
//                for (Well well : wells) {
//                    sb.append(well.getMapLocation().x).append(" ").append(well.getMapLocation().y).append(", ");
//                }
//                sb.append("\n");
//                System.out.println(sb.toString());
//            }
        }
        constructUnits(rc);
    }

}
