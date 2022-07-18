package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 * 363. Max Sum of Rectangle No Larger Than K
 * Hard
 * <p>
 * Given an m x n matrix matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
 * <p>
 * It is guaranteed that there will be a rectangle with a sum no larger than k.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 * Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
 * Example 2:
 * <p>
 * Input: matrix = [[2,2,-1]], k = 3
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 * <p>
 * <p>
 * Follow up: What if the number of rows is much larger than the number of columns?
 */
public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = matrix[i][j];
                if (i > 0) {
                    t += dp[i - 1][j];
                }
                if (j > 0) {
                    t += dp[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    t -= dp[i - 1][j - 1];
                }
                dp[i][j] = t;
                for (int r = 0; r <= i; ++r) {
                    for (int c = 0; c <= j; ++c) {
                        int d = dp[i][j];
                        if (r > 0) {
                            d -= dp[r - 1][j];
                        }
                        if (c > 0) {
                            d -= dp[i][c - 1];
                        }
                        if (r > 0 && c > 0) {
                            d += dp[r - 1][c - 1];
                        }
                        if (d <= k) {
                            ans = Math.max(ans, d);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
