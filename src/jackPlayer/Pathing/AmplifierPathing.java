// Inspired by https://github.com/IvanGeffner/battlecode2021/blob/master/thirtyone/BFSMuckraker.java.
package jackPlayer.Pathing;
import battlecode.common.*;

public class AmplifierPathing extends Pathing {

    public AmplifierPathing(RobotController rc) {
        super(rc);
    }

    /*
     * PART 1: Initialization
     * All the locations that will be explored formatted as:
     *  0 -> Positive && 1 -> Negative
     *  Location: loc(0 or 1)(x)(0 or 1)y
     *  Distance: dist(0 or 1)(x)(0 or 1)y
     *  Direction: dir(0 or 1)(x)(0 or 1)y
     */

    private MapLocation loc1513; // location representing relative coordinate (-5, -3)
    private int dist1513;        // shortest distance to location from current location
    private Direction dir1513;   // best direction to take now to optimally get to location

    private MapLocation loc1512; // location representing relative coordinate (-5, -2)
    private int dist1512;        // shortest distance to location from current location
    private Direction dir1512;   // best direction to take now to optimally get to location

    private MapLocation loc1511; // location representing relative coordinate (-5, -1)
    private int dist1511;        // shortest distance to location from current location
    private Direction dir1511;   // best direction to take now to optimally get to location

    private MapLocation loc1500; // location representing relative coordinate (-5, 0)
    private int dist1500;        // shortest distance to location from current location
    private Direction dir1500;   // best direction to take now to optimally get to location

    private MapLocation loc1501; // location representing relative coordinate (-5, 1)
    private int dist1501;        // shortest distance to location from current location
    private Direction dir1501;   // best direction to take now to optimally get to location

    private MapLocation loc1502; // location representing relative coordinate (-5, 2)
    private int dist1502;        // shortest distance to location from current location
    private Direction dir1502;   // best direction to take now to optimally get to location

    private MapLocation loc1503; // location representing relative coordinate (-5, 3)
    private int dist1503;        // shortest distance to location from current location
    private Direction dir1503;   // best direction to take now to optimally get to location

    private MapLocation loc1414; // location representing relative coordinate (-4, -4)
    private int dist1414;        // shortest distance to location from current location
    private Direction dir1414;   // best direction to take now to optimally get to location

    private MapLocation loc1413; // location representing relative coordinate (-4, -3)
    private int dist1413;        // shortest distance to location from current location
    private Direction dir1413;   // best direction to take now to optimally get to location

    private MapLocation loc1412; // location representing relative coordinate (-4, -2)
    private int dist1412;        // shortest distance to location from current location
    private Direction dir1412;   // best direction to take now to optimally get to location

    private MapLocation loc1411; // location representing relative coordinate (-4, -1)
    private int dist1411;        // shortest distance to location from current location
    private Direction dir1411;   // best direction to take now to optimally get to location

    private MapLocation loc1400; // location representing relative coordinate (-4, 0)
    private int dist1400;        // shortest distance to location from current location
    private Direction dir1400;   // best direction to take now to optimally get to location

    private MapLocation loc1401; // location representing relative coordinate (-4, 1)
    private int dist1401;        // shortest distance to location from current location
    private Direction dir1401;   // best direction to take now to optimally get to location

    private MapLocation loc1402; // location representing relative coordinate (-4, 2)
    private int dist1402;        // shortest distance to location from current location
    private Direction dir1402;   // best direction to take now to optimally get to location

    private MapLocation loc1403; // location representing relative coordinate (-4, 3)
    private int dist1403;        // shortest distance to location from current location
    private Direction dir1403;   // best direction to take now to optimally get to location

    private MapLocation loc1404; // location representing relative coordinate (-4, 4)
    private int dist1404;        // shortest distance to location from current location
    private Direction dir1404;   // best direction to take now to optimally get to location

    private MapLocation loc1315; // location representing relative coordinate (-3, -5)
    private int dist1315;        // shortest distance to location from current location
    private Direction dir1315;   // best direction to take now to optimally get to location

    private MapLocation loc1314; // location representing relative coordinate (-3, -4)
    private int dist1314;        // shortest distance to location from current location
    private Direction dir1314;   // best direction to take now to optimally get to location

    private MapLocation loc1313; // location representing relative coordinate (-3, -3)
    private int dist1313;        // shortest distance to location from current location
    private Direction dir1313;   // best direction to take now to optimally get to location

    private MapLocation loc1312; // location representing relative coordinate (-3, -2)
    private int dist1312;        // shortest distance to location from current location
    private Direction dir1312;   // best direction to take now to optimally get to location

    private MapLocation loc1311; // location representing relative coordinate (-3, -1)
    private int dist1311;        // shortest distance to location from current location
    private Direction dir1311;   // best direction to take now to optimally get to location

    private MapLocation loc1300; // location representing relative coordinate (-3, 0)
    private int dist1300;        // shortest distance to location from current location
    private Direction dir1300;   // best direction to take now to optimally get to location

    private MapLocation loc1301; // location representing relative coordinate (-3, 1)
    private int dist1301;        // shortest distance to location from current location
    private Direction dir1301;   // best direction to take now to optimally get to location

    private MapLocation loc1302; // location representing relative coordinate (-3, 2)
    private int dist1302;        // shortest distance to location from current location
    private Direction dir1302;   // best direction to take now to optimally get to location

    private MapLocation loc1303; // location representing relative coordinate (-3, 3)
    private int dist1303;        // shortest distance to location from current location
    private Direction dir1303;   // best direction to take now to optimally get to location

    private MapLocation loc1304; // location representing relative coordinate (-3, 4)
    private int dist1304;        // shortest distance to location from current location
    private Direction dir1304;   // best direction to take now to optimally get to location

    private MapLocation loc1305; // location representing relative coordinate (-3, 5)
    private int dist1305;        // shortest distance to location from current location
    private Direction dir1305;   // best direction to take now to optimally get to location

    private MapLocation loc1215; // location representing relative coordinate (-2, -5)
    private int dist1215;        // shortest distance to location from current location
    private Direction dir1215;   // best direction to take now to optimally get to location

    private MapLocation loc1214; // location representing relative coordinate (-2, -4)
    private int dist1214;        // shortest distance to location from current location
    private Direction dir1214;   // best direction to take now to optimally get to location

    private MapLocation loc1213; // location representing relative coordinate (-2, -3)
    private int dist1213;        // shortest distance to location from current location
    private Direction dir1213;   // best direction to take now to optimally get to location

    private MapLocation loc1212; // location representing relative coordinate (-2, -2)
    private int dist1212;        // shortest distance to location from current location
    private Direction dir1212;   // best direction to take now to optimally get to location

    private MapLocation loc1211; // location representing relative coordinate (-2, -1)
    private int dist1211;        // shortest distance to location from current location
    private Direction dir1211;   // best direction to take now to optimally get to location

    private MapLocation loc1200; // location representing relative coordinate (-2, 0)
    private int dist1200;        // shortest distance to location from current location
    private Direction dir1200;   // best direction to take now to optimally get to location

    private MapLocation loc1201; // location representing relative coordinate (-2, 1)
    private int dist1201;        // shortest distance to location from current location
    private Direction dir1201;   // best direction to take now to optimally get to location

    private MapLocation loc1202; // location representing relative coordinate (-2, 2)
    private int dist1202;        // shortest distance to location from current location
    private Direction dir1202;   // best direction to take now to optimally get to location

    private MapLocation loc1203; // location representing relative coordinate (-2, 3)
    private int dist1203;        // shortest distance to location from current location
    private Direction dir1203;   // best direction to take now to optimally get to location

    private MapLocation loc1204; // location representing relative coordinate (-2, 4)
    private int dist1204;        // shortest distance to location from current location
    private Direction dir1204;   // best direction to take now to optimally get to location

    private MapLocation loc1205; // location representing relative coordinate (-2, 5)
    private int dist1205;        // shortest distance to location from current location
    private Direction dir1205;   // best direction to take now to optimally get to location

    private MapLocation loc1115; // location representing relative coordinate (-1, -5)
    private int dist1115;        // shortest distance to location from current location
    private Direction dir1115;   // best direction to take now to optimally get to location

    private MapLocation loc1114; // location representing relative coordinate (-1, -4)
    private int dist1114;        // shortest distance to location from current location
    private Direction dir1114;   // best direction to take now to optimally get to location

    private MapLocation loc1113; // location representing relative coordinate (-1, -3)
    private int dist1113;        // shortest distance to location from current location
    private Direction dir1113;   // best direction to take now to optimally get to location

    private MapLocation loc1112; // location representing relative coordinate (-1, -2)
    private int dist1112;        // shortest distance to location from current location
    private Direction dir1112;   // best direction to take now to optimally get to location

    private MapLocation loc1111; // location representing relative coordinate (-1, -1)
    private int dist1111;        // shortest distance to location from current location
    private Direction dir1111;   // best direction to take now to optimally get to location

    private MapLocation loc1100; // location representing relative coordinate (-1, 0)
    private int dist1100;        // shortest distance to location from current location
    private Direction dir1100;   // best direction to take now to optimally get to location

    private MapLocation loc1101; // location representing relative coordinate (-1, 1)
    private int dist1101;        // shortest distance to location from current location
    private Direction dir1101;   // best direction to take now to optimally get to location

    private MapLocation loc1102; // location representing relative coordinate (-1, 2)
    private int dist1102;        // shortest distance to location from current location
    private Direction dir1102;   // best direction to take now to optimally get to location

    private MapLocation loc1103; // location representing relative coordinate (-1, 3)
    private int dist1103;        // shortest distance to location from current location
    private Direction dir1103;   // best direction to take now to optimally get to location

    private MapLocation loc1104; // location representing relative coordinate (-1, 4)
    private int dist1104;        // shortest distance to location from current location
    private Direction dir1104;   // best direction to take now to optimally get to location

    private MapLocation loc1105; // location representing relative coordinate (-1, 5)
    private int dist1105;        // shortest distance to location from current location
    private Direction dir1105;   // best direction to take now to optimally get to location

    private MapLocation loc0015; // location representing relative coordinate (0, -5)
    private int dist0015;        // shortest distance to location from current location
    private Direction dir0015;   // best direction to take now to optimally get to location

    private MapLocation loc0014; // location representing relative coordinate (0, -4)
    private int dist0014;        // shortest distance to location from current location
    private Direction dir0014;   // best direction to take now to optimally get to location

    private MapLocation loc0013; // location representing relative coordinate (0, -3)
    private int dist0013;        // shortest distance to location from current location
    private Direction dir0013;   // best direction to take now to optimally get to location

    private MapLocation loc0012; // location representing relative coordinate (0, -2)
    private int dist0012;        // shortest distance to location from current location
    private Direction dir0012;   // best direction to take now to optimally get to location

    private MapLocation loc0011; // location representing relative coordinate (0, -1)
    private int dist0011;        // shortest distance to location from current location
    private Direction dir0011;   // best direction to take now to optimally get to location

    private MapLocation loc0000; // location representing relative coordinate (0, 0)
    private int dist0000;        // shortest distance to location from current location
    private Direction dir0000;   // best direction to take now to optimally get to location

    private MapLocation loc0001; // location representing relative coordinate (0, 1)
    private int dist0001;        // shortest distance to location from current location
    private Direction dir0001;   // best direction to take now to optimally get to location

    private MapLocation loc0002; // location representing relative coordinate (0, 2)
    private int dist0002;        // shortest distance to location from current location
    private Direction dir0002;   // best direction to take now to optimally get to location

    private MapLocation loc0003; // location representing relative coordinate (0, 3)
    private int dist0003;        // shortest distance to location from current location
    private Direction dir0003;   // best direction to take now to optimally get to location

    private MapLocation loc0004; // location representing relative coordinate (0, 4)
    private int dist0004;        // shortest distance to location from current location
    private Direction dir0004;   // best direction to take now to optimally get to location

    private MapLocation loc0005; // location representing relative coordinate (0, 5)
    private int dist0005;        // shortest distance to location from current location
    private Direction dir0005;   // best direction to take now to optimally get to location

    private MapLocation loc0115; // location representing relative coordinate (1, -5)
    private int dist0115;        // shortest distance to location from current location
    private Direction dir0115;   // best direction to take now to optimally get to location

    private MapLocation loc0114; // location representing relative coordinate (1, -4)
    private int dist0114;        // shortest distance to location from current location
    private Direction dir0114;   // best direction to take now to optimally get to location

    private MapLocation loc0113; // location representing relative coordinate (1, -3)
    private int dist0113;        // shortest distance to location from current location
    private Direction dir0113;   // best direction to take now to optimally get to location

    private MapLocation loc0112; // location representing relative coordinate (1, -2)
    private int dist0112;        // shortest distance to location from current location
    private Direction dir0112;   // best direction to take now to optimally get to location

    private MapLocation loc0111; // location representing relative coordinate (1, -1)
    private int dist0111;        // shortest distance to location from current location
    private Direction dir0111;   // best direction to take now to optimally get to location

    private MapLocation loc0100; // location representing relative coordinate (1, 0)
    private int dist0100;        // shortest distance to location from current location
    private Direction dir0100;   // best direction to take now to optimally get to location

    private MapLocation loc0101; // location representing relative coordinate (1, 1)
    private int dist0101;        // shortest distance to location from current location
    private Direction dir0101;   // best direction to take now to optimally get to location

    private MapLocation loc0102; // location representing relative coordinate (1, 2)
    private int dist0102;        // shortest distance to location from current location
    private Direction dir0102;   // best direction to take now to optimally get to location

    private MapLocation loc0103; // location representing relative coordinate (1, 3)
    private int dist0103;        // shortest distance to location from current location
    private Direction dir0103;   // best direction to take now to optimally get to location

    private MapLocation loc0104; // location representing relative coordinate (1, 4)
    private int dist0104;        // shortest distance to location from current location
    private Direction dir0104;   // best direction to take now to optimally get to location

    private MapLocation loc0105; // location representing relative coordinate (1, 5)
    private int dist0105;        // shortest distance to location from current location
    private Direction dir0105;   // best direction to take now to optimally get to location

    private MapLocation loc0215; // location representing relative coordinate (2, -5)
    private int dist0215;        // shortest distance to location from current location
    private Direction dir0215;   // best direction to take now to optimally get to location

    private MapLocation loc0214; // location representing relative coordinate (2, -4)
    private int dist0214;        // shortest distance to location from current location
    private Direction dir0214;   // best direction to take now to optimally get to location

    private MapLocation loc0213; // location representing relative coordinate (2, -3)
    private int dist0213;        // shortest distance to location from current location
    private Direction dir0213;   // best direction to take now to optimally get to location

    private MapLocation loc0212; // location representing relative coordinate (2, -2)
    private int dist0212;        // shortest distance to location from current location
    private Direction dir0212;   // best direction to take now to optimally get to location

    private MapLocation loc0211; // location representing relative coordinate (2, -1)
    private int dist0211;        // shortest distance to location from current location
    private Direction dir0211;   // best direction to take now to optimally get to location

    private MapLocation loc0200; // location representing relative coordinate (2, 0)
    private int dist0200;        // shortest distance to location from current location
    private Direction dir0200;   // best direction to take now to optimally get to location

    private MapLocation loc0201; // location representing relative coordinate (2, 1)
    private int dist0201;        // shortest distance to location from current location
    private Direction dir0201;   // best direction to take now to optimally get to location

    private MapLocation loc0202; // location representing relative coordinate (2, 2)
    private int dist0202;        // shortest distance to location from current location
    private Direction dir0202;   // best direction to take now to optimally get to location

    private MapLocation loc0203; // location representing relative coordinate (2, 3)
    private int dist0203;        // shortest distance to location from current location
    private Direction dir0203;   // best direction to take now to optimally get to location

    private MapLocation loc0204; // location representing relative coordinate (2, 4)
    private int dist0204;        // shortest distance to location from current location
    private Direction dir0204;   // best direction to take now to optimally get to location

    private MapLocation loc0205; // location representing relative coordinate (2, 5)
    private int dist0205;        // shortest distance to location from current location
    private Direction dir0205;   // best direction to take now to optimally get to location

    private MapLocation loc0315; // location representing relative coordinate (3, -5)
    private int dist0315;        // shortest distance to location from current location
    private Direction dir0315;   // best direction to take now to optimally get to location

    private MapLocation loc0314; // location representing relative coordinate (3, -4)
    private int dist0314;        // shortest distance to location from current location
    private Direction dir0314;   // best direction to take now to optimally get to location

    private MapLocation loc0313; // location representing relative coordinate (3, -3)
    private int dist0313;        // shortest distance to location from current location
    private Direction dir0313;   // best direction to take now to optimally get to location

    private MapLocation loc0312; // location representing relative coordinate (3, -2)
    private int dist0312;        // shortest distance to location from current location
    private Direction dir0312;   // best direction to take now to optimally get to location

    private MapLocation loc0311; // location representing relative coordinate (3, -1)
    private int dist0311;        // shortest distance to location from current location
    private Direction dir0311;   // best direction to take now to optimally get to location

    private MapLocation loc0300; // location representing relative coordinate (3, 0)
    private int dist0300;        // shortest distance to location from current location
    private Direction dir0300;   // best direction to take now to optimally get to location

    private MapLocation loc0301; // location representing relative coordinate (3, 1)
    private int dist0301;        // shortest distance to location from current location
    private Direction dir0301;   // best direction to take now to optimally get to location

    private MapLocation loc0302; // location representing relative coordinate (3, 2)
    private int dist0302;        // shortest distance to location from current location
    private Direction dir0302;   // best direction to take now to optimally get to location

    private MapLocation loc0303; // location representing relative coordinate (3, 3)
    private int dist0303;        // shortest distance to location from current location
    private Direction dir0303;   // best direction to take now to optimally get to location

    private MapLocation loc0304; // location representing relative coordinate (3, 4)
    private int dist0304;        // shortest distance to location from current location
    private Direction dir0304;   // best direction to take now to optimally get to location

    private MapLocation loc0305; // location representing relative coordinate (3, 5)
    private int dist0305;        // shortest distance to location from current location
    private Direction dir0305;   // best direction to take now to optimally get to location

    private MapLocation loc0414; // location representing relative coordinate (4, -4)
    private int dist0414;        // shortest distance to location from current location
    private Direction dir0414;   // best direction to take now to optimally get to location

    private MapLocation loc0413; // location representing relative coordinate (4, -3)
    private int dist0413;        // shortest distance to location from current location
    private Direction dir0413;   // best direction to take now to optimally get to location

    private MapLocation loc0412; // location representing relative coordinate (4, -2)
    private int dist0412;        // shortest distance to location from current location
    private Direction dir0412;   // best direction to take now to optimally get to location

    private MapLocation loc0411; // location representing relative coordinate (4, -1)
    private int dist0411;        // shortest distance to location from current location
    private Direction dir0411;   // best direction to take now to optimally get to location

    private MapLocation loc0400; // location representing relative coordinate (4, 0)
    private int dist0400;        // shortest distance to location from current location
    private Direction dir0400;   // best direction to take now to optimally get to location

    private MapLocation loc0401; // location representing relative coordinate (4, 1)
    private int dist0401;        // shortest distance to location from current location
    private Direction dir0401;   // best direction to take now to optimally get to location

    private MapLocation loc0402; // location representing relative coordinate (4, 2)
    private int dist0402;        // shortest distance to location from current location
    private Direction dir0402;   // best direction to take now to optimally get to location

    private MapLocation loc0403; // location representing relative coordinate (4, 3)
    private int dist0403;        // shortest distance to location from current location
    private Direction dir0403;   // best direction to take now to optimally get to location

    private MapLocation loc0404; // location representing relative coordinate (4, 4)
    private int dist0404;        // shortest distance to location from current location
    private Direction dir0404;   // best direction to take now to optimally get to location

    private MapLocation loc0513; // location representing relative coordinate (5, -3)
    private int dist0513;        // shortest distance to location from current location
    private Direction dir0513;   // best direction to take now to optimally get to location

    private MapLocation loc0512; // location representing relative coordinate (5, -2)
    private int dist0512;        // shortest distance to location from current location
    private Direction dir0512;   // best direction to take now to optimally get to location

    private MapLocation loc0511; // location representing relative coordinate (5, -1)
    private int dist0511;        // shortest distance to location from current location
    private Direction dir0511;   // best direction to take now to optimally get to location

    private MapLocation loc0500; // location representing relative coordinate (5, 0)
    private int dist0500;        // shortest distance to location from current location
    private Direction dir0500;   // best direction to take now to optimally get to location

    private MapLocation loc0501; // location representing relative coordinate (5, 1)
    private int dist0501;        // shortest distance to location from current location
    private Direction dir0501;   // best direction to take now to optimally get to location

    private MapLocation loc0502; // location representing relative coordinate (5, 2)
    private int dist0502;        // shortest distance to location from current location
    private Direction dir0502;   // best direction to take now to optimally get to location

    private MapLocation loc0503; // location representing relative coordinate (5, 3)
    private int dist0503;        // shortest distance to location from current location
    private Direction dir0503;   // best direction to take now to optimally get to location

