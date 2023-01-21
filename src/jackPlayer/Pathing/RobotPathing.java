// Inspired by https://github.com/IvanGeffner/battlecode2021/blob/master/thirtyone/BFSMuckraker.java.
package jackPlayer.Pathing;
import battlecode.common.*;

public class RobotPathing extends Pathing {

    public RobotPathing(RobotController rc) {
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

	private MapLocation loc1400; // location representing relative coordinate (-4, 0)
	private int dist1400;        // shortest distance to location from current location
	private Direction dir1400;   // best direction to take now to optimally get to location

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

	private MapLocation loc0400; // location representing relative coordinate (4, 0)
	private int dist0400;        // shortest distance to location from current location
	private Direction dir0400;   // best direction to take now to optimally get to location

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

		/* 
		 * PART 2: Single iteration of Bellman-Ford
		 * The single iteration of Bellman-Ford is ran here
		*/        

		if (rc.canSenseLocation(loc1100) && rc.sensePassability(loc1100)) { // check (-1, 0)
			if (!rc.isLocationOccupied(loc1100)) { 
				if (dist1100 > dist0000) { // from (0, 0)
				    dist1100 = dist0000;
				    dir1100 = Direction.WEST;
				}
				dist1100 += 1 + (rc.senseMapInfo(loc1100).hasCloud() ? 10 : 0);
			}
		}

		if (rc.canSenseLocation(loc0011) && rc.sensePassability(loc0011)) { // check (0, -1)
			if (!rc.isLocationOccupied(loc0011)) { 
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

		if (rc.canSenseLocation(loc0001) && rc.sensePassability(loc0001)) { // check (0, 1)
			if (!rc.isLocationOccupied(loc0001)) { 
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

		if (rc.canSenseLocation(loc0100) && rc.sensePassability(loc0100)) { // check (1, 0)
			if (!rc.isLocationOccupied(loc0100)) { 
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

		if (rc.canSenseLocation(loc1111) && rc.sensePassability(loc1111)) { // check (-1, -1)
			if (!rc.isLocationOccupied(loc1111)) { 
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

		if (rc.canSenseLocation(loc1101) && rc.sensePassability(loc1101)) { // check (-1, 1)
			if (!rc.isLocationOccupied(loc1101)) { 
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

		if (rc.canSenseLocation(loc0111) && rc.sensePassability(loc0111)) { // check (1, -1)
			if (!rc.isLocationOccupied(loc0111)) { 
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

		if (rc.canSenseLocation(loc0101) && rc.sensePassability(loc0101)) { // check (1, 1)
			if (!rc.isLocationOccupied(loc0101)) { 
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

		if (rc.canSenseLocation(loc1200) && rc.sensePassability(loc1200)) { // check (-2, 0)
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

		if (rc.canSenseLocation(loc0012) && rc.sensePassability(loc0012)) { // check (0, -2)
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

		if (rc.canSenseLocation(loc0002) && rc.sensePassability(loc0002)) { // check (0, 2)
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

		if (rc.canSenseLocation(loc0200) && rc.sensePassability(loc0200)) { // check (2, 0)
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

		if (rc.canSenseLocation(loc1211) && rc.sensePassability(loc1211)) { // check (-2, -1)
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

		if (rc.canSenseLocation(loc1201) && rc.sensePassability(loc1201)) { // check (-2, 1)
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

		if (rc.canSenseLocation(loc1112) && rc.sensePassability(loc1112)) { // check (-1, -2)
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

		if (rc.canSenseLocation(loc1102) && rc.sensePassability(loc1102)) { // check (-1, 2)
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

		if (rc.canSenseLocation(loc0112) && rc.sensePassability(loc0112)) { // check (1, -2)
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

		if (rc.canSenseLocation(loc0102) && rc.sensePassability(loc0102)) { // check (1, 2)
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

		if (rc.canSenseLocation(loc0211) && rc.sensePassability(loc0211)) { // check (2, -1)
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

		if (rc.canSenseLocation(loc0201) && rc.sensePassability(loc0201)) { // check (2, 1)
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

		if (rc.canSenseLocation(loc1212) && rc.sensePassability(loc1212)) { // check (-2, -2)
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

		if (rc.canSenseLocation(loc1202) && rc.sensePassability(loc1202)) { // check (-2, 2)
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

		if (rc.canSenseLocation(loc0212) && rc.sensePassability(loc0212)) { // check (2, -2)
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

		if (rc.canSenseLocation(loc0202) && rc.sensePassability(loc0202)) { // check (2, 2)
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

		if (rc.canSenseLocation(loc1300) && rc.sensePassability(loc1300)) { // check (-3, 0)
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

		if (rc.canSenseLocation(loc0013) && rc.sensePassability(loc0013)) { // check (0, -3)
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

		if (rc.canSenseLocation(loc0003) && rc.sensePassability(loc0003)) { // check (0, 3)
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

		if (rc.canSenseLocation(loc0300) && rc.sensePassability(loc0300)) { // check (3, 0)
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

		if (rc.canSenseLocation(loc1311) && rc.sensePassability(loc1311)) { // check (-3, -1)
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

		if (rc.canSenseLocation(loc1301) && rc.sensePassability(loc1301)) { // check (-3, 1)
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

		if (rc.canSenseLocation(loc1113) && rc.sensePassability(loc1113)) { // check (-1, -3)
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

		if (rc.canSenseLocation(loc1103) && rc.sensePassability(loc1103)) { // check (-1, 3)
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

		if (rc.canSenseLocation(loc0113) && rc.sensePassability(loc0113)) { // check (1, -3)
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

		if (rc.canSenseLocation(loc0103) && rc.sensePassability(loc0103)) { // check (1, 3)
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

		if (rc.canSenseLocation(loc0311) && rc.sensePassability(loc0311)) { // check (3, -1)
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

		if (rc.canSenseLocation(loc0301) && rc.sensePassability(loc0301)) { // check (3, 1)
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

		if (rc.canSenseLocation(loc1312) && rc.sensePassability(loc1312)) { // check (-3, -2)
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

		if (rc.canSenseLocation(loc1302) && rc.sensePassability(loc1302)) { // check (-3, 2)
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

		if (rc.canSenseLocation(loc1213) && rc.sensePassability(loc1213)) { // check (-2, -3)
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

		if (rc.canSenseLocation(loc1203) && rc.sensePassability(loc1203)) { // check (-2, 3)
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

		if (rc.canSenseLocation(loc0213) && rc.sensePassability(loc0213)) { // check (2, -3)
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

		if (rc.canSenseLocation(loc0203) && rc.sensePassability(loc0203)) { // check (2, 3)
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

		if (rc.canSenseLocation(loc0312) && rc.sensePassability(loc0312)) { // check (3, -2)
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

		if (rc.canSenseLocation(loc0302) && rc.sensePassability(loc0302)) { // check (3, 2)
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

		if (rc.canSenseLocation(loc1400) && rc.sensePassability(loc1400)) { // check (-4, 0)
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

		if (rc.canSenseLocation(loc0014) && rc.sensePassability(loc0014)) { // check (0, -4)
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

		if (rc.canSenseLocation(loc0004) && rc.sensePassability(loc0004)) { // check (0, 4)
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

		if (rc.canSenseLocation(loc0400) && rc.sensePassability(loc0400)) { // check (4, 0)
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


		/* 
		 * PART 4: Edge Checking
		 * If the target location wasn"t in the region Bellman-Ford ran on,
		 * then we will try to find a edge node with the most optimal direction
		 * to move in
		*/   

		Direction ans = null;
		double bestScore = 0;
		double currDist = Math.sqrt(loc0000.distanceSquaredTo(target));

		double score1400 = (currDist - Math.sqrt(loc1400.distanceSquaredTo(target))) / dist1400; // (-4, 0)
		if (score1400 > bestScore && dir1400 != null) {
		    bestScore = score1400;
		    ans = dir1400;
		}

		double score1312 = (currDist - Math.sqrt(loc1312.distanceSquaredTo(target))) / dist1312; // (-3, -2)
		if (score1312 > bestScore && dir1312 != null) {
		    bestScore = score1312;
		    ans = dir1312;
		}

		double score1311 = (currDist - Math.sqrt(loc1311.distanceSquaredTo(target))) / dist1311; // (-3, -1)
		if (score1311 > bestScore && dir1311 != null) {
		    bestScore = score1311;
		    ans = dir1311;
		}

		double score1300 = (currDist - Math.sqrt(loc1300.distanceSquaredTo(target))) / dist1300; // (-3, 0)
		if (score1300 > bestScore && dir1300 != null) {
		    bestScore = score1300;
		    ans = dir1300;
		}

		double score1301 = (currDist - Math.sqrt(loc1301.distanceSquaredTo(target))) / dist1301; // (-3, 1)
		if (score1301 > bestScore && dir1301 != null) {
		    bestScore = score1301;
		    ans = dir1301;
		}

		double score1302 = (currDist - Math.sqrt(loc1302.distanceSquaredTo(target))) / dist1302; // (-3, 2)
		if (score1302 > bestScore && dir1302 != null) {
		    bestScore = score1302;
		    ans = dir1302;
		}

		double score1213 = (currDist - Math.sqrt(loc1213.distanceSquaredTo(target))) / dist1213; // (-2, -3)
		if (score1213 > bestScore && dir1213 != null) {
		    bestScore = score1213;
		    ans = dir1213;
		}

		double score1212 = (currDist - Math.sqrt(loc1212.distanceSquaredTo(target))) / dist1212; // (-2, -2)
		if (score1212 > bestScore && dir1212 != null) {
		    bestScore = score1212;
		    ans = dir1212;
		}

		double score1211 = (currDist - Math.sqrt(loc1211.distanceSquaredTo(target))) / dist1211; // (-2, -1)
		if (score1211 > bestScore && dir1211 != null) {
		    bestScore = score1211;
		    ans = dir1211;
		}

		double score1200 = (currDist - Math.sqrt(loc1200.distanceSquaredTo(target))) / dist1200; // (-2, 0)
		if (score1200 > bestScore && dir1200 != null) {
		    bestScore = score1200;
		    ans = dir1200;
		}

		double score1201 = (currDist - Math.sqrt(loc1201.distanceSquaredTo(target))) / dist1201; // (-2, 1)
		if (score1201 > bestScore && dir1201 != null) {
		    bestScore = score1201;
		    ans = dir1201;
		}

		double score1202 = (currDist - Math.sqrt(loc1202.distanceSquaredTo(target))) / dist1202; // (-2, 2)
		if (score1202 > bestScore && dir1202 != null) {
		    bestScore = score1202;
		    ans = dir1202;
		}

		double score1203 = (currDist - Math.sqrt(loc1203.distanceSquaredTo(target))) / dist1203; // (-2, 3)
		if (score1203 > bestScore && dir1203 != null) {
		    bestScore = score1203;
		    ans = dir1203;
		}

		double score1113 = (currDist - Math.sqrt(loc1113.distanceSquaredTo(target))) / dist1113; // (-1, -3)
		if (score1113 > bestScore && dir1113 != null) {
		    bestScore = score1113;
		    ans = dir1113;
		}

		double score1112 = (currDist - Math.sqrt(loc1112.distanceSquaredTo(target))) / dist1112; // (-1, -2)
		if (score1112 > bestScore && dir1112 != null) {
		    bestScore = score1112;
		    ans = dir1112;
		}

		double score1111 = (currDist - Math.sqrt(loc1111.distanceSquaredTo(target))) / dist1111; // (-1, -1)
		if (score1111 > bestScore && dir1111 != null) {
		    bestScore = score1111;
		    ans = dir1111;
		}

		double score1100 = (currDist - Math.sqrt(loc1100.distanceSquaredTo(target))) / dist1100; // (-1, 0)
		if (score1100 > bestScore && dir1100 != null) {
		    bestScore = score1100;
		    ans = dir1100;
		}

		double score1101 = (currDist - Math.sqrt(loc1101.distanceSquaredTo(target))) / dist1101; // (-1, 1)
		if (score1101 > bestScore && dir1101 != null) {
		    bestScore = score1101;
		    ans = dir1101;
		}

		double score1102 = (currDist - Math.sqrt(loc1102.distanceSquaredTo(target))) / dist1102; // (-1, 2)
		if (score1102 > bestScore && dir1102 != null) {
		    bestScore = score1102;
		    ans = dir1102;
		}

		double score1103 = (currDist - Math.sqrt(loc1103.distanceSquaredTo(target))) / dist1103; // (-1, 3)
		if (score1103 > bestScore && dir1103 != null) {
		    bestScore = score1103;
		    ans = dir1103;
		}

		double score0014 = (currDist - Math.sqrt(loc0014.distanceSquaredTo(target))) / dist0014; // (0, -4)
		if (score0014 > bestScore && dir0014 != null) {
		    bestScore = score0014;
		    ans = dir0014;
		}

		double score0013 = (currDist - Math.sqrt(loc0013.distanceSquaredTo(target))) / dist0013; // (0, -3)
		if (score0013 > bestScore && dir0013 != null) {
		    bestScore = score0013;
		    ans = dir0013;
		}

		double score0012 = (currDist - Math.sqrt(loc0012.distanceSquaredTo(target))) / dist0012; // (0, -2)
		if (score0012 > bestScore && dir0012 != null) {
		    bestScore = score0012;
		    ans = dir0012;
		}

		double score0011 = (currDist - Math.sqrt(loc0011.distanceSquaredTo(target))) / dist0011; // (0, -1)
		if (score0011 > bestScore && dir0011 != null) {
		    bestScore = score0011;
		    ans = dir0011;
		}

		double score0001 = (currDist - Math.sqrt(loc0001.distanceSquaredTo(target))) / dist0001; // (0, 1)
		if (score0001 > bestScore && dir0001 != null) {
		    bestScore = score0001;
		    ans = dir0001;
		}

		double score0002 = (currDist - Math.sqrt(loc0002.distanceSquaredTo(target))) / dist0002; // (0, 2)
		if (score0002 > bestScore && dir0002 != null) {
		    bestScore = score0002;
		    ans = dir0002;
		}

		double score0003 = (currDist - Math.sqrt(loc0003.distanceSquaredTo(target))) / dist0003; // (0, 3)
		if (score0003 > bestScore && dir0003 != null) {
		    bestScore = score0003;
		    ans = dir0003;
		}

		double score0004 = (currDist - Math.sqrt(loc0004.distanceSquaredTo(target))) / dist0004; // (0, 4)
		if (score0004 > bestScore && dir0004 != null) {
		    bestScore = score0004;
		    ans = dir0004;
		}

		double score0113 = (currDist - Math.sqrt(loc0113.distanceSquaredTo(target))) / dist0113; // (1, -3)
		if (score0113 > bestScore && dir0113 != null) {
		    bestScore = score0113;
		    ans = dir0113;
		}

		double score0112 = (currDist - Math.sqrt(loc0112.distanceSquaredTo(target))) / dist0112; // (1, -2)
		if (score0112 > bestScore && dir0112 != null) {
		    bestScore = score0112;
		    ans = dir0112;
		}

		double score0111 = (currDist - Math.sqrt(loc0111.distanceSquaredTo(target))) / dist0111; // (1, -1)
		if (score0111 > bestScore && dir0111 != null) {
		    bestScore = score0111;
		    ans = dir0111;
		}

		double score0100 = (currDist - Math.sqrt(loc0100.distanceSquaredTo(target))) / dist0100; // (1, 0)
		if (score0100 > bestScore && dir0100 != null) {
		    bestScore = score0100;
		    ans = dir0100;
		}

		double score0101 = (currDist - Math.sqrt(loc0101.distanceSquaredTo(target))) / dist0101; // (1, 1)
		if (score0101 > bestScore && dir0101 != null) {
		    bestScore = score0101;
		    ans = dir0101;
		}

		double score0102 = (currDist - Math.sqrt(loc0102.distanceSquaredTo(target))) / dist0102; // (1, 2)
		if (score0102 > bestScore && dir0102 != null) {
		    bestScore = score0102;
		    ans = dir0102;
		}

		double score0103 = (currDist - Math.sqrt(loc0103.distanceSquaredTo(target))) / dist0103; // (1, 3)
		if (score0103 > bestScore && dir0103 != null) {
		    bestScore = score0103;
		    ans = dir0103;
		}

		double score0213 = (currDist - Math.sqrt(loc0213.distanceSquaredTo(target))) / dist0213; // (2, -3)
		if (score0213 > bestScore && dir0213 != null) {
		    bestScore = score0213;
		    ans = dir0213;
		}

		double score0212 = (currDist - Math.sqrt(loc0212.distanceSquaredTo(target))) / dist0212; // (2, -2)
		if (score0212 > bestScore && dir0212 != null) {
		    bestScore = score0212;
		    ans = dir0212;
		}

		double score0211 = (currDist - Math.sqrt(loc0211.distanceSquaredTo(target))) / dist0211; // (2, -1)
		if (score0211 > bestScore && dir0211 != null) {
		    bestScore = score0211;
		    ans = dir0211;
		}

		double score0200 = (currDist - Math.sqrt(loc0200.distanceSquaredTo(target))) / dist0200; // (2, 0)
		if (score0200 > bestScore && dir0200 != null) {
		    bestScore = score0200;
		    ans = dir0200;
		}

		double score0201 = (currDist - Math.sqrt(loc0201.distanceSquaredTo(target))) / dist0201; // (2, 1)
		if (score0201 > bestScore && dir0201 != null) {
		    bestScore = score0201;
		    ans = dir0201;
		}

		double score0202 = (currDist - Math.sqrt(loc0202.distanceSquaredTo(target))) / dist0202; // (2, 2)
		if (score0202 > bestScore && dir0202 != null) {
		    bestScore = score0202;
		    ans = dir0202;
		}

		double score0203 = (currDist - Math.sqrt(loc0203.distanceSquaredTo(target))) / dist0203; // (2, 3)
		if (score0203 > bestScore && dir0203 != null) {
		    bestScore = score0203;
		    ans = dir0203;
		}

		double score0312 = (currDist - Math.sqrt(loc0312.distanceSquaredTo(target))) / dist0312; // (3, -2)
		if (score0312 > bestScore && dir0312 != null) {
		    bestScore = score0312;
		    ans = dir0312;
		}

		double score0311 = (currDist - Math.sqrt(loc0311.distanceSquaredTo(target))) / dist0311; // (3, -1)
		if (score0311 > bestScore && dir0311 != null) {
		    bestScore = score0311;
		    ans = dir0311;
		}

		double score0300 = (currDist - Math.sqrt(loc0300.distanceSquaredTo(target))) / dist0300; // (3, 0)
		if (score0300 > bestScore && dir0300 != null) {
		    bestScore = score0300;
		    ans = dir0300;
		}

		double score0301 = (currDist - Math.sqrt(loc0301.distanceSquaredTo(target))) / dist0301; // (3, 1)
		if (score0301 > bestScore && dir0301 != null) {
		    bestScore = score0301;
		    ans = dir0301;
		}

		double score0302 = (currDist - Math.sqrt(loc0302.distanceSquaredTo(target))) / dist0302; // (3, 2)
		if (score0302 > bestScore && dir0302 != null) {
		    bestScore = score0302;
		    ans = dir0302;
		}

		double score0400 = (currDist - Math.sqrt(loc0400.distanceSquaredTo(target))) / dist0400; // (4, 0)
		if (score0400 > bestScore && dir0400 != null) {
		    bestScore = score0400;
		    ans = dir0400;
		}

        return ans;
    }
}
