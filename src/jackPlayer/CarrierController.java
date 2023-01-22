package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Communications.Headquarter;
import jackPlayer.Communications.PageLocation;
import jackPlayer.Communications.Well;
import jackPlayer.Pathing.RobotPathing;

import java.util.*;

public class CarrierController extends Controller {

    private MapLocation headquarter;
    private MapLocation wellLocation;
    private Team myTeam;
    private ResourceType wellType;
    private Queue<MapLocation> moves;
    private MapLocation target;
    private int[][] adjFromTarget = {
        {0, 4}, {4, 0}, {-4, 0}, {0, -4},
        {4, 4}, {4, -4}, {-4, 4}, {-4, -4}
    };

    public CarrierController(RobotController rc) throws GameActionException {
        super(rc);
        assignWell(rc);
        assignHQ(rc);
        pathing = new RobotPathing(rc);
        moves = new ArrayDeque<>();
        myTeam = rc.getTeam();
        target = null;
    }

    private void assignWell(RobotController rc) throws GameActionException {
        List<Well> wells = getShortStaffedWells(rc);
        if (wells == null)
            return;

        MapLocation curLoc = rc.getLocation();
        // If this is too expensive switch to repeatedly taking the minimum
        wells.sort(Comparator.comparingInt(o -> curLoc.distanceSquaredTo(o.getMapLocation())));
        for (Well well : wells) {
            if (Communications.getPage(rc) != PageLocation.WELLS.page)
                break;

            wellLocation = well.getMapLocation();
            wellType = well.getType();
            Communications.incrementWellWorkers(rc, well);
            break;
        }
    }

    private void assignHQ(RobotController rc) throws GameActionException {
        List<Headquarter> headQuarters = Communications.getHeadQuarters(rc);
        if (headQuarters == null || headQuarters.size() == 0) {
            if (headquarter != null)
                return;

            for (RobotInfo robot : rc.senseNearbyRobots()) {
                if (robot.getType() == RobotType.HEADQUARTERS) {
                    MapLocation curLoc = rc.getLocation();
                    if (headquarter == null ||
                            curLoc.distanceSquaredTo(robot.getLocation()) < curLoc.distanceSquaredTo(headquarter)) {
                        headquarter = robot.getLocation();
                    }
                }
            }
        } else {
            MapLocation curLoc = rc.getLocation();
            headquarter = headQuarters.stream().min(
                    Comparator.comparingInt(o -> curLoc.distanceSquaredTo(o.getMapLocation()))
            ).get().getMapLocation();
        }
    }

    private void attemptCollect(RobotController rc) throws GameActionException {
        if (rc.canCollectResource(wellLocation, -1)) {
            rc.collectResource(wellLocation, -1);

            if (totalHeld(rc) == 40) {
                // Could be closer to a different hq now
                assignHQ(rc);
            }
        }
    }

    private void attemptDeposit(RobotController rc) throws GameActionException {
        if (headquarter.isAdjacentTo(rc.getLocation()) && rc.isActionReady()) {
            int exAmount = rc.getResourceAmount(ResourceType.ELIXIR);
            if (exAmount > 0) {
                rc.transferResource(headquarter, ResourceType.ELIXIR, exAmount);
                return;
            }
            int adAmount = rc.getResourceAmount(ResourceType.ADAMANTIUM);
            if (adAmount > 0 && rc.isActionReady()) {
                rc.transferResource(headquarter, ResourceType.ADAMANTIUM, adAmount);
                return;
            }
            int mnAmount = rc.getResourceAmount(ResourceType.MANA);
            if (mnAmount > 0) {
                rc.transferResource(headquarter, ResourceType.MANA, mnAmount);
                return;
            }

            if (rc.canTakeAnchor(headquarter, Anchor.STANDARD)) {
                rc.takeAnchor(headquarter, Anchor.STANDARD);
            }
        }
    }

    private void attemptToPutAnchor(RobotController rc) throws GameActionException {
        if (rc.getAnchor() == null) {
            return;
        }

        MapLocation closestIslandLocation = null;
        int closest = Integer.MAX_VALUE;
        int[] islands = rc.senseNearbyIslands();

        for (int idx : islands) {
            // Don't try to place on currently occupied island by my team
            if (rc.senseAnchor(idx) != null && rc.senseTeamOccupyingIsland(idx).equals(myTeam)) {
                continue;
            }

            MapLocation[] islandLocations = rc.senseNearbyIslandLocations(idx);
            for (MapLocation loc : islandLocations) {
                int distance = myLocation.distanceSquaredTo(loc);
                if (distance < closest) {
                    closestIslandLocation = loc;
                    closest = distance;
                }
            }
        }

        if (closestIslandLocation != null) {
            pathing.move(closestIslandLocation);
            if (rc.canPlaceAnchor()) {
                rc.placeAnchor();
            }
        }

    }

