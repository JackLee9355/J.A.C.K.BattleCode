package jackPlayer.Pathing;

import battlecode.common.*;

public class Tracker {
    private final int ARRAY_SIZE = 60;
    public boolean[][] visited;

    public Tracker() {
        visited = new boolean[ARRAY_SIZE][ARRAY_SIZE];
    }

    public void reset() {
        visited = new boolean[ARRAY_SIZE][ARRAY_SIZE];
    }

    public void add(MapLocation location) {
        if (location.x >= 0 && location.x < 60 && location.y >= 0 && location.y < 60) {
            visited[location.x][location.y] = true;
        }
    }

    public boolean check(MapLocation location) {
        if (location.x >= 0 && location.x < 60 && location.y >= 0 && location.y < 60) {
            return visited[location.x][location.y];
        }
        return false;
    }
}
