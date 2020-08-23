package com.vaani.leetcode.interval;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/insert-interval/
 * <p>
 * 57. Insert Interval
 * Hard
 * <p>
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * <p>
 * You may assume that the intervals were initially sorted according to their start times.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
/*
 * <p>Solution: O(n): Iterate through each interval and check for each overlapping case
 */
public class InsertInterval {

    public static void main(String[] args) throws Exception {
        Interval i1 = new Interval(1, 2);
        Interval i2 = new Interval(3, 5);
        Interval i3 = new Interval(6, 7);
        Interval i4 = new Interval(8, 10);
        Interval i5 = new Interval(12, 16);
        List<Interval> list = Arrays.asList(i1, i2, i3, i4, i5);
        int[][] result = new InsertInterval.UsingDataModel().insert(new int[][]{
                {1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}
        }, new int[]{2, 5});
        Arrays.stream(result).map(x -> x[0] + " " + x[1]).forEach(System.out::println);
    }

    static class UsingDataModel {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            List<Interval> intervalList = Arrays.stream(intervals).map(intervalArr -> new Interval(intervalArr[0], intervalArr[1])).collect(Collectors.toList());

            Interval newIntervalObj = new Interval(newInterval[0], newInterval[1]);
            int start = newIntervalObj.start;
            int end = newIntervalObj.end;

            List<Interval> result = new LinkedList<>();
            int i = 0;
            // add all the intervals ending before newInterval starts
            while (i < intervalList.size() && intervalList.get(i).end < start) {
                result.add(intervalList.get(i++));
            }

            // merge all overlapping intervals to one considering newInterval
            // Note we are updating start and end now
            while (i < intervalList.size() && intervalList.get(i).start <= end) {
                start = Math.min(start, intervalList.get(i).start);
                end = Math.max(end, intervalList.get(i).end);
                i++;
            }
            result.add(new Interval(start, end));
            // add all the rest
            while (i < intervalList.size()) {
                result.add(intervalList.get(i++));
            }
            return result.stream().map(interval -> new int[]{interval.start, interval.end}).toArray(int[][]::new);

        }
    }


    static class WithoutDataModel {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int start = newInterval[0];
            int end = newInterval[1];

            List<int[]> result = new LinkedList<>();
            int i = 0;
            // add all the intervals ending before newInterval starts
            while (i < intervals.length && intervals[i][1] < start) {
                result.add(intervals[i++]);
            }

            // merge all overlapping intervals to one considering newInterval
            // Note we are updating start and end now
            while (i < intervals.length && intervals[i][0] <= end) {
                start = Math.min(start, intervals[i][0]);
                end = Math.max(end, intervals[i][1]);
                i++;
            }
            result.add(new int[]{start, end});
            // add all the rest
            while (i < intervals.length) {
                result.add(intervals[i++]);
            }
            return result.toArray(int[][]::new);
        }
    }
}
