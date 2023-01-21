package jackPlayer.Pathing;

import battlecode.common.*;

public class Tracker {
    private final static int ARRAY_SIZE = 60;
    private boolean[][] visitedLocations;

    public Tracker() {
        visitedLocations = new boolean[ARRAY_SIZE][ARRAY_SIZE];
    }

    public void reset() {
        visitedLocations = new boolean[ARRAY_SIZE][ARRAY_SIZE];
    }

    public void add(MapLocation location) {
        visitedLocations[location.x][location.y] = true;
    }

    public boolean check(MapLocation location) {
        return visitedLocations[location.x][location.y];
    }
}
