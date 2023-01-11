package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Well;

import java.util.*;

public class CarrierController extends Controller {

    private MapLocation wellLocation;
    private ResourceType wellType;

    public CarrierController(RobotController rc) throws GameActionException {
        super(rc);
        assignWell(rc);
    }

    private void assignWell(RobotController rc) throws GameActionException {
        List<Well> wells = Communications.getWells(rc);
        if (wells == null)
            return; // TODO: Add logic here beside just waiting

        MapLocation curLoc = rc.getLocation();
        // If this is too expensive switch to repeatedly taking the minimum
        Collections.sort(wells, Comparator.comparingInt(o -> curLoc.distanceSquaredTo(o.getMapLocation())));
        for (Well well : wells) {
            if (well.getWorkerCount() <= 15 || well.getPressure() < 5) {
                wellLocation = well.getMapLocation();
                wellType = well.getType();
                Communications.incrementWellWorkers(rc, well);
            }
        }
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);

        if (wellLocation == null) {
            assignWell(rc);
            if (wellLocation == null)
                return;
        }

    }
}
