package jackPlayer;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;

public class BoosterController extends Controller {
public static int area = 0;
    public BoosterController(RobotController rc) {
        super(rc);

    }


    @Override
    public void run(RobotController rc) throws GameActionException {
        super.run(rc);

    }

    public void boost(RobotController rc) throws GameActionException {
        if(rc.canBoost()){
            if(area == 0){
                area = rc.getMapWidth() * rc.getMapHeight();
            }
            RobotInfo[] nearbyRobots = rc.senseNearbyRobots(rc.getType().actionRadiusSquared, rc.getTeam());
            int numRobots = rc.getRobotCount();

            if(area <= 1000){ //so like 20x20 or like 30x30 - robots are going to be closer to each other
                if(numRobots >= (area/3)){ //so at least 1/3 of the map is covered in our robots
                    if(nearbyRobots.length >= (numRobots/2) || nearbyRobots.length >= 20){
                        rc.boost();
                    } else { //otherwise it should move and try and be surrounded by other robots
                        Controller.generalExplore(rc);
                    }
                } else { //less than 1/3 of the map is covered in our robots
                    if(nearbyRobots.length >= (numRobots/100) && nearbyRobots.length > 0){
                        rc.boost();
                    }
                }
            } else { //so map is more like 40x40 to 60x60 - robots are going to be more spread out
                if(numRobots >= (area/4)){ //so at least 1/4 of the map is covered in our robots
                    if(nearbyRobots.length >= (numRobots/3) || nearbyRobots.length >= 10){
                        rc.boost();
                    } else { //otherwise it should move and try and be surrounded by other robots
                        Controller.generalExplore(rc);
                    }
                } else {
                    if(nearbyRobots.length >= (numRobots/100) && nearbyRobots.length > 0){
                        rc.boost();
                    }
                }
            }

        }
    }
}
