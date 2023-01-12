package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Well;

import java.util.Arrays;
import java.util.List;

public class AmplifierController extends Controller {
    public static Well assignedWell = null;
    public static MapLocation assignedWellLoc = null;
    public static List<Well> wells;
    public static WellInfo [] nearbyWells;
    public AmplifierController(RobotController rc) {
        super(rc);

    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);

    }

    public void assignToWell(RobotController rc) throws GameActionException {
        //assign to a well that is the closest and is unoccupied
        wells = Communications.getWells(rc);
        MapLocation curr = rc.getLocation();
        Well assignTo = null;
        int distToWell = 1000;
        for(Well well : wells){
            if(!well.isAmplifierPresent()){
                int tempDistToWell = curr.distanceSquaredTo(well.getMapLocation());
                if(tempDistToWell < distToWell){
                    assignTo = well;
                    distToWell = tempDistToWell;
                }
            }
        }
        assignedWell = assignTo;
        assignedWellLoc = assignedWell.getMapLocation();
    }

    public void moveToWell(RobotController rc) throws GameActionException {
        if(assignedWell != null) {
            MapLocation curr = rc.getLocation();
            if (curr.distanceSquaredTo(assignedWellLoc) > 2 && rc.canMove(curr.directionTo(assignedWellLoc))) {
                rc.move(curr.directionTo(assignedWellLoc));
            } else if (curr.distanceSquaredTo(assignedWellLoc) <= 2) {
                updateWellInfo(rc);
            }
        }
    }

    //update the well info and search for nearby wells
    public static void updateWellInfo(RobotController rc) throws GameActionException {
        searchForWells(rc);
        //manageWell and update who it is assigned to

    }

    public static void searchForWells(RobotController rc) throws GameActionException {
        WellInfo [] temp = rc.senseNearbyWells();
        wells = Communications.getWells(rc);
        if(!temp.equals(nearbyWells)){ //means we found a new one
            nearbyWells = temp;
            boolean found = false;
            for(Well well : wells){
                for(WellInfo nearbyWell : nearbyWells){
                    if(nearbyWell.getMapLocation().distanceSquaredTo(well.getMapLocation()) > 0){
                        found = true;
                        break;
                    }
                }
            }
            if(!found){
                //manageWell
            }
        }

    }

}
