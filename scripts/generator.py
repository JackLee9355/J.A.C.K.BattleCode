import itertools
import textwrap
import sys

# The following code generates a Bellman-Ford approach to pathing, there are 4 major parts in this code generate
# PART 1: Initialization & Build
# - We simply generate 3 different variables relative to each location in the vision of the robot.
# - These variables are location, distance, and direction and these variables are setup for each point on the map.
# - We then initialize it in the beginning of getBestDirection()
# PART 2: Single iteration of Bellman-Ford
# - We simply build a summation on multiple paths, with attached costs to them in this part.
# PART 3: Massive Switch Statement
# - This simply checks (very quickly) if the target location is within the vision of the robot, if so.
# - The direction attached to that node will be the most optimal direction to move in.
# PART 4: Edge Checking
# - The code remaining after the switch statement block checks the edge nodes for the best direction.
# - This is comparison is perform by a heuristic. we are trying to choose an edge tile that maximizes (distsq(start, l_i) - distsq(l_i, target))
# - (i.e. it decreases the distance to the target by as much as possible) But since we"re operating on a grid, there are likely going to be ties.
# - So to break the ties, they divide by bellman_ford_dist(l_i, target).

# NORMAL VISION
# NOTE: We might want to reduce some of these visions if we run into bytecost limits
VISION_EUCLIDEAN_DISTANCE = {"Carrier": 16, "Launcher": 20, "Destabilizer": 20, "Booster": 20, "Amplifier": 34}
SMALLER_VISION_EUCLIDEAN_DISTANCE = {"Carrier": 10, "Launcher": 10, "Destabilizer": 10, "Booster": 10, "Amplifier": 24}

DIRECTIONS = {
    (1, 0): "Direction.EAST",
    (-1, 0): "Direction.WEST",
    (0, 1): "Direction.NORTH",
    (0, -1): "Direction.SOUTH",
    (1, 1): "Direction.NORTHEAST",
    (-1, 1): "Direction.NORTHWEST",
    (1, -1): "Direction.SOUTHEAST",
    (-1, -1): "Direction.SOUTHWEST",
}

""" UTILITY FUNCTIONS """

# Encoding takes a (x, y) coordinate and encodes into to a formate where 4 digits
# represent two pairs with the first digit referring to the sign (+ or -) of that digit
# For Example: loc1315 -> (-3, -5), loc0411 -> (4, -1)
def encode(x: int, y: int) -> str:
    encoding = "0" + str(x) if x >= 0 else "1" + str(abs(x))
    encoding += "0" + str(y) if y >= 0 else "1" + str(abs(y))
    return encoding

def distanceSquared(x: int, y: int) -> int:
    return x * x + y * y

def sign(x: int) -> int:
    if x > 0:
        return 1
    if x < 0:
        return -1
    return 0

""" GENERATION FUNCTIONS """

# PART 1: Initialization
def generateVariables(vision: int) -> str:
    res = textwrap.indent(textwrap.dedent(f"""
        /*
         * PART 1: Initialization
         * All the locations that will be explored formatted as:
         *  0 -> Positive && 1 -> Negative
         *  Location: loc(0 or 1)(x)(0 or 1)y
         *  Distance: dist(0 or 1)(x)(0 or 1)y
         *  Direction: dir(0 or 1)(x)(0 or 1)y
        */        
    """), "\t")
    cnt = 0
    for x, y in itertools.product(range(-8, 8), range(-8, 8)):
        if distanceSquared(x, y) <= vision:
            cnt += 1
            res += textwrap.indent(textwrap.dedent(f"""
                private MapLocation loc{encode(x, y)}; // location representing relative coordinate ({x}, {y})
                private int dist{encode(x, y)};        // shortest distance to location from current location
                private Direction dir{encode(x, y)};   // best direction to take now to optimally get to location
            """), "\t")

    return res + "\n"

# PART 1: Build
def generateInitialization(vision: int) -> str:
    res = textwrap.indent(textwrap.dedent(f"""
        /* 
         * PART 1: Build
         * Builds a graph from the vision radius of the robot
        */        
    """), "\t\t")

    res += textwrap.indent(textwrap.dedent(f"""
        loc{encode(0, 0)} = rc.getLocation();
        dist{encode(0, 0)} = 0;
        dir{encode(0, 0)} = Direction.CENTER;
    """),"\t\t")

    for r in range(1, vision + 1):
        for x, y in itertools.product(range(-8, 8), range(-8, 8)):
            if distanceSquared(x, y) == r:
                adjLocation = encode(x - sign(x), y - sign(y))
                direction = DIRECTIONS[(sign(x), sign(y))]
                res += textwrap.indent(textwrap.dedent(f"""
                    loc{encode(x, y)} = loc{adjLocation}.add({direction}); // ({x}, {y}) from ({x - sign(x)}, {y - sign(y)})
                    dist{encode(x, y)} = 99999;
                    dir{encode(x, y)} = null;
                """), "\t\t")

    return res

