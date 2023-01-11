package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.EntityType;
import jackPlayer.Communications.Well;

import java.util.List;
import java.util.Random;

public abstract class Controller {
    protected int turnCount = 0;
    protected static final Random rng = new Random(6147);
    protected static final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    protected final int mapWidth;
    protected final int mapHeight;

    protected MapLocation myLocation;
    protected final int[] sharedArray = new int[64];

    public Controller(RobotController rc) {
        mapWidth = rc.getMapWidth();
        mapHeight = rc.getMapHeight();
    }

    public void run(RobotController rc) throws GameActionException {
        turnCount++;
        myLocation = rc.getLocation();
    }

    protected void manageWell(RobotController rc, WellInfo wellInfo) throws GameActionException {
        List<Well> wells;
        if ((wells = Communications.getWells(rc)) == null) {
            return;
        }
        int index = -1;
        for (int i = 0; i < wells.size(); i++) {
            Well w = wells.get(i);
            if (w.getMapLocation().equals(wellInfo.getMapLocation())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Communications.input(rc, EntityType.WELL, wellInfo.getMapLocation().x, wellInfo.getMapLocation().y);
        } else {
            // TODO: update info for well
        }
    }


}
