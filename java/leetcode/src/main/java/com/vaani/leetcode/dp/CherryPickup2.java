package com.vaani.leetcode.dp;

/**
 * 1463. Cherry Pickup II
 * Hard
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
 * <p>
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 * <p>
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 * <p>
 * - From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * - When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * - When both robots stay on the same cell, only one of them takes the cherries.
 * - Both robots cannot move outside of the grid at any moment.
 * - Both robots should reach the bottom row in the grid.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 * Output: 24
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
 * Total of cherries: 12 + 12 = 24.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
 * Output: 28
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
 * Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
 * Total of cherries: 17 + 11 = 28.
 * Example 3:
 * <p>
 * Input: grid = [[1,0,0,3],[0,0,0,3],[0,0,3,3],[9,0,3,3]]
 * Output: 22
 * Example 4:
 * <p>
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == grid.length
 * cols == grid[i].length
 * 2 <= rows, cols <= 70
 * 0 <= grid[i][j] <= 100
 */
public class CherryPickup2 {
    public int cherryPickup(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;

        int[][] dirs = new int[][]{{1, -1}, {0, -1}, {-1, -1}, {1, 0}, {0, 0}, {-1, 0}, {1, 1}, {0, 1}, {-1, 1}};
        int[][] dp = new int[c][c];

        for (int k = r - 1; k >= 0; k--) {
            int[][] currDp = new int[c][c];
            int colMin = k >= c ? 0 : c - k - 1;
            int rowMax = k >= c ? (c - 1) : k;
            for (int i = 0; i <= rowMax; i++) {
                for (int j = c - 1; j >= colMin; j--) {
                    for (int[] dir : dirs) {
                        int prevRow = i + dir[0];
                        int prevCol = j + dir[1];
                        if (prevRow >= 0 && prevRow < c && prevCol >= 0 && prevCol < c) {
                            currDp[i][j] = Math.max(currDp[i][j], dp[prevRow][prevCol]);
                        }
                    }
                    currDp[i][j] += (i != j ? (grid[k][i] + grid[k][j]) : (grid[k][i]));
                }
            }
            dp = currDp;
        }
        return dp[0][c - 1];
    }
}
