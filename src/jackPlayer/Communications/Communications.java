package jackPlayer.Communications;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.ResourceType;
import battlecode.common.RobotController;

import java.util.ArrayList;
import java.util.List;

public class Communications {

    private static int unpack(int packedInt, PackedMask mask) {
        return (packedInt & mask.mask) >>> mask.shift;
    }

    private static int getPage(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageIndex.PAGE_NUMBER.index), PackedMask.PAGE_INDEX);
    }

    private static ResourceType intToResourceType(int value) {
        switch (value) {
            case 1:
                return ResourceType.ADAMANTIUM;
            case 2:
                return ResourceType.MANA;
            case 3:
                return ResourceType.ELIXIR;
            default:
                return null;
        }
    }

    public static List<Well> getWells(RobotController rc) throws GameActionException {
        if (getPage(rc) != PageLocation.WELLS.page)
            return null;

        List<Well> wells = new ArrayList<>();
        for (int i = PageIndex.WELLS.index; i < 62; i += 2) {
            int packedLoc = rc.readSharedArray(i);
            int wellType = unpack(packedLoc, PackedMask.WELL_TYPE);
            if (wellType == 0) // Using this as the null terminator for the well list.
                break;

            boolean hasAmplifier = unpack(packedLoc, PackedMask.AMPLIFIER_PRESENT) != 0;
            int x = unpack(packedLoc, PackedMask.WELL_X);
            int y = unpack(packedLoc, PackedMask.WELL_Y);
            int packedStatus = rc.readSharedArray(i + 1);
            int workerCount = unpack(packedStatus, PackedMask.WELL_WORKER_COUNT);
            int pressure = unpack(packedStatus, PackedMask.WELL_PRESSURE);

            wells.add(new Well(i, hasAmplifier, workerCount, pressure - 64, intToResourceType(wellType), new MapLocation(x,y)));
        }
        return wells;
    }

    public static void incrementWellWorkers (RobotController rc, Well well) throws GameActionException {
        int countIndex = well.getWellIndex() + 1;
        int newCount = well.getWorkerCount() < 15 ? well.getWorkerCount() + 1 : 15;
        // TODO: Need to write a generalized pack function
        rc.writeSharedArray(countIndex, (newCount << 4) & (well.getPressure()));
    }
}


