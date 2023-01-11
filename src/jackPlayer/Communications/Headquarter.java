package jackPlayer.Communications;

import battlecode.common.MapLocation;

public class Headquarter {

    public Headquarter(MapLocation mapLocation) {
        this.mapLocation = mapLocation;
    }

    private MapLocation mapLocation;

    public MapLocation getMapLocation() {
        return mapLocation;
    }
}
