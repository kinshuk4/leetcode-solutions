package com.vaani.leetcode.depth_first_search;

import java.util.Arrays;

/* Given an integer matrix, find the length of the longest increasing path.
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 * Example 1:
 * nums = [
 * 	[9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
Return 4
The longest increasing path is [1, 2, 6, 9].

Example 2:

nums = [
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Return 4
The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/
// Naive DFS Solution
public class LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
        int[][] nums = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        UsingDFS1 underTest = new UsingDFS1();
        System.out.println(underTest.longestIncreasingPath(nums));
    }

    // TLE
    static class UsingDFS1 {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int result = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    result = Math.max(result, dfs(matrix, i, j));
                }
            }
            return result;
        }

        private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        private int dfs(int[][] matrix, int i, int j) {
            int ans = 0;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < matrix.length && 0 <= y && y < matrix[0].length && matrix[x][y] > matrix[i][j]) {
                    ans = Math.max(ans, dfs(matrix, x, y));
                }
            }
            return ++ans;
        }
    }

    // https://leetcode.com/problems/longest-increasing-path-in-a-matrix/discuss/78308/15ms-Concise-Java-Solution
    private static class UsingDFSMemo {


        public int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] memo = new int[m][n];
            int result = 1;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    result = Math.max(result, dfs(matrix, i, j, memo));
                }
            }
            return result;
        }

        private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        private int dfs(int[][] matrix, int i, int j, int[][] memo) {
            // memory
            if (memo[i][j] != 0) {
                return memo[i][j];
            }

            int m = matrix.length;
            int n = matrix[0].length;
            int max = 1;
            for (int[] d : dirs) {
                int x = i + d[0], y = j + d[1];
                if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                    max = Math.max(max, 1 + Math.max(memo[i][j], dfs(matrix, x, y, memo)));
                }
            }
            memo[i][j] = max;
            return max;
        }
    }

    static class UsingDP {
        public static int longestIncreasingPath(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int row = matrix.length;
            int col = matrix[0].length;
            int[][] dp = new int[row][col];
            // Init value
            for (int i = 0; i < row; i++) {
                Arrays.fill(dp[i], 1);
            }
            int ret = 0;
            int oldvalue;
            boolean updating;
            while (true) {
                updating = false;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        oldvalue = dp[i][j];
                        if (i >= 1 && matrix[i - 1][j] < matrix[i][j]) {
                            dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i][j]);
                        }
                        if (j >= 1 && matrix[i][j - 1] < matrix[i][j]) {
                            dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j]);
                        }
                        if (oldvalue != dp[i][j]) {
                            updating = true;
                        }
                        ret = Math.max(ret, dp[i][j]);
                    }
                }
                for (int i = row - 1; i >= 0; i--) {
                    for (int j = col - 1; j >= 0; j--) {
                        oldvalue = dp[i][j];
                        if (i < row - 1 && matrix[i + 1][j] < matrix[i][j]) {
                            dp[i][j] = Math.max(dp[i + 1][j] + 1, dp[i][j]);
                        }
                        if (j < col - 1 && matrix[i][j + 1] < matrix[i][j]) {
                            dp[i][j] = Math.max(dp[i][j + 1] + 1, dp[i][j]);
                        }
                        if (oldvalue != dp[i][j]) {
                            updating = true;
                        }
                        ret = Math.max(ret, dp[i][j]);
                    }
                }
                if (!updating) {
                    break;
                }
            }
            return ret + 1;
        }
    }


}
