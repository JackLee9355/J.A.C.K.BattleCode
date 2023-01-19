package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.*;
import jackPlayer.Pathing.*;

import java.util.ArrayList;
import java.util.List;

public class LauncherController extends Controller {

    private final int WEIGHT_BOOSTER = 10;
    private final int WEIGHT_DESTABILIZER = 10;
    private final int WEIGHT_LAUNCHER = 20;
    private final int WEIGHT_AMPLIFIER = 25;
    private final int WEIGHT_CARRIER = 25;
    private final int WEIGHT_HEADQUARTERS = 30;
    private final int AVERAGE_MAX_HEALTH = 200;
    private final RobotType type;
    private final Team myTeam;
    private final Team enemyTeam;
    private RobotInfo[] enemies;
    boolean visitedApprox;
    MapLocation guessLoc;

    public LauncherController(RobotController rc) {
        super(rc);
        type = rc.getType();
        myTeam = rc.getTeam();
        enemyTeam = myTeam.opponent();
        pathing = new RobotPathing(rc);
        visitedApprox = false;
        guessLoc = null;
    }

    private MapLocation bestEnemyToAttack(RobotController rc) throws GameActionException {
        MapLocation bestEnemy = null;
        double minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < enemies.length; i++) {

            if (enemies[i].getType() == RobotType.HEADQUARTERS) {
                continue;
            }

            MapLocation enemyLocation = enemies[i].getLocation();
            double enemyMaxHealth = enemies[i].getType().getMaxHealth();
            double health = enemies[i].getHealth();
            double distance = myLocation.distanceSquaredTo(enemyLocation);
            double weight = 0;

            // Priorities
            switch (enemies[i].getType()) {
                case BOOSTER:
                    weight += WEIGHT_BOOSTER;
                    break;
                case DESTABILIZER:
                    weight += WEIGHT_DESTABILIZER;
                    break;
                case LAUNCHER:
                    weight += WEIGHT_LAUNCHER;
                    break;
                case AMPLIFIER:
                    weight += WEIGHT_AMPLIFIER;
                    break;
                case CARRIER:
                    weight += WEIGHT_CARRIER;
                    break;
                case HEADQUARTERS:
                    weight += WEIGHT_HEADQUARTERS;
            }

            // Consider Health (let this be a major factor)
            weight += (AVERAGE_MAX_HEALTH * health) / enemyMaxHealth;

            // Prioritize enemies in attack radius
            if (distance <= type.actionRadiusSquared) {
                // Find the enemy with the lowest weight (best to attack)
                if (weight < minWeight) {
                    bestEnemy = enemyLocation;
                    minWeight = weight;
                }
            } else {
                // If enemy is out of radius, include distance in the weight
                if (distance + weight < minWeight) {
                    bestEnemy = enemyLocation;
                    minWeight = weight;
                }
            }
        }

        return bestEnemy;
    }

    private boolean attack(RobotController rc, MapLocation enemyLocation) throws GameActionException {
        if (rc.isActionReady() && enemyLocation != null) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
                return true;
            }
        }
        return false;
    }

    private boolean microMove(RobotController rc) throws GameActionException {
        Direction bestMove = null;
        int bestDistance = Integer.MIN_VALUE;
        int bestCount = Integer.MAX_VALUE;
        for (Direction d : directions) {
            if (!rc.canMove(d)) {
                continue;
            }
            MapLocation loc = myLocation.add(d);
            int count = 0;
            int minDistance = Integer.MAX_VALUE;
            for (RobotInfo e : enemies) {
                int dist = e.getLocation().distanceSquaredTo(loc);
                minDistance = Math.min(minDistance, dist);
                if (dist <= type.actionRadiusSquared) {
                    count++;
                }
            }
            if (count > 0 && (count < bestCount || minDistance > bestDistance)) {
                bestMove = d;
                bestDistance = minDistance;
                bestCount = count;
            }
        }
        if (bestMove != null) {
            rc.setIndicatorString("Micro!");
            rc.move(bestMove);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        boolean alreadyAttacked = false, alreadyMoved = false;
        enemies = rc.senseNearbyRobots(type.visionRadiusSquared, enemyTeam);

        MapLocation enemyLocation = bestEnemyToAttack(rc);
        alreadyAttacked = attack(rc, enemyLocation);

        // Micro move if enemies around
        if (enemies.length > 1) {
            alreadyMoved = microMove(rc);
        }

        // Focus is set
        if (!alreadyMoved && Communications.getCoordination(rc) > 0) {
            MapLocation target = new MapLocation(Communications.getFocusX(rc), Communications.getFocusY(rc));

            // If not in focus radius
            if (!myLocation.isWithinDistanceSquared(target, type.visionRadiusSquared)) {
                rc.setIndicatorString("Heading to focus: (" + target.x + ", " + target.y + ")");
                pathing.move(target);
                alreadyMoved = true;
            }
        }

        if (!alreadyMoved && enemies.length > 0) {
            alreadyMoved = microMove(rc);
        }

        // Chase enemy if didn't move earlier
        if (!alreadyMoved && enemyLocation != null) {
            rc.setIndicatorString("Chasing!");
            pathing.move(enemyLocation);
            alreadyMoved = true;
        }

        // Move to a guess of where the enemy is
        List<MapLocation> enemyGuesses = approxEnemyBase(rc);
        if (!visitedApprox && guessLoc == null && enemyGuesses != null) {
            MapLocation min = null;
            int dist = Integer.MAX_VALUE;
            for (MapLocation e : enemyGuesses) {
                int d = myLocation.distanceSquaredTo(e);
                if (d < dist) {
                    dist = d;
                    min = e;
                }
            }
            if (dist < type.visionRadiusSquared) {
                visitedApprox = true;
            }
            guessLoc = min;
        }
        if (!visitedApprox && !alreadyMoved) {
            if (guessLoc != null) {
                rc.setIndicatorString("Heading to guess: (" + guessLoc.x + ", " + guessLoc.y + ")");
                pathing.move(guessLoc);
                alreadyMoved = true;
                if (myLocation.distanceSquaredTo(guessLoc) < type.actionRadiusSquared) {
                    visitedApprox = true;
                }
            }
        }

        // No enemies in vision, explore
        if (!alreadyMoved) {
            rc.setIndicatorString("Exploring");
            generalExplore(rc);
            alreadyMoved = true;
        }

        // Find new best enemy to attack and try now
        if (!alreadyAttacked) {
            enemyLocation = bestEnemyToAttack(rc);
            alreadyAttacked = attack(rc, enemyLocation);
        }
    }
}