    private int totalHeld(RobotController rc) {
        return rc.getResourceAmount(ResourceType.ADAMANTIUM) +
                rc.getResourceAmount(ResourceType.ELIXIR) +
                rc.getResourceAmount(ResourceType.MANA);
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        
        moves.add(myLocation);
        if (moves.size() > 5) {
            moves.remove();
        }

        attack(rc);

        if (rc.getAnchor() != null) {
            attemptToPutAnchor(rc); // TODO: improve
            generalExplore(rc);
            return;
        }

        if (totalHeld(rc) < 40) {
            if (wellLocation == null) {
                assignWell(rc);
            }
            if (wellLocation == null) {
                generalExplore(rc);
            } else {
                rc.setIndicatorString("Gathering from Well: " + wellLocation.x + ", " + wellLocation.y);
                if (target != null && moves.size() == 5 && myLocation.distanceSquaredTo(moves.peek()) <= 2) {
                    target = null; 
                    moves.clear();
                }

                if (target != null) {
                    pathing.move(target);
                    if (target.distanceSquaredTo(myLocation) <= 2) {
                        target = null; 
                        moves.clear();
                    }
                } else if (moves.size() == 5 && myLocation.distanceSquaredTo(moves.peek()) <= 2 && wellLocation.distanceSquaredTo(myLocation) > 2) {
                    List<MapLocation> randomLocations = new ArrayList<>();
                    int x = wellLocation.x, y = wellLocation.y;

                    for (int[] dir : adjFromTarget) {
                        int xf = x + dir[0], yf = y + dir[1];
                        if (xf >= 0 && xf < mapWidth && yf >= 0 && yf < mapHeight) {
                            randomLocations.add(new MapLocation(xf, yf));
                        }
                    }
                    
                    int index = rng.nextInt(randomLocations.size());
                    target = randomLocations.get(index);
                    pathing.move(target);
                } else if (wellLocation.distanceSquaredTo(myLocation) > 2) {
                    // pathingAStar.pathTo(rc, wellLocation);
                    pathing.move(wellLocation);
                }
                attemptCollect(rc);
            }
            assignHQ(rc);
        } else {
            if (headquarter == null) {
                assignHQ(rc);
            }
            if (headquarter == null) {
                generalExplore(rc);
            } else {
                rc.setIndicatorString("Returning to Headquarters: " + headquarter.x + ", " + headquarter.y);
                if (headquarter.distanceSquaredTo(myLocation) > 2) {
                    // pathingAStar.pathTo(rc, headquarter);
                    pathing.move(headquarter);
                }
                attemptDeposit(rc);
                if (rc.canTakeAnchor(headquarter, Anchor.STANDARD)) {
                    rc.takeAnchor(headquarter, Anchor.STANDARD);
                }
                if (rc.canTakeAnchor(headquarter, Anchor.ACCELERATING)) {
                    rc.takeAnchor(headquarter, Anchor.ACCELERATING);
                }
            }
            assignWell(rc);
        }
    }


    public static void attack(RobotController rc) throws GameActionException {
        if (rc.isActionReady()) {
            RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, rc.getTeam().opponent());
            int indexAttack = -1;
            int health = Integer.MAX_VALUE;
            for (int i = 0; i < enemies.length; i++) {
                int enemyHealth = enemies[i].getHealth();
                RobotInfo enemy = enemies[i];
                if (!enemy.getType().equals(RobotType.HEADQUARTERS)) {
                    if (enemyHealth == rc.getType().damage) {
                        indexAttack = i;
                        break;
                    } else if (enemyHealth < health) {
                        indexAttack = i;
                        health = enemyHealth;
                    }
                }
            }
            if (indexAttack >= 0) {
                MapLocation enemyLoc = enemies[indexAttack].getLocation();
                if (rc.canAttack(enemyLoc)) {
                    rc.attack(enemyLoc);
                }
            }
        }
    }

}
