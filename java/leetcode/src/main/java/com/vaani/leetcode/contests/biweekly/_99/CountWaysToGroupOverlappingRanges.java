package com.vaani.leetcode.contests.biweekly._99;

import java.util.*;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

public class CountWaysToGroupOverlappingRanges {
    public static void main(String[] args) {
        CountWaysToGroupOverlappingRanges underTest = new CountWaysToGroupOverlappingRanges();
        assertEquals(2, underTest.countWays(new int[][]{{6, 10}, {5, 15}}));
        assertEquals(4, underTest.countWays(new int[][]{{1, 3}, {10, 20}, {2, 5}, {4, 8}}));
    }



    public int countWays(int[][] ranges) {
        if (ranges.length < 2) {
            return 2;
        }
//        Arrays.sort(ranges, Comparator.comparing(a -> a[0]));
        Arrays.sort(ranges, (a,b) -> a[0] != b[0]  ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
//        Arrays.sort(ranges, (a, b) -> ((Integer) a[0]).compareTo((Integer) b[0]));

        int result = 0;

        for (int i = 0; i < ranges.length - 1; i++) {
            int[] cur = ranges[i];
            int[] nxt = ranges[i + 1];


            if (cur[1] >= nxt[0]) {

                //Check if interval completely overlaps or not
                if (nxt[1] >= cur[1]) {
                    result += 1;
//                    ranges[i + 1][1] = ranges[i][1];
                } else {
                    result += 2;
                }
            }
        }
        return result;

    }

    public int countWays2(int[][] ranges) {
        Arrays.sort(ranges, (a, b) -> Integer.compare(a[0], b[0]));    // sort intervals by start time
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1])); // minHeap by end
        int ans = 0;
        for (int[] interval : ranges) {
            while (!minHeap.isEmpty() && minHeap.peek()[1] < interval[0]) {
                minHeap.poll();
                ans += 1;
            }
            minHeap.offer(interval);
        }
        return ans * 2;

    }
}
