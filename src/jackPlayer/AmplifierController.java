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
    public static List<Well> wells;
    public static WellInfo[] nearbyWells;

    public AmplifierController(RobotController rc) {
        super(rc);
        pathing = new RobotPathing(rc);
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        generalExplore(rc);
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
        assignedWell = assignTo;
        assignedWellLoc = assignedWell.getMapLocation();
    }

    public void moveToWell(RobotController rc) throws GameActionException {
        if (assignedWell != null) {
            MapLocation curr = rc.getLocation();
            if (curr.distanceSquaredTo(assignedWellLoc) > 4 && rc.canMove(curr.directionTo(assignedWellLoc))) {
                rc.move(curr.directionTo(assignedWellLoc));
            } else if (curr.distanceSquaredTo(assignedWellLoc) <= 4) { //arrived at well
                searchForWells(rc); //see if there are any other nearby wells to add and to get the WellInfo of the assigned well

            }
        }
    }

    //update the well info
    public static void updateWellInfo(RobotController rc, WellInfo well) throws GameActionException {
        //manageWell and update who it is assigned to
        Controller.manageWell(rc, well);

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
                    //manageWell
                    updateWellInfo(rc, wellToUpdate);
                    if (assignedWell == null) {
                        assignedWell = well;
                        assignedWellLoc = well.getMapLocation();
                    }
                }
            }

        }

    }

}
