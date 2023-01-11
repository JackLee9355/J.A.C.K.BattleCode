package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.Well;

import java.util.*;

public class CarrierController extends Controller {

    private MapLocation headquarter;
    private MapLocation wellLocation;
    private ResourceType wellType;

    public CarrierController(RobotController rc) throws GameActionException {
        super(rc);
        assignWell(rc);
        assignHQ(rc);
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

    private void assignHQ(RobotController rc) throws GameActionException {
        List<Headquarter> headQuarters = Communications.getHeadQuarters(rc);
        if (headQuarters == null && headQuarters.size() != 0) {

        } else {
            MapLocation curLoc = rc.getLocation();
            headquarter = headQuarters.stream().min(
                    Comparator.comparingInt(o -> curLoc.distanceSquaredTo(o.getMapLocation()))
            ).get().getMapLocation();
        }
    }

    private void attemptCollect(RobotController rc) throws GameActionException {
        if (rc.canCollectResource(wellLocation, -1)) {
            rc.collectResource(wellLocation, -1);
        }
    }

    private void attemptDeposit(RobotController rc) throws GameActionException {
        if (headquarter.isAdjacentTo(rc.getLocation())) {
            rc.transferResource(headquarter, wellType, rc.getResourceAmount(wellType));
        }
    }

    private int totalHeld(RobotController rc) {
        return rc.getResourceAmount(ResourceType.ADAMANTIUM) +
                rc.getResourceAmount(ResourceType.ELIXIR) +
                rc.getResourceAmount(ResourceType.MANA);
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);

        assignHQ(rc);
        if (wellLocation == null) {
            assignWell(rc);
            if (wellLocation == null)
                return;
        }

        attemptCollect(rc);
        attemptDeposit(rc);
        if (totalHeld(rc) < 40) {
            moveTowards(rc, wellLocation);
        } else {
            moveTowards(rc, headquarter);
        }
        attemptCollect(rc);
        attemptDeposit(rc);
    }
}
