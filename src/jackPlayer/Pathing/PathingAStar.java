package jackPlayer.Pathing;

import battlecode.common.*;

import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathingAStar {

    private static final int DIAMETER = 12;
    private static final int RADIUS = DIAMETER / 2;
    private static final int[][] DIRS = new int[][]{
            {0, 1},
            {1, 0},
            {1, 1},
            {0, -1},
            {-1, 0},
            {-1, -1},
            {1, -1},
            {-1, 1}
    };

    int[][] map;
    int[][] scores;
    int[][][] parents;

    public PathingAStar(RobotController rc) {
        map = new int[rc.getMapWidth()][rc.getMapHeight()];
        scores = new int[rc.getMapWidth()][rc.getMapHeight()];
        parents = new int[rc.getMapWidth()][rc.getMapHeight()][2];
    }

    public void updateMap(RobotController rc, MapLocation curLocation) throws GameActionException {
        MapLocation[] locations = rc.getAllLocationsWithinRadiusSquared(curLocation, rc.getType().visionRadiusSquared);
        for (MapLocation loc : locations) {
            if (rc.canSenseLocation(loc)) {
                MapInfo info = rc.senseMapInfo(loc);
                map[loc.x][loc.y] = info.isPassable() && !rc.isLocationOccupied(loc) ? (info.hasCloud() ? 4 : 2) : -1;
            }
        }
    }

    // TODO: update perimeter?

    public void resetArea(int lowX, int highX, int lowY, int highY) {
        for (int i = lowX; i < highX; i++) {
            for (int j = lowY; j < highY; j++) {
                scores[i][j] = 0;
                parents[i][j] = null;
            }
        }
    }

    public void pathTo(RobotController rc, MapLocation target) throws GameActionException {
        MapLocation curLocation = rc.getLocation();
        int lowX = Math.max(0, curLocation.x - RADIUS);
        int highX = Math.min(map.length, curLocation.y + RADIUS);
        int lowY = Math.max(0, curLocation.y - RADIUS);
        int highY = Math.min(map.length, curLocation.y + RADIUS);
        resetArea(lowX, highX, lowY, highY);
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0])); // {Score, X, Y}
        queue.add(new int[]{1, curLocation.x, curLocation.y});
        map[curLocation.x][curLocation.y] = 1;
        rc.setIndicatorString("Search Started");
        int[] last = new int[]{-1, -1, Integer.MAX_VALUE};
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            int x = cur[1]; // Map
            int y = cur[2]; // Map
            if (scores[x][y] < 0) {
                continue;
            }
            scores[x][y] = -1;
            if (map[x][y] == 0 || (x == target.x && y == target.y) ||
                    Math.abs(curLocation.x - x) >= RADIUS - 1 || Math.abs(curLocation.y - y) >= RADIUS - 1) {
                last = new int[]{x, y};
                break;
            }
            for (int[] d : DIRS) {
                int nX = x + d[0];
                int nY = y + d[1];
                if (isValid(nX, nY, lowX, lowY, highX, highY)) {
                    int score = cur[0] + map[nX][nY] + Math.max(Math.abs(target.x - nX), Math.abs(target.y - nY));
                    if (scores[nX][nY] == 0 || scores[nX][nY] > score) {
                        scores[nX][nY] = score;
                        parents[nX][nY] = new int[]{x, y};
                        queue.add(new int[]{score, nX, nY});
                        if (score < last[2]) {
                            last = new int[]{nX, nY, score};
                        }
                    }
                }
            }
        }
        rc.setIndicatorString("Search Complete");
        if (last[0] == -1 && last[1] == -1) {
            rc.setIndicatorString("Path not found. (" + target.x + ", " + target.y + ")");
            return;
        }
        int[] prev = last;
        int[] cur = last;
        while (cur != null && parents[cur[0]][cur[1]] != null) {
            prev = cur;
            cur = parents[cur[0]][cur[1]];
        }
        Direction d = curLocation.directionTo(new MapLocation(prev[0], prev[1]));
        rc.setIndicatorString("Moving toward: " + d.toString());
        if (rc.canMove(d)) {
            rc.move(d);
        }
    }

    private boolean isValid(int x, int y, int lowX, int lowY, int highX, int highY) {
        return x >= lowX && x < highX && y >= lowY && y < highY && map[x][y] != -1 && scores[x][y] >= 0;
    }

}
