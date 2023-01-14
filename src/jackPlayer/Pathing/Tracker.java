package jackPlayer.Pathing;

import battlecode.common.*;

public class Tracker {
    private final static int ARRAY_SIZE = 60;
    public long[] visitedLocations;

    public Tracker() {
        visitedLocations = new long[ARRAY_SIZE];
    }

    public void reset() {
        visitedLocations = new long[ARRAY_SIZE];
    }

    public void add(MapLocation location) {
        visitedLocations[location.x] |= (1 << location.y);
    }

    public boolean check(MapLocation location) {
        return (visitedLocations[location.x] & (1 << location.y)) > 0;
    }
}
