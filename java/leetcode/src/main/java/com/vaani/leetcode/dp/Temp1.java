package com.vaani.leetcode.dp;

public class Temp1 {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] cache = new int[n][m];
        helper(nums, multipliers, 0, 0, cache);
        return cache[n - 1][m - 1];
    }

    public int helper(int[] nums, int[] multipliers, int left, int idx, int[][] cache) {
        if (idx == multipliers.length) {
            return 0;
        }
        if (cache[left][idx] != 0) {
            return cache[left][idx];
        }
        cache[left][idx] = Math.max(nums[left] * multipliers[idx] + helper(nums, multipliers, left + 1, idx + 1, cache)
                , nums[nums.length - 1 - (idx - left)] * multipliers[idx] + helper(nums, multipliers, left, idx + 1, cache));
        return cache[left][idx];
    }

    static class UsingBUDP {
        public int maximumScore(int[] nums, int[] multipliers) {
            int n = nums.length;
            int m = multipliers.length;
            int[][] dp = new int[n+1][m+1];
            for (int idx = m - 1; idx >= 0; idx--) {
                for (int left = idx; left >= 0; left--) {
                    dp[idx][left] = Math.max(nums[left] * multipliers[idx] + dp[idx+1][left+1], nums[n - 1 - (idx - left)] + dp[idx + 1][left]);
                }
            }
            return dp[0][0];
        }
    }
}
