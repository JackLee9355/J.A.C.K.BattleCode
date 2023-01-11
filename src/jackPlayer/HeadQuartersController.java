package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;

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
        if (headQuartersIndex == 0) {
            if (turnCount > 2 && turnCount % 2 == 0) {
                Communications.iteratePage(rc);
            }
        }

        constructUnits(rc);
    }

}
