package jackPlayer;

import battlecode.common.*;

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

    public MapLocation closestEnemy(RobotController rc) throws GameActionException {
        MapLocation[] enemyLocations = enemiesInVision(rc, visionRadiusSquared, enemyTeam);
        if (enemyLocations.length > 0) {
            return closestLocation(enemyLocations, enemyLocations[0], myLocation);
        } else {
            return null;
        }
    }

    public boolean attack(RobotController rc, MapLocation enemyLocation) throws GameActionException {
        if (rc.isActionReady() && myLocation.distanceSquaredTo(enemyLocation) <= type.actionRadiusSquared) {
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
            moveTowardsAStar(rc, enemyLocation);
        } else {
            generalExplore(rc);
        }
    }
}
