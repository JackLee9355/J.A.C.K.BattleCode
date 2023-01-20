package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.PageLocation;
import jackPlayer.Communications.Well;

import java.util.List;

public class HeadQuartersController extends Controller {

    int headQuartersIndex;
    int carriersConstructed;
    int launchersConstructed;
    int amplifiersConstructed;
    int anchorsBuilt;
    MapLocation focusTarget;
    private int[][] prevWorkerCount = new int[60][60];

    public HeadQuartersController(RobotController rc) {
        super(rc);
        anchorsBuilt = 0;
        focusTarget = null;
        carriersConstructed = 0;
        launchersConstructed = 0;
        amplifiersConstructed = 0;
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

    private boolean constructLauncher(RobotController rc) throws GameActionException {
        if (rc.getResourceAmount(ResourceType.MANA) < 60)
            return false;

        boolean built = false;
        for (MapLocation loc : adjacentSquares(rc)) {
            if (rc.canBuildRobot(RobotType.LAUNCHER, loc)) {
                rc.buildRobot(RobotType.LAUNCHER, loc);
                built = true;
                break;
            }
        }
        if (built) {
            launchersConstructed++;
        }
        return built;
    }

    private boolean constructCarrier(RobotController rc) throws GameActionException {
        if (rc.getResourceAmount(ResourceType.ADAMANTIUM) < 50)
            return false;

        List<Well> wells = getShortStaffedWells(rc);
        if (wells == null || wells.size() == 0)
            return false;
        int prevStaffed = 0;
        for (Well well : wells) {
            if (prevWorkerCount[well.getMapLocation().x][well.getMapLocation().y] >= WELL_STAFF) {
                prevStaffed += 1;
            }
        }
        if (wells.size() == prevStaffed)
            return false;

        boolean built = false;
        for (MapLocation loc : adjacentSquares(rc)) {
            if (rc.canBuildRobot(RobotType.CARRIER, loc)) {
                rc.buildRobot(RobotType.CARRIER, loc);
                built = true;
                break;
            }
        }
        if (built) {
            carriersConstructed++;
        }
        return built;
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
        if (built) {
            amplifiersConstructed++;
        }
    }

    private void constructUnits(RobotController rc) throws GameActionException {
        boolean built = true;
        if (turnCount % 10 == 5) {
            constructAmplifier(rc);
        }
        while (rc.isActionReady() && built) {
            built = false;
            built |= constructLauncher(rc);
            built |= constructCarrier(rc);
        }
    }

    public void run(RobotController rc) throws GameActionException {
        super.run(rc); // Common actions
        rc.setIndicatorString(headQuartersIndex + "");
        if (turnCount < 3) {
            Communications.addFriendlyHeadquarters(rc, myLocation.x, myLocation.y, headQuartersIndex);
        }
        if (headQuartersIndex == 0) {
            Communications.processInput(rc);
            if (rc.getRoundNum() % 50 < PageLocation.NUM_PAGES && Communications.getPage(rc) == PageLocation.WELLS.page) {
                prevWorkerCount = new int[60][60];
                for (Well well : Communications.getWells(rc)) {
                    prevWorkerCount[well.getMapLocation().x][well.getMapLocation().y] = well.getWorkerCount();
                }
                Communications.resetAllWellWorkers(rc);
            }
            if (turnCount > 10) {
                Communications.iteratePage(rc);
            }
            if (turnCount > 100) {
                if ((turnCount / 50) % 2 == 0) {
                    if (focusTarget == null) {
                        List<MapLocation> targets = approxEnemyBase(rc);
                        if (targets != null) {
                            focusTarget = targets.get((turnCount / 100) % targets.size());
                        }
                    } else {
                        rc.setIndicatorString("Focus set to: (" + focusTarget.x + ", " + focusTarget.y + ")");
                        Communications.updateControl(rc, 1, focusTarget.x, focusTarget.y);
                    }
                } else {
                    focusTarget = null;
                    rc.setIndicatorString("Focus is null");
                    Communications.updateControl(rc, 0, 0, 0);
                }
            }
//            if (wells != null) {
//                StringBuilder sb = new StringBuilder();
//                for (Well well : wells) {
//                    sb.append(well.getMapLocation().x).append(" ").append(well.getMapLocation().y).append(", ");
//                }
//                sb.append("\n");
//                System.out.println(sb.toString());
//            }
        }

        if (turnCount >= 400 && anchorsBuilt < 2) {
            if (rc.canBuildAnchor(Anchor.STANDARD)) {
                rc.buildAnchor(Anchor.STANDARD);
                anchorsBuilt++;
            }
        } else {
            constructUnits(rc);
        }
    }

}
