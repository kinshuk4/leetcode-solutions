package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/k-inverse-pairs-array/
 * 629. K Inverse Pairs Array
 * Hard
 * <p>
 * For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
 * <p>
 * Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs. Since the answer can be huge, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3, k = 0
 * Output: 1
 * Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
 * Example 2:
 * <p>
 * Input: n = 3, k = 1
 * Output: 2
 * Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 1000
 * 0 <= k <= 1000
 */
public class KInversePairsArray {
    public int kInversePairs(int n, int k) {
        final int MOD = 1000000007;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                dp[i][j] = Math.floorMod(dp[i - 1][j] + dp[i][j - 1], MOD);
                if (j - i >= 0) {
                    dp[i][j] = Math.floorMod(dp[i][j] - dp[i - 1][j - i], MOD);
                }
            }
        }
        return dp[n][k];
    }
}
