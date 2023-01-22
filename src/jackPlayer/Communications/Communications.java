package jackPlayer.Communications;

import battlecode.common.*;

import java.util.ArrayList;
import java.util.List;

public class Communications {

    private static final int NULL_INDICATOR = (1 << 16) - 1;
    private static final int PAGE_SIZE = 64;
    private static final int PAGE_COUNT = 2;
    private static final int[][] pages = new int[PAGE_COUNT][PAGE_SIZE];

    static int control_number; // Only master headquarters manages

    private static int unpack(int packedInt, PackedMask mask) {
        return (packedInt & mask.mask) >>> mask.shift;
    }

    private static int packControl(int index, int coordination, int focusX, int focusY) {
        return (index << PackedMask.PAGE_INDEX.shift) | (coordination << PackedMask.COORDINATION.shift) |
                ((focusX >> 1) << PackedMask.FOCUS_X.shift) | ((focusY >> 1) << PackedMask.FOCUS_Y.shift);
    }

    private static int packInput(int type, int x, int y) {
        return (type << PackedMask.INPUT_TYPE.shift) | (x << PackedMask.INPUT_X.shift) | (y << PackedMask.INPUT_Y.shift);
    }

    private static int packWellPosition(int t, int x, int y) {
        return (t << PackedMask.WELL_TYPE.shift) | (x << PackedMask.WELL_X.shift) | (y << PackedMask.WELL_Y.shift);
    }

    private static int packWellStatus(int workerCount, int pressure) {
        return (workerCount << PackedMask.WELL_WORKER_COUNT.shift) | (pressure << PackedMask.WELL_PRESSURE.shift);
    }

    private static int packHeadQuarters(int x, int y) {
        return (1 << 15) | (x << PackedMask.HEADQUARTER_X.shift) | (y << PackedMask.HEADQUARTER_Y.shift);
    }

    private static int packAnchor() {
        return -1; // TODO
    }

