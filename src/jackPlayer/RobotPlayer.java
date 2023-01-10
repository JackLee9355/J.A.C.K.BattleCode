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

    public static void generalExplore(RobotController rc) throws GameActionException {
        Direction dir = null;
        int[] nearby = new int[4]; //0-N (up) 1-E (right), 2-S (down), 3-W (left)
        // +1 for ally robot (to disperse), +1 for opposite direction if enemy robot (to avoid)
        RobotInfo[] nearbyRobots = rc.senseNearbyRobots();
        int thisX = rc.getLocation().x;
        int thisY = rc.getLocation().y;
        for (RobotInfo robot : nearbyRobots) {
            int x = robot.location.x;
            int y = robot.location.y;
            int diffX = x - thisX; //pos = enemy is N, neg = enemy = S
            int diffY = y - thisY; //pos = enemy is E, neg = W
            if (diffX > 0) {
                nearby[0] += 1;
            }
            if (diffX < 0) {
                nearby[2] += 1;
            }
            if (diffY > 0) {
                nearby[1] += 1;
            }
            if (diffY < 0) {
                nearby[3] += 1;
            }
        }
        boolean N = false;
        boolean S = false;
        boolean E = false;
        boolean W = false;
        int updown = nearby[0] - nearby[2];
        int ew = nearby[1] - nearby[3];
        if (updown > 0) {
            S = true; //enemies in N
        } else if (updown < 0) {
            N = true;
        }
        if (ew > 0) {
            W = true;
        }
        if (ew < 0) {
            E = true;
        }
        if(N && E && rc.canMove(Direction.NORTHEAST)){
            dir = Direction.NORTHEAST;
        } else if (N && W && rc.canMove((Direction.NORTHWEST))){
            dir = Direction.NORTHWEST;
        } else if (S && E && rc.canMove((Direction.SOUTHEAST))){
            dir = Direction.SOUTHEAST;
        } else if (S && W && rc.canMove(Direction.SOUTHWEST)){
            dir = Direction.SOUTHWEST;
        } else if(N && rc.canMove(Direction.NORTH)){
            dir = Direction.NORTH;
        } else if (E && rc.canMove(Direction.EAST)){
            dir = Direction.EAST;
        } else if (S && rc.canMove(Direction.SOUTH)){
            dir = Direction.SOUTH;
        } else if(W && rc.canMove((Direction.WEST)){
            dir = Direction.WEST;
        }
        if(dir != null){
            rc.move(dir);
        }
    }}