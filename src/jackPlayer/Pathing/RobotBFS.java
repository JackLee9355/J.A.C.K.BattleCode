package jackPlayer.Pathing;

import battlecode.common.Direction;
import battlecode.common.MapLocation;
import battlecode.common.RobotController;

public class RobotBFS extends BFS {

    public RobotBFS(RobotController rc){
        super(rc);
    }

    // 69 locs to check
    // first digit: x dir, second digit: y dir, 0 means positive, 1 negative
    // measuring from center

    // leftmost line vertical in vision radius
    static MapLocation l1412;
    static double v1412;
    static Direction d1412;
    static double r1412;

    static MapLocation l1411;
    static double v1411;
    static Direction d1411;
    static double r1411;

    static MapLocation l1400;
    static double v1400;
    static Direction d1400;
    static double r1400;

    static MapLocation l1401;
    static double v1401;
    static Direction d1401;
    static double r1401;

    static MapLocation l1402;
    static double v1402;
    static Direction d1402;
    static double r1402;

    // second leftmost vertical line in vision radius
    static MapLocation l1313;
    static double v1313;
    static Direction d1313;
    static double r1313;

    static MapLocation l1312;
    static double v1312;
    static Direction d1312;
    static double r1312;

    static MapLocation l1311;
    static double v1311;
    static Direction d1311;
    static double r1311;

    static MapLocation l1300;
    static double v1300;
    static Direction d1300;
    static double r1300;

    static MapLocation l1301;
    static double v1301;
    static Direction d1301;
    static double r1301;

    static MapLocation l1302;
    static double v1302;
    static Direction d1302;
    static double r1302;

    static MapLocation l1303;
    static double v1303;
    static Direction d1303;
    static double r1303;

    // third from left vertical line
    static MapLocation l1214;
    static double v1214;
    static Direction d1214;
    static double r1214;

    static MapLocation l1213;
    static double v1213;
    static Direction d1213;
    static double r1213;

    static MapLocation l1212;
    static double v1212;
    static Direction d1212;
    static double r1212;

    static MapLocation l1211;
    static double v1211;
    static Direction d1211;
    static double r1211;

    static MapLocation l1200;
    static double v1200;
    static Direction d1200;
    static double r1200;

    static MapLocation l1201;
    static double v1201;
    static Direction d1201;
    static double r1201;

    static MapLocation l1202;
    static double v1202;
    static Direction d1202;
    static double r1202;

    static MapLocation l1203;
    static double v1203;
    static Direction d1203;
    static double r1203;

    static MapLocation l1204;
    static double v1204;
    static Direction d1204;
    static double r1204;

    // fourth from left vertical line
    static MapLocation l1114;
    static double v1114;
    static Direction d1114;
    static double r1114;

    static MapLocation l1113;
    static double v1113;
    static Direction d1113;
    static double r1113;

    static MapLocation l1112;
    static double v1112;
    static Direction d1112;
    static double r1112;

    static MapLocation l1111;
    static double v1111;
    static Direction d1111;
    static double r1111;

    static MapLocation l1100;
    static double v1100;
    static Direction d1100;
    static double r1100;

    static MapLocation l1101;
    static double v1101;
    static Direction d1101;
    static double r1101;

    static MapLocation l1102;
    static double v1102;
    static Direction d1102;
    static double r1102;

    static MapLocation l1103;
    static double v1103;
    static Direction d1103;
    static double r1103;

    static MapLocation l1104;
    static double v1104;
    static Direction d1104;
    static double r1104;

    // middle vertical line
    static MapLocation l0014;
    static double v0014;
    static Direction d0014;
    static double r0014;

    static MapLocation l0013;
    static double v0013;
    static Direction d0013;
    static double r0013;

    static MapLocation l0012;
    static double v0012;
    static Direction d0012;
    static double r0012;

    static MapLocation l0011;
    static double v0011;
    static Direction d0011;
    static double r0011;

    static MapLocation l0000;
    static double v0000;
    static Direction d0000;
    static double r0000;

    static MapLocation l0001;
    static double v0001;
    static Direction d0001;
    static double r0001;

    static MapLocation l0002;
    static double v0002;
    static Direction d0002;
    static double r0002;

    static MapLocation l0003;
    static double v0003;
    static Direction d0003;
    static double r0003;

    static MapLocation l0004;
    static double v0004;
    static Direction d0004;
    static double r0004;

    // immediately right of middle vertical line
    static MapLocation l0114;
    static double v0114;
    static Direction d0114;
    static double r0114;

    static MapLocation l0113;
    static double v0113;
    static Direction d0113;
    static double r0113;

    static MapLocation l0112;
    static double v0112;
    static Direction d0112;
    static double r0112;

    static MapLocation l0111;
    static double v0111;
    static Direction d0111;
    static double r0111;

    static MapLocation l0100;
    static double v0100;
    static Direction d0100;
    static double r0100;

    static MapLocation l0101;
    static double v0101;
    static Direction d0101;
    static double r0101;

    static MapLocation l0102;
    static double v0102;
    static Direction d0102;
    static double r0102;

    static MapLocation l0103;
    static double v0103;
    static Direction d0103;
    static double r0103;

    static MapLocation l0104;
    static double v0104;
    static Direction d0104;
    static double r0104;

    // two right of middle vertical line
    static MapLocation l0214;
    static double v0214;
    static Direction d0214;
    static double r0214;

    static MapLocation l0213;
    static double v0213;
    static Direction d0213;
    static double r0213;

    static MapLocation l0212;
    static double v0212;
    static Direction d0212;
    static double r0212;

    static MapLocation l0211;
    static double v0211;
    static Direction d0211;
    static double r0211;

    static MapLocation l0200;
    static double v0200;
    static Direction d0200;
    static double r0200;

    static MapLocation l0201;
    static double v0201;
    static Direction d0201;
    static double r0201;

    static MapLocation l0202;
    static double v0202;
    static Direction d0202;
    static double r0202;

    static MapLocation l0203;
    static double v0203;
    static Direction d0203;
    static double r0203;

    static MapLocation l0204;
    static double v0204;
    static Direction d0204;
    static double r0204;

    // three right of middle vertical line
    static MapLocation l0313;
    static double v0313;
    static Direction d0313;
    static double r0313;

    static MapLocation l0312;
    static double v0312;
    static Direction d0312;
    static double r0312;

    static MapLocation l0311;
    static double v0311;
    static Direction d0311;
    static double r0311;

    static MapLocation l0300;
    static double v0300;
    static Direction d0300;
    static double r0300;

    static MapLocation l0301;
    static double v0301;
    static Direction d0301;
    static double r0301;

    static MapLocation l0302;
    static double v0302;
    static Direction d0302;
    static double r0302;

    static MapLocation l0303;
    static double v0303;
    static Direction d0303;
    static double r0303;

    // four right of middle vertical line
    static MapLocation l0412;
    static double v0412;
    static Direction d0412;
    static double r0412;

    static MapLocation l0411;
    static double v0411;
    static Direction d0411;
    static double r0411;

    static MapLocation l0400;
    static double v0400;
    static Direction d0400;
    static double r0400;

    static MapLocation l0401;
    static double v0401;
    static Direction d0401;
    static double r0401;

    static MapLocation l0402;
    static double v0402;
    static Direction d0402;
    static double r0402;

    // ACTUAL BFS
    // this is basically a dijkstra
    // implement BFS class
    public Direction getBestDirection(MapLocation target){
        l0000 = rc.getLocation();
        v0000 = 0;

        l1101 = rc.adjacentLocation(Direction.NORTHWEST);
        v1101 = 1000000;
        d1101 = null;

        l0001 = rc.adjacentLocation(Direction.NORTH);
        v0001 = 1000000;
        d0001 = null;

        l0101 = rc.adjacentLocation(Direction.NORTHEAST);
        v0101 = 1000000;
        d0101 = null;

        l1100 = rc.adjacentLocation(Direction.WEST);
        v1100 = 1000000;
        d1100 = null;

        l0100 = rc.adjacentLocation(Direction.EAST);
        v0100 = 1000000;
        d0100 = null;

        l1111 = rc.adjacentLocation(Direction.SOUTHWEST);
        v1111 = 1000000;
        d1111 = null;

        l0011 = rc.adjacentLocation(Direction.SOUTH);
        v0011 = 1000000;
        d0011 = null;

        l0111 = rc.adjacentLocation(Direction.SOUTHEAST);
        v0111 = 1000000;
        d0111 = null;

        l1102 = l1101.add(Direction.NORTH);
        v1102 = 1000000;
        d1102 = null;

        l0002 = l0001.add(Direction.NORTH);
        v0002 = 1000000;
        d0002 = null;

        l0102 = l0101.add(Direction.NORTH);
        v0102 = 1000000;
        d0102 = null;

        l1201 = l1101.add(Direction.WEST);
        v1201 = 1000000;
        d1201 = null;

        l0201 = l0101.add(Direction.EAST);
        v0201 = 1000000;
        d0201 = null;

        l1200 = l1100.add(Direction.WEST);
        v1200 = 1000000;
        d1200 = null;

        l0200 = l0100.add(Direction.EAST);
        v0200 = 1000000;
        d0200 = null;

        l1211 = l1111.add(Direction.WEST);
        v1211 = 1000000;
        d1211 = null;

        l0211 = l0111.add(Direction.EAST);
        v0211 = 1000000;
        d0211 = null;

        l1112 = l1111.add(Direction.SOUTH);
        v1112 = 1000000;
        d1112 = null;

        l0012 = l0011.add(Direction.SOUTH);
        v0012 = 1000000;
        d0012 = null;

        l0112 = l0111.add(Direction.SOUTH);
        v0112 = 1000000;
        d0112 = null;

        l1103 = l1102.add(Direction.NORTH);
        v1103 = 1000000;
        d1103 = null;

        l0003 = l0002.add(Direction.NORTH);
        v0003 = 1000000;
        d0003 = null;

        l0103 = l0102.add(Direction.NORTH);
        v0103 = 1000000;
        d0103 = null;

        l1202 = l1102.add(Direction.WEST);
        v1202 = 1000000;
        d1202 = null;

        l0202 = l0102.add(Direction.EAST);
        v0202 = 1000000;
        d0202 = null;

        l1301 = l1201.add(Direction.WEST);
        v1301 = 1000000;
        d1301 = null;

        l0301 = l0201.add(Direction.EAST);
        v0301 = 1000000;
        d0301 = null;

        l1300 = l1200.add(Direction.WEST);
        v1300 = 1000000;
        d1300 = null;

        l0300 = l0200.add(Direction.EAST);
        v0300 = 1000000;
        d0300 = null;

        l1311 = l1211.add(Direction.WEST);
        v1311 = 1000000;
        d1311 = null;

        l0311 = l0211.add(Direction.EAST);
        v0311 = 1000000;
        d0311 = null;

        l1212 = l1112.add(Direction.WEST);
        v1212 = 1000000;
        d1212 = null;

        l0212 = l0112.add(Direction.EAST);
        v0212 = 1000000;
        d0212 = null;

        l1113 = l1112.add(Direction.SOUTH);
        v1113 = 1000000;
        d1113 = null;

        l0013 = l0012.add(Direction.SOUTH);
        v0013 = 1000000;
        d0013 = null;

        l0113 = l0112.add(Direction.SOUTH);
        v0113 = 1000000;
        d0113 = null;

        l1203 = l1202.add(Direction.NORTH);
        v1203 = 1000000;
        d1203 = null;

        l1204 = l1203.add(Direction.NORTH);
        v1204 = 1000000;
        d1204 = null;

        l1104 = l1103.add(Direction.NORTH);
        v1104 = 1000000;
        d1104 = null;

        l0004 = l0003.add(Direction.NORTH);
        v0004 = 1000000;
        d0004 = null;

        l0104 = l0103.add(Direction.NORTH);
        v0104 = 1000000;
        d0104 = null;

        l0203 = l0202.add(Direction.NORTH);
        v0203 = 1000000;
        d0203 = null;

        l0204 = l0203.add(Direction.NORTH);
        v0204 = 1000000;
        d0204 = null;

        l1303 = l1203.add(Direction.WEST);
        v1303 = 1000000;
        d1303 = null;

        l0303 = l0203.add(Direction.EAST);
        v0303 = 1000000;
        d0303 = null;

        l1302 = l1202.add(Direction.WEST);
        v1302 = 1000000;
        d1302 = null;

        l1402 = l1302.add(Direction.WEST);
        v1402 = 1000000;
        d1402 = null;

        l0302 = l0202.add(Direction.EAST);
        v0302 = 1000000;
        d0302 = null;

        l0402 = l0302.add(Direction.EAST);
        v0402 = 1000000;
        d0402 = null;

        l1401 = l1301.add(Direction.WEST);
        v1401 = 1000000;
        d1401 = null;

        l0401 = l0301.add(Direction.EAST);
        v0401 = 1000000;
        d0401 = null;

        l1400 = l1300.add(Direction.WEST);
        v1400 = 1000000;
        d1400 = null;

        l0400 = l0300.add(Direction.EAST);
        v0400 = 1000000;
        d0400 = null;

        l1411 = l1311.add(Direction.WEST);
        v1411 = 1000000;
        d1411 = null;

        l0411 = l0311.add(Direction.EAST);
        v0411 = 1000000;
        d0411 = null;

        l1312 = l1212.add(Direction.WEST);
        v1312 = 1000000;
        d1312 = null;

        l1412 = l1312.add(Direction.WEST);
        v1412 = 1000000;
        d1412 = null;

        l0312 = l0212.add(Direction.EAST);
        v0312 = 1000000;
        d0312 = null;

        l0412 = l0312.add(Direction.EAST);
        v0412 = 1000000;
        d0412 = null;

        l1213 = l1212.add(Direction.SOUTH);
        v1213 = 1000000;
        d1213 = null;

        l1313 = l1213.add(Direction.WEST);
        v1313 = 1000000;
        d1313 = null;

        l0213 = l0212.add(Direction.SOUTH);
        v0213 = 1000000;
        d0213 = null;

        l0313 = l0213.add(Direction.EAST);
        v0313 = 1000000;
        d0313 = null;

        l1214 = l1213.add(Direction.SOUTH);
        v1214 = 1000000;
        d1214 = null;

        l1114 = l1113.add(Direction.SOUTH);
        v1114 = 1000000;
        d1114 = null;

        l0014 = l0013.add(Direction.SOUTH);
        v0014 = 1000000;
        d0014 = null;

        l0114 = l0113.add(Direction.SOUTH);
        v0114 = 1000000;
        d0114 = null;

        l0214 = l0213.add(Direction.SOUTH);
        v0214 = 1000000;
        d0214 = null;

        try {

            if (rc.onTheMap(l1101)) {
                if (!rc.isLocationOccupied(l1101)) {
                    r1101 = 10;
                    if (v1101 > v0000 + r1101) {
                        v1101 = v0000 + r1101;
                        d1101 = Direction.NORTHWEST;
                    }
                }
            }
            if (rc.onTheMap(l0001)) {
                if (!rc.isLocationOccupied(l0001)) {
                    r0001 = 10;
                    if (v0001 > v0000 + r0001) {
                        v0001 = v0000 + r0001;
                        d0001 = Direction.NORTH;
                    }
                }
            }
            if (rc.onTheMap(l0101)) {
                if (!rc.isLocationOccupied(l0101)) {
                    r0101 = 10;
                    if (v0101 > v0000 + r0101) {
                        v0101 = v0000 + r0101;
                        d0101 = Direction.NORTHEAST;
                    }
                }
            }
            if (rc.onTheMap(l1100)) {
                if (!rc.isLocationOccupied(l1100)) {
                    r1100 = 10;
                    if (v1100 > v0000 + r1100) {
                        v1100 = v0000 + r1100;
                        d1100 = Direction.WEST;
                    }
                }
            }
            if (rc.onTheMap(l0100)) {
                if (!rc.isLocationOccupied(l0100)) {
                    r0100 = 10;
                    if (v0100 > v0000 + r0100) {
                        v0100 = v0000 + r0100;
                        d0100 = Direction.EAST;
                    }
                }
            }
            if (rc.onTheMap(l1111)) {
                if (!rc.isLocationOccupied(l1111)) {
                    r1111 = 10;
                    if (v1111 > v0000 + r1111) {
                        v1111 = v0000 + r1111;
                        d1111 = Direction.SOUTHWEST;
                    }
                }
            }
            if (rc.onTheMap(l0011)) {
                if (!rc.isLocationOccupied(l0011)) {
                    r0011 = 10;
                    if (v0011 > v0000 + r0011) {
                        v0011 = v0000 + r0011;
                        d0011 = Direction.SOUTH;
                    }
                }
            }
            if (rc.onTheMap(l0111)) {
                if (!rc.isLocationOccupied(l0111)) {
                    r0111 = 10;
                    if (v0111 > v0000 + r0111) {
                        v0111 = v0000 + r0111;
                        d0111 = Direction.SOUTHEAST;
                    }
                }
            }
            if (rc.onTheMap(l1102)) {
                r1102 = 10;
                if (v1102 > v1101 + r1102) {
                    v1102 = v1101 + r1102;
                    d1102 = d1101;
                }
                if (v1102 > v0001 + r1102) {
                    v1102 = v0001 + r1102;
                    d1102 = d0001;
                }
            }
            if (rc.onTheMap(l0002)) {
                r0002 = 10;
                if (v0002 > v1101 + r0002) {
                    v0002 = v1101 + r0002;
                    d0002 = d1101;
                }
                if (v0002 > v0001 + r0002) {
                    v0002 = v0001 + r0002;
                    d0002 = d0001;
                }
                if (v0002 > v0101 + r0002) {
                    v0002 = v0101 + r0002;
                    d0002 = d0101;
                }
            }
            if (rc.onTheMap(l0102)) {
                r0102 = 10;
                if (v0102 > v0001 + r0102) {
                    v0102 = v0001 + r0102;
                    d0102 = d0001;
                }
                if (v0102 > v0101 + r0102) {
                    v0102 = v0101 + r0102;
                    d0102 = d0101;
                }
            }
            if (rc.onTheMap(l1201)) {
                r1201 = 10;
                if (v1201 > v1100 + r1201) {
                    v1201 = v1100 + r1201;
                    d1201 = d1100;
                }
                if (v1201 > v1101 + r1201) {
                    v1201 = v1101 + r1201;
                    d1201 = d1101;
                }
            }
            if (rc.onTheMap(l0201)) {
                r0201 = 10;
                if (v0201 > v0100 + r0201) {
                    v0201 = v0100 + r0201;
                    d0201 = d0100;
                }
                if (v0201 > v0101 + r0201) {
                    v0201 = v0101 + r0201;
                    d0201 = d0101;
                }
            }
            if (rc.onTheMap(l1200)) {
                r1200 = 10;
                if (v1200 > v1111 + r1200) {
                    v1200 = v1111 + r1200;
                    d1200 = d1111;
                }
                if (v1200 > v1100 + r1200) {
                    v1200 = v1100 + r1200;
                    d1200 = d1100;
                }
                if (v1200 > v1101 + r1200) {
                    v1200 = v1101 + r1200;
                    d1200 = d1101;
                }
            }
            if (rc.onTheMap(l0200)) {
                r0200 = 10;
                if (v0200 > v0111 + r0200) {
                    v0200 = v0111 + r0200;
                    d0200 = d0111;
                }
                if (v0200 > v0100 + r0200) {
                    v0200 = v0100 + r0200;
                    d0200 = d0100;
                }
                if (v0200 > v0101 + r0200) {
                    v0200 = v0101 + r0200;
                    d0200 = d0101;
                }
            }
            if (rc.onTheMap(l1211)) {
                r1211 = 10;
                if (v1211 > v1111 + r1211) {
                    v1211 = v1111 + r1211;
                    d1211 = d1111;
                }
                if (v1211 > v1100 + r1211) {
                    v1211 = v1100 + r1211;
                    d1211 = d1100;
                }
            }
            if (rc.onTheMap(l0211)) {
                r0211 = 10;
                if (v0211 > v0111 + r0211) {
                    v0211 = v0111 + r0211;
                    d0211 = d0111;
                }
                if (v0211 > v0100 + r0211) {
                    v0211 = v0100 + r0211;
                    d0211 = d0100;
                }
            }
            if (rc.onTheMap(l1112)) {
                r1112 = 10;
                if (v1112 > v1111 + r1112) {
                    v1112 = v1111 + r1112;
                    d1112 = d1111;
                }
                if (v1112 > v0011 + r1112) {
                    v1112 = v0011 + r1112;
                    d1112 = d0011;
                }
            }
            if (rc.onTheMap(l0012)) {
                r0012 = 10;
                if (v0012 > v1111 + r0012) {
                    v0012 = v1111 + r0012;
                    d0012 = d1111;
                }
                if (v0012 > v0011 + r0012) {
                    v0012 = v0011 + r0012;
                    d0012 = d0011;
                }
                if (v0012 > v0111 + r0012) {
                    v0012 = v0111 + r0012;
                    d0012 = d0111;
                }
//              System.out.println("HI IT'S ME " + Double.toString(v0012));
//              System.out.println("HI IT'S ME " + d0012.toString());
            }
//          System.out.println("HI IT'S ME " + d0012.toString());
            if (rc.onTheMap(l0112)) {
                r0112 = 10;
                if (v0112 > v0011 + r0112) {
                    v0112 = v0011 + r0112;
                    d0112 = d0011;
                }
                if (v0112 > v0111 + r0112) {
                    v0112 = v0111 + r0112;
                    d0112 = d0111;
                }
            }
            if (rc.onTheMap(l1103)) {
                r1103 = 10;
                if (v1103 > v1102 + r1103) {
                    v1103 = v1102 + r1103;
                    d1103 = d1102;
                }
                if (v1103 > v0002 + r1103) {
                    v1103 = v0002 + r1103;
                    d1103 = d0002;
                }
            }
            if (rc.onTheMap(l0003)) {
                r0003 = 10;
                if (v0003 > v1102 + r0003) {
                    v0003 = v1102 + r0003;
                    d0003 = d1102;
                }
                if (v0003 > v0002 + r0003) {
                    v0003 = v0002 + r0003;
                    d0003 = d0002;
                }
                if (v0003 > v0102 + r0003) {
                    v0003 = v0102 + r0003;
                    d0003 = d0102;
                }
            }
            if (rc.onTheMap(l0103)) {
                r0103 = 10;
                if (v0103 > v0002 + r0103) {
                    v0103 = v0002 + r0103;
                    d0103 = d0002;
                }
                if (v0103 > v0102 + r0103) {
                    v0103 = v0102 + r0103;
                    d0103 = d0102;
                }
            }
            if (rc.onTheMap(l1202)) {
                r1202 = 10;
                if (v1202 > v1201 + r1202) {
                    v1202 = v1201 + r1202;
                    d1202 = d1201;
                }
                if (v1202 > v1101 + r1202) {
                    v1202 = v1101 + r1202;
                    d1202 = d1101;
                }
                if (v1202 > v1102 + r1202) {
                    v1202 = v1102 + r1202;
                    d1202 = d1102;
                }
            }
            if (rc.onTheMap(l0202)) {
                r0202 = 10;
                if (v0202 > v0101 + r0202) {
                    v0202 = v0101 + r0202;
                    d0202 = d0101;
                }
                if (v0202 > v0102 + r0202) {
                    v0202 = v0102 + r0202;
                    d0202 = d0102;
                }
                if (v0202 > v0201 + r0202) {
                    v0202 = v0201 + r0202;
                    d0202 = d0201;
                }
            }
            if (rc.onTheMap(l1301)) {
                r1301 = 10;
                if (v1301 > v1200 + r1301) {
                    v1301 = v1200 + r1301;
                    d1301 = d1200;
                }
                if (v1301 > v1201 + r1301) {
                    v1301 = v1201 + r1301;
                    d1301 = d1201;
                }
            }
            if (rc.onTheMap(l0301)) {
                r0301 = 10;
                if (v0301 > v0200 + r0301) {
                    v0301 = v0200 + r0301;
                    d0301 = d0200;
                }
                if (v0301 > v0201 + r0301) {
                    v0301 = v0201 + r0301;
                    d0301 = d0201;
                }
            }
            if (rc.onTheMap(l1300)) {
                r1300 = 10;
                if (v1300 > v1211 + r1300) {
                    v1300 = v1211 + r1300;
                    d1300 = d1211;
                }
                if (v1300 > v1200 + r1300) {
                    v1300 = v1200 + r1300;
                    d1300 = d1200;
                }
                if (v1300 > v1201 + r1300) {
                    v1300 = v1201 + r1300;
                    d1300 = d1201;
                }
            }
            if (rc.onTheMap(l0300)) {
                r0300 = 10;
                if (v0300 > v0211 + r0300) {
                    v0300 = v0211 + r0300;
                    d0300 = d0211;
                }
                if (v0300 > v0200 + r0300) {
                    v0300 = v0200 + r0300;
                    d0300 = d0200;
                }
                if (v0300 > v0201 + r0300) {
                    v0300 = v0201 + r0300;
                    d0300 = d0201;
                }
            }
            if (rc.onTheMap(l1311)) {
                r1311 = 10;
                if (v1311 > v1211 + r1311) {
                    v1311 = v1211 + r1311;
                    d1311 = d1211;
                }
                if (v1311 > v1200 + r1311) {
                    v1311 = v1200 + r1311;
                    d1311 = d1200;
                }
            }
            if (rc.onTheMap(l0311)) {
                r0311 = 10;
                if (v0311 > v0211 + r0311) {
                    v0311 = v0211 + r0311;
                    d0311 = d0211;
                }
                if (v0311 > v0200 + r0311) {
                    v0311 = v0200 + r0311;
                    d0311 = d0200;
                }
            }
            if (rc.onTheMap(l1212)) {
                r1212 = 10;
                if (v1212 > v1211 + r1212) {
                    v1212 = v1211 + r1212;
                    d1212 = d1211;
                }
                if (v1212 > v1112 + r1212) {
                    v1212 = v1112 + r1212;
                    d1212 = d1112;
                }
                if (v1212 > v1111 + r1212) {
                    v1212 = v1111 + r1212;
                    d1212 = d1111;
                }
            }
            if (rc.onTheMap(l0212)) {
                r0212 = 10;
                if (v0212 > v0112 + r0212) {
                    v0212 = v0112 + r0212;
                    d0212 = d0112;
                }
                if (v0212 > v0111 + r0212) {
                    v0212 = v0111 + r0212;
                    d0212 = d0111;
                }
                if (v0212 > v0211 + r0212) {
                    v0212 = v0211 + r0212;
                    d0212 = d0211;
                }
            }
            if (rc.onTheMap(l1113)) {
                r1113 = 10;
                if (v1113 > v1112 + r1113) {
                    v1113 = v1112 + r1113;
                    d1113 = d1112;
                }
                if (v1113 > v0012 + r1113) {
                    v1113 = v0012 + r1113;
                    d1113 = d0012;
                }
            }
            if (rc.onTheMap(l0013)) {
                r0013 = 10;
                if (v0013 > v1112 + r0013) {
                    v0013 = v1112 + r0013;
                    d0013 = d1112;
                }
                if (v0013 > v0012 + r0013) {
                    v0013 = v0012 + r0013;
                    d0013 = d0012;
                }
                if (v0013 > v0112 + r0013) {
                    v0013 = v0112 + r0013;
                    d0013 = d0112;
                }
            }
            if (rc.onTheMap(l0113)) {
                r0113 = 10;
                if (v0113 > v0012 + r0113) {
                    v0113 = v0012 + r0113;
                    d0113 = d0012;
                }
                if (v0113 > v0112 + r0113) {
                    v0113 = v0112 + r0113;
                    d0113 = d0112;
                }
            }
            if (rc.onTheMap(l1204)) {
                r1204 = 10;
                if (v1204 > v1103 + r1204) {
                    v1204 = v1103 + r1204;
                    d1204 = d1103;
                }
            }
            if (rc.onTheMap(l1104)) {
                r1104 = 10;
                if (v1104 > v1103 + r1104) {
                    v1104 = v1103 + r1104;
                    d1104 = d1103;
                }
                if (v1104 > v0003 + r1104) {
                    v1104 = v0003 + r1104;
                    d1104 = d0003;
                }
            }
            if (rc.onTheMap(l0004)) {
                r0004 = 10;
                if (v0004 > v1103 + r0004) {
                    v0004 = v1103 + r0004;
                    d0004 = d1103;
                }
                if (v0004 > v0003 + r0004) {
                    v0004 = v0003 + r0004;
                    d0004 = d0003;
                }
                if (v0004 > v0103 + r0004) {
                    v0004 = v0103 + r0004;
                    d0004 = d0103;
                }
            }
            if (rc.onTheMap(l0104)) {
                r0104 = 10;
                if (v0104 > v0003 + r0104) {
                    v0104 = v0003 + r0104;
                    d0104 = d0003;
                }
                if (v0104 > v0103 + r0104) {
                    v0104 = v0103 + r0104;
                    d0104 = d0103;
                }
            }
            if (rc.onTheMap(l0204)) {
                r0204 = 10;
                if (v0204 > v0103 + r0204) {
                    v0204 = v0103 + r0204;
                    d0204 = d0103;
                }
            }
            if (rc.onTheMap(l1303)) {
                r1303 = 10;
                if (v1303 > v1202 + r1303) {
                    v1303 = v1202 + r1303;
                    d1303 = d1202;
                }
            }
            if (rc.onTheMap(l1203)) {
                r1203 = 10;
                if (v1203 > v1202 + r1203) {
                    v1203 = v1202 + r1203;
                    d1203 = d1202;
                }
                if (v1203 > v1102 + r1203) {
                    v1203 = v1102 + r1203;
                    d1203 = d1102;
                }
                if (v1203 > v1103 + r1203) {
                    v1203 = v1103 + r1203;
                    d1203 = d1103;
                }
            }
            if (rc.onTheMap(l0203)) {
                r0203 = 10;
                if (v0203 > v0102 + r0203) {
                    v0203 = v0102 + r0203;
                    d0203 = d0102;
                }
                if (v0203 > v0103 + r0203) {
                    v0203 = v0103 + r0203;
                    d0203 = d0103;
                }
                if (v0203 > v0202 + r0203) {
                    v0203 = v0202 + r0203;
                    d0203 = d0202;
                }
            }
            if (rc.onTheMap(l0303)) {
                r0303 = 10;
                if (v0303 > v0202 + r0303) {
                    v0303 = v0202 + r0303;
                    d0303 = d0202;
                }
            }
            if (rc.onTheMap(l1402)) {
                r1402 = 10;
                if (v1402 > v1301 + r1402) {
                    v1402 = v1301 + r1402;
                    d1402 = d1301;
                }
            }
            if (rc.onTheMap(l1302)) {
                r1302 = 10;
                if (v1302 > v1301 + r1302) {
                    v1302 = v1301 + r1302;
                    d1302 = d1301;
                }
                if (v1302 > v1201 + r1302) {
                    v1302 = v1201 + r1302;
                    d1302 = d1201;
                }
                if (v1302 > v1202 + r1302) {
                    v1302 = v1202 + r1302;
                    d1302 = d1202;
                }
            }
            if (rc.onTheMap(l0302)) {
                r0302 = 10;
                if (v0302 > v0201 + r0302) {
                    v0302 = v0201 + r0302;
                    d0302 = d0201;
                }
                if (v0302 > v0202 + r0302) {
                    v0302 = v0202 + r0302;
                    d0302 = d0202;
                }
                if (v0302 > v0301 + r0302) {
                    v0302 = v0301 + r0302;
                    d0302 = d0301;
                }
            }
            if (rc.onTheMap(l0402)) {
                r0402 = 10;
                if (v0402 > v0301 + r0402) {
                    v0402 = v0301 + r0402;
                    d0402 = d0301;
                }
            }
            if (rc.onTheMap(l1401)) {
                r1401 = 10;
                if (v1401 > v1300 + r1401) {
                    v1401 = v1300 + r1401;
                    d1401 = d1300;
                }
                if (v1401 > v1301 + r1401) {
                    v1401 = v1301 + r1401;
                    d1401 = d1301;
                }
            }
            if (rc.onTheMap(l0401)) {
                r0401 = 10;
                if (v0401 > v0300 + r0401) {
                    v0401 = v0300 + r0401;
                    d0401 = d0300;
                }
                if (v0401 > v0301 + r0401) {
                    v0401 = v0301 + r0401;
                    d0401 = d0301;
                }
            }
            if (rc.onTheMap(l1400)) {
                r1400 = 10;
                if (v1400 > v1311 + r1400) {
                    v1400 = v1311 + r1400;
                    d1400 = d1311;
                }
                if (v1400 > v1300 + r1400) {
                    v1400 = v1300 + r1400;
                    d1400 = d1300;
                }
                if (v1400 > v1301 + r1400) {
                    v1400 = v1301 + r1400;
                    d1400 = d1301;
                }
            }
            if (rc.onTheMap(l0400)) {
                r0400 = 10;
                if (v0400 > v0311 + r0400) {
                    v0400 = v0311 + r0400;
                    d0400 = d0311;
                }
                if (v0400 > v0300 + r0400) {
                    v0400 = v0300 + r0400;
                    d0400 = d0300;
                }
                if (v0400 > v0301 + r0400) {
                    v0400 = v0301 + r0400;
                    d0400 = d0301;
                }
            }
            if (rc.onTheMap(l1411)) {
                r1411 = 10;
                if (v1411 > v1311 + r1411) {
                    v1411 = v1311 + r1411;
                    d1411 = d1311;
                }
                if (v1411 > v1300 + r1411) {
                    v1411 = v1300 + r1411;
                    d1411 = d1300;
                }
            }
            if (rc.onTheMap(l0411)) {
                r0411 = 10;
                if (v0411 > v0311 + r0411) {
                    v0411 = v0311 + r0411;
                    d0411 = d0311;
                }
                if (v0411 > v0300 + r0411) {
                    v0411 = v0300 + r0411;
                    d0411 = d0300;
                }
            }
            if (rc.onTheMap(l1412)) {
                r1412 = 10;
                if (v1412 > v1311 + r1412) {
                    v1412 = v1311 + r1412;
                    d1412 = d1311;
                }
            }
            if (rc.onTheMap(l1312)) {
                r1312 = 10;
                if (v1312 > v1311 + r1312) {
                    v1312 = v1311 + r1312;
                    d1312 = d1311;
                }
                if (v1312 > v1212 + r1312) {
                    v1312 = v1212 + r1312;
                    d1312 = d1212;
                }
                if (v1312 > v1211 + r1312) {
                    v1312 = v1211 + r1312;
                    d1312 = d1211;
                }
            }
            if (rc.onTheMap(l0312)) {
                r0312 = 10;
                if (v0312 > v0212 + r0312) {
                    v0312 = v0212 + r0312;
                    d0312 = d0212;
                }
                if (v0312 > v0211 + r0312) {
                    v0312 = v0211 + r0312;
                    d0312 = d0211;
                }
                if (v0312 > v0311 + r0312) {
                    v0312 = v0311 + r0312;
                    d0312 = d0311;
                }
            }
            if (rc.onTheMap(l0412)) {
                r0412 = 10;
                if (v0412 > v0311 + r0412) {
                    v0412 = v0311 + r0412;
                    d0412 = d0311;
                }
            }
            if (rc.onTheMap(l1313)) {
                r1313 = 10;
                if (v1313 > v1212 + r1313) {
                    v1313 = v1212 + r1313;
                    d1313 = d1212;
                }
            }
            if (rc.onTheMap(l1213)) {
                r1213 = 10;
                if (v1213 > v1212 + r1213) {
                    v1213 = v1212 + r1213;
                    d1213 = d1212;
                }
                if (v1213 > v1113 + r1213) {
                    v1213 = v1113 + r1213;
                    d1213 = d1113;
                }
                if (v1213 > v1112 + r1213) {
                    v1213 = v1112 + r1213;
                    d1213 = d1112;
                }
            }
            if (rc.onTheMap(l0213)) {
                r0213 = 10;
                if (v0213 > v0113 + r0213) {
                    v0213 = v0113 + r0213;
                    d0213 = d0113;
                }
                if (v0213 > v0112 + r0213) {
                    v0213 = v0112 + r0213;
                    d0213 = d0112;
                }
                if (v0213 > v0212 + r0213) {
                    v0213 = v0212 + r0213;
                    d0213 = d0212;
                }
            }
            if (rc.onTheMap(l0313)) {
                r0313 = 10;
                if (v0313 > v0212 + r0313) {
                    v0313 = v0212 + r0313;
                    d0313 = d0212;
                }
            }
            if (rc.onTheMap(l1214)) {
                r1214 = 10;
                if (v1214 > v1113 + r1214) {
                    v1214 = v1113 + r1214;
                    d1214 = d1113;
                }
            }
            if (rc.onTheMap(l1114)) {
                r1114 = 10;
                if (v1114 > v1113 + r1114) {
                    v1114 = v1113 + r1114;
                    d1114 = d1113;
                }
                if (v1114 > v0013 + r1114) {
                    v1114 = v0013 + r1114;
                    d1114 = d0013;
                }
            }
            if (rc.onTheMap(l0014)) {
                r0014 = 10;
                if (v0014 > v1113 + r0014) {
                    v0014 = v1113 + r0014;
                    d0014 = d1113;
                }
                if (v0014 > v0013 + r0014) {
                    v0014 = v0013 + r0014;
                    d0014 = d0013;
                }
                if (v0014 > v0113 + r0014) {
                    v0014 = v0113 + r0014;
                    d0014 = d0113;
                }
            }
            if (rc.onTheMap(l0114)) {
                r0114 = 10;
                if (v0114 > v0013 + r0114) {
                    v0114 = v0013 + r0114;
                    d0114 = d0013;
                }
                if (v0114 > v0113 + r0114) {
                    v0114 = v0113 + r0114;
                    d0114 = d0113;
                }
            }
            if (rc.onTheMap(l0214)) {
                r0214 = 10;
                if (v0214 > v0113 + r0214) {
                    v0214 = v0113 + r0214;
                    d0214 = d0113;
                }
            }
            int dx = target.x - l0000.x;
            int dy = target.y - l0000.y;

//          System.out.println("DISTANCE: " + Integer.toString(dx) + " " + Integer.toString(dy));
//          System.out.println("DIRECTION: " + d0012.toString());

            switch(dx) {
                case -4:
                    switch(dy) {
                        case -2:
                            return d1412;
                        case -1:
                            return d1411;
                        case 0:
                            return d1400;
                        case 1:
                            return d1401;
                        case 2:
                            return d1402;
                    }
                    break;
                case -3:
                    switch(dy) {
                        case -3:
                            return d1313;
                        case -2:
                            return d1312;
                        case -1:
                            return d1311;
                        case 0:
                            return d1300;
                        case 1:
                            return d1301;
                        case 2:
                            return d1302;
                        case 3:
                            return d1303;
                    }
                    break;
                case -2:
                    switch(dy) {
                        case -4:
                            return d1214;
                        case -3:
                            return d1213;
                        case -2:
                            return d1212;
                        case -1:
                            return d1211;
                        case 0:
                            return d1200;
                        case 1:
                            return d1201;
                        case 2:
                            return d1202;
                        case 3:
                            return d1203;
                        case 4:
                            return d1204;
                    }
                    break;
                case -1:
                    switch(dy) {
                        case -4:
                            return d1114;
                        case -3:
                            return d1113;
                        case -2:
                            return d1112;
                        case -1:
                            return d1111;
                        case 0:
                            return d1100;
                        case 1:
                            return d1101;
                        case 2:
                            return d1102;
                        case 3:
                            return d1103;
                        case 4:
                            return d1104;
                    }
                    break;
                case 0:
                    switch(dy) {
                        case -4:
                            return d0014;
                        case -3:
                            return d0013;
                        case -2:
                            return d0012;
                        case -1:
                            return d0011;
                        case 0:
                            return d0000;
                        case 1:
                            return d0001;
                        case 2:
                            return d0002;
                        case 3:
                            return d0003;
                        case 4:
                            return d0004;
                    }
                    break;
                case 1:
                    switch(dy) {
                        case -4:
                            return d0114;
                        case -3:
                            return d0113;
                        case -2:
                            return d0112;
                        case -1:
                            return d0111;
                        case 0:
                            return d0100;
                        case 1:
                            return d0101;
                        case 2:
                            return d0102;
                        case 3:
                            return d0103;
                        case 4:
                            return d0104;
                    }
                    break;
                case 2:
                    switch(dy) {
                        case -4:
                            return d0214;
                        case -3:
                            return d0213;
                        case -2:
                            return d0212;
                        case -1:
                            return d0211;
                        case 0:
                            return d0200;
                        case 1:
                            return d0201;
                        case 2:
                            return d0202;
                        case 3:
                            return d0203;
                        case 4:
                            return d0204;
                    }
                    break;
                case 3:
                    switch(dy) {
                        case -3:
                            return d0313;
                        case -2:
                            return d0312;
                        case -1:
                            return d0311;
                        case 0:
                            return d0300;
                        case 1:
                            return d0301;
                        case 2:
                            return d0302;
                        case 3:
                            return d0303;
                    }
                    break;
                case 4:
                    switch(dy) {
                        case -2:
                            return d0412;
                        case -1:
                            return d0411;
                        case 0:
                            return d0400;
                        case 1:
                            return d0401;
                        case 2:
                            return d0402;
                    }
                    break;
            }

            Direction ans = null;
            double bestEstimation = 0;
            double initialDist = Math.sqrt(l0000.distanceSquaredTo(target));

            double dist1412 = (initialDist - Math.sqrt(l1412.distanceSquaredTo(target))) / v1412;
            if (dist1412 > bestEstimation) {
                bestEstimation = dist1412;
                ans = d1412;
            }
            double dist1411 = (initialDist - Math.sqrt(l1411.distanceSquaredTo(target))) / v1411;
            if (dist1411 > bestEstimation) {
                bestEstimation = dist1411;
                ans = d1411;
            }
            double dist1400 = (initialDist - Math.sqrt(l1400.distanceSquaredTo(target))) / v1400;
            if (dist1400 > bestEstimation) {
                bestEstimation = dist1400;
                ans = d1400;
            }
            double dist1401 = (initialDist - Math.sqrt(l1401.distanceSquaredTo(target))) / v1401;
            if (dist1401 > bestEstimation) {
                bestEstimation = dist1401;
                ans = d1401;
            }
            double dist1402 = (initialDist - Math.sqrt(l1402.distanceSquaredTo(target))) / v1402;
            if (dist1402 > bestEstimation) {
                bestEstimation = dist1402;
                ans = d1402;
            }
            double dist1313 = (initialDist - Math.sqrt(l1313.distanceSquaredTo(target))) / v1313;
            if (dist1313 > bestEstimation) {
                bestEstimation = dist1313;
                ans = d1313;
            }
            double dist1312 = (initialDist - Math.sqrt(l1312.distanceSquaredTo(target))) / v1312;
            if (dist1312 > bestEstimation) {
                bestEstimation = dist1312;
                ans = d1312;
            }
            double dist1302 = (initialDist - Math.sqrt(l1302.distanceSquaredTo(target))) / v1302;
            if (dist1302 > bestEstimation) {
                bestEstimation = dist1302;
                ans = d1302;
            }
            double dist1303 = (initialDist - Math.sqrt(l1303.distanceSquaredTo(target))) / v1303;
            if (dist1303 > bestEstimation) {
                bestEstimation = dist1303;
                ans = d1303;
            }
            double dist1214 = (initialDist - Math.sqrt(l1214.distanceSquaredTo(target))) / v1214;
            if (dist1214 > bestEstimation) {
                bestEstimation = dist1214;
                ans = d1214;
            }
            double dist1213 = (initialDist - Math.sqrt(l1213.distanceSquaredTo(target))) / v1213;
            if (dist1213 > bestEstimation) {
                bestEstimation = dist1213;
                ans = d1213;
            }
            double dist1203 = (initialDist - Math.sqrt(l1203.distanceSquaredTo(target))) / v1203;
            if (dist1203 > bestEstimation) {
                bestEstimation = dist1203;
                ans = d1203;
            }
            double dist1204 = (initialDist - Math.sqrt(l1204.distanceSquaredTo(target))) / v1204;
            if (dist1204 > bestEstimation) {
                bestEstimation = dist1204;
                ans = d1204;
            }
            double dist1114 = (initialDist - Math.sqrt(l1114.distanceSquaredTo(target))) / v1114;
            if (dist1114 > bestEstimation) {
                bestEstimation = dist1114;
                ans = d1114;
            }
            double dist1104 = (initialDist - Math.sqrt(l1104.distanceSquaredTo(target))) / v1104;
            if (dist1104 > bestEstimation) {
                bestEstimation = dist1104;
                ans = d1104;
            }
            double dist0014 = (initialDist - Math.sqrt(l0014.distanceSquaredTo(target))) / v0014;
            if (dist0014 > bestEstimation) {
                bestEstimation = dist0014;
                ans = d0014;
            }
            double dist0004 = (initialDist - Math.sqrt(l0004.distanceSquaredTo(target))) / v0004;
            if (dist0004 > bestEstimation) {
                bestEstimation = dist0004;
                ans = d0004;
            }
            double dist0114 = (initialDist - Math.sqrt(l0114.distanceSquaredTo(target))) / v0114;
            if (dist0114 > bestEstimation) {
                bestEstimation = dist0114;
                ans = d0114;
            }
            double dist0104 = (initialDist - Math.sqrt(l0104.distanceSquaredTo(target))) / v0104;
            if (dist0104 > bestEstimation) {
                bestEstimation = dist0104;
                ans = d0104;
            }
            double dist0214 = (initialDist - Math.sqrt(l0214.distanceSquaredTo(target))) / v0214;
            if (dist0214 > bestEstimation) {
                bestEstimation = dist0214;
                ans = d0214;
            }
            double dist0213 = (initialDist - Math.sqrt(l0213.distanceSquaredTo(target))) / v0213;
            if (dist0213 > bestEstimation) {
                bestEstimation = dist0213;
                ans = d0213;
            }
            double dist0203 = (initialDist - Math.sqrt(l0203.distanceSquaredTo(target))) / v0203;
            if (dist0203 > bestEstimation) {
                bestEstimation = dist0203;
                ans = d0203;
            }
            double dist0204 = (initialDist - Math.sqrt(l0204.distanceSquaredTo(target))) / v0204;
            if (dist0204 > bestEstimation) {
                bestEstimation = dist0204;
                ans = d0204;
            }
            double dist0313 = (initialDist - Math.sqrt(l0313.distanceSquaredTo(target))) / v0313;
            if (dist0313 > bestEstimation) {
                bestEstimation = dist0313;
                ans = d0313;
            }
            double dist0312 = (initialDist - Math.sqrt(l0312.distanceSquaredTo(target))) / v0312;
            if (dist0312 > bestEstimation) {
                bestEstimation = dist0312;
                ans = d0312;
            }
            double dist0302 = (initialDist - Math.sqrt(l0302.distanceSquaredTo(target))) / v0302;
            if (dist0302 > bestEstimation) {
                bestEstimation = dist0302;
                ans = d0302;
            }
            double dist0303 = (initialDist - Math.sqrt(l0303.distanceSquaredTo(target))) / v0303;
            if (dist0303 > bestEstimation) {
                bestEstimation = dist0303;
                ans = d0303;
            }
            double dist0412 = (initialDist - Math.sqrt(l0412.distanceSquaredTo(target))) / v0412;
            if (dist0412 > bestEstimation) {
                bestEstimation = dist0412;
                ans = d0412;
            }
            double dist0411 = (initialDist - Math.sqrt(l0411.distanceSquaredTo(target))) / v0411;
            if (dist0411 > bestEstimation) {
                bestEstimation = dist0411;
                ans = d0411;
            }
            double dist0400 = (initialDist - Math.sqrt(l0400.distanceSquaredTo(target))) / v0400;
            if (dist0400 > bestEstimation) {
                bestEstimation = dist0400;
                ans = d0400;
            }
            double dist0401 = (initialDist - Math.sqrt(l0401.distanceSquaredTo(target))) / v0401;
            if (dist0401 > bestEstimation) {
                bestEstimation = dist0401;
                ans = d0401;
            }
            double dist0402 = (initialDist - Math.sqrt(l0402.distanceSquaredTo(target))) / v0402;
            if (dist0402 > bestEstimation) {
//              bestEstimation = dist0402;
                ans = d0402;
            }
            return ans;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
