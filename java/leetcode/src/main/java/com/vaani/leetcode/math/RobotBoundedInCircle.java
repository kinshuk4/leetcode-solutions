package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/robot-bounded-in-circle/
 * 1041. Robot Bounded In Circle
 * Medium
 * <p>
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 * <p>
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * <p>
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "GGLLGG"
 * Output: true
 * Explanation:
 * The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * Example 2:
 * <p>
 * Input: "GG"
 * Output: false
 * Explanation:
 * The robot moves north indefinitely.
 * Example 3:
 * <p>
 * Input: "GL"
 * Output: true
 * Explanation:
 * The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 */
public class RobotBoundedInCircle {

    /* Note the 2 points:
    1. If you end up where you started, it is a circle.
    2. if you end up in a different place with facing north, hence you are drifting away.

     */
    public boolean isRobotBounded(String instructions) {
        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int dirIdx = 0; // 0:north(up), 1: right, 2: down, 3: left

        int[] currLocation = new int[]{0, 0}; // x,y

        for (char in : instructions.toCharArray()) {
            if (in == 'G') {
                currLocation[0] += dirs[dirIdx][0];
                currLocation[1] += dirs[dirIdx][1];
            } else if (in == 'L') {
                dirIdx = (dirIdx + 3) % 4;
            } else {
                dirIdx = (dirIdx + 1) % 4;
            }
        }

        boolean sameLocation = currLocation[0] == 0 && currLocation[1] == 0;
        if (sameLocation) { // ended up at the same place
            return true;
        } else if (dirIdx == 0) { // if the direction is north and location has changed
            return false;
        }
        return true; // it is always true
    }
}
