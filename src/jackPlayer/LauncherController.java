package jackPlayer;

import battlecode.common.*;
import jackPlayer.Communications.Communications;
import jackPlayer.Pathing.RobotPathing;

public class LauncherController extends Controller {

    private enum State {
        ATTACK,
        DEFEND,
        RETREAT,
        EXPLORE,
        RUSH
    }

    private final int WEIGHT_BOOSTER = 30;
    private final int WEIGHT_DESTABILIZER = 30;
    private final int WEIGHT_LAUNCHER = 20;
    private final int WEIGHT_AMPLIFIER = 10;
    private final int WEIGHT_CARRIER = 10;
    private final int WEIGHT_HEADQUARTERS = 0;
    private final RobotType type;
    private final Team myTeam;
    private final Team enemyTeam;
    private RobotInfo[] enemies;
    private RobotInfo[] friendlies;
    private State launcherState;
    private int enemiesLength, friendliesLength;

    public LauncherController(RobotController rc) {
        super(rc);
        type = rc.getType();
        myTeam = rc.getTeam();
        enemyTeam = myTeam.opponent();
        pathing = new RobotPathing(rc);
        launcherState = State.EXPLORE;
    }

    private MapLocation bestEnemyToAttack(RobotController rc) throws GameActionException {
        MapLocation bestEnemy = null;
        int minWeight = Integer.MAX_VALUE;

        for (int i = 0; i < enemiesLength; i++) {
            MapLocation enemyLocation = enemies[i].getLocation();
            int weight = myLocation.distanceSquaredTo(enemyLocation);

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

            if (weight < minWeight) {
                bestEnemy = enemyLocation;
                minWeight = weight;
            }
        }

        return bestEnemy;
    }

    private void rush(RobotController rc) throws GameActionException {
        rc.setIndicatorString("Rushing");

        MapLocation target = new MapLocation(Communications.getFocusX(rc), Communications.getFocusY(rc));
        if (!myLocation.isWithinDistanceSquared(target, type.visionRadiusSquared)) {
            pathing.move(target);
        }

        MapLocation enemyLocation = bestEnemyToAttack(rc);

        if (rc.isActionReady() && enemyLocation != null) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
            }
            pathing.move(enemyLocation);
        }
    }

    private void retreat(RobotController rc) throws GameActionException {
        rc.setIndicatorString("Retreating");

        MapLocation enemyLocation = bestEnemyToAttack(rc);

        if (rc.isActionReady()) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
            }
        }

        Direction awayFromEnemy = myLocation.directionTo(enemyLocation).opposite();
        if (rc.isMovementReady()) {
            if (rc.canMove(awayFromEnemy)) {
                rc.move(awayFromEnemy);
            } else {
                generalExplore(rc);
            }
        }
    }

    private void attack(RobotController rc) throws GameActionException {
        rc.setIndicatorString("Attacking");

        // Spotted enemy, attack then move towards it
        MapLocation enemyLocation = bestEnemyToAttack(rc);
        if (rc.isActionReady()) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
            }
            pathing.move(enemyLocation);
        } else {
            rc.setIndicatorString("Exploring");
            explore(rc);
        }
    }

    private void explore(RobotController rc) throws GameActionException {
        rc.setIndicatorString("Exploring");

        generalExplore(rc);
        // Enemy could be possibly spotted in attack range, try to attack
        MapLocation enemyLocation = bestEnemyToAttack(rc);
        if (rc.isActionReady() && enemyLocation != null) {
            if (rc.canAttack(enemyLocation)) {
                rc.attack(enemyLocation);
            }
        }
    }

    private void switchState(RobotController rc) throws GameActionException {
        if (Communications.getCoordination(rc) > 0) {
            launcherState = State.RUSH;
        }  else if (friendliesLength < enemiesLength) {
            launcherState = State.RETREAT;
        } else if (enemiesLength > 0) {
            launcherState = State.ATTACK;
        } else {
            launcherState = State.EXPLORE;
        }
    }

    private void scan(RobotController rc) {
        rc.setIndicatorString("Scanning surroundings");
        enemiesLength = 0; friendliesLength = 0;
        RobotInfo[] robots = rc.senseNearbyRobots();
        enemies = new RobotInfo[robots.length];
        friendlies = new RobotInfo[robots.length];

        for (RobotInfo robot : robots) {
            if (robot.getTeam().equals(enemyTeam)) {
                // Ignore headquarters for now
                if (!robot.getType().equals(RobotType.HEADQUARTERS)) {
                    enemies[enemiesLength++] = robot;
                }
            } else {
                friendlies[friendliesLength++] = robot;
            }
        }
    }

    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);
        scan(rc);
        switchState(rc);

        switch (launcherState) {
            case RUSH:
                rush(rc);
                break;
            case RETREAT:
                retreat(rc);
                break;
            case ATTACK:
                attack(rc);
                break;
            case EXPLORE:
                explore(rc);
        }

    }
}
