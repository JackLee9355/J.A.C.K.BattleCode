package jackPlayer.Communications;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.ResourceType;
import battlecode.common.RobotController;

import java.util.ArrayList;
import java.util.List;

public class Communications {

    private static final int NULL_INDICATOR = (1 << 16) - 1;
    private static int[][] pages = new int[2][64];

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

    public static void initPages(RobotController rc) throws GameActionException {
        for (int i = 0; i < pages.length; i++) {
            pages[i][0] = i;
            for (int j = 1; j < 64; j++) {
                pages[i][j] = NULL_INDICATOR;
            }
        }
        for (int i = 0; i < 64; i++) {
            rc.writeSharedArray(i, pages[0][i]);
        }
    }

    public static void iteratePage(RobotController rc) throws GameActionException {
        int pageIndex = getPage(rc);
        for (int i = 0; i < 64; i++) {
            pages[pageIndex][i] = rc.readSharedArray(i);
        }
        pageIndex++;
        pageIndex %= pages.length;
        for (int i = 0; i < 64; i++) {
            rc.writeSharedArray(i, pages[pageIndex][i]);
        }
    }

    public static void processInput(RobotController rc) throws GameActionException {
        int input;
        if ((input = rc.readSharedArray(1)) == NULL_INDICATOR) {
            return;
        }


    }

    public static List<Headquarter> getHeadQuarters(RobotController rc) throws GameActionException {
        if (getPage(rc) != PageLocation.HEADQUARTERS.page)
            return null;
        List<Headquarter> headquarters = new ArrayList<>();
        for (int i = PageIndex.HEADQUARTERS.index; i < PageIndex.HEADQUARTERS.index + 4; i++) {
            int packedInfo = rc.readSharedArray(i);
            if (packedInfo == 0 || packedInfo == NULL_INDICATOR) {
                break;
            }
            int x = unpack(packedInfo, PackedMask.HEADQUATER_X);
            int y = unpack(packedInfo, PackedMask.HEADQUATER_Y);
            headquarters.add(new Headquarter(new MapLocation(x, y)));
        }
        return headquarters;
    }

    public static List<Well> getWells(RobotController rc) throws GameActionException {
        if (getPage(rc) != PageLocation.WELLS.page)
            return null;

        List<Well> wells = new ArrayList<>();
        for (int i = PageIndex.WELLS.index; i < 62; i += 2) {
            int packedLoc = rc.readSharedArray(i);
            int wellType = unpack(packedLoc, PackedMask.WELL_TYPE);
            if (wellType == NULL_INDICATOR) // Using this as the null terminator for the well list.
                break;

            boolean hasAmplifier = unpack(packedLoc, PackedMask.AMPLIFIER_PRESENT) != 0;
            int x = unpack(packedLoc, PackedMask.WELL_X);
            int y = unpack(packedLoc, PackedMask.WELL_Y);
            int packedStatus = rc.readSharedArray(i + 1);
            int workerCount = unpack(packedStatus, PackedMask.WELL_WORKER_COUNT);
            int pressure = unpack(packedStatus, PackedMask.WELL_PRESSURE);

            wells.add(new Well(hasAmplifier, workerCount, pressure, intToResourceType(wellType), new MapLocation(x, y)));
        }
        return wells;
    }
}


