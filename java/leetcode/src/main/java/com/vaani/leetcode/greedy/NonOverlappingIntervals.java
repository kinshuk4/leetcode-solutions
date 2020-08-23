package com.vaani.leetcode.greedy;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * 435. Non-overlapping Intervals
 * Medium
 * <p>
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 * <p>
 * Example 3:
 * <p>
 * Input: [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * You may assume the interval's end point is always bigger than its start point.
 * Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) throws Exception {
//        Interval i1 = new Interval(1, 4);
//        Interval i2 = new Interval(5, 9);
//        Interval i3 = new Interval(3, 12);
//        // Interval i4 = new Interval(1, 3);
//        Interval[] intervals = {i1, i2, i3};
        int[][] intervals = {
                {1, 4}, {5, 9}, {3, 12}
        };
        System.out.println(new NonOverlappingIntervals.UsingIntervalDataModel().eraseOverlapIntervals(intervals));
    }

    static class UsingIntervalDataModel {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            List<Interval> sortedIntervals = Arrays.stream(intervals)
                    .map(intervalArr -> new Interval(intervalArr[0], intervalArr[1]))
                    .sorted((o1, o2) -> o1.end - o2.end).collect(Collectors.toList());
            int count = 0;
            Interval prev = sortedIntervals.get(0);
            for (int i = 1; i < sortedIntervals.size(); i++) {
                if (sortedIntervals.get(i).start < prev.end) {
                    count++;
                } else {
                    prev = sortedIntervals.get(i);
                }
            }
            return count;
        }
    }

    // faster but not cleaner
    static class WithoutUsingDataModel {
        public int eraseOverlapIntervals(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            Arrays.sort(intervals, (o1, o2) -> o1[1] - o2[1]);

            int count = 0;
            int[] prev = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (intervals[i][0] < prev[1]) {
                    count++;
                } else {
                    prev = intervals[i];
                }
            }
            return count;
        }
    }

}
