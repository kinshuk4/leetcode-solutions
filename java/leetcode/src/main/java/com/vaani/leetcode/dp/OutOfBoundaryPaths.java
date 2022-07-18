package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/out-of-boundary-paths/
 * 576. Out of Boundary Paths
 * Medium
 * <p>
 * <p>
 * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent four cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
 * <p>
 * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * Output: 6
 * Example 2:
 * <p>
 * <p>
 * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * Output: 12
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow <= m
 * 0 <= startColumn <= n
 */
public class OutOfBoundaryPaths {

    public static void main(String[] args) {
        System.out.println(new OutOfBoundaryPaths().findPaths(2, 2, 2, 0, 0));
    }

    /**
     * <p>Solution: O(m x n x N x 4) Move in all possible directions from the starting position (i, j)
     * and keep track of distance traversed and ensure the distance traversed does not exceed N. Keep
     * the count of number of possibilities to go out of the boundary for each cell reached. Return the
     * sum in cell (a, b)
     */
    final int[] R = {1, -1, 0, 0};
    final int[] C = {0, 0, 1, -1};
    int[][][] dp;
    final static int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        dp = new int[m][n][maxMove + 1];

        for (int k = 1; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int p = 0; p < 4; p++) {
                        int newR = i + R[p];
                        int newC = j + C[p];
                        if (newR < 0 || newC < 0 || newR >= m || newC >= n) {
                            dp[i][j][k] = ((dp[i][j][k] + 1) % MOD);
                        } else {
                            dp[i][j][k] = (((dp[i][j][k] + dp[newR][newC][k - 1])) % MOD);
                        }
                    }
                }
            }
        }

        return dp[startRow][startColumn][maxMove];
    }
}
