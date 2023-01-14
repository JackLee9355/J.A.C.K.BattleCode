package stupidjack.Communications;

import battlecode.common.GameActionException;
import battlecode.common.MapLocation;
import battlecode.common.ResourceType;
import battlecode.common.RobotController;
import jackPlayer.Communications.EntityType;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.PackedMask;
import jackPlayer.Communications.PageLocation;
import jackPlayer.Communications.Well;

import java.util.ArrayList;
import java.util.List;

public class Communications {

    private static final int NULL_INDICATOR = (1 << 16) - 1;
    private static final int PAGE_SIZE = 64;
    private static final int PAGE_COUNT = 2;
    private static final int[][] pages = new int[PAGE_COUNT][PAGE_SIZE];

    static int control_number; // Only master headquarters manages

    private static int unpack(int packedInt, jackPlayer.Communications.PackedMask mask) {
        return (packedInt & mask.mask) >>> mask.shift;
    }

    private static int packControl(int index, int coordination, int focus_x, int focus_y) {
        return (index << jackPlayer.Communications.PackedMask.PAGE_INDEX.shift) | (coordination << jackPlayer.Communications.PackedMask.COORDINATION.shift) |
                ((focus_x >> 1) << jackPlayer.Communications.PackedMask.FOCUS_X.shift) | ((focus_y >> 1) << jackPlayer.Communications.PackedMask.FOCUS_Y.shift);
    }

    private static int packInput(int type, int x, int y) {
        return (type << jackPlayer.Communications.PackedMask.INPUT_TYPE.shift) | (x << jackPlayer.Communications.PackedMask.INPUT_X.shift) | (y << jackPlayer.Communications.PackedMask.INPUT_Y.shift);
    }

    private static int packWellPosition(int x, int y) {
        return (x << jackPlayer.Communications.PackedMask.WELL_X.shift) | (y << jackPlayer.Communications.PackedMask.WELL_Y.shift);
    }

    private static int packWellStatus() {
        return -1; // TODO
    }

    private static int packHeadQuarters(int x, int y) {
        return (1 << 15) | (x << jackPlayer.Communications.PackedMask.HEADQUARTER_X.shift) | (y << jackPlayer.Communications.PackedMask.HEADQUARTER_Y.shift);
    }

    private static int packAnchor() {
        return -1; // TODO
    }

    private static int getPage(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(jackPlayer.Communications.PageLocation.PAGE_NUMBER.index), jackPlayer.Communications.PackedMask.PAGE_INDEX);
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

    private static jackPlayer.Communications.EntityType intToEntityType(int value) {
        switch (value) {
            case 0:
                return jackPlayer.Communications.EntityType.ANCHOR;
            case 1:
                return jackPlayer.Communications.EntityType.WELL;
            case 2:
                return jackPlayer.Communications.EntityType.HEADQUARTER;
            default:
                return null;
        }
    }

    private static boolean addWell(RobotController rc, int x, int y) throws GameActionException {
        for (int i = jackPlayer.Communications.PageLocation.WELLS.index; i < jackPlayer.Communications.PageLocation.WELLS.end; i += jackPlayer.Communications.PageLocation.WELLS.size) {
            if (pages[jackPlayer.Communications.PageLocation.WELLS.page][i] == NULL_INDICATOR) {
                pages[jackPlayer.Communications.PageLocation.WELLS.page][i] = packWellPosition(x, y);
                pages[jackPlayer.Communications.PageLocation.WELLS.page][i + 1] = 0;
                if (getPage(rc) == jackPlayer.Communications.PageLocation.WELLS.page) {
                    rc.writeSharedArray(i, pages[jackPlayer.Communications.PageLocation.WELLS.page][i]);
                    rc.writeSharedArray(i + 1, pages[jackPlayer.Communications.PageLocation.WELLS.page][i + 1]);
                }
                return true;
            }
        }
        return false;
    }

    private static boolean addAnchor(int x, int y) {
        return false; // TODO: write
    }

    private static boolean addEnemyBase(int x, int y) {
        return false; // TODO: write
    }

    public static void initPages(RobotController rc) throws GameActionException {
        for (int i = 0; i < pages.length; i++) {
            pages[i][0] = packControl(i, 0, 0, 0);
            for (int j = 1; j < PAGE_SIZE; j++) {
                pages[i][j] = NULL_INDICATOR;
            }
        }
        for (int i = 0; i < PAGE_SIZE; i++) {
            rc.writeSharedArray(i, pages[0][i]);
        }
    }

    public static void iteratePage(RobotController rc) throws GameActionException {
        int pageIndex = getPage(rc);
        for (int i = 1; i < PAGE_SIZE; i++) {
            int read = rc.readSharedArray(i);
            if (read != NULL_INDICATOR) {
                pages[pageIndex][i] = read;
//                System.out.println("Saved: " + read + ", Index: " + i);
            }
        }
        pageIndex++;
        pageIndex %= pages.length;
        for (int i = 0; i < PAGE_SIZE; i++) {
            rc.writeSharedArray(i, pages[pageIndex][i]);
        }
    }

    public static void addFriendlyHeadquarters(RobotController rc, int x, int y, int index) throws GameActionException {
        int packed = packHeadQuarters(x, y);
        rc.writeSharedArray(jackPlayer.Communications.PageLocation.HEADQUARTERS.index + index, packed);
    }

