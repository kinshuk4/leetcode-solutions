package com.vaani.leetcode.depth_first_search;

import java.util.Arrays;

public class WhereWillTheBallFall {
    public static void main(String[] args) {
        int[][] grid = {{1, -1, -1, 1, -1, 1, 1, 1, 1, 1, -1, 1, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, 1, -1, 1, -1, 1, -1, -1, -1, -1, 1, -1, 1, 1, -1, -1, -1, -1, -1, 1}, {-1, 1, 1, 1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, 1, -1, -1, 1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, -1, -1, 1, -1, 1, -1, -1, -1, -1, 1, 1, -1, 1, 1}, {1, -1, -1, -1, -1, 1, -1, 1, 1, 1, 1, 1, 1, 1, -1, 1, -1, -1, -1, 1, -1, -1, 1, -1, 1, -1, 1, -1, -1, 1, -1, 1, -1, 1, 1, -1, -1, 1, 1, -1, 1, -1}};
        System.out.println(Arrays.toString(new WhereWillTheBallFall().findBall(grid)));
    }

    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int b = 0; b < n; b++) {
            ans[b] = lastPoint(grid, b)[1];
        }
        return ans;
    }

    public int[] lastPoint(int[][] grid, int ballNum) {
        if (ballNum == 0 && grid[0][0] != 1) {
            return new int[]{-1, -1};
        }
        if (ballNum == grid[0].length - 1 && grid[0][ballNum] != -1) {
            return new int[]{-1, -1};
        }
        int m = grid.length;
        int n = grid[0].length;

        int c = ballNum;
        int ansC = -1;
        boolean right = grid[0][ballNum] == 1;
        for (int r = 0; r < m && c < n; r++) {
            if (c > n - 1 || c < 0) {
                return new int[]{-1, -1};
            }
            if (right && grid[r][c] == 1 && grid[r][c + 1] == -1) {
                return new int[]{-1, -1};
            }
            if (!right && grid[r][c] == -1 && grid[r][c - 1] == 1) {
                return new int[]{-1, -1};
            }
            if (grid[r][c] == 1) {
                c = c + 1;
                right = true;
            } else {
                c = c - 1;
                right = false;
            }
            if (r == m - 1) {
                return new int[]{r, c};
            }
        }
        return new int[]{-1, -1};
    }
}
