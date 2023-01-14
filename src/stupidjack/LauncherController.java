package stupidjack;

import battlecode.common.*;
import jackPlayer.Controller;

public class LauncherController extends Controller {
    private RobotType type;
    private Team enemyTeam;
    private int visionRadiusSquared;

    public LauncherController(RobotController rc) {
        super(rc);
        type = rc.getType();
        enemyTeam = rc.getTeam().opponent();
        visionRadiusSquared = rc.getType().visionRadiusSquared;
    }

    public MapLocation[] enemiesInVision(RobotController rc, int visionRadiusSquared, Team enemyTeam) throws GameActionException {
        RobotInfo[] enemies = rc.senseNearbyRobots(visionRadiusSquared, enemyTeam);
        MapLocation[] enemyLocations = new MapLocation[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyLocations[i] = enemies[i].location;
        }
        return enemyLocations;
    }

    public MapLocation closestEnemy(RobotController rc) throws GameActionException {
        rc.setIndicatorString("Scanning surrounding");
        RobotInfo[] enemies = rc.senseNearbyRobots(visionRadiusSquared, enemyTeam);
        MapLocation closestEnemyLocation = null;
        int closestDistance = Integer.MAX_VALUE;

        for (int i = 0; i < enemies.length; i++) {
            MapLocation enemyLocation = enemies[i].getLocation();
            int distance = myLocation.distanceSquaredTo(enemyLocation);
            // Ignore headquarters for now
            if (enemies[i].getType() != RobotType.HEADQUARTERS && distance < closestDistance) {
                closestEnemyLocation = enemyLocation;
                closestDistance = distance;
            }
        }

        return closestEnemyLocation;
    }

    public boolean attack(RobotController rc, MapLocation enemyLocation) throws GameActionException {
        rc.setIndicatorString("Attempting to attack");
        if (rc.isActionReady()) {
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
        MapLocation enemyLocation = closestEnemy(rc);
        if (enemyLocation != null) {
            attack(rc, enemyLocation);
            moveTowardsBFS(rc, enemyLocation);
        } else {
            rc.setIndicatorString("Exploring");
            generalExplore(rc);
        }
    }
}
