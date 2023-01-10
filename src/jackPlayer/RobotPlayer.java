package jackPlayer;

import battlecode.common.*;

/**
 * RobotPlayer is the class that describes your main robot strategy.
 * The run() method inside this class is like your main function: this is what we'll call once your robot
 * is created!
 */
public strictfp class RobotPlayer {

    @SuppressWarnings("unused")
    public static void run(RobotController rc) throws GameActionException {

        System.out.println("I'm a " + rc.getType() + " and I just got created! I have health " + rc.getHealth());

        Controller p;
        switch (rc.getType()) {
            case HEADQUARTERS:
                p = new HeadQuartersController();
                break;
            case CARRIER:
                p = new CarrierController();
                break;
            case LAUNCHER:
                p = new LauncherController();
                break;
            case BOOSTER:
                p = new BoosterController();
                break;
            case DESTABILIZER:
                p = new DestabilizerController();
                break;
            case AMPLIFIER:
                p = new AmplifierController();
                break;
            default:
                return;
        }

        rc.setIndicatorString("Hello world!");

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
}