# PART 2: Single iteration of Bellman-Ford
def generateBFS(vision: int) -> str:
    res = textwrap.indent(textwrap.dedent(f"""
        /* 
         * PART 2: Single iteration of Bellman-Ford
         * The single iteration of Bellman-Ford is ran here
        */        
    """), "\t\t")

    visited = set([encode(0, 0)])

    for r2 in range(1, vision + 1):
        for x, y in itertools.product(range(-8, 8), range(-8, 8)):
            if distanceSquared(x, y) == r2:
                res += textwrap.indent(textwrap.dedent(f"""
                    if (rc.canSenseLocation(loc{encode(x, y)})) {{ // check ({x}, {y})
                """), "\t\t").rstrip("\n")

                
                if r2 <= 2:
                    res += textwrap.indent(textwrap.dedent(f"""
                        if (!rc.isLocationOccupied(loc{encode(x, y)}) && rc.sensePassability(loc{encode(x, y)})) {{ 
                    """), "\t\t\t").rstrip("\n")

                surroundings = []
                for dx, dy in itertools.product(range(-1, 2), range(-1, 2)):
                    if (dx, dy) != (0, 0) and distanceSquared(x + dx, y + dy) <= vision:
                        surroundings.append((dx, dy))
                surroundings.sort(key = lambda dist: distanceSquared(x + dist[0], y + dist[1]))

                indent = "\t\t\t\t" if r2 <= 2 else "\t\t\t"

                for dx, dy in surroundings:
                    if encode(x + dx, y + dy) in visited:
                        res += textwrap.indent(textwrap.dedent(f"""
                            if (dist{encode(x, y)} > dist{encode(x + dx, y + dy)}) {{ // from ({x + dx}, {y + dy})
                                dist{encode(x, y)} = dist{encode(x + dx, y + dy)};
                                dir{encode(x, y)} = {DIRECTIONS[(-dx, -dy)] if (x + dx, y + dy) == (0, 0) else f"dir{encode(x + dx, y + dy)}"};
                            }}
                        """), indent)
                
                res += f"{indent}dist{encode(x, y)} += 1 + (rc.senseMapInfo(loc{encode(x, y)}).hasCloud() ? 10 : 0);\n"

                if r2 <= 2:
                    res += "\t\t\t}\n"

                res += "\t\t}\n"  
                
                visited.add(encode(x, y))

    return res

# PART 3: Massive Switch Statement & PART 4: Edge Checking
def generateSelection(vision: int, smallerVision: int) -> str:

    # PART 3
    res = textwrap.indent(textwrap.dedent(f"""
        /* 
         * PART 3: Massive Switch Statement
         * We check if the target location is in the vision of the robot that
         * Bellman-Ford was ran on
        */        
    """), "\t\t")

    res += textwrap.indent(textwrap.dedent(f"""
        int target_dx = target.x - loc{encode(0, 0)}.x;
        int target_dy = target.y - loc{encode(0, 0)}.y;
        switch (target_dx) {{
    """), "\t\t").rstrip("\n")

    for targetX in range(-8, 8):
        if targetX ** 2 <= vision:
            res += textwrap.indent(textwrap.dedent(f"""
                case {targetX}:
                    switch (target_dy) {{
            """), "\t\t\t").rstrip("\n")

            for targetY in range(-8, 8):
                if distanceSquared(targetX, targetY) <= vision:
                    res += textwrap.indent(textwrap.dedent(f"""
                        case {targetY}:
                            return dir{encode(targetX, targetY)}; // destination is at relative location ({targetX}, {targetY})
                    """), "\t\t\t\t\t")
            res += textwrap.indent(textwrap.dedent(f"""
                }}
                break;
            """), "\t\t\t\t").strip("\n")
    
    # PART 4
    res += textwrap.indent(textwrap.dedent(f"""
        }}

        /* 
         * PART 4: Edge Checking
         * If the target location wasn"t in the region Bellman-Ford ran on,
         * then we will try to find a edge node with the most optimal direction
         * to move in
        */   

        Direction ans = null;
        double bestScore = 0;
        double currDist = Math.sqrt(loc{encode(0,0)}.distanceSquaredTo(target));
    """), "\t\t")

    # We only want the (x, y) locations that are at the edge of the robots vision radius,
    # so we check if the point is between the smaller vision & actual vision
    for x, y in itertools.product(range(-8, 8), range(-8, 8)):
        if smallerVision < distanceSquared(x, y) <= vision:
            res += textwrap.indent(textwrap.dedent(f"""
                double score{encode(x,y)} = (currDist - Math.sqrt(loc{encode(x,y)}.distanceSquaredTo(target))) / dist{encode(x,y)}; // ({x}, {y})
                if (score{encode(x,y)} > bestScore) {{
                    bestScore = score{encode(x,y)};
                    ans = dir{encode(x,y)};
                }}
            """), "\t\t")

    return res

""" MAIN GENERATION FUNCTION """

# Builds the file
def generate(unit: str) -> None:
    bot = "Amplifier" if unit == "Amplifier" else "Robot"
    vision = VISION_EUCLIDEAN_DISTANCE[unit]
    smallerVision = SMALLER_VISION_EUCLIDEAN_DISTANCE[unit]
    file = open(f"{unit}Pathing.java", "w")

    result = textwrap.dedent(f"""
        // Inspired by https://github.com/IvanGeffner/battlecode2021/blob/master/thirtyone/BFSMuckraker.java.
        package jackPlayer.Pathing;
        import battlecode.common.*;
        
        public class {unit}Pathing extends Pathing {{

            public {unit}Pathing(RobotController rc) {{
                super(rc);
            }}
    """).lstrip("\n")

    result += generateVariables(vision)
    result += "\tpublic Direction getBestDirection(MapLocation target) throws GameActionException {"
    result += generateInitialization(vision)
    result += generateBFS(vision)
    result += generateSelection(vision, smallerVision)
    result += textwrap.dedent("""
                return ans;
            }
        }
    """)

    file.write(result)
    file.close()

def main() -> None:
    if len(sys.argv) == 2:
        generate(sys.argv[1])
    else:
        for unit in ("Carrier", "Launcher", "Destabilizer", "Booster", "Amplifier"):
            generate(unit)

if __name__ == "__main__":
    main()