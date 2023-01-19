package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Well;
import jackPlayer.Pathing.RobotPathing;

import java.util.Arrays;
import java.util.List;

public class AmplifierController extends Controller {
    public static Well assignedWell = null;
    public static MapLocation assignedWellLoc = null;
    public static boolean atWell = false;
    public static List<Well> wells;
    public static WellInfo[] nearbyWells;
    public boolean noWells = false;

    public AmplifierController(RobotController rc) {
        super(rc);
        pathing = new RobotPathing(rc);
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        if (assignedWell == null) {
            assignToWell(rc);
        }
        if (noWells) {
            generalExplore(rc);
        }
        if (atWell == false && assignedWell != null) {
            moveToWell(rc);
        }
    }

    public void assignToWell(RobotController rc) throws GameActionException {
        //assign to a well that is the closest and is unoccupied
        wells = Communications.getWells(rc);
        if (wells == null) {
            return;
        }
        MapLocation curr = rc.getLocation();
        Well assignTo = null;
        int distToWell = 1000;
        for (Well well : wells) {
            if (!well.isAmplifierPresent()) {
                int tempDistToWell = curr.distanceSquaredTo(well.getMapLocation());
                if (tempDistToWell < distToWell) {
                    assignTo = well;
                    distToWell = tempDistToWell;
                }
            }
        }
        if (assignTo == null) {
            noWells = true;
        } else {
            assignedWell = assignTo;
            assignedWellLoc = assignedWell.getMapLocation();
        }
    }

    public void moveToWell(RobotController rc) throws GameActionException {
        MapLocation curr = rc.getLocation();
        if (curr.distanceSquaredTo(assignedWellLoc) > 4 && rc.canMove(curr.directionTo(assignedWellLoc))) {
            rc.move(curr.directionTo(assignedWellLoc));
        } else if (curr.distanceSquaredTo(assignedWellLoc) <= 4) { //arrived at well
            atWell = true;
        }
    }


    public static void searchForWells(RobotController rc) throws GameActionException {
        WellInfo[] temp = rc.senseNearbyWells();
        wells = Communications.getWells(rc);
        if (wells == null) {
            return;
        }
        if (!Arrays.equals(temp, nearbyWells)) { //means we found a new one
            nearbyWells = temp;
            for (Well well : wells) {
                WellInfo wellToUpdate = null;
                for (WellInfo nearbyWell : nearbyWells) {
                    if (nearbyWell.getMapLocation().distanceSquaredTo(well.getMapLocation()) > 0 || nearbyWell.getMapLocation().distanceSquaredTo(assignedWellLoc) == 0) {
                        wellToUpdate = nearbyWell;
                        break;
                    }
                }
                if (wellToUpdate != null) {
                    if (assignedWell == null) {
                        assignedWell = well;
                        assignedWellLoc = well.getMapLocation();
                    }
                }
            }

        }

    }

}