    public Direction getBestDirection(MapLocation target) throws GameActionException {
        /*
         * PART 1: Build
         * Builds a graph from the vision radius of the robot
         */

        loc0000 = rc.getLocation();
        dist0000 = 0;
        dir0000 = Direction.CENTER;

        loc1100 = loc0000.add(Direction.WEST); // (-1, 0) from (0, 0)
        dist1100 = 99999;
        dir1100 = null;

        loc0011 = loc0000.add(Direction.SOUTH); // (0, -1) from (0, 0)
        dist0011 = 99999;
        dir0011 = null;

        loc0001 = loc0000.add(Direction.NORTH); // (0, 1) from (0, 0)
        dist0001 = 99999;
        dir0001 = null;

        loc0100 = loc0000.add(Direction.EAST); // (1, 0) from (0, 0)
        dist0100 = 99999;
        dir0100 = null;

        loc1111 = loc0000.add(Direction.SOUTHWEST); // (-1, -1) from (0, 0)
        dist1111 = 99999;
        dir1111 = null;

        loc1101 = loc0000.add(Direction.NORTHWEST); // (-1, 1) from (0, 0)
        dist1101 = 99999;
        dir1101 = null;

        loc0111 = loc0000.add(Direction.SOUTHEAST); // (1, -1) from (0, 0)
        dist0111 = 99999;
        dir0111 = null;

        loc0101 = loc0000.add(Direction.NORTHEAST); // (1, 1) from (0, 0)
        dist0101 = 99999;
        dir0101 = null;

        loc1200 = loc1100.add(Direction.WEST); // (-2, 0) from (-1, 0)
        dist1200 = 99999;
        dir1200 = null;

        loc0012 = loc0011.add(Direction.SOUTH); // (0, -2) from (0, -1)
        dist0012 = 99999;
        dir0012 = null;

        loc0002 = loc0001.add(Direction.NORTH); // (0, 2) from (0, 1)
        dist0002 = 99999;
        dir0002 = null;

        loc0200 = loc0100.add(Direction.EAST); // (2, 0) from (1, 0)
        dist0200 = 99999;
        dir0200 = null;

        loc1211 = loc1100.add(Direction.SOUTHWEST); // (-2, -1) from (-1, 0)
        dist1211 = 99999;
        dir1211 = null;

        loc1201 = loc1100.add(Direction.NORTHWEST); // (-2, 1) from (-1, 0)
        dist1201 = 99999;
        dir1201 = null;

        loc1112 = loc0011.add(Direction.SOUTHWEST); // (-1, -2) from (0, -1)
        dist1112 = 99999;
        dir1112 = null;

        loc1102 = loc0001.add(Direction.NORTHWEST); // (-1, 2) from (0, 1)
        dist1102 = 99999;
        dir1102 = null;

        loc0112 = loc0011.add(Direction.SOUTHEAST); // (1, -2) from (0, -1)
        dist0112 = 99999;
        dir0112 = null;

        loc0102 = loc0001.add(Direction.NORTHEAST); // (1, 2) from (0, 1)
        dist0102 = 99999;
        dir0102 = null;

        loc0211 = loc0100.add(Direction.SOUTHEAST); // (2, -1) from (1, 0)
        dist0211 = 99999;
        dir0211 = null;

        loc0201 = loc0100.add(Direction.NORTHEAST); // (2, 1) from (1, 0)
        dist0201 = 99999;
        dir0201 = null;

        loc1212 = loc1111.add(Direction.SOUTHWEST); // (-2, -2) from (-1, -1)
        dist1212 = 99999;
        dir1212 = null;

        loc1202 = loc1101.add(Direction.NORTHWEST); // (-2, 2) from (-1, 1)
        dist1202 = 99999;
        dir1202 = null;

        loc0212 = loc0111.add(Direction.SOUTHEAST); // (2, -2) from (1, -1)
        dist0212 = 99999;
        dir0212 = null;

        loc0202 = loc0101.add(Direction.NORTHEAST); // (2, 2) from (1, 1)
        dist0202 = 99999;
        dir0202 = null;

        loc1300 = loc1200.add(Direction.WEST); // (-3, 0) from (-2, 0)
        dist1300 = 99999;
        dir1300 = null;

        loc0013 = loc0012.add(Direction.SOUTH); // (0, -3) from (0, -2)
        dist0013 = 99999;
        dir0013 = null;

        loc0003 = loc0002.add(Direction.NORTH); // (0, 3) from (0, 2)
        dist0003 = 99999;
        dir0003 = null;

        loc0300 = loc0200.add(Direction.EAST); // (3, 0) from (2, 0)
        dist0300 = 99999;
        dir0300 = null;

        loc1311 = loc1200.add(Direction.SOUTHWEST); // (-3, -1) from (-2, 0)
        dist1311 = 99999;
        dir1311 = null;

        loc1301 = loc1200.add(Direction.NORTHWEST); // (-3, 1) from (-2, 0)
        dist1301 = 99999;
        dir1301 = null;

        loc1113 = loc0012.add(Direction.SOUTHWEST); // (-1, -3) from (0, -2)
        dist1113 = 99999;
        dir1113 = null;

        loc1103 = loc0002.add(Direction.NORTHWEST); // (-1, 3) from (0, 2)
        dist1103 = 99999;
        dir1103 = null;

        loc0113 = loc0012.add(Direction.SOUTHEAST); // (1, -3) from (0, -2)
        dist0113 = 99999;
        dir0113 = null;

        loc0103 = loc0002.add(Direction.NORTHEAST); // (1, 3) from (0, 2)
        dist0103 = 99999;
        dir0103 = null;

        loc0311 = loc0200.add(Direction.SOUTHEAST); // (3, -1) from (2, 0)
        dist0311 = 99999;
        dir0311 = null;

        loc0301 = loc0200.add(Direction.NORTHEAST); // (3, 1) from (2, 0)
        dist0301 = 99999;
        dir0301 = null;

        loc1312 = loc1211.add(Direction.SOUTHWEST); // (-3, -2) from (-2, -1)
        dist1312 = 99999;
        dir1312 = null;

        loc1302 = loc1201.add(Direction.NORTHWEST); // (-3, 2) from (-2, 1)
        dist1302 = 99999;
        dir1302 = null;

        loc1213 = loc1112.add(Direction.SOUTHWEST); // (-2, -3) from (-1, -2)
        dist1213 = 99999;
        dir1213 = null;

        loc1203 = loc1102.add(Direction.NORTHWEST); // (-2, 3) from (-1, 2)
        dist1203 = 99999;
        dir1203 = null;

        loc0213 = loc0112.add(Direction.SOUTHEAST); // (2, -3) from (1, -2)
        dist0213 = 99999;
        dir0213 = null;

        loc0203 = loc0102.add(Direction.NORTHEAST); // (2, 3) from (1, 2)
        dist0203 = 99999;
        dir0203 = null;

        loc0312 = loc0211.add(Direction.SOUTHEAST); // (3, -2) from (2, -1)
        dist0312 = 99999;
        dir0312 = null;

        loc0302 = loc0201.add(Direction.NORTHEAST); // (3, 2) from (2, 1)
        dist0302 = 99999;
        dir0302 = null;

        loc1400 = loc1300.add(Direction.WEST); // (-4, 0) from (-3, 0)
        dist1400 = 99999;
        dir1400 = null;

        loc0014 = loc0013.add(Direction.SOUTH); // (0, -4) from (0, -3)
        dist0014 = 99999;
        dir0014 = null;

        loc0004 = loc0003.add(Direction.NORTH); // (0, 4) from (0, 3)
        dist0004 = 99999;
        dir0004 = null;

        loc0400 = loc0300.add(Direction.EAST); // (4, 0) from (3, 0)
        dist0400 = 99999;
        dir0400 = null;

        loc1411 = loc1300.add(Direction.SOUTHWEST); // (-4, -1) from (-3, 0)
        dist1411 = 99999;
        dir1411 = null;

        loc1401 = loc1300.add(Direction.NORTHWEST); // (-4, 1) from (-3, 0)
        dist1401 = 99999;
        dir1401 = null;

        loc1114 = loc0013.add(Direction.SOUTHWEST); // (-1, -4) from (0, -3)
        dist1114 = 99999;
        dir1114 = null;

        loc1104 = loc0003.add(Direction.NORTHWEST); // (-1, 4) from (0, 3)
        dist1104 = 99999;
        dir1104 = null;

        loc0114 = loc0013.add(Direction.SOUTHEAST); // (1, -4) from (0, -3)
        dist0114 = 99999;
        dir0114 = null;

        loc0104 = loc0003.add(Direction.NORTHEAST); // (1, 4) from (0, 3)
        dist0104 = 99999;
        dir0104 = null;

        loc0411 = loc0300.add(Direction.SOUTHEAST); // (4, -1) from (3, 0)
        dist0411 = 99999;
        dir0411 = null;

        loc0401 = loc0300.add(Direction.NORTHEAST); // (4, 1) from (3, 0)
        dist0401 = 99999;
        dir0401 = null;

        loc1313 = loc1212.add(Direction.SOUTHWEST); // (-3, -3) from (-2, -2)
        dist1313 = 99999;
        dir1313 = null;

        loc1303 = loc1202.add(Direction.NORTHWEST); // (-3, 3) from (-2, 2)
        dist1303 = 99999;
        dir1303 = null;

        loc0313 = loc0212.add(Direction.SOUTHEAST); // (3, -3) from (2, -2)
        dist0313 = 99999;
        dir0313 = null;

        loc0303 = loc0202.add(Direction.NORTHEAST); // (3, 3) from (2, 2)
        dist0303 = 99999;
        dir0303 = null;

        loc1412 = loc1311.add(Direction.SOUTHWEST); // (-4, -2) from (-3, -1)
        dist1412 = 99999;
        dir1412 = null;

        loc1402 = loc1301.add(Direction.NORTHWEST); // (-4, 2) from (-3, 1)
        dist1402 = 99999;
        dir1402 = null;

        loc1214 = loc1113.add(Direction.SOUTHWEST); // (-2, -4) from (-1, -3)
        dist1214 = 99999;
        dir1214 = null;

        loc1204 = loc1103.add(Direction.NORTHWEST); // (-2, 4) from (-1, 3)
        dist1204 = 99999;
        dir1204 = null;

        loc0214 = loc0113.add(Direction.SOUTHEAST); // (2, -4) from (1, -3)
        dist0214 = 99999;
        dir0214 = null;

        loc0204 = loc0103.add(Direction.NORTHEAST); // (2, 4) from (1, 3)
        dist0204 = 99999;
        dir0204 = null;

        loc0412 = loc0311.add(Direction.SOUTHEAST); // (4, -2) from (3, -1)
        dist0412 = 99999;
        dir0412 = null;

        loc0402 = loc0301.add(Direction.NORTHEAST); // (4, 2) from (3, 1)
        dist0402 = 99999;
        dir0402 = null;

        loc1500 = loc1400.add(Direction.WEST); // (-5, 0) from (-4, 0)
        dist1500 = 99999;
        dir1500 = null;

        loc1413 = loc1312.add(Direction.SOUTHWEST); // (-4, -3) from (-3, -2)
        dist1413 = 99999;
        dir1413 = null;

        loc1403 = loc1302.add(Direction.NORTHWEST); // (-4, 3) from (-3, 2)
        dist1403 = 99999;
        dir1403 = null;

        loc1314 = loc1213.add(Direction.SOUTHWEST); // (-3, -4) from (-2, -3)
        dist1314 = 99999;
        dir1314 = null;

        loc1304 = loc1203.add(Direction.NORTHWEST); // (-3, 4) from (-2, 3)
        dist1304 = 99999;
        dir1304 = null;

        loc0015 = loc0014.add(Direction.SOUTH); // (0, -5) from (0, -4)
        dist0015 = 99999;
        dir0015 = null;

        loc0005 = loc0004.add(Direction.NORTH); // (0, 5) from (0, 4)
        dist0005 = 99999;
        dir0005 = null;

        loc0314 = loc0213.add(Direction.SOUTHEAST); // (3, -4) from (2, -3)
        dist0314 = 99999;
        dir0314 = null;

        loc0304 = loc0203.add(Direction.NORTHEAST); // (3, 4) from (2, 3)
        dist0304 = 99999;
        dir0304 = null;

        loc0413 = loc0312.add(Direction.SOUTHEAST); // (4, -3) from (3, -2)
        dist0413 = 99999;
        dir0413 = null;

        loc0403 = loc0302.add(Direction.NORTHEAST); // (4, 3) from (3, 2)
        dist0403 = 99999;
        dir0403 = null;

        loc0500 = loc0400.add(Direction.EAST); // (5, 0) from (4, 0)
        dist0500 = 99999;
        dir0500 = null;

        loc1511 = loc1400.add(Direction.SOUTHWEST); // (-5, -1) from (-4, 0)
        dist1511 = 99999;
        dir1511 = null;

        loc1501 = loc1400.add(Direction.NORTHWEST); // (-5, 1) from (-4, 0)
        dist1501 = 99999;
        dir1501 = null;

        loc1115 = loc0014.add(Direction.SOUTHWEST); // (-1, -5) from (0, -4)
        dist1115 = 99999;
        dir1115 = null;

        loc1105 = loc0004.add(Direction.NORTHWEST); // (-1, 5) from (0, 4)
        dist1105 = 99999;
        dir1105 = null;

        loc0115 = loc0014.add(Direction.SOUTHEAST); // (1, -5) from (0, -4)
        dist0115 = 99999;
        dir0115 = null;

        loc0105 = loc0004.add(Direction.NORTHEAST); // (1, 5) from (0, 4)
        dist0105 = 99999;
        dir0105 = null;

        loc0511 = loc0400.add(Direction.SOUTHEAST); // (5, -1) from (4, 0)
        dist0511 = 99999;
        dir0511 = null;

        loc0501 = loc0400.add(Direction.NORTHEAST); // (5, 1) from (4, 0)
        dist0501 = 99999;
        dir0501 = null;

        loc1512 = loc1411.add(Direction.SOUTHWEST); // (-5, -2) from (-4, -1)
        dist1512 = 99999;
        dir1512 = null;

        loc1502 = loc1401.add(Direction.NORTHWEST); // (-5, 2) from (-4, 1)
        dist1502 = 99999;
        dir1502 = null;

        loc1215 = loc1114.add(Direction.SOUTHWEST); // (-2, -5) from (-1, -4)
        dist1215 = 99999;
        dir1215 = null;

        loc1205 = loc1104.add(Direction.NORTHWEST); // (-2, 5) from (-1, 4)
        dist1205 = 99999;
        dir1205 = null;

        loc0215 = loc0114.add(Direction.SOUTHEAST); // (2, -5) from (1, -4)
        dist0215 = 99999;
        dir0215 = null;

        loc0205 = loc0104.add(Direction.NORTHEAST); // (2, 5) from (1, 4)
        dist0205 = 99999;
        dir0205 = null;

        loc0512 = loc0411.add(Direction.SOUTHEAST); // (5, -2) from (4, -1)
        dist0512 = 99999;
        dir0512 = null;

        loc0502 = loc0401.add(Direction.NORTHEAST); // (5, 2) from (4, 1)
        dist0502 = 99999;
        dir0502 = null;

        loc1414 = loc1313.add(Direction.SOUTHWEST); // (-4, -4) from (-3, -3)
        dist1414 = 99999;
        dir1414 = null;

        loc1404 = loc1303.add(Direction.NORTHWEST); // (-4, 4) from (-3, 3)
        dist1404 = 99999;
        dir1404 = null;

        loc0414 = loc0313.add(Direction.SOUTHEAST); // (4, -4) from (3, -3)
        dist0414 = 99999;
        dir0414 = null;

        loc0404 = loc0303.add(Direction.NORTHEAST); // (4, 4) from (3, 3)
        dist0404 = 99999;
        dir0404 = null;

        loc1513 = loc1412.add(Direction.SOUTHWEST); // (-5, -3) from (-4, -2)
        dist1513 = 99999;
        dir1513 = null;

        loc1503 = loc1402.add(Direction.NORTHWEST); // (-5, 3) from (-4, 2)
        dist1503 = 99999;
        dir1503 = null;

        loc1315 = loc1214.add(Direction.SOUTHWEST); // (-3, -5) from (-2, -4)
        dist1315 = 99999;
        dir1315 = null;

        loc1305 = loc1204.add(Direction.NORTHWEST); // (-3, 5) from (-2, 4)
        dist1305 = 99999;
        dir1305 = null;

        loc0315 = loc0214.add(Direction.SOUTHEAST); // (3, -5) from (2, -4)
        dist0315 = 99999;
        dir0315 = null;

        loc0305 = loc0204.add(Direction.NORTHEAST); // (3, 5) from (2, 4)
        dist0305 = 99999;
        dir0305 = null;

        loc0513 = loc0412.add(Direction.SOUTHEAST); // (5, -3) from (4, -2)
        dist0513 = 99999;
        dir0513 = null;

        loc0503 = loc0402.add(Direction.NORTHEAST); // (5, 3) from (4, 2)
        dist0503 = 99999;
        dir0503 = null;

        /*
         * PART 2: Single iteration of Bellman-Ford
         * The single iteration of Bellman-Ford is ran here
         */

        if (rc.canSenseLocation(loc1100)) { // check (-1, 0)
            if (!rc.isLocationOccupied(loc1100) && rc.sensePassability(loc1100)) {
                if (dist1100 > dist0000) { // from (0, 0)
                    dist1100 = dist0000;
                    dir1100 = Direction.WEST;
                }
                dist1100 += 1 + (rc.senseMapInfo(loc1100).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc0011)) { // check (0, -1)
            if (!rc.isLocationOccupied(loc0011) && rc.sensePassability(loc0011)) {
                if (dist0011 > dist0000) { // from (0, 0)
                    dist0011 = dist0000;
                    dir0011 = Direction.SOUTH;
                }

                if (dist0011 > dist1100) { // from (-1, 0)
                    dist0011 = dist1100;
                    dir0011 = dir1100;
                }
                dist0011 += 1 + (rc.senseMapInfo(loc0011).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc0001)) { // check (0, 1)
            if (!rc.isLocationOccupied(loc0001) && rc.sensePassability(loc0001)) {
                if (dist0001 > dist0000) { // from (0, 0)
                    dist0001 = dist0000;
                    dir0001 = Direction.NORTH;
                }

                if (dist0001 > dist1100) { // from (-1, 0)
                    dist0001 = dist1100;
                    dir0001 = dir1100;
                }
                dist0001 += 1 + (rc.senseMapInfo(loc0001).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc0100)) { // check (1, 0)
            if (!rc.isLocationOccupied(loc0100) && rc.sensePassability(loc0100)) {
                if (dist0100 > dist0000) { // from (0, 0)
                    dist0100 = dist0000;
                    dir0100 = Direction.EAST;
                }

                if (dist0100 > dist0011) { // from (0, -1)
                    dist0100 = dist0011;
                    dir0100 = dir0011;
                }

                if (dist0100 > dist0001) { // from (0, 1)
                    dist0100 = dist0001;
                    dir0100 = dir0001;
                }
                dist0100 += 1 + (rc.senseMapInfo(loc0100).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc1111)) { // check (-1, -1)
            if (!rc.isLocationOccupied(loc1111) && rc.sensePassability(loc1111)) {
                if (dist1111 > dist0000) { // from (0, 0)
                    dist1111 = dist0000;
                    dir1111 = Direction.SOUTHWEST;
                }

                if (dist1111 > dist1100) { // from (-1, 0)
                    dist1111 = dist1100;
                    dir1111 = dir1100;
                }

                if (dist1111 > dist0011) { // from (0, -1)
                    dist1111 = dist0011;
                    dir1111 = dir0011;
                }
                dist1111 += 1 + (rc.senseMapInfo(loc1111).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc1101)) { // check (-1, 1)
            if (!rc.isLocationOccupied(loc1101) && rc.sensePassability(loc1101)) {
                if (dist1101 > dist0000) { // from (0, 0)
                    dist1101 = dist0000;
                    dir1101 = Direction.NORTHWEST;
                }

                if (dist1101 > dist1100) { // from (-1, 0)
                    dist1101 = dist1100;
                    dir1101 = dir1100;
                }

                if (dist1101 > dist0001) { // from (0, 1)
                    dist1101 = dist0001;
                    dir1101 = dir0001;
                }
                dist1101 += 1 + (rc.senseMapInfo(loc1101).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc0111)) { // check (1, -1)
            if (!rc.isLocationOccupied(loc0111) && rc.sensePassability(loc0111)) {
                if (dist0111 > dist0000) { // from (0, 0)
                    dist0111 = dist0000;
                    dir0111 = Direction.SOUTHEAST;
                }

                if (dist0111 > dist0011) { // from (0, -1)
                    dist0111 = dist0011;
                    dir0111 = dir0011;
                }

                if (dist0111 > dist0100) { // from (1, 0)
                    dist0111 = dist0100;
                    dir0111 = dir0100;
                }
                dist0111 += 1 + (rc.senseMapInfo(loc0111).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc0101)) { // check (1, 1)
            if (!rc.isLocationOccupied(loc0101) && rc.sensePassability(loc0101)) {
                if (dist0101 > dist0000) { // from (0, 0)
                    dist0101 = dist0000;
                    dir0101 = Direction.NORTHEAST;
                }

                if (dist0101 > dist0001) { // from (0, 1)
                    dist0101 = dist0001;
                    dir0101 = dir0001;
                }

                if (dist0101 > dist0100) { // from (1, 0)
                    dist0101 = dist0100;
                    dir0101 = dir0100;
                }
                dist0101 += 1 + (rc.senseMapInfo(loc0101).hasCloud() ? 10 : 0);
            }
        }

        if (rc.canSenseLocation(loc1200)) { // check (-2, 0)
            if (dist1200 > dist1100) { // from (-1, 0)
                dist1200 = dist1100;
                dir1200 = dir1100;
            }

            if (dist1200 > dist1111) { // from (-1, -1)
                dist1200 = dist1111;
                dir1200 = dir1111;
            }

            if (dist1200 > dist1101) { // from (-1, 1)
                dist1200 = dist1101;
                dir1200 = dir1101;
            }
            dist1200 += 1 + (rc.senseMapInfo(loc1200).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0012)) { // check (0, -2)
            if (dist0012 > dist0011) { // from (0, -1)
                dist0012 = dist0011;
                dir0012 = dir0011;
            }

            if (dist0012 > dist1111) { // from (-1, -1)
                dist0012 = dist1111;
                dir0012 = dir1111;
            }

            if (dist0012 > dist0111) { // from (1, -1)
                dist0012 = dist0111;
                dir0012 = dir0111;
            }
            dist0012 += 1 + (rc.senseMapInfo(loc0012).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0002)) { // check (0, 2)
            if (dist0002 > dist0001) { // from (0, 1)
                dist0002 = dist0001;
                dir0002 = dir0001;
            }

            if (dist0002 > dist1101) { // from (-1, 1)
                dist0002 = dist1101;
                dir0002 = dir1101;
            }

            if (dist0002 > dist0101) { // from (1, 1)
                dist0002 = dist0101;
                dir0002 = dir0101;
            }
            dist0002 += 1 + (rc.senseMapInfo(loc0002).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0200)) { // check (2, 0)
            if (dist0200 > dist0100) { // from (1, 0)
                dist0200 = dist0100;
                dir0200 = dir0100;
            }

            if (dist0200 > dist0111) { // from (1, -1)
                dist0200 = dist0111;
                dir0200 = dir0111;
            }

            if (dist0200 > dist0101) { // from (1, 1)
                dist0200 = dist0101;
                dir0200 = dir0101;
            }
            dist0200 += 1 + (rc.senseMapInfo(loc0200).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1211)) { // check (-2, -1)
            if (dist1211 > dist1100) { // from (-1, 0)
                dist1211 = dist1100;
                dir1211 = dir1100;
            }

            if (dist1211 > dist1111) { // from (-1, -1)
                dist1211 = dist1111;
                dir1211 = dir1111;
            }

            if (dist1211 > dist1200) { // from (-2, 0)
                dist1211 = dist1200;
                dir1211 = dir1200;
            }
            dist1211 += 1 + (rc.senseMapInfo(loc1211).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1201)) { // check (-2, 1)
            if (dist1201 > dist1100) { // from (-1, 0)
                dist1201 = dist1100;
                dir1201 = dir1100;
            }

            if (dist1201 > dist1101) { // from (-1, 1)
                dist1201 = dist1101;
                dir1201 = dir1101;
            }

            if (dist1201 > dist1200) { // from (-2, 0)
                dist1201 = dist1200;
                dir1201 = dir1200;
            }
            dist1201 += 1 + (rc.senseMapInfo(loc1201).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1112)) { // check (-1, -2)
            if (dist1112 > dist0011) { // from (0, -1)
                dist1112 = dist0011;
                dir1112 = dir0011;
            }

            if (dist1112 > dist1111) { // from (-1, -1)
                dist1112 = dist1111;
                dir1112 = dir1111;
            }

            if (dist1112 > dist0012) { // from (0, -2)
                dist1112 = dist0012;
                dir1112 = dir0012;
            }

            if (dist1112 > dist1211) { // from (-2, -1)
                dist1112 = dist1211;
                dir1112 = dir1211;
            }
            dist1112 += 1 + (rc.senseMapInfo(loc1112).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1102)) { // check (-1, 2)
            if (dist1102 > dist0001) { // from (0, 1)
                dist1102 = dist0001;
                dir1102 = dir0001;
            }

            if (dist1102 > dist1101) { // from (-1, 1)
                dist1102 = dist1101;
                dir1102 = dir1101;
            }

            if (dist1102 > dist0002) { // from (0, 2)
                dist1102 = dist0002;
                dir1102 = dir0002;
            }

            if (dist1102 > dist1201) { // from (-2, 1)
                dist1102 = dist1201;
                dir1102 = dir1201;
            }
            dist1102 += 1 + (rc.senseMapInfo(loc1102).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0112)) { // check (1, -2)
            if (dist0112 > dist0011) { // from (0, -1)
                dist0112 = dist0011;
                dir0112 = dir0011;
            }

            if (dist0112 > dist0111) { // from (1, -1)
                dist0112 = dist0111;
                dir0112 = dir0111;
            }

            if (dist0112 > dist0012) { // from (0, -2)
                dist0112 = dist0012;
                dir0112 = dir0012;
            }
            dist0112 += 1 + (rc.senseMapInfo(loc0112).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0102)) { // check (1, 2)
            if (dist0102 > dist0001) { // from (0, 1)
                dist0102 = dist0001;
                dir0102 = dir0001;
            }

            if (dist0102 > dist0101) { // from (1, 1)
                dist0102 = dist0101;
                dir0102 = dir0101;
            }

            if (dist0102 > dist0002) { // from (0, 2)
                dist0102 = dist0002;
                dir0102 = dir0002;
            }
            dist0102 += 1 + (rc.senseMapInfo(loc0102).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0211)) { // check (2, -1)
            if (dist0211 > dist0100) { // from (1, 0)
                dist0211 = dist0100;
                dir0211 = dir0100;
            }

            if (dist0211 > dist0111) { // from (1, -1)
                dist0211 = dist0111;
                dir0211 = dir0111;
            }

            if (dist0211 > dist0200) { // from (2, 0)
                dist0211 = dist0200;
                dir0211 = dir0200;
            }

            if (dist0211 > dist0112) { // from (1, -2)
                dist0211 = dist0112;
                dir0211 = dir0112;
            }
            dist0211 += 1 + (rc.senseMapInfo(loc0211).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0201)) { // check (2, 1)
            if (dist0201 > dist0100) { // from (1, 0)
                dist0201 = dist0100;
                dir0201 = dir0100;
            }

            if (dist0201 > dist0101) { // from (1, 1)
                dist0201 = dist0101;
                dir0201 = dir0101;
            }

            if (dist0201 > dist0200) { // from (2, 0)
                dist0201 = dist0200;
                dir0201 = dir0200;
            }

            if (dist0201 > dist0102) { // from (1, 2)
                dist0201 = dist0102;
                dir0201 = dir0102;
            }
            dist0201 += 1 + (rc.senseMapInfo(loc0201).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1212)) { // check (-2, -2)
            if (dist1212 > dist1111) { // from (-1, -1)
                dist1212 = dist1111;
                dir1212 = dir1111;
            }

            if (dist1212 > dist1211) { // from (-2, -1)
                dist1212 = dist1211;
                dir1212 = dir1211;
            }

            if (dist1212 > dist1112) { // from (-1, -2)
                dist1212 = dist1112;
                dir1212 = dir1112;
            }
            dist1212 += 1 + (rc.senseMapInfo(loc1212).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1202)) { // check (-2, 2)
            if (dist1202 > dist1101) { // from (-1, 1)
                dist1202 = dist1101;
                dir1202 = dir1101;
            }

            if (dist1202 > dist1201) { // from (-2, 1)
                dist1202 = dist1201;
                dir1202 = dir1201;
            }

            if (dist1202 > dist1102) { // from (-1, 2)
                dist1202 = dist1102;
                dir1202 = dir1102;
            }
            dist1202 += 1 + (rc.senseMapInfo(loc1202).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0212)) { // check (2, -2)
            if (dist0212 > dist0111) { // from (1, -1)
                dist0212 = dist0111;
                dir0212 = dir0111;
            }

            if (dist0212 > dist0112) { // from (1, -2)
                dist0212 = dist0112;
                dir0212 = dir0112;
            }

            if (dist0212 > dist0211) { // from (2, -1)
                dist0212 = dist0211;
                dir0212 = dir0211;
            }
            dist0212 += 1 + (rc.senseMapInfo(loc0212).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0202)) { // check (2, 2)
            if (dist0202 > dist0101) { // from (1, 1)
                dist0202 = dist0101;
                dir0202 = dir0101;
            }

            if (dist0202 > dist0102) { // from (1, 2)
                dist0202 = dist0102;
                dir0202 = dir0102;
            }

            if (dist0202 > dist0201) { // from (2, 1)
                dist0202 = dist0201;
                dir0202 = dir0201;
            }
            dist0202 += 1 + (rc.senseMapInfo(loc0202).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1300)) { // check (-3, 0)
            if (dist1300 > dist1200) { // from (-2, 0)
                dist1300 = dist1200;
                dir1300 = dir1200;
            }

            if (dist1300 > dist1211) { // from (-2, -1)
                dist1300 = dist1211;
                dir1300 = dir1211;
            }

            if (dist1300 > dist1201) { // from (-2, 1)
                dist1300 = dist1201;
                dir1300 = dir1201;
            }
            dist1300 += 1 + (rc.senseMapInfo(loc1300).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0013)) { // check (0, -3)
            if (dist0013 > dist0012) { // from (0, -2)
                dist0013 = dist0012;
                dir0013 = dir0012;
            }

            if (dist0013 > dist1112) { // from (-1, -2)
                dist0013 = dist1112;
                dir0013 = dir1112;
            }

            if (dist0013 > dist0112) { // from (1, -2)
                dist0013 = dist0112;
                dir0013 = dir0112;
            }
            dist0013 += 1 + (rc.senseMapInfo(loc0013).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0003)) { // check (0, 3)
            if (dist0003 > dist0002) { // from (0, 2)
                dist0003 = dist0002;
                dir0003 = dir0002;
            }

            if (dist0003 > dist1102) { // from (-1, 2)
                dist0003 = dist1102;
                dir0003 = dir1102;
            }

            if (dist0003 > dist0102) { // from (1, 2)
                dist0003 = dist0102;
                dir0003 = dir0102;
            }
            dist0003 += 1 + (rc.senseMapInfo(loc0003).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0300)) { // check (3, 0)
            if (dist0300 > dist0200) { // from (2, 0)
                dist0300 = dist0200;
                dir0300 = dir0200;
            }

            if (dist0300 > dist0211) { // from (2, -1)
                dist0300 = dist0211;
                dir0300 = dir0211;
            }

            if (dist0300 > dist0201) { // from (2, 1)
                dist0300 = dist0201;
                dir0300 = dir0201;
            }
            dist0300 += 1 + (rc.senseMapInfo(loc0300).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1311)) { // check (-3, -1)
            if (dist1311 > dist1200) { // from (-2, 0)
                dist1311 = dist1200;
                dir1311 = dir1200;
            }

            if (dist1311 > dist1211) { // from (-2, -1)
                dist1311 = dist1211;
                dir1311 = dir1211;
            }

            if (dist1311 > dist1212) { // from (-2, -2)
                dist1311 = dist1212;
                dir1311 = dir1212;
            }

            if (dist1311 > dist1300) { // from (-3, 0)
                dist1311 = dist1300;
                dir1311 = dir1300;
            }
            dist1311 += 1 + (rc.senseMapInfo(loc1311).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1301)) { // check (-3, 1)
            if (dist1301 > dist1200) { // from (-2, 0)
                dist1301 = dist1200;
                dir1301 = dir1200;
            }

            if (dist1301 > dist1201) { // from (-2, 1)
                dist1301 = dist1201;
                dir1301 = dir1201;
            }

            if (dist1301 > dist1202) { // from (-2, 2)
                dist1301 = dist1202;
                dir1301 = dir1202;
            }

            if (dist1301 > dist1300) { // from (-3, 0)
                dist1301 = dist1300;
                dir1301 = dir1300;
            }
            dist1301 += 1 + (rc.senseMapInfo(loc1301).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1113)) { // check (-1, -3)
            if (dist1113 > dist0012) { // from (0, -2)
                dist1113 = dist0012;
                dir1113 = dir0012;
            }

            if (dist1113 > dist1112) { // from (-1, -2)
                dist1113 = dist1112;
                dir1113 = dir1112;
            }

            if (dist1113 > dist1212) { // from (-2, -2)
                dist1113 = dist1212;
                dir1113 = dir1212;
            }

            if (dist1113 > dist0013) { // from (0, -3)
                dist1113 = dist0013;
                dir1113 = dir0013;
            }
            dist1113 += 1 + (rc.senseMapInfo(loc1113).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1103)) { // check (-1, 3)
            if (dist1103 > dist0002) { // from (0, 2)
                dist1103 = dist0002;
                dir1103 = dir0002;
            }

            if (dist1103 > dist1102) { // from (-1, 2)
                dist1103 = dist1102;
                dir1103 = dir1102;
            }

            if (dist1103 > dist1202) { // from (-2, 2)
                dist1103 = dist1202;
                dir1103 = dir1202;
            }

            if (dist1103 > dist0003) { // from (0, 3)
                dist1103 = dist0003;
                dir1103 = dir0003;
            }
            dist1103 += 1 + (rc.senseMapInfo(loc1103).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0113)) { // check (1, -3)
            if (dist0113 > dist0012) { // from (0, -2)
                dist0113 = dist0012;
                dir0113 = dir0012;
            }

            if (dist0113 > dist0112) { // from (1, -2)
                dist0113 = dist0112;
                dir0113 = dir0112;
            }

            if (dist0113 > dist0212) { // from (2, -2)
                dist0113 = dist0212;
                dir0113 = dir0212;
            }

            if (dist0113 > dist0013) { // from (0, -3)
                dist0113 = dist0013;
                dir0113 = dir0013;
            }
            dist0113 += 1 + (rc.senseMapInfo(loc0113).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0103)) { // check (1, 3)
            if (dist0103 > dist0002) { // from (0, 2)
                dist0103 = dist0002;
                dir0103 = dir0002;
            }

            if (dist0103 > dist0102) { // from (1, 2)
                dist0103 = dist0102;
                dir0103 = dir0102;
            }

            if (dist0103 > dist0202) { // from (2, 2)
                dist0103 = dist0202;
                dir0103 = dir0202;
            }

            if (dist0103 > dist0003) { // from (0, 3)
                dist0103 = dist0003;
                dir0103 = dir0003;
            }
            dist0103 += 1 + (rc.senseMapInfo(loc0103).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0311)) { // check (3, -1)
            if (dist0311 > dist0200) { // from (2, 0)
                dist0311 = dist0200;
                dir0311 = dir0200;
            }

            if (dist0311 > dist0211) { // from (2, -1)
                dist0311 = dist0211;
                dir0311 = dir0211;
            }

            if (dist0311 > dist0212) { // from (2, -2)
                dist0311 = dist0212;
                dir0311 = dir0212;
            }

            if (dist0311 > dist0300) { // from (3, 0)
                dist0311 = dist0300;
                dir0311 = dir0300;
            }
            dist0311 += 1 + (rc.senseMapInfo(loc0311).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0301)) { // check (3, 1)
            if (dist0301 > dist0200) { // from (2, 0)
                dist0301 = dist0200;
                dir0301 = dir0200;
            }

            if (dist0301 > dist0201) { // from (2, 1)
                dist0301 = dist0201;
                dir0301 = dir0201;
            }

            if (dist0301 > dist0202) { // from (2, 2)
                dist0301 = dist0202;
                dir0301 = dir0202;
            }

            if (dist0301 > dist0300) { // from (3, 0)
                dist0301 = dist0300;
                dir0301 = dir0300;
            }
            dist0301 += 1 + (rc.senseMapInfo(loc0301).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1312)) { // check (-3, -2)
            if (dist1312 > dist1211) { // from (-2, -1)
                dist1312 = dist1211;
                dir1312 = dir1211;
            }

            if (dist1312 > dist1212) { // from (-2, -2)
                dist1312 = dist1212;
                dir1312 = dir1212;
            }

            if (dist1312 > dist1311) { // from (-3, -1)
                dist1312 = dist1311;
                dir1312 = dir1311;
            }
            dist1312 += 1 + (rc.senseMapInfo(loc1312).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1302)) { // check (-3, 2)
            if (dist1302 > dist1201) { // from (-2, 1)
                dist1302 = dist1201;
                dir1302 = dir1201;
            }

            if (dist1302 > dist1202) { // from (-2, 2)
                dist1302 = dist1202;
                dir1302 = dir1202;
            }

            if (dist1302 > dist1301) { // from (-3, 1)
                dist1302 = dist1301;
                dir1302 = dir1301;
            }
            dist1302 += 1 + (rc.senseMapInfo(loc1302).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1213)) { // check (-2, -3)
            if (dist1213 > dist1112) { // from (-1, -2)
                dist1213 = dist1112;
                dir1213 = dir1112;
            }

            if (dist1213 > dist1212) { // from (-2, -2)
                dist1213 = dist1212;
                dir1213 = dir1212;
            }

            if (dist1213 > dist1113) { // from (-1, -3)
                dist1213 = dist1113;
                dir1213 = dir1113;
            }

            if (dist1213 > dist1312) { // from (-3, -2)
                dist1213 = dist1312;
                dir1213 = dir1312;
            }
            dist1213 += 1 + (rc.senseMapInfo(loc1213).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1203)) { // check (-2, 3)
            if (dist1203 > dist1102) { // from (-1, 2)
                dist1203 = dist1102;
                dir1203 = dir1102;
            }

            if (dist1203 > dist1202) { // from (-2, 2)
                dist1203 = dist1202;
                dir1203 = dir1202;
            }

            if (dist1203 > dist1103) { // from (-1, 3)
                dist1203 = dist1103;
                dir1203 = dir1103;
            }

            if (dist1203 > dist1302) { // from (-3, 2)
                dist1203 = dist1302;
                dir1203 = dir1302;
            }
            dist1203 += 1 + (rc.senseMapInfo(loc1203).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0213)) { // check (2, -3)
            if (dist0213 > dist0112) { // from (1, -2)
                dist0213 = dist0112;
                dir0213 = dir0112;
            }

            if (dist0213 > dist0212) { // from (2, -2)
                dist0213 = dist0212;
                dir0213 = dir0212;
            }

            if (dist0213 > dist0113) { // from (1, -3)
                dist0213 = dist0113;
                dir0213 = dir0113;
            }
            dist0213 += 1 + (rc.senseMapInfo(loc0213).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0203)) { // check (2, 3)
            if (dist0203 > dist0102) { // from (1, 2)
                dist0203 = dist0102;
                dir0203 = dir0102;
            }

            if (dist0203 > dist0202) { // from (2, 2)
                dist0203 = dist0202;
                dir0203 = dir0202;
            }

            if (dist0203 > dist0103) { // from (1, 3)
                dist0203 = dist0103;
                dir0203 = dir0103;
            }
            dist0203 += 1 + (rc.senseMapInfo(loc0203).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0312)) { // check (3, -2)
            if (dist0312 > dist0211) { // from (2, -1)
                dist0312 = dist0211;
                dir0312 = dir0211;
            }

            if (dist0312 > dist0212) { // from (2, -2)
                dist0312 = dist0212;
                dir0312 = dir0212;
            }

            if (dist0312 > dist0311) { // from (3, -1)
                dist0312 = dist0311;
                dir0312 = dir0311;
            }

            if (dist0312 > dist0213) { // from (2, -3)
                dist0312 = dist0213;
                dir0312 = dir0213;
            }
            dist0312 += 1 + (rc.senseMapInfo(loc0312).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0302)) { // check (3, 2)
            if (dist0302 > dist0201) { // from (2, 1)
                dist0302 = dist0201;
                dir0302 = dir0201;
            }

            if (dist0302 > dist0202) { // from (2, 2)
                dist0302 = dist0202;
                dir0302 = dir0202;
            }

            if (dist0302 > dist0301) { // from (3, 1)
                dist0302 = dist0301;
                dir0302 = dir0301;
            }

            if (dist0302 > dist0203) { // from (2, 3)
                dist0302 = dist0203;
                dir0302 = dir0203;
            }
            dist0302 += 1 + (rc.senseMapInfo(loc0302).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1400)) { // check (-4, 0)
            if (dist1400 > dist1300) { // from (-3, 0)
                dist1400 = dist1300;
                dir1400 = dir1300;
            }

            if (dist1400 > dist1311) { // from (-3, -1)
                dist1400 = dist1311;
                dir1400 = dir1311;
            }

            if (dist1400 > dist1301) { // from (-3, 1)
                dist1400 = dist1301;
                dir1400 = dir1301;
            }
            dist1400 += 1 + (rc.senseMapInfo(loc1400).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0014)) { // check (0, -4)
            if (dist0014 > dist0013) { // from (0, -3)
                dist0014 = dist0013;
                dir0014 = dir0013;
            }

            if (dist0014 > dist1113) { // from (-1, -3)
                dist0014 = dist1113;
                dir0014 = dir1113;
            }

            if (dist0014 > dist0113) { // from (1, -3)
                dist0014 = dist0113;
                dir0014 = dir0113;
            }
            dist0014 += 1 + (rc.senseMapInfo(loc0014).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0004)) { // check (0, 4)
            if (dist0004 > dist0003) { // from (0, 3)
                dist0004 = dist0003;
                dir0004 = dir0003;
            }

            if (dist0004 > dist1103) { // from (-1, 3)
                dist0004 = dist1103;
                dir0004 = dir1103;
            }

            if (dist0004 > dist0103) { // from (1, 3)
                dist0004 = dist0103;
                dir0004 = dir0103;
            }
            dist0004 += 1 + (rc.senseMapInfo(loc0004).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0400)) { // check (4, 0)
            if (dist0400 > dist0300) { // from (3, 0)
                dist0400 = dist0300;
                dir0400 = dir0300;
            }

            if (dist0400 > dist0311) { // from (3, -1)
                dist0400 = dist0311;
                dir0400 = dir0311;
            }

            if (dist0400 > dist0301) { // from (3, 1)
                dist0400 = dist0301;
                dir0400 = dir0301;
            }
            dist0400 += 1 + (rc.senseMapInfo(loc0400).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1411)) { // check (-4, -1)
            if (dist1411 > dist1300) { // from (-3, 0)
                dist1411 = dist1300;
                dir1411 = dir1300;
            }

            if (dist1411 > dist1311) { // from (-3, -1)
                dist1411 = dist1311;
                dir1411 = dir1311;
            }

            if (dist1411 > dist1312) { // from (-3, -2)
                dist1411 = dist1312;
                dir1411 = dir1312;
            }

            if (dist1411 > dist1400) { // from (-4, 0)
                dist1411 = dist1400;
                dir1411 = dir1400;
            }
            dist1411 += 1 + (rc.senseMapInfo(loc1411).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1401)) { // check (-4, 1)
            if (dist1401 > dist1300) { // from (-3, 0)
                dist1401 = dist1300;
                dir1401 = dir1300;
            }

            if (dist1401 > dist1301) { // from (-3, 1)
                dist1401 = dist1301;
                dir1401 = dir1301;
            }

            if (dist1401 > dist1302) { // from (-3, 2)
                dist1401 = dist1302;
                dir1401 = dir1302;
            }

            if (dist1401 > dist1400) { // from (-4, 0)
                dist1401 = dist1400;
                dir1401 = dir1400;
            }
            dist1401 += 1 + (rc.senseMapInfo(loc1401).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1114)) { // check (-1, -4)
            if (dist1114 > dist0013) { // from (0, -3)
                dist1114 = dist0013;
                dir1114 = dir0013;
            }

            if (dist1114 > dist1113) { // from (-1, -3)
                dist1114 = dist1113;
                dir1114 = dir1113;
            }

            if (dist1114 > dist1213) { // from (-2, -3)
                dist1114 = dist1213;
                dir1114 = dir1213;
            }

            if (dist1114 > dist0014) { // from (0, -4)
                dist1114 = dist0014;
                dir1114 = dir0014;
            }
            dist1114 += 1 + (rc.senseMapInfo(loc1114).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1104)) { // check (-1, 4)
            if (dist1104 > dist0003) { // from (0, 3)
                dist1104 = dist0003;
                dir1104 = dir0003;
            }

            if (dist1104 > dist1103) { // from (-1, 3)
                dist1104 = dist1103;
                dir1104 = dir1103;
            }

            if (dist1104 > dist1203) { // from (-2, 3)
                dist1104 = dist1203;
                dir1104 = dir1203;
            }

            if (dist1104 > dist0004) { // from (0, 4)
                dist1104 = dist0004;
                dir1104 = dir0004;
            }
            dist1104 += 1 + (rc.senseMapInfo(loc1104).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0114)) { // check (1, -4)
            if (dist0114 > dist0013) { // from (0, -3)
                dist0114 = dist0013;
                dir0114 = dir0013;
            }

            if (dist0114 > dist0113) { // from (1, -3)
                dist0114 = dist0113;
                dir0114 = dir0113;
            }

            if (dist0114 > dist0213) { // from (2, -3)
                dist0114 = dist0213;
                dir0114 = dir0213;
            }

            if (dist0114 > dist0014) { // from (0, -4)
                dist0114 = dist0014;
                dir0114 = dir0014;
            }
            dist0114 += 1 + (rc.senseMapInfo(loc0114).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0104)) { // check (1, 4)
            if (dist0104 > dist0003) { // from (0, 3)
                dist0104 = dist0003;
                dir0104 = dir0003;
            }

            if (dist0104 > dist0103) { // from (1, 3)
                dist0104 = dist0103;
                dir0104 = dir0103;
            }

            if (dist0104 > dist0203) { // from (2, 3)
                dist0104 = dist0203;
                dir0104 = dir0203;
            }

            if (dist0104 > dist0004) { // from (0, 4)
                dist0104 = dist0004;
                dir0104 = dir0004;
            }
            dist0104 += 1 + (rc.senseMapInfo(loc0104).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0411)) { // check (4, -1)
            if (dist0411 > dist0300) { // from (3, 0)
                dist0411 = dist0300;
                dir0411 = dir0300;
            }

            if (dist0411 > dist0311) { // from (3, -1)
                dist0411 = dist0311;
                dir0411 = dir0311;
            }

            if (dist0411 > dist0312) { // from (3, -2)
                dist0411 = dist0312;
                dir0411 = dir0312;
            }

            if (dist0411 > dist0400) { // from (4, 0)
                dist0411 = dist0400;
                dir0411 = dir0400;
            }
            dist0411 += 1 + (rc.senseMapInfo(loc0411).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0401)) { // check (4, 1)
            if (dist0401 > dist0300) { // from (3, 0)
                dist0401 = dist0300;
                dir0401 = dir0300;
            }

            if (dist0401 > dist0301) { // from (3, 1)
                dist0401 = dist0301;
                dir0401 = dir0301;
            }

            if (dist0401 > dist0302) { // from (3, 2)
                dist0401 = dist0302;
                dir0401 = dir0302;
            }

            if (dist0401 > dist0400) { // from (4, 0)
                dist0401 = dist0400;
                dir0401 = dir0400;
            }
            dist0401 += 1 + (rc.senseMapInfo(loc0401).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1313)) { // check (-3, -3)
            if (dist1313 > dist1212) { // from (-2, -2)
                dist1313 = dist1212;
                dir1313 = dir1212;
            }

            if (dist1313 > dist1312) { // from (-3, -2)
                dist1313 = dist1312;
                dir1313 = dir1312;
            }

            if (dist1313 > dist1213) { // from (-2, -3)
                dist1313 = dist1213;
                dir1313 = dir1213;
            }
            dist1313 += 1 + (rc.senseMapInfo(loc1313).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1303)) { // check (-3, 3)
            if (dist1303 > dist1202) { // from (-2, 2)
                dist1303 = dist1202;
                dir1303 = dir1202;
            }

            if (dist1303 > dist1302) { // from (-3, 2)
                dist1303 = dist1302;
                dir1303 = dir1302;
            }

            if (dist1303 > dist1203) { // from (-2, 3)
                dist1303 = dist1203;
                dir1303 = dir1203;
            }
            dist1303 += 1 + (rc.senseMapInfo(loc1303).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0313)) { // check (3, -3)
            if (dist0313 > dist0212) { // from (2, -2)
                dist0313 = dist0212;
                dir0313 = dir0212;
            }

            if (dist0313 > dist0213) { // from (2, -3)
                dist0313 = dist0213;
                dir0313 = dir0213;
            }

            if (dist0313 > dist0312) { // from (3, -2)
                dist0313 = dist0312;
                dir0313 = dir0312;
            }
            dist0313 += 1 + (rc.senseMapInfo(loc0313).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0303)) { // check (3, 3)
            if (dist0303 > dist0202) { // from (2, 2)
                dist0303 = dist0202;
                dir0303 = dir0202;
            }

            if (dist0303 > dist0203) { // from (2, 3)
                dist0303 = dist0203;
                dir0303 = dir0203;
            }

            if (dist0303 > dist0302) { // from (3, 2)
                dist0303 = dist0302;
                dir0303 = dir0302;
            }
            dist0303 += 1 + (rc.senseMapInfo(loc0303).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1412)) { // check (-4, -2)
            if (dist1412 > dist1311) { // from (-3, -1)
                dist1412 = dist1311;
                dir1412 = dir1311;
            }

            if (dist1412 > dist1312) { // from (-3, -2)
                dist1412 = dist1312;
                dir1412 = dir1312;
            }

            if (dist1412 > dist1411) { // from (-4, -1)
                dist1412 = dist1411;
                dir1412 = dir1411;
            }

            if (dist1412 > dist1313) { // from (-3, -3)
                dist1412 = dist1313;
                dir1412 = dir1313;
            }
            dist1412 += 1 + (rc.senseMapInfo(loc1412).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1402)) { // check (-4, 2)
            if (dist1402 > dist1301) { // from (-3, 1)
                dist1402 = dist1301;
                dir1402 = dir1301;
            }

            if (dist1402 > dist1302) { // from (-3, 2)
                dist1402 = dist1302;
                dir1402 = dir1302;
            }

            if (dist1402 > dist1401) { // from (-4, 1)
                dist1402 = dist1401;
                dir1402 = dir1401;
            }

            if (dist1402 > dist1303) { // from (-3, 3)
                dist1402 = dist1303;
                dir1402 = dir1303;
            }
            dist1402 += 1 + (rc.senseMapInfo(loc1402).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1214)) { // check (-2, -4)
            if (dist1214 > dist1113) { // from (-1, -3)
                dist1214 = dist1113;
                dir1214 = dir1113;
            }

            if (dist1214 > dist1213) { // from (-2, -3)
                dist1214 = dist1213;
                dir1214 = dir1213;
            }

            if (dist1214 > dist1114) { // from (-1, -4)
                dist1214 = dist1114;
                dir1214 = dir1114;
            }

            if (dist1214 > dist1313) { // from (-3, -3)
                dist1214 = dist1313;
                dir1214 = dir1313;
            }
            dist1214 += 1 + (rc.senseMapInfo(loc1214).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1204)) { // check (-2, 4)
            if (dist1204 > dist1103) { // from (-1, 3)
                dist1204 = dist1103;
                dir1204 = dir1103;
            }

            if (dist1204 > dist1203) { // from (-2, 3)
                dist1204 = dist1203;
                dir1204 = dir1203;
            }

            if (dist1204 > dist1104) { // from (-1, 4)
                dist1204 = dist1104;
                dir1204 = dir1104;
            }

            if (dist1204 > dist1303) { // from (-3, 3)
                dist1204 = dist1303;
                dir1204 = dir1303;
            }
            dist1204 += 1 + (rc.senseMapInfo(loc1204).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0214)) { // check (2, -4)
            if (dist0214 > dist0113) { // from (1, -3)
                dist0214 = dist0113;
                dir0214 = dir0113;
            }

            if (dist0214 > dist0213) { // from (2, -3)
                dist0214 = dist0213;
                dir0214 = dir0213;
            }

            if (dist0214 > dist0114) { // from (1, -4)
                dist0214 = dist0114;
                dir0214 = dir0114;
            }

            if (dist0214 > dist0313) { // from (3, -3)
                dist0214 = dist0313;
                dir0214 = dir0313;
            }
            dist0214 += 1 + (rc.senseMapInfo(loc0214).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0204)) { // check (2, 4)
            if (dist0204 > dist0103) { // from (1, 3)
                dist0204 = dist0103;
                dir0204 = dir0103;
            }

            if (dist0204 > dist0203) { // from (2, 3)
                dist0204 = dist0203;
                dir0204 = dir0203;
            }

            if (dist0204 > dist0104) { // from (1, 4)
                dist0204 = dist0104;
                dir0204 = dir0104;
            }

            if (dist0204 > dist0303) { // from (3, 3)
                dist0204 = dist0303;
                dir0204 = dir0303;
            }
            dist0204 += 1 + (rc.senseMapInfo(loc0204).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0412)) { // check (4, -2)
            if (dist0412 > dist0311) { // from (3, -1)
                dist0412 = dist0311;
                dir0412 = dir0311;
            }

            if (dist0412 > dist0312) { // from (3, -2)
                dist0412 = dist0312;
                dir0412 = dir0312;
            }

            if (dist0412 > dist0411) { // from (4, -1)
                dist0412 = dist0411;
                dir0412 = dir0411;
            }

            if (dist0412 > dist0313) { // from (3, -3)
                dist0412 = dist0313;
                dir0412 = dir0313;
            }
            dist0412 += 1 + (rc.senseMapInfo(loc0412).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0402)) { // check (4, 2)
            if (dist0402 > dist0301) { // from (3, 1)
                dist0402 = dist0301;
                dir0402 = dir0301;
            }

            if (dist0402 > dist0302) { // from (3, 2)
                dist0402 = dist0302;
                dir0402 = dir0302;
            }

            if (dist0402 > dist0401) { // from (4, 1)
                dist0402 = dist0401;
                dir0402 = dir0401;
            }

            if (dist0402 > dist0303) { // from (3, 3)
                dist0402 = dist0303;
                dir0402 = dir0303;
            }
            dist0402 += 1 + (rc.senseMapInfo(loc0402).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1500)) { // check (-5, 0)
            if (dist1500 > dist1400) { // from (-4, 0)
                dist1500 = dist1400;
                dir1500 = dir1400;
            }

            if (dist1500 > dist1411) { // from (-4, -1)
                dist1500 = dist1411;
                dir1500 = dir1411;
            }

            if (dist1500 > dist1401) { // from (-4, 1)
                dist1500 = dist1401;
                dir1500 = dir1401;
            }
            dist1500 += 1 + (rc.senseMapInfo(loc1500).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1413)) { // check (-4, -3)
            if (dist1413 > dist1312) { // from (-3, -2)
                dist1413 = dist1312;
                dir1413 = dir1312;
            }

            if (dist1413 > dist1313) { // from (-3, -3)
                dist1413 = dist1313;
                dir1413 = dir1313;
            }

            if (dist1413 > dist1412) { // from (-4, -2)
                dist1413 = dist1412;
                dir1413 = dir1412;
            }
            dist1413 += 1 + (rc.senseMapInfo(loc1413).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1403)) { // check (-4, 3)
            if (dist1403 > dist1302) { // from (-3, 2)
                dist1403 = dist1302;
                dir1403 = dir1302;
            }

            if (dist1403 > dist1303) { // from (-3, 3)
                dist1403 = dist1303;
                dir1403 = dir1303;
            }

            if (dist1403 > dist1402) { // from (-4, 2)
                dist1403 = dist1402;
                dir1403 = dir1402;
            }
            dist1403 += 1 + (rc.senseMapInfo(loc1403).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1314)) { // check (-3, -4)
            if (dist1314 > dist1213) { // from (-2, -3)
                dist1314 = dist1213;
                dir1314 = dir1213;
            }

            if (dist1314 > dist1313) { // from (-3, -3)
                dist1314 = dist1313;
                dir1314 = dir1313;
            }

            if (dist1314 > dist1214) { // from (-2, -4)
                dist1314 = dist1214;
                dir1314 = dir1214;
            }

            if (dist1314 > dist1413) { // from (-4, -3)
                dist1314 = dist1413;
                dir1314 = dir1413;
            }
            dist1314 += 1 + (rc.senseMapInfo(loc1314).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1304)) { // check (-3, 4)
            if (dist1304 > dist1203) { // from (-2, 3)
                dist1304 = dist1203;
                dir1304 = dir1203;
            }

            if (dist1304 > dist1303) { // from (-3, 3)
                dist1304 = dist1303;
                dir1304 = dir1303;
            }

            if (dist1304 > dist1204) { // from (-2, 4)
                dist1304 = dist1204;
                dir1304 = dir1204;
            }

            if (dist1304 > dist1403) { // from (-4, 3)
                dist1304 = dist1403;
                dir1304 = dir1403;
            }
            dist1304 += 1 + (rc.senseMapInfo(loc1304).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0015)) { // check (0, -5)
            if (dist0015 > dist0014) { // from (0, -4)
                dist0015 = dist0014;
                dir0015 = dir0014;
            }

            if (dist0015 > dist1114) { // from (-1, -4)
                dist0015 = dist1114;
                dir0015 = dir1114;
            }

            if (dist0015 > dist0114) { // from (1, -4)
                dist0015 = dist0114;
                dir0015 = dir0114;
            }
            dist0015 += 1 + (rc.senseMapInfo(loc0015).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0005)) { // check (0, 5)
            if (dist0005 > dist0004) { // from (0, 4)
                dist0005 = dist0004;
                dir0005 = dir0004;
            }

            if (dist0005 > dist1104) { // from (-1, 4)
                dist0005 = dist1104;
                dir0005 = dir1104;
            }

            if (dist0005 > dist0104) { // from (1, 4)
                dist0005 = dist0104;
                dir0005 = dir0104;
            }
            dist0005 += 1 + (rc.senseMapInfo(loc0005).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0314)) { // check (3, -4)
            if (dist0314 > dist0213) { // from (2, -3)
                dist0314 = dist0213;
                dir0314 = dir0213;
            }

            if (dist0314 > dist0313) { // from (3, -3)
                dist0314 = dist0313;
                dir0314 = dir0313;
            }

            if (dist0314 > dist0214) { // from (2, -4)
                dist0314 = dist0214;
                dir0314 = dir0214;
            }
            dist0314 += 1 + (rc.senseMapInfo(loc0314).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0304)) { // check (3, 4)
            if (dist0304 > dist0203) { // from (2, 3)
                dist0304 = dist0203;
                dir0304 = dir0203;
            }

            if (dist0304 > dist0303) { // from (3, 3)
                dist0304 = dist0303;
                dir0304 = dir0303;
            }

            if (dist0304 > dist0204) { // from (2, 4)
                dist0304 = dist0204;
                dir0304 = dir0204;
            }
            dist0304 += 1 + (rc.senseMapInfo(loc0304).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0413)) { // check (4, -3)
            if (dist0413 > dist0312) { // from (3, -2)
                dist0413 = dist0312;
                dir0413 = dir0312;
            }

            if (dist0413 > dist0313) { // from (3, -3)
                dist0413 = dist0313;
                dir0413 = dir0313;
            }

            if (dist0413 > dist0412) { // from (4, -2)
                dist0413 = dist0412;
                dir0413 = dir0412;
            }

            if (dist0413 > dist0314) { // from (3, -4)
                dist0413 = dist0314;
                dir0413 = dir0314;
            }
            dist0413 += 1 + (rc.senseMapInfo(loc0413).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0403)) { // check (4, 3)
            if (dist0403 > dist0302) { // from (3, 2)
                dist0403 = dist0302;
                dir0403 = dir0302;
            }

            if (dist0403 > dist0303) { // from (3, 3)
                dist0403 = dist0303;
                dir0403 = dir0303;
            }

            if (dist0403 > dist0402) { // from (4, 2)
                dist0403 = dist0402;
                dir0403 = dir0402;
            }

            if (dist0403 > dist0304) { // from (3, 4)
                dist0403 = dist0304;
                dir0403 = dir0304;
            }
            dist0403 += 1 + (rc.senseMapInfo(loc0403).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0500)) { // check (5, 0)
            if (dist0500 > dist0400) { // from (4, 0)
                dist0500 = dist0400;
                dir0500 = dir0400;
            }

            if (dist0500 > dist0411) { // from (4, -1)
                dist0500 = dist0411;
                dir0500 = dir0411;
            }

            if (dist0500 > dist0401) { // from (4, 1)
                dist0500 = dist0401;
                dir0500 = dir0401;
            }
            dist0500 += 1 + (rc.senseMapInfo(loc0500).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1511)) { // check (-5, -1)
            if (dist1511 > dist1400) { // from (-4, 0)
                dist1511 = dist1400;
                dir1511 = dir1400;
            }

            if (dist1511 > dist1411) { // from (-4, -1)
                dist1511 = dist1411;
                dir1511 = dir1411;
            }

            if (dist1511 > dist1412) { // from (-4, -2)
                dist1511 = dist1412;
                dir1511 = dir1412;
            }

            if (dist1511 > dist1500) { // from (-5, 0)
                dist1511 = dist1500;
                dir1511 = dir1500;
            }
            dist1511 += 1 + (rc.senseMapInfo(loc1511).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1501)) { // check (-5, 1)
            if (dist1501 > dist1400) { // from (-4, 0)
                dist1501 = dist1400;
                dir1501 = dir1400;
            }

            if (dist1501 > dist1401) { // from (-4, 1)
                dist1501 = dist1401;
                dir1501 = dir1401;
            }

            if (dist1501 > dist1402) { // from (-4, 2)
                dist1501 = dist1402;
                dir1501 = dir1402;
            }

            if (dist1501 > dist1500) { // from (-5, 0)
                dist1501 = dist1500;
                dir1501 = dir1500;
            }
            dist1501 += 1 + (rc.senseMapInfo(loc1501).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1115)) { // check (-1, -5)
            if (dist1115 > dist0014) { // from (0, -4)
                dist1115 = dist0014;
                dir1115 = dir0014;
            }

            if (dist1115 > dist1114) { // from (-1, -4)
                dist1115 = dist1114;
                dir1115 = dir1114;
            }

            if (dist1115 > dist1214) { // from (-2, -4)
                dist1115 = dist1214;
                dir1115 = dir1214;
            }

            if (dist1115 > dist0015) { // from (0, -5)
                dist1115 = dist0015;
                dir1115 = dir0015;
            }
            dist1115 += 1 + (rc.senseMapInfo(loc1115).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1105)) { // check (-1, 5)
            if (dist1105 > dist0004) { // from (0, 4)
                dist1105 = dist0004;
                dir1105 = dir0004;
            }

            if (dist1105 > dist1104) { // from (-1, 4)
                dist1105 = dist1104;
                dir1105 = dir1104;
            }

            if (dist1105 > dist1204) { // from (-2, 4)
                dist1105 = dist1204;
                dir1105 = dir1204;
            }

            if (dist1105 > dist0005) { // from (0, 5)
                dist1105 = dist0005;
                dir1105 = dir0005;
            }
            dist1105 += 1 + (rc.senseMapInfo(loc1105).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0115)) { // check (1, -5)
            if (dist0115 > dist0014) { // from (0, -4)
                dist0115 = dist0014;
                dir0115 = dir0014;
            }

            if (dist0115 > dist0114) { // from (1, -4)
                dist0115 = dist0114;
                dir0115 = dir0114;
            }

            if (dist0115 > dist0214) { // from (2, -4)
                dist0115 = dist0214;
                dir0115 = dir0214;
            }

            if (dist0115 > dist0015) { // from (0, -5)
                dist0115 = dist0015;
                dir0115 = dir0015;
            }
            dist0115 += 1 + (rc.senseMapInfo(loc0115).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0105)) { // check (1, 5)
            if (dist0105 > dist0004) { // from (0, 4)
                dist0105 = dist0004;
                dir0105 = dir0004;
            }

            if (dist0105 > dist0104) { // from (1, 4)
                dist0105 = dist0104;
                dir0105 = dir0104;
            }

            if (dist0105 > dist0204) { // from (2, 4)
                dist0105 = dist0204;
                dir0105 = dir0204;
            }

            if (dist0105 > dist0005) { // from (0, 5)
                dist0105 = dist0005;
                dir0105 = dir0005;
            }
            dist0105 += 1 + (rc.senseMapInfo(loc0105).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0511)) { // check (5, -1)
            if (dist0511 > dist0400) { // from (4, 0)
                dist0511 = dist0400;
                dir0511 = dir0400;
            }

            if (dist0511 > dist0411) { // from (4, -1)
                dist0511 = dist0411;
                dir0511 = dir0411;
            }

            if (dist0511 > dist0412) { // from (4, -2)
                dist0511 = dist0412;
                dir0511 = dir0412;
            }

            if (dist0511 > dist0500) { // from (5, 0)
                dist0511 = dist0500;
                dir0511 = dir0500;
            }
            dist0511 += 1 + (rc.senseMapInfo(loc0511).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0501)) { // check (5, 1)
            if (dist0501 > dist0400) { // from (4, 0)
                dist0501 = dist0400;
                dir0501 = dir0400;
            }

            if (dist0501 > dist0401) { // from (4, 1)
                dist0501 = dist0401;
                dir0501 = dir0401;
            }

            if (dist0501 > dist0402) { // from (4, 2)
                dist0501 = dist0402;
                dir0501 = dir0402;
            }

            if (dist0501 > dist0500) { // from (5, 0)
                dist0501 = dist0500;
                dir0501 = dir0500;
            }
            dist0501 += 1 + (rc.senseMapInfo(loc0501).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1512)) { // check (-5, -2)
            if (dist1512 > dist1411) { // from (-4, -1)
                dist1512 = dist1411;
                dir1512 = dir1411;
            }

            if (dist1512 > dist1412) { // from (-4, -2)
                dist1512 = dist1412;
                dir1512 = dir1412;
            }

            if (dist1512 > dist1413) { // from (-4, -3)
                dist1512 = dist1413;
                dir1512 = dir1413;
            }

            if (dist1512 > dist1511) { // from (-5, -1)
                dist1512 = dist1511;
                dir1512 = dir1511;
            }
            dist1512 += 1 + (rc.senseMapInfo(loc1512).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1502)) { // check (-5, 2)
            if (dist1502 > dist1401) { // from (-4, 1)
                dist1502 = dist1401;
                dir1502 = dir1401;
            }

            if (dist1502 > dist1402) { // from (-4, 2)
                dist1502 = dist1402;
                dir1502 = dir1402;
            }

            if (dist1502 > dist1403) { // from (-4, 3)
                dist1502 = dist1403;
                dir1502 = dir1403;
            }

            if (dist1502 > dist1501) { // from (-5, 1)
                dist1502 = dist1501;
                dir1502 = dir1501;
            }
            dist1502 += 1 + (rc.senseMapInfo(loc1502).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1215)) { // check (-2, -5)
            if (dist1215 > dist1114) { // from (-1, -4)
                dist1215 = dist1114;
                dir1215 = dir1114;
            }

            if (dist1215 > dist1214) { // from (-2, -4)
                dist1215 = dist1214;
                dir1215 = dir1214;
            }

            if (dist1215 > dist1314) { // from (-3, -4)
                dist1215 = dist1314;
                dir1215 = dir1314;
            }

            if (dist1215 > dist1115) { // from (-1, -5)
                dist1215 = dist1115;
                dir1215 = dir1115;
            }
            dist1215 += 1 + (rc.senseMapInfo(loc1215).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1205)) { // check (-2, 5)
            if (dist1205 > dist1104) { // from (-1, 4)
                dist1205 = dist1104;
                dir1205 = dir1104;
            }

            if (dist1205 > dist1204) { // from (-2, 4)
                dist1205 = dist1204;
                dir1205 = dir1204;
            }

            if (dist1205 > dist1304) { // from (-3, 4)
                dist1205 = dist1304;
                dir1205 = dir1304;
            }

            if (dist1205 > dist1105) { // from (-1, 5)
                dist1205 = dist1105;
                dir1205 = dir1105;
            }
            dist1205 += 1 + (rc.senseMapInfo(loc1205).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0215)) { // check (2, -5)
            if (dist0215 > dist0114) { // from (1, -4)
                dist0215 = dist0114;
                dir0215 = dir0114;
            }

            if (dist0215 > dist0214) { // from (2, -4)
                dist0215 = dist0214;
                dir0215 = dir0214;
            }

            if (dist0215 > dist0314) { // from (3, -4)
                dist0215 = dist0314;
                dir0215 = dir0314;
            }

            if (dist0215 > dist0115) { // from (1, -5)
                dist0215 = dist0115;
                dir0215 = dir0115;
            }
            dist0215 += 1 + (rc.senseMapInfo(loc0215).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0205)) { // check (2, 5)
            if (dist0205 > dist0104) { // from (1, 4)
                dist0205 = dist0104;
                dir0205 = dir0104;
            }

            if (dist0205 > dist0204) { // from (2, 4)
                dist0205 = dist0204;
                dir0205 = dir0204;
            }

            if (dist0205 > dist0304) { // from (3, 4)
                dist0205 = dist0304;
                dir0205 = dir0304;
            }

            if (dist0205 > dist0105) { // from (1, 5)
                dist0205 = dist0105;
                dir0205 = dir0105;
            }
            dist0205 += 1 + (rc.senseMapInfo(loc0205).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0512)) { // check (5, -2)
            if (dist0512 > dist0411) { // from (4, -1)
                dist0512 = dist0411;
                dir0512 = dir0411;
            }

            if (dist0512 > dist0412) { // from (4, -2)
                dist0512 = dist0412;
                dir0512 = dir0412;
            }

            if (dist0512 > dist0413) { // from (4, -3)
                dist0512 = dist0413;
                dir0512 = dir0413;
            }

            if (dist0512 > dist0511) { // from (5, -1)
                dist0512 = dist0511;
                dir0512 = dir0511;
            }
            dist0512 += 1 + (rc.senseMapInfo(loc0512).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0502)) { // check (5, 2)
            if (dist0502 > dist0401) { // from (4, 1)
                dist0502 = dist0401;
                dir0502 = dir0401;
            }

            if (dist0502 > dist0402) { // from (4, 2)
                dist0502 = dist0402;
                dir0502 = dir0402;
            }

            if (dist0502 > dist0403) { // from (4, 3)
                dist0502 = dist0403;
                dir0502 = dir0403;
            }

            if (dist0502 > dist0501) { // from (5, 1)
                dist0502 = dist0501;
                dir0502 = dir0501;
            }
            dist0502 += 1 + (rc.senseMapInfo(loc0502).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1414)) { // check (-4, -4)
            if (dist1414 > dist1313) { // from (-3, -3)
                dist1414 = dist1313;
                dir1414 = dir1313;
            }

            if (dist1414 > dist1413) { // from (-4, -3)
                dist1414 = dist1413;
                dir1414 = dir1413;
            }

            if (dist1414 > dist1314) { // from (-3, -4)
                dist1414 = dist1314;
                dir1414 = dir1314;
            }
            dist1414 += 1 + (rc.senseMapInfo(loc1414).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1404)) { // check (-4, 4)
            if (dist1404 > dist1303) { // from (-3, 3)
                dist1404 = dist1303;
                dir1404 = dir1303;
            }

            if (dist1404 > dist1403) { // from (-4, 3)
                dist1404 = dist1403;
                dir1404 = dir1403;
            }

            if (dist1404 > dist1304) { // from (-3, 4)
                dist1404 = dist1304;
                dir1404 = dir1304;
            }
            dist1404 += 1 + (rc.senseMapInfo(loc1404).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0414)) { // check (4, -4)
            if (dist0414 > dist0313) { // from (3, -3)
                dist0414 = dist0313;
                dir0414 = dir0313;
            }

            if (dist0414 > dist0314) { // from (3, -4)
                dist0414 = dist0314;
                dir0414 = dir0314;
            }

            if (dist0414 > dist0413) { // from (4, -3)
                dist0414 = dist0413;
                dir0414 = dir0413;
            }
            dist0414 += 1 + (rc.senseMapInfo(loc0414).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0404)) { // check (4, 4)
            if (dist0404 > dist0303) { // from (3, 3)
                dist0404 = dist0303;
                dir0404 = dir0303;
            }

            if (dist0404 > dist0304) { // from (3, 4)
                dist0404 = dist0304;
                dir0404 = dir0304;
            }

            if (dist0404 > dist0403) { // from (4, 3)
                dist0404 = dist0403;
                dir0404 = dir0403;
            }
            dist0404 += 1 + (rc.senseMapInfo(loc0404).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1513)) { // check (-5, -3)
            if (dist1513 > dist1412) { // from (-4, -2)
                dist1513 = dist1412;
                dir1513 = dir1412;
            }

            if (dist1513 > dist1413) { // from (-4, -3)
                dist1513 = dist1413;
                dir1513 = dir1413;
            }

            if (dist1513 > dist1512) { // from (-5, -2)
                dist1513 = dist1512;
                dir1513 = dir1512;
            }

            if (dist1513 > dist1414) { // from (-4, -4)
                dist1513 = dist1414;
                dir1513 = dir1414;
            }
            dist1513 += 1 + (rc.senseMapInfo(loc1513).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1503)) { // check (-5, 3)
            if (dist1503 > dist1402) { // from (-4, 2)
                dist1503 = dist1402;
                dir1503 = dir1402;
            }

            if (dist1503 > dist1403) { // from (-4, 3)
                dist1503 = dist1403;
                dir1503 = dir1403;
            }

            if (dist1503 > dist1502) { // from (-5, 2)
                dist1503 = dist1502;
                dir1503 = dir1502;
            }

            if (dist1503 > dist1404) { // from (-4, 4)
                dist1503 = dist1404;
                dir1503 = dir1404;
            }
            dist1503 += 1 + (rc.senseMapInfo(loc1503).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1315)) { // check (-3, -5)
            if (dist1315 > dist1214) { // from (-2, -4)
                dist1315 = dist1214;
                dir1315 = dir1214;
            }

            if (dist1315 > dist1314) { // from (-3, -4)
                dist1315 = dist1314;
                dir1315 = dir1314;
            }

            if (dist1315 > dist1215) { // from (-2, -5)
                dist1315 = dist1215;
                dir1315 = dir1215;
            }

            if (dist1315 > dist1414) { // from (-4, -4)
                dist1315 = dist1414;
                dir1315 = dir1414;
            }
            dist1315 += 1 + (rc.senseMapInfo(loc1315).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc1305)) { // check (-3, 5)
            if (dist1305 > dist1204) { // from (-2, 4)
                dist1305 = dist1204;
                dir1305 = dir1204;
            }

            if (dist1305 > dist1304) { // from (-3, 4)
                dist1305 = dist1304;
                dir1305 = dir1304;
            }

            if (dist1305 > dist1205) { // from (-2, 5)
                dist1305 = dist1205;
                dir1305 = dir1205;
            }

            if (dist1305 > dist1404) { // from (-4, 4)
                dist1305 = dist1404;
                dir1305 = dir1404;
            }
            dist1305 += 1 + (rc.senseMapInfo(loc1305).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0315)) { // check (3, -5)
            if (dist0315 > dist0214) { // from (2, -4)
                dist0315 = dist0214;
                dir0315 = dir0214;
            }

            if (dist0315 > dist0314) { // from (3, -4)
                dist0315 = dist0314;
                dir0315 = dir0314;
            }

            if (dist0315 > dist0215) { // from (2, -5)
                dist0315 = dist0215;
                dir0315 = dir0215;
            }

            if (dist0315 > dist0414) { // from (4, -4)
                dist0315 = dist0414;
                dir0315 = dir0414;
            }
            dist0315 += 1 + (rc.senseMapInfo(loc0315).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0305)) { // check (3, 5)
            if (dist0305 > dist0204) { // from (2, 4)
                dist0305 = dist0204;
                dir0305 = dir0204;
            }

            if (dist0305 > dist0304) { // from (3, 4)
                dist0305 = dist0304;
                dir0305 = dir0304;
            }

            if (dist0305 > dist0205) { // from (2, 5)
                dist0305 = dist0205;
                dir0305 = dir0205;
            }

            if (dist0305 > dist0404) { // from (4, 4)
                dist0305 = dist0404;
                dir0305 = dir0404;
            }
            dist0305 += 1 + (rc.senseMapInfo(loc0305).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0513)) { // check (5, -3)
            if (dist0513 > dist0412) { // from (4, -2)
                dist0513 = dist0412;
                dir0513 = dir0412;
            }

            if (dist0513 > dist0413) { // from (4, -3)
                dist0513 = dist0413;
                dir0513 = dir0413;
            }

            if (dist0513 > dist0512) { // from (5, -2)
                dist0513 = dist0512;
                dir0513 = dir0512;
            }

            if (dist0513 > dist0414) { // from (4, -4)
                dist0513 = dist0414;
                dir0513 = dir0414;
            }
            dist0513 += 1 + (rc.senseMapInfo(loc0513).hasCloud() ? 10 : 0);
        }

        if (rc.canSenseLocation(loc0503)) { // check (5, 3)
            if (dist0503 > dist0402) { // from (4, 2)
                dist0503 = dist0402;
                dir0503 = dir0402;
            }

            if (dist0503 > dist0403) { // from (4, 3)
                dist0503 = dist0403;
                dir0503 = dir0403;
            }

            if (dist0503 > dist0502) { // from (5, 2)
                dist0503 = dist0502;
                dir0503 = dir0502;
            }

            if (dist0503 > dist0404) { // from (4, 4)
                dist0503 = dist0404;
                dir0503 = dir0404;
            }
            dist0503 += 1 + (rc.senseMapInfo(loc0503).hasCloud() ? 10 : 0);
        }

        /*
         * PART 3: Massive Switch Statement
         * We check if the target location is in the vision of the robot that
         * Bellman-Ford was ran on
         */

        int target_dx = target.x - loc0000.x;
        int target_dy = target.y - loc0000.y;
        switch (target_dx) {
            case -5:
                switch (target_dy) {
                    case -3:
                        return dir1513; // destination is at relative location (-5, -3)

                    case -2:
                        return dir1512; // destination is at relative location (-5, -2)

                    case -1:
                        return dir1511; // destination is at relative location (-5, -1)

                    case 0:
                        return dir1500; // destination is at relative location (-5, 0)

                    case 1:
                        return dir1501; // destination is at relative location (-5, 1)

                    case 2:
                        return dir1502; // destination is at relative location (-5, 2)

                    case 3:
                        return dir1503; // destination is at relative location (-5, 3)
                }
                break;
            case -4:
                switch (target_dy) {
                    case -4:
                        return dir1414; // destination is at relative location (-4, -4)

                    case -3:
                        return dir1413; // destination is at relative location (-4, -3)

                    case -2:
                        return dir1412; // destination is at relative location (-4, -2)

                    case -1:
                        return dir1411; // destination is at relative location (-4, -1)

                    case 0:
                        return dir1400; // destination is at relative location (-4, 0)

                    case 1:
                        return dir1401; // destination is at relative location (-4, 1)

                    case 2:
                        return dir1402; // destination is at relative location (-4, 2)

                    case 3:
                        return dir1403; // destination is at relative location (-4, 3)

                    case 4:
                        return dir1404; // destination is at relative location (-4, 4)
                }
                break;
            case -3:
                switch (target_dy) {
                    case -5:
                        return dir1315; // destination is at relative location (-3, -5)

                    case -4:
                        return dir1314; // destination is at relative location (-3, -4)

                    case -3:
                        return dir1313; // destination is at relative location (-3, -3)

                    case -2:
                        return dir1312; // destination is at relative location (-3, -2)

                    case -1:
                        return dir1311; // destination is at relative location (-3, -1)

                    case 0:
                        return dir1300; // destination is at relative location (-3, 0)

                    case 1:
                        return dir1301; // destination is at relative location (-3, 1)

                    case 2:
                        return dir1302; // destination is at relative location (-3, 2)

                    case 3:
                        return dir1303; // destination is at relative location (-3, 3)

                    case 4:
                        return dir1304; // destination is at relative location (-3, 4)

                    case 5:
                        return dir1305; // destination is at relative location (-3, 5)
                }
                break;
            case -2:
                switch (target_dy) {
                    case -5:
                        return dir1215; // destination is at relative location (-2, -5)

                    case -4:
                        return dir1214; // destination is at relative location (-2, -4)

                    case -3:
                        return dir1213; // destination is at relative location (-2, -3)

                    case -2:
                        return dir1212; // destination is at relative location (-2, -2)

                    case -1:
                        return dir1211; // destination is at relative location (-2, -1)

                    case 0:
                        return dir1200; // destination is at relative location (-2, 0)

                    case 1:
                        return dir1201; // destination is at relative location (-2, 1)

                    case 2:
                        return dir1202; // destination is at relative location (-2, 2)

                    case 3:
                        return dir1203; // destination is at relative location (-2, 3)

                    case 4:
                        return dir1204; // destination is at relative location (-2, 4)

                    case 5:
                        return dir1205; // destination is at relative location (-2, 5)
                }
                break;
            case -1:
                switch (target_dy) {
                    case -5:
                        return dir1115; // destination is at relative location (-1, -5)

                    case -4:
                        return dir1114; // destination is at relative location (-1, -4)

                    case -3:
                        return dir1113; // destination is at relative location (-1, -3)

                    case -2:
                        return dir1112; // destination is at relative location (-1, -2)

                    case -1:
                        return dir1111; // destination is at relative location (-1, -1)

                    case 0:
                        return dir1100; // destination is at relative location (-1, 0)

                    case 1:
                        return dir1101; // destination is at relative location (-1, 1)

                    case 2:
                        return dir1102; // destination is at relative location (-1, 2)

                    case 3:
                        return dir1103; // destination is at relative location (-1, 3)

                    case 4:
                        return dir1104; // destination is at relative location (-1, 4)

                    case 5:
                        return dir1105; // destination is at relative location (-1, 5)
                }
                break;
            case 0:
                switch (target_dy) {
                    case -5:
                        return dir0015; // destination is at relative location (0, -5)

                    case -4:
                        return dir0014; // destination is at relative location (0, -4)

                    case -3:
                        return dir0013; // destination is at relative location (0, -3)

                    case -2:
                        return dir0012; // destination is at relative location (0, -2)

                    case -1:
                        return dir0011; // destination is at relative location (0, -1)

                    case 0:
                        return dir0000; // destination is at relative location (0, 0)

                    case 1:
                        return dir0001; // destination is at relative location (0, 1)

                    case 2:
                        return dir0002; // destination is at relative location (0, 2)

                    case 3:
                        return dir0003; // destination is at relative location (0, 3)

                    case 4:
                        return dir0004; // destination is at relative location (0, 4)

                    case 5:
                        return dir0005; // destination is at relative location (0, 5)
                }
                break;
            case 1:
                switch (target_dy) {
                    case -5:
                        return dir0115; // destination is at relative location (1, -5)

                    case -4:
                        return dir0114; // destination is at relative location (1, -4)

                    case -3:
                        return dir0113; // destination is at relative location (1, -3)

                    case -2:
                        return dir0112; // destination is at relative location (1, -2)

                    case -1:
                        return dir0111; // destination is at relative location (1, -1)

                    case 0:
                        return dir0100; // destination is at relative location (1, 0)

                    case 1:
                        return dir0101; // destination is at relative location (1, 1)

                    case 2:
                        return dir0102; // destination is at relative location (1, 2)

                    case 3:
                        return dir0103; // destination is at relative location (1, 3)

                    case 4:
                        return dir0104; // destination is at relative location (1, 4)

                    case 5:
                        return dir0105; // destination is at relative location (1, 5)
                }
                break;
            case 2:
                switch (target_dy) {
                    case -5:
                        return dir0215; // destination is at relative location (2, -5)

                    case -4:
                        return dir0214; // destination is at relative location (2, -4)

                    case -3:
                        return dir0213; // destination is at relative location (2, -3)

                    case -2:
                        return dir0212; // destination is at relative location (2, -2)

                    case -1:
                        return dir0211; // destination is at relative location (2, -1)

                    case 0:
                        return dir0200; // destination is at relative location (2, 0)

                    case 1:
                        return dir0201; // destination is at relative location (2, 1)

                    case 2:
                        return dir0202; // destination is at relative location (2, 2)

                    case 3:
                        return dir0203; // destination is at relative location (2, 3)

                    case 4:
                        return dir0204; // destination is at relative location (2, 4)

                    case 5:
                        return dir0205; // destination is at relative location (2, 5)
                }
                break;
            case 3:
                switch (target_dy) {
                    case -5:
                        return dir0315; // destination is at relative location (3, -5)

                    case -4:
                        return dir0314; // destination is at relative location (3, -4)

                    case -3:
                        return dir0313; // destination is at relative location (3, -3)

                    case -2:
                        return dir0312; // destination is at relative location (3, -2)

                    case -1:
                        return dir0311; // destination is at relative location (3, -1)

                    case 0:
                        return dir0300; // destination is at relative location (3, 0)

                    case 1:
                        return dir0301; // destination is at relative location (3, 1)

                    case 2:
                        return dir0302; // destination is at relative location (3, 2)

                    case 3:
                        return dir0303; // destination is at relative location (3, 3)

                    case 4:
                        return dir0304; // destination is at relative location (3, 4)

                    case 5:
                        return dir0305; // destination is at relative location (3, 5)
                }
                break;
            case 4:
                switch (target_dy) {
                    case -4:
                        return dir0414; // destination is at relative location (4, -4)

                    case -3:
                        return dir0413; // destination is at relative location (4, -3)

                    case -2:
                        return dir0412; // destination is at relative location (4, -2)

                    case -1:
                        return dir0411; // destination is at relative location (4, -1)

                    case 0:
                        return dir0400; // destination is at relative location (4, 0)

                    case 1:
                        return dir0401; // destination is at relative location (4, 1)

                    case 2:
                        return dir0402; // destination is at relative location (4, 2)

                    case 3:
                        return dir0403; // destination is at relative location (4, 3)

                    case 4:
                        return dir0404; // destination is at relative location (4, 4)
                }
                break;
            case 5:
                switch (target_dy) {
                    case -3:
                        return dir0513; // destination is at relative location (5, -3)

                    case -2:
                        return dir0512; // destination is at relative location (5, -2)

                    case -1:
                        return dir0511; // destination is at relative location (5, -1)

                    case 0:
                        return dir0500; // destination is at relative location (5, 0)

                    case 1:
                        return dir0501; // destination is at relative location (5, 1)

                    case 2:
                        return dir0502; // destination is at relative location (5, 2)

                    case 3:
                        return dir0503; // destination is at relative location (5, 3)
                }
                break;
        }

        /*
         * PART 4: Edge Checking
         * If the target location wasn"t in the region Bellman-Ford ran on,
         * then we will try to find a edge node with the most optimal direction
         * to move in
         */

        Direction ans = null;
        double bestScore = 0;
        double currDist = Math.sqrt(loc0000.distanceSquaredTo(target));

        double score1513 = (currDist - Math.sqrt(loc1513.distanceSquaredTo(target))) / dist1513; // (-5, -3)
        if (score1513 > bestScore) {
            bestScore = score1513;
            ans = dir1513;
        }

        double score1512 = (currDist - Math.sqrt(loc1512.distanceSquaredTo(target))) / dist1512; // (-5, -2)
        if (score1512 > bestScore) {
            bestScore = score1512;
            ans = dir1512;
        }

        double score1511 = (currDist - Math.sqrt(loc1511.distanceSquaredTo(target))) / dist1511; // (-5, -1)
        if (score1511 > bestScore) {
            bestScore = score1511;
            ans = dir1511;
        }

        double score1500 = (currDist - Math.sqrt(loc1500.distanceSquaredTo(target))) / dist1500; // (-5, 0)
        if (score1500 > bestScore) {
            bestScore = score1500;
            ans = dir1500;
        }

        double score1501 = (currDist - Math.sqrt(loc1501.distanceSquaredTo(target))) / dist1501; // (-5, 1)
        if (score1501 > bestScore) {
            bestScore = score1501;
            ans = dir1501;
        }

        double score1502 = (currDist - Math.sqrt(loc1502.distanceSquaredTo(target))) / dist1502; // (-5, 2)
        if (score1502 > bestScore) {
            bestScore = score1502;
            ans = dir1502;
        }

        double score1503 = (currDist - Math.sqrt(loc1503.distanceSquaredTo(target))) / dist1503; // (-5, 3)
        if (score1503 > bestScore) {
            bestScore = score1503;
            ans = dir1503;
        }

        double score1414 = (currDist - Math.sqrt(loc1414.distanceSquaredTo(target))) / dist1414; // (-4, -4)
        if (score1414 > bestScore) {
            bestScore = score1414;
            ans = dir1414;
        }

        double score1413 = (currDist - Math.sqrt(loc1413.distanceSquaredTo(target))) / dist1413; // (-4, -3)
        if (score1413 > bestScore) {
            bestScore = score1413;
            ans = dir1413;
        }

        double score1403 = (currDist - Math.sqrt(loc1403.distanceSquaredTo(target))) / dist1403; // (-4, 3)
        if (score1403 > bestScore) {
            bestScore = score1403;
            ans = dir1403;
        }

        double score1404 = (currDist - Math.sqrt(loc1404.distanceSquaredTo(target))) / dist1404; // (-4, 4)
        if (score1404 > bestScore) {
            bestScore = score1404;
            ans = dir1404;
        }

        double score1315 = (currDist - Math.sqrt(loc1315.distanceSquaredTo(target))) / dist1315; // (-3, -5)
        if (score1315 > bestScore) {
            bestScore = score1315;
            ans = dir1315;
        }

        double score1314 = (currDist - Math.sqrt(loc1314.distanceSquaredTo(target))) / dist1314; // (-3, -4)
        if (score1314 > bestScore) {
            bestScore = score1314;
            ans = dir1314;
        }

        double score1304 = (currDist - Math.sqrt(loc1304.distanceSquaredTo(target))) / dist1304; // (-3, 4)
        if (score1304 > bestScore) {
            bestScore = score1304;
            ans = dir1304;
        }

        double score1305 = (currDist - Math.sqrt(loc1305.distanceSquaredTo(target))) / dist1305; // (-3, 5)
        if (score1305 > bestScore) {
            bestScore = score1305;
            ans = dir1305;
        }

        double score1215 = (currDist - Math.sqrt(loc1215.distanceSquaredTo(target))) / dist1215; // (-2, -5)
        if (score1215 > bestScore) {
            bestScore = score1215;
            ans = dir1215;
        }

        double score1205 = (currDist - Math.sqrt(loc1205.distanceSquaredTo(target))) / dist1205; // (-2, 5)
        if (score1205 > bestScore) {
            bestScore = score1205;
            ans = dir1205;
        }

        double score1115 = (currDist - Math.sqrt(loc1115.distanceSquaredTo(target))) / dist1115; // (-1, -5)
        if (score1115 > bestScore) {
            bestScore = score1115;
            ans = dir1115;
        }

        double score1105 = (currDist - Math.sqrt(loc1105.distanceSquaredTo(target))) / dist1105; // (-1, 5)
        if (score1105 > bestScore) {
            bestScore = score1105;
            ans = dir1105;
        }

        double score0015 = (currDist - Math.sqrt(loc0015.distanceSquaredTo(target))) / dist0015; // (0, -5)
        if (score0015 > bestScore) {
            bestScore = score0015;
            ans = dir0015;
        }

        double score0005 = (currDist - Math.sqrt(loc0005.distanceSquaredTo(target))) / dist0005; // (0, 5)
        if (score0005 > bestScore) {
            bestScore = score0005;
            ans = dir0005;
        }

        double score0115 = (currDist - Math.sqrt(loc0115.distanceSquaredTo(target))) / dist0115; // (1, -5)
        if (score0115 > bestScore) {
            bestScore = score0115;
            ans = dir0115;
        }

        double score0105 = (currDist - Math.sqrt(loc0105.distanceSquaredTo(target))) / dist0105; // (1, 5)
        if (score0105 > bestScore) {
            bestScore = score0105;
            ans = dir0105;
        }

        double score0215 = (currDist - Math.sqrt(loc0215.distanceSquaredTo(target))) / dist0215; // (2, -5)
        if (score0215 > bestScore) {
            bestScore = score0215;
            ans = dir0215;
        }

        double score0205 = (currDist - Math.sqrt(loc0205.distanceSquaredTo(target))) / dist0205; // (2, 5)
        if (score0205 > bestScore) {
            bestScore = score0205;
            ans = dir0205;
        }

        double score0315 = (currDist - Math.sqrt(loc0315.distanceSquaredTo(target))) / dist0315; // (3, -5)
        if (score0315 > bestScore) {
            bestScore = score0315;
            ans = dir0315;
        }

        double score0314 = (currDist - Math.sqrt(loc0314.distanceSquaredTo(target))) / dist0314; // (3, -4)
        if (score0314 > bestScore) {
            bestScore = score0314;
            ans = dir0314;
        }

        double score0304 = (currDist - Math.sqrt(loc0304.distanceSquaredTo(target))) / dist0304; // (3, 4)
        if (score0304 > bestScore) {
            bestScore = score0304;
            ans = dir0304;
        }

        double score0305 = (currDist - Math.sqrt(loc0305.distanceSquaredTo(target))) / dist0305; // (3, 5)
        if (score0305 > bestScore) {
            bestScore = score0305;
            ans = dir0305;
        }

        double score0414 = (currDist - Math.sqrt(loc0414.distanceSquaredTo(target))) / dist0414; // (4, -4)
        if (score0414 > bestScore) {
            bestScore = score0414;
            ans = dir0414;
        }

        double score0413 = (currDist - Math.sqrt(loc0413.distanceSquaredTo(target))) / dist0413; // (4, -3)
        if (score0413 > bestScore) {
            bestScore = score0413;
            ans = dir0413;
        }

        double score0403 = (currDist - Math.sqrt(loc0403.distanceSquaredTo(target))) / dist0403; // (4, 3)
        if (score0403 > bestScore) {
            bestScore = score0403;
            ans = dir0403;
        }

        double score0404 = (currDist - Math.sqrt(loc0404.distanceSquaredTo(target))) / dist0404; // (4, 4)
        if (score0404 > bestScore) {
            bestScore = score0404;
            ans = dir0404;
        }

        double score0513 = (currDist - Math.sqrt(loc0513.distanceSquaredTo(target))) / dist0513; // (5, -3)
        if (score0513 > bestScore) {
            bestScore = score0513;
            ans = dir0513;
        }

        double score0512 = (currDist - Math.sqrt(loc0512.distanceSquaredTo(target))) / dist0512; // (5, -2)
        if (score0512 > bestScore) {
            bestScore = score0512;
            ans = dir0512;
        }

        double score0511 = (currDist - Math.sqrt(loc0511.distanceSquaredTo(target))) / dist0511; // (5, -1)
        if (score0511 > bestScore) {
            bestScore = score0511;
            ans = dir0511;
        }

        double score0500 = (currDist - Math.sqrt(loc0500.distanceSquaredTo(target))) / dist0500; // (5, 0)
        if (score0500 > bestScore) {
            bestScore = score0500;
            ans = dir0500;
        }

        double score0501 = (currDist - Math.sqrt(loc0501.distanceSquaredTo(target))) / dist0501; // (5, 1)
        if (score0501 > bestScore) {
            bestScore = score0501;
            ans = dir0501;
        }

        double score0502 = (currDist - Math.sqrt(loc0502.distanceSquaredTo(target))) / dist0502; // (5, 2)
        if (score0502 > bestScore) {
            bestScore = score0502;
            ans = dir0502;
        }

        double score0503 = (currDist - Math.sqrt(loc0503.distanceSquaredTo(target))) / dist0503; // (5, 3)
        if (score0503 > bestScore) {
            bestScore = score0503;
            ans = dir0503;
        }

        return ans;
    }
}
