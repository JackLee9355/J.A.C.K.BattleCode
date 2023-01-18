package jackPlayer;

import java.util.*;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.EntityType;
import jackPlayer.Communications.Well;
import jackPlayer.Pathing.Pathing;

public abstract strictfp class Controller {
    protected int turnCount = 0;
    protected final int mapWidth;
    protected final int mapHeight;
    protected MapLocation myLocation;
    protected Pathing pathing;
    protected final Random rng = new Random(6147);
    private final int[][] map; // [x][y]
    private final int[][] DIRS = new int[][]{
            {0, 1}, {1, 0}, {1, 1},
            {0, -1}, {-1, 0}, {-1, -1},
            {1, -1}, {-1, 1}
    };
    protected final Direction[] directions = {
            Direction.NORTH,
            Direction.NORTHEAST,
            Direction.EAST,
            Direction.SOUTHEAST,
            Direction.SOUTH,
            Direction.SOUTHWEST,
            Direction.WEST,
            Direction.NORTHWEST,
    };

    protected boolean[][] exists = new boolean[60][60];
    protected WellInfo[] wellCache = new WellInfo[3600];
    protected int end = 0;

    public Controller(RobotController rc) {
        mapWidth = rc.getMapWidth();
        mapHeight = rc.getMapHeight();
        map = new int[mapWidth][mapHeight];
    }

    public void run(RobotController rc) throws GameActionException {
        turnCount++;
        myLocation = rc.getLocation();
        cacheNewWells(rc);
        if (turnCount > 1 && rc.canWriteSharedArray(0, 0)) { // Worried about weird behavior on master hq
            writeWellCache(rc);
        }
    }

    protected MapLocation rotate(MapLocation point) {
        int centerX = mapWidth / 2;
        int centerY = mapHeight / 2;
        int dx = centerX - point.x;
        int dy = centerY - point.y;
        return new MapLocation(centerX + dx, centerY + dy);
    }

    protected static void manageWell(RobotController rc, WellInfo wellInfo) throws GameActionException {
        List<Well> wells;
        if ((wells = Communications.getWells(rc)) == null) {
            return;
        }
        int index = -1;
        for (int i = 0; i < wells.size(); i++) {
            Well w = wells.get(i);
            if (w.getMapLocation().equals(wellInfo.getMapLocation())) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            Communications.input(rc, EntityType.WELL, wellInfo.getMapLocation().x, wellInfo.getMapLocation().y);
        } else {
            // TODO: update info for well
        }
    }

    protected static List<Well> getShortStaffedWells(RobotController rc) throws GameActionException {
        List<Well> wells = Communications.getWells(rc);

        if (wells == null)
            return null;

        List<Well> shortWells = new ArrayList<>();
        for (Well well : wells) {
            if (well.getWorkerCount() < 10 /* || well.getPressure() < 5 */ ) {
                shortWells.add(well);
            }
        }
        return shortWells;
    }

    protected List<MapLocation> adjacentSquares(RobotController rc) throws GameActionException {
        List<MapLocation> locations = new ArrayList<>();
        for (Direction dir : directions) {
            locations.add(rc.getLocation().add(dir));
        }
        return locations;
    }

    private void writeWellCache(RobotController rc) throws GameActionException {
        if (end == 0)
            return;

        boolean[][] managed = new boolean[60][60];
        List<Well> wells = Communications.getWells(rc);
        if (wells == null)
            return;

        for (Well well : wells) {
            managed[well.getMapLocation().x][well.getMapLocation().y] = true;
        }

        for (int i = 0; i < end; i++) {
            WellInfo well = wellCache[i];
            if (!managed[well.getMapLocation().x][well.getMapLocation().y]) {
                manageWell(rc, well);
                // System.out.println("Managing new well at location: " + wellLoc.x + ", " + wellLoc.y);
                break; // We can't add more than one anyway
            }
        }
        end = 0;
        wellCache = new WellInfo[3600];
    }

    private void cacheNewWells(RobotController rc) throws GameActionException {
        for (WellInfo wellInfo : rc.senseNearbyWells()) {
            int x = wellInfo.getMapLocation().x;
            int y = wellInfo.getMapLocation().y;
            if (!exists[x][y]) {
                wellCache[end++] = wellInfo;
                exists[x][y] = true;
            }
        }
    }

    private double dotProduct(double[] a, double[] b) {
        return a[0] * b[0] + a[1] * b[1];
    }

    public void generalExplore(RobotController rc) throws GameActionException {
        if (rc.isMovementReady()) {
            RobotInfo[] robots = rc.senseNearbyRobots(-1, rc.getTeam()); // TODO: Clouds
            Direction dir = directions[rng.nextInt(8)];
            double[] vector = new double[]{dir.dx, dir.dx};
            for (RobotInfo r : robots) {
                MapLocation loc = r.location;
                int dx = loc.x - myLocation.x;
                int dy = loc.y - myLocation.y;
                double inverse = 1.2 / (dx * dx + dy * dy);
                vector[0] -= dx * inverse;
                vector[1] -= dy * inverse;
            }
            double max = Double.MIN_VALUE;
            for (Direction d : directions) {
                MapLocation target = myLocation.add(d);
                if (rc.onTheMap(target) && rc.sensePassability(target) && !rc.isLocationOccupied(target)) {
                    double dot = dotProduct(new double[]{d.dx, d.dy}, vector);
                    if (dot > max) {
                        dir = d;
                        max = dot;
                    }
                }
            }
            if (rc.canMove(dir)) {
                rc.move(dir);
            }
        }
    }
}
