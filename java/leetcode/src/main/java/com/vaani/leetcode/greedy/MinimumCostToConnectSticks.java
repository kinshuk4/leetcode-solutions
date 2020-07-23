package com.vaani.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 * You have some sticks with positive integer lengths.
 * <p>
 * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y. You perform this action until there is one stick remaining.
 * <p>
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 * <p>
 * Example 1:
 * Input: sticks = [2,4,3]
 * Output: 14
 * Explanation = 2 + 3 = 5 =>5, 5 + 4 = 14
 * <p>
 * Example 2:
 * Input: sticks = [1,8,3,5]
 * Output: 30
 * Explanation: 1 + 3 => 4, 4 + 5 = 13 => 13, 9 + 8 = 30
 * <p>
 * Constraints:
 * <p>
 * 1 <= sticks.length <= 10^4
 * 1 <= sticks[i] <= 10^4
 */
public class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length < 2) {
            return 0;
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Arrays.stream(sticks).forEach(minHeap::add);


        int cost = 0;
        while (minHeap.size() > 1) {
            int merge = minHeap.poll() + minHeap.poll();
            cost += merge;
            minHeap.add(merge);
        }

        return cost;
    }
}