    public static void input(RobotController rc, jackPlayer.Communications.EntityType type, int x, int y) throws GameActionException {
//        System.out.println("Inputting (" + x + ", " + y + ")");
        if (rc.readSharedArray(jackPlayer.Communications.PageLocation.INPUT.index) == NULL_INDICATOR) {
            rc.writeSharedArray(jackPlayer.Communications.PageLocation.INPUT.index, packInput(type.id, x, y));
        }
    }

    public static void processInput(RobotController rc) throws GameActionException {
        int input;
        if ((input = rc.readSharedArray(jackPlayer.Communications.PageLocation.INPUT.index)) == NULL_INDICATOR) {
            return;
        }
        jackPlayer.Communications.EntityType type = intToEntityType(unpack(input, jackPlayer.Communications.PackedMask.INPUT_TYPE));
        int x = unpack(input, jackPlayer.Communications.PackedMask.INPUT_X);
        int y = unpack(input, jackPlayer.Communications.PackedMask.INPUT_Y);
//        System.out.println("Read as (" + x + ", " + y + ")");
        boolean added = false;
        if (type != null) {
            switch (type) {
                case ANCHOR:
                    added = addAnchor(x, y);
                    break;
                case WELL:
                    added = addWell(rc, x, y);
                    break;
                case HEADQUARTER:
                    added = addEnemyBase(x, y);
                    break;
            }
        }
        rc.writeSharedArray(jackPlayer.Communications.PageLocation.INPUT.index, NULL_INDICATOR);
        if (type == EntityType.WELL && !added) {
            System.out.println("Could not add well! (List full?)");
        }
    }

    public static List<jackPlayer.Communications.Headquarter> getHeadQuarters(RobotController rc) throws GameActionException {
        if (getPage(rc) != jackPlayer.Communications.PageLocation.HEADQUARTERS.page) {
            return null;
        }
        List<jackPlayer.Communications.Headquarter> headquarters = new ArrayList<>();
        for (int i = jackPlayer.Communications.PageLocation.HEADQUARTERS.index; i < jackPlayer.Communications.PageLocation.HEADQUARTERS.end; i += jackPlayer.Communications.PageLocation.HEADQUARTERS.size) {
            int packedInfo = rc.readSharedArray(i);
            if (packedInfo == 0 || packedInfo == NULL_INDICATOR) {
                break;
            }
            int x = unpack(packedInfo, jackPlayer.Communications.PackedMask.HEADQUARTER_X);
            int y = unpack(packedInfo, jackPlayer.Communications.PackedMask.HEADQUARTER_Y);
            headquarters.add(new Headquarter(new MapLocation(x, y)));
        }
        return headquarters;
    }

    public static List<jackPlayer.Communications.Well> getWells(RobotController rc) throws GameActionException {
        if (getPage(rc) != jackPlayer.Communications.PageLocation.WELLS.page) {
            return null;
        }
        List<jackPlayer.Communications.Well> wells = new ArrayList<>();
        for (int i = jackPlayer.Communications.PageLocation.WELLS.index; i < jackPlayer.Communications.PageLocation.WELLS.end; i += jackPlayer.Communications.PageLocation.WELLS.size) {
            int packedLoc = rc.readSharedArray(i);
            if (packedLoc == NULL_INDICATOR) {
                break;
            }
            int wellType = unpack(packedLoc, jackPlayer.Communications.PackedMask.WELL_TYPE);
            boolean hasAmplifier = unpack(packedLoc, jackPlayer.Communications.PackedMask.AMPLIFIER_PRESENT) != 0;
            int x = unpack(packedLoc, jackPlayer.Communications.PackedMask.WELL_X);
            int y = unpack(packedLoc, jackPlayer.Communications.PackedMask.WELL_Y);
            int packedStatus = rc.readSharedArray(i + 1);
            int workerCount = unpack(packedStatus, jackPlayer.Communications.PackedMask.WELL_WORKER_COUNT);
            int pressure = unpack(packedStatus, jackPlayer.Communications.PackedMask.WELL_PRESSURE);

            wells.add(new jackPlayer.Communications.Well(i, hasAmplifier, workerCount, pressure, intToResourceType(wellType), new MapLocation(x, y)));
        }
        return wells;
    }

    public static void incrementWellWorkers(RobotController rc, Well well) throws GameActionException {
        int countIndex = well.getWellIndex() + 1;
        int newCount = well.getWorkerCount() < 15 ? well.getWorkerCount() + 1 : 15;
        // TODO: Need to write a generalized pack function
        rc.writeSharedArray(countIndex, (newCount << 4) & (well.getPressure()));
    }

    public static int getCoordination(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(jackPlayer.Communications.PageLocation.PAGE_NUMBER.index), jackPlayer.Communications.PackedMask.COORDINATION);
    }

    public static int getFocusX(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(jackPlayer.Communications.PageLocation.PAGE_NUMBER.index), jackPlayer.Communications.PackedMask.FOCUS_X) * 2;
    }

    public static int getFocusY(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageLocation.PAGE_NUMBER.index), PackedMask.FOCUS_Y) * 2;
    }
}


