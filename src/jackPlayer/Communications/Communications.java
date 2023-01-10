package jackPlayer.Communications;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class Communications {

    private static int unpack(int packedInt, PackedMask mask) {
        return (packedInt & mask.mask) >>> mask.shift;
    }

    private static int getPage(RobotController rc) throws GameActionException {
        return unpack(rc.readSharedArray(PageIndex.PAGE_NUMBER.index), PackedMask.PAGE_INDEX);
    }
}


