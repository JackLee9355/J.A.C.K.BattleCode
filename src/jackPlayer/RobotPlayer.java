package jackPlayer;

import battlecode.common.*;

import java.awt.*;

/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public strictfp class RobotPlayer {

    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        Controller p;
        switch (rc.getType()) {
            case HEADQUARTERS:
                p = new HeadQuartersController(rc);
                break;
            case CARRIER:
                p = new CarrierController(rc);
                break;
            case LAUNCHER:
                p = new LauncherController(rc);
                break;
            case BOOSTER:
                p = new BoosterController(rc);
                break;
            case DESTABILIZER:
                p = new DestabilizerController(rc);
                break;
            case AMPLIFIER:
                p = new AmplifierController(rc);
                break;
            default:
                return;
        }

        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                p.run(rc);
            } catch (GameActionException e) {
                System.out.println(rc.getType() + " Game Exception");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(rc.getType() + " Exception");
                e.printStackTrace();
            } finally {
                Clock.yield();
            }
        }
    }

    public static void attack(RobotController rc) throws GameActionException {
        if (rc.isActionReady()) {
            RobotInfo[] enemies = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, rc.getTeam().opponent());
            int indexAttack = -1;
            int health = 100;
            for (int i = 0; i < enemies.length; i++) {
                int enemyHealth = enemies[i].getHealth();
                if (enemyHealth < health) {
                    indexAttack = i;
                    health = enemyHealth;
                }
            }
            if (indexAttack >= 0) {
                MapLocation enemyLoc = enemies[indexAttack].getLocation();
                if (rc.canAttack(enemyLoc)) {
                    rc.attack(enemyLoc);
                }
            } else if (enemies.length > 0) { //there exist enemies in the action range, but they are all at full health
                MapLocation enemyLoc = enemies[0].getLocation();
                if (rc.canAttack(enemyLoc)) {
                    rc.attack(enemyLoc);
                }
            }
        }
    }
}