package com.vaani.leetcode.contests.biweekly._102;

public class FindTheWidthOfColumnsOfAGrid {
    public static void main(String[] args) {
        FindTheWidthOfColumnsOfAGrid underTest = new FindTheWidthOfColumnsOfAGrid();
    }
    public int[] findColumnWidth(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] ans = new int[n];
        for(int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                int len = String.valueOf(grid[i][j]).length();
                ans[j] = Math.max(ans[j], len);
            }
        }
        return ans;
    }
}
