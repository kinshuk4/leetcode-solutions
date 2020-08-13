package com.vaani.leetcode.hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-harmonious-subsequence/
 * 594. Longest Harmonious Subsequence
 * Easy
 * <p>
 * We define a harmounious array as an array where the difference between its maximum value and its minimum value is exactly 1.
 * <p>
 * Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,2,2,5,2,3,7]
 * Output: 5
 * Explanation: The longest harmonious subsequence is [3,2,2,2,3].
 * <p>
 * <p>
 * <p>
 * Note: The length of the input array will not exceed 20,000.
 */
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        // key is long to handle Integer.MAX_VALUE
        Map<Long, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(i -> map.put((long) i, map.getOrDefault((long) i, 0) + 1));

        int ans = 0;
        for (long key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                ans = Math.max(ans, map.get(key + 1) + map.get(key));
            }
        }
        return ans;
    }
}
