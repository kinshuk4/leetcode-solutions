package com.vaani.leetcode.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 452. Minimum Number of Arrows to Burst Balloons
 * Medium
 * <p>
 * There are some spherical balloons spread in two-dimensional space.
 * For each balloon, provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice.
 * The start is always smaller than the end.
 * <p>
 * An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
 * <p>
 * Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 * Example 2:
 * <p>
 * Input: points = [[1,2],[3,4],[5,6],[7,8]]
 * Output: 4
 * Example 3:
 * <p>
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 * Example 4:
 * <p>
 * Input: points = [[1,2]]
 * Output: 1
 * Example 5:
 * <p>
 * Input: points = [[2,3],[2,3]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= points.length <= 10^4
 * points.length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int numArrows = 1; // numBalloons > 0 => numArrows = 1

        int prevEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int[] curr = points[i];

            // no overlap
            if (curr[0] > prevEnd) {
                numArrows++;
                prevEnd = curr[1];
            }else {
                prevEnd = Math.min(prevEnd, curr[1]);
            }
        }
        return numArrows;

    }

}
