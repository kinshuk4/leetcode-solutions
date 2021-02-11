package com.vaani.leetcode.greedy;

import java.util.*;

/**
 * 1658. Minimum Operations to Reduce X to Zero
 * Medium
 * You are given an integer array nums and an integer x. In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. Note that this modifies the array for future operations.
 * <p>
 * Return the minimum number of operations to reduce x to exactly 0 if it's possible, otherwise, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,4,2,3], x = 5
 * Output: 2
 * Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
 * Example 2:
 * <p>
 * Input: nums = [5,6,7,8,9], x = 4
 * Output: -1
 * Example 3:
 * <p>
 * Input: nums = [3,2,20,1,1,3], x = 10
 * Output: 5
 * Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 */
public class MinimumOperationsToReduceXToZero {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int target = Arrays.stream(nums).sum() - x;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int ans = -1;
        int prefixSum = 0;

        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            map.put(prefixSum, i);
            if (map.containsKey(prefixSum - target)) {
                int currLenSubArray = i - map.get(prefixSum - target);
                ans = Math.max(ans, currLenSubArray);
            }
        }

        if (ans == -1) {
            return ans;
        } else {
            return n - ans;
        }
    }
}
