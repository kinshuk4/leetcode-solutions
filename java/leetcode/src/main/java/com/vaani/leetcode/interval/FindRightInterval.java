package com.vaani.leetcode.interval;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 436. Find Right Interval
 * Medium
 * <p>
 * 404
 * <p>
 * 142
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.
 * <p>
 * For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.
 * <p>
 * Note:
 * <p>
 * You may assume the interval's end point is always bigger than its start point.
 * You may assume none of these intervals have the same start point.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [ [1,2] ]
 * <p>
 * Output: [-1]
 * <p>
 * Explanation: There is only one interval in the collection, so it outputs -1.
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: [ [3,4], [2,3], [1,2] ]
 * <p>
 * Output: [-1, 0, 1]
 * <p>
 * Explanation: There is no satisfied "right" interval for [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point;
 * For [1,2], the interval [2,3] has minimum-"right" start point.
 * <p>
 * <p>
 * Example 3:
 * <p>
 * Input: [ [1,4], [2,3], [3,4] ]
 * <p>
 * Output: [-1, 2, -1]
 * <p>
 * Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
 * For [2,3], the interval [3,4] has minimum-"right" start point.
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class FindRightInterval {
    public static void main(String[] args) {
        Arrays.stream(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})).forEach(p -> System.out.println(p));
    }

    public static int[] findRightInterval(int[][] intervals) {
        int[] result = new int[intervals.length];
        // map records the index for each start point
        Interval[] intervalObjArr = new Interval[intervals.length]; // = Arrays.stream(intervals).map(x -> new Interval(x[0], x[1])).toArray(Interval[]::new);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int[] interval = intervals[i];
            var intervalObj = new Interval(interval[0], interval[1]);
            intervalObjArr[i] = intervalObj;
            map.put(intervalObj.start, i);
        }

        //sort according to start point
        Arrays.sort(intervalObjArr, Comparator.comparingInt(a -> a.start));

        //binary search to insert current end point to larger sorted start points
        for (int i = 0; i < intervalObjArr.length; i++) {
            int target = intervalObjArr[i].end;
            int lo = i + 1, hi = intervals.length;
            while (lo < hi) {
                int mid = (hi - lo) / 2 + lo;
                if (intervalObjArr[mid].start < target) lo = mid + 1;
                else hi = mid;
            }
            result[map.get(intervalObjArr[i].start)] = (hi == intervals.length) ? -1 : map.get(intervalObjArr[hi].start);
        }
        return result;
    }


}
