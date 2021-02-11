package com.vaani.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/cherry-pickup/
 * 741. Cherry Pickup
 * Hard
 * <p>
 * You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.
 * <p>
 * 0 means the cell is empty, so you can pass through,
 * 1 means the cell contains a cherry that you can pick up and pass through, or
 * -1 means the cell contains a thorn that blocks your way.
 * Return the maximum number of cherries you can collect by following the rules below:
 * <p>
 * - Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
 * - After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
 * - When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
 * - If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 * Output: 5
 * Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 * Example 2:
 * <p>
 * Input: grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * grid[i][j] is -1, 0, or 1.
 * grid[0][0] != -1
 * grid[n - 1][n - 1] != -1
 */
public class CherryPickup {

    public static void main(String[] args) throws Exception {
        int[][] A = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        System.out.println(new CherryPickup().cherryPickup(A));
    }

    /**
     * <p>Solution O(N ^ 3) time-complexity. Traversing from (0, 0) -> (n - 1, n - 1) or the other way
     * around both are the same. The key point to note here is the traversal should be done by two
     * person simultaneously starting from (0, 0). Notice after t steps, each position (r, c) we could
     * be, is on the line r + c = t (along the diagonal). So if we have two people at positions (r1, c1)
     * and (r2, c2), then r2 = r1 + c1 - c2.
     */

    public int cherryPickup(int[][] grid) {
        int[][][] DP = new int[grid.length][grid.length][grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                Arrays.fill(DP[i][j], -1);
            }
        }
        int result = dp(grid.length, 0, 0, 0, DP, grid);
        return Math.max(result, 0);
    }

    private int dp(int N, int r1, int c1, int c2, int[][][] DP, int[][] grid) {
        int r2 = r1 + (c1 - c2);
        if (r1 >= N || c1 >= N || c2 >= N || r2 >= N || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        } else if (DP[r1][c1][c2] != -1) {
            return DP[r1][c1][c2];
        } else if (r1 == N - 1 && c1 == N - 1) {
            return grid[N - 1][N - 1];
        } else {
            int max = (c1 == c2) ? grid[r1][c1] : (grid[r1][c1] + grid[r2][c2]);
            // verify all the 4 possibilities.
            // (P1 down + P2 right), (P1 right, P2 right), (P1 right + P2 down), (P1 down, P2 down)
            max += Math.max(Math.max(
                    Math.max(dp(N, r1 + 1, c1, c2, DP, grid), dp(N, r1 + 1, c1, c2 + 1, DP, grid)),
                    dp(N, r1, c1 + 1, c2, DP, grid)),
                    dp(N, r1, c1 + 1, c2 + 1, DP, grid));
            DP[r1][c1][c2] = max;
            return max;
        }
    }
}