    public static int getPage(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageLocation.CONTROL.index), PackedMask.PAGE_INDEX);
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

    public static EntityType intToEntityType(int value) {
        switch (value) {
            case 0:
                return EntityType.ANCHOR;
            case 1:
                return EntityType.AD_WELL;
            case 2:
                return EntityType.MN_WELL;
            case 3:
                return EntityType.EX_WELL;
            case 4:
                return EntityType.HEADQUARTER;
            default:
                return null;
        }
    }

    private static boolean addWell(RobotController rc, int t, int x, int y) throws GameActionException {
        for (int i = PageLocation.WELLS.index; i < PageLocation.WELLS.end; i += PageLocation.WELLS.size) {
            if (pages[PageLocation.WELLS.page][i] == NULL_INDICATOR) {
                pages[PageLocation.WELLS.page][i] = packWellPosition(t, x, y);
                pages[PageLocation.WELLS.page][i + 1] = 0;
                if (getPage(rc) == PageLocation.WELLS.page) {
                    rc.writeSharedArray(i, pages[PageLocation.WELLS.page][i]);
                    rc.writeSharedArray(i + 1, pages[PageLocation.WELLS.page][i + 1]);
                }
                return true;
            } else {
                int well_X = unpack(pages[PageLocation.WELLS.page][i], PackedMask.WELL_X);
                int well_Y = unpack(pages[PageLocation.WELLS.page][i], PackedMask.WELL_Y);
                if (x == well_X && y == well_Y) {
                    return true;
                }
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

    public static void updateControl(RobotController rc, int coordination, int focusX, int focusY) throws GameActionException {
        int pageIndex = getPage(rc);
        for (int i = 0; i < PAGE_COUNT; i++) {
            pages[i][PageLocation.CONTROL.index] = packControl(i, coordination, focusX, focusY);
        }
        rc.writeSharedArray(PageLocation.CONTROL.index, packControl(pageIndex, coordination, focusX, focusY));
    }

    public static void addFriendlyHeadquarters(RobotController rc, int x, int y, int index) throws GameActionException {
        int packed = packHeadQuarters(x, y);
        rc.writeSharedArray(PageLocation.HEADQUARTERS.index + index, packed);
    }

    public static void input(RobotController rc, EntityType type, int x, int y) throws GameActionException {
//        System.out.println("Inputting (" + x + ", " + y + ")");
        if (rc.readSharedArray(PageLocation.INPUT.index) == NULL_INDICATOR) {
            rc.writeSharedArray(PageLocation.INPUT.index, packInput(type.id, x, y));
        }
    }

    public static void processInput(RobotController rc) throws GameActionException {
        int input;
        if ((input = rc.readSharedArray(PageLocation.INPUT.index)) == NULL_INDICATOR) {
            return;
        }
        EntityType type = intToEntityType(unpack(input, PackedMask.INPUT_TYPE));
        int x = unpack(input, PackedMask.INPUT_X);
        int y = unpack(input, PackedMask.INPUT_Y);
//        System.out.println("Read as (" + x + ", " + y + ")");
        boolean added = false;
        if (type != null) {
            switch (type) {
                case ANCHOR:
                    added = addAnchor(x, y);
                    break;
                case AD_WELL:
                case MN_WELL:
                case EX_WELL:
                    added = addWell(rc, type.id, x, y);
                    break;
                case HEADQUARTER:
                    added = addEnemyBase(x, y);
                    break;
            }
        }
        rc.writeSharedArray(PageLocation.INPUT.index, NULL_INDICATOR);
    }

    public static List<Headquarter> getHeadQuarters(RobotController rc) throws GameActionException {
        if (getPage(rc) != PageLocation.HEADQUARTERS.page) {
            return null;
        }
        List<Headquarter> headquarters = new ArrayList<>();
        for (int i = PageLocation.HEADQUARTERS.index; i < PageLocation.HEADQUARTERS.end; i += PageLocation.HEADQUARTERS.size) {
            int packedInfo = rc.readSharedArray(i);
            if (packedInfo == 0 || packedInfo == NULL_INDICATOR) {
                break;
            }
            int x = unpack(packedInfo, PackedMask.HEADQUARTER_X);
            int y = unpack(packedInfo, PackedMask.HEADQUARTER_Y);
            headquarters.add(new Headquarter(new MapLocation(x, y)));
        }
        return headquarters;
    }

    public static List<Well> getWells(RobotController rc) throws GameActionException {
        if (getPage(rc) != PageLocation.WELLS.page) {
            return null;
        }
        List<Well> wells = new ArrayList<>();
        for (int i = PageLocation.WELLS.index; i < PageLocation.WELLS.end; i += PageLocation.WELLS.size) {
            int packedLoc = rc.readSharedArray(i);
            if (packedLoc == NULL_INDICATOR) {
                break;
            }
            int wellType = unpack(packedLoc, PackedMask.WELL_TYPE);
            boolean hasAmplifier = unpack(packedLoc, PackedMask.AMPLIFIER_PRESENT) != 0;
            int x = unpack(packedLoc, PackedMask.WELL_X);
            int y = unpack(packedLoc, PackedMask.WELL_Y);
            int packedStatus = rc.readSharedArray(i + 1);
            int workerCount = unpack(packedStatus, PackedMask.WELL_WORKER_COUNT);
//            if (rc.getType() == RobotType.HEADQUARTERS)
//                System.out.println("This wells count is " + workerCount + " at " + x + " " + y);
            int pressure = unpack(packedStatus, PackedMask.WELL_PRESSURE);

            wells.add(new Well(i, hasAmplifier, workerCount, pressure, intToResourceType(wellType), new MapLocation(x, y)));
        }
        return wells;
    }

    public static boolean incrementWellWorkers(RobotController rc, Well well) throws GameActionException {
        int countIndex = well.getWellIndex() + 1;
        if (getPage(rc) != PageLocation.WELLS.page) {
            // System.out.println("Trying to increment well on wrong page");
            return false;
        }

        if (!rc.canWriteSharedArray(countIndex, 0)) {
            // System.out.println("Can't write to shared array while incrementing well workers.");
            return false;
        }

        int newCount = well.getWorkerCount() < 15 ? well.getWorkerCount() + 1 : 15;
        rc.writeSharedArray(countIndex, packWellStatus(newCount, well.getPressure()));
        return true;
    }

    public static void resetAllWellWorkers(RobotController rc) throws GameActionException {
        for (int i = PageLocation.WELLS.index; i < PageLocation.WELLS.end; i += PageLocation.WELLS.size) {
            int packedStatus = rc.readSharedArray(i + 1);
            int pressure = unpack(packedStatus, PackedMask.WELL_PRESSURE);
            rc.writeSharedArray(i + 1, packWellStatus(0, pressure));
        }
    }

    public static int getCoordination(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageLocation.CONTROL.index), PackedMask.COORDINATION);
    }

    public static int getFocusX(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageLocation.CONTROL.index), PackedMask.FOCUS_X) * 2;
    }

    public static int getFocusY(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageLocation.CONTROL.index), PackedMask.FOCUS_Y) * 2;
    }
}


