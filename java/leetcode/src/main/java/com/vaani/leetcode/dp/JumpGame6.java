package com.vaani.leetcode.dp;

import java.util.*;

/**
 * 1696. Jump Game VI
 * Medium
 * You are given a 0-indexed integer array nums and an integer k.
 * <p>
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array.
 * That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * <p>
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * <p>
 * Return the maximum score you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
 * Example 2:
 * <p>
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
 * Example 3:
 * <p>
 * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length, k <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class JumpGame6 {
    public int maxResult(int[] nums, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        maxHeap.offer(new int[]{nums[0], 0});
        int maxScore = nums[0];
        for (int i = 1; i < nums.length; i++) {
            while (!(i - maxHeap.peek()[1] <= k)) {
                maxHeap.poll();
            }
            int[] curr = maxHeap.peek();
            maxScore = curr[0] + nums[i];
            maxHeap.offer(new int[]{maxScore, i});
        }
        return maxScore;
    }
}
