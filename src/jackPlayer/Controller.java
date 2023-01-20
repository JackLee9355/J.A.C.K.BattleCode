package jackPlayer;

import java.util.*;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.EntityType;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.Well;
import jackPlayer.Pathing.Pathing;
import jackPlayer.Pathing.PathingAStar;

public abstract strictfp class Controller {
    protected int turnCount = 0;
    protected final int mapWidth;
    protected final int mapHeight;
    protected MapLocation myLocation;
    protected Pathing pathing;
    protected final Random rng = new Random(6147);
    //    protected final PathingAStar pathingAStar;
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

    protected static final boolean[][] managed = new boolean[60][60];
    protected static final LinkedList<WellInfo> wellCache = new LinkedList<>();

    private static final char[][] BM_MESSAGE = new char[][]{
            "#### #### #### ####".toCharArray(),
            "#    #    #       #".toCharArray(),
            "#    #    #      # ".toCharArray(),
            "# ## # ## ###    # ".toCharArray(),
            "#  # #  # #     #  ".toCharArray(),
            "#  # #  # #     #  ".toCharArray(),
            "#### #### #### ####".toCharArray()
    };

    private static final int BM_HEIGHT = BM_MESSAGE.length;
    private static final int BM_WIDTH = BM_MESSAGE[0].length;

    public Controller(RobotController rc) {
        mapWidth = rc.getMapWidth();
        mapHeight = rc.getMapHeight();
//        pathingAStar = new PathingAStar(rc);
    }

    private void writeBM(RobotController rc) {
        int bx = rng.nextInt(BM_WIDTH);
        int by = rng.nextInt(BM_HEIGHT);
        int x = bx;
        int y = BM_HEIGHT - by - 1;
        System.out.println("Sent BM at " + x + ", " + y);
        rc.setIndicatorDot(new MapLocation(x, y), 122, 0, 25);
    }

    public void run(RobotController rc) throws GameActionException {
        turnCount++;
        myLocation = rc.getLocation();
        cacheNewWells(rc);
//        if (turnCount % 5 == 0) {
//            pathingAStar.updateMap(rc, myLocation);
//        }
        if (rc.canWriteSharedArray(0, 0)) {
            writeWellCache(rc);
        }
//        writeBM(rc);
    }

    protected MapLocation rotate(MapLocation point) {
        int centerX = mapWidth / 2;
        int centerY = mapHeight / 2;
        int dx = centerX - point.x;
        int dy = centerY - point.y;
        return new MapLocation(centerX + dx, centerY + dy);
    }

    protected List<MapLocation> approxEnemyBase(RobotController rc) throws GameActionException {
        List<Headquarter> headquarters = Communications.getHeadQuarters(rc);
        if (headquarters == null) {
            return null;
        }
        List<MapLocation> rotated = new ArrayList<>();
        for (Headquarter h : headquarters) {
            rotated.add(rotate(h.getMapLocation()));
        }
        return rotated;
    }

    protected static boolean manageWell(RobotController rc, WellInfo wellInfo, List<Well> wells) throws GameActionException {
        if (wells == null)
            return false;

        int index = -1;
        if (wells != null) {
            for (int i = 0; i < wells.size(); i++) {
                Well w = wells.get(i);
                if (w.getMapLocation().equals(wellInfo.getMapLocation())) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            EntityType type;
            switch (wellInfo.getResourceType()) {
                case ADAMANTIUM:
                    type = EntityType.AD_WELL;
                    break;
                case MANA:
                    type = EntityType.MN_WELL;
                    break;
                case ELIXIR:
                    type = EntityType.EX_WELL;
                    break;
                default:
                    type = null;
            }
            if (type != null) {
                Communications.input(rc, type, wellInfo.getMapLocation().x, wellInfo.getMapLocation().y);
            }
        } else {
            // TODO: update info for well
        }
        return true;
    }

    protected static List<Well> getShortStaffedWells(RobotController rc) throws GameActionException {
        List<Well> wells = Communications.getWells(rc);

        if (wells == null)
            return null;

        List<Well> shortWells = new ArrayList<>();
        for (Well well : wells) {
            if (well.getWorkerCount() < 8 /* || well.getPressure() < 5 */) {
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
        if (wellCache.size() == 0) {
            return;
        }

        List<Well> wells = Communications.getWells(rc);
        if (wells == null)
            return;

        Iterator<WellInfo> iterator = wellCache.iterator();
        while (iterator.hasNext()) {
            WellInfo wellInfo = iterator.next();
            if (manageWell(rc, wellInfo, wells)) {
                managed[wellInfo.getMapLocation().x][wellInfo.getMapLocation().y] = true;
                iterator.remove();
            } else {
                break;
            }
        }
    }

    private void cacheNewWells(RobotController rc) throws GameActionException {
        for (WellInfo wellInfo : rc.senseNearbyWells()) {
            int x = wellInfo.getMapLocation().x;
            int y = wellInfo.getMapLocation().y;
            if (!managed[x][y]) {
                wellCache.add(wellInfo);
                managed[x][y] = true;
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
