package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.*;
import jackPlayer.Pathing.*;

public class LauncherController extends Controller {

    private final int WEIGHT_BOOSTER = 10;
    private final int WEIGHT_DESTABILIZER = 10;
    private final int WEIGHT_LAUNCHER = 20;
    private final int WEIGHT_AMPLIFIER = 25;
    private final int WEIGHT_CARRIER = 25;
    private final int WEIGHT_HEADQUARTERS = 30;
    private final int AVERAGE_MAX_HEALTH = 20;
    private final RobotType type;
    private final Team myTeam;
    private final Team enemyTeam;
    private RobotInfo[] enemies;

    public LauncherController(RobotController rc) {
        super(rc);
        type = rc.getType();
        myTeam = rc.getTeam();
        enemyTeam = myTeam.opponent();
        pathing = new RobotPathing(rc);
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
        rc.setIndicatorString("Attacking");

        if (rc.isActionReady() && enemyLocation != null) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
                return true;
            }
        }
        return false;
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        boolean alreadyAttacked = false, alreadyMoved = false;
        enemies = rc.senseNearbyRobots(type.visionRadiusSquared, enemyTeam);

        MapLocation enemyLocation = bestEnemyToAttack(rc);
        alreadyAttacked = attack(rc, enemyLocation);

        // Focus is set
        if (Communications.getCoordination(rc) > 0) {
            MapLocation target = new MapLocation(Communications.getFocusX(rc), Communications.getFocusY(rc));

            // If not in focus radius
            if (!myLocation.isWithinDistanceSquared(target, type.visionRadiusSquared)) {
                pathing.move(target);
                alreadyMoved = true;

                // Find new best enemy to attack and try now
                if (!alreadyAttacked) {
                    enemyLocation = bestEnemyToAttack(rc);
                    alreadyAttacked = attack(rc, enemyLocation);
                }
            }
        }

        // Chase enemy if didn't move earlier
        if (!alreadyMoved && enemyLocation != null) {
            pathing.move(enemyLocation);
            alreadyMoved = true;

            // If enemy is now in attack radius, try an attack
            if (!alreadyAttacked) {
                alreadyAttacked = attack(rc, enemyLocation);
            }
        }

        // No enemies in vision, explore
        if (!alreadyMoved) {
            generalExplore(rc);

            // Find new best enemy to attack and try now
            if (!alreadyAttacked) {
                enemyLocation = bestEnemyToAttack(rc);
                attack(rc, enemyLocation);
            }
        }
    }
}
