package com.vaani.leetcode.interval;

import java.util.Arrays;

/**
 * 1288. Remove Covered Intervals
 * Medium
 * <p>
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 * <p>
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 * <p>
 * After doing so, return the number of remaining intervals.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[2,3]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: intervals = [[0,10],[5,12]]
 * Output: 2
 * Example 4:
 * <p>
 * Input: intervals = [[3,10],[4,10],[5,11]]
 * Output: 2
 * Example 5:
 * <p>
 * Input: intervals = [[1,2],[1,4],[3,4]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 1000
 * intervals[i].length == 2
 * 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * All the intervals are unique.
 */
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);

        int removed = 0, lastEnd = -1;
        for (int[] currInterval : intervals) {

            int currEnd = currInterval[1];
            if (currEnd <= lastEnd) {
                removed += 1;
            } else {
                lastEnd = currEnd;
            }
        }
        return intervals.length - removed;
    }
}
