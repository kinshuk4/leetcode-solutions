package com.vaani.leetcode.dp;

import java.util.Arrays;

public class PaintHouse3 {

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        long[][][] memo = new long[m][target + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < target + 1; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        int ans = (int)dfs(houses, cost, m, n, target, 0, 0, memo);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    private long dfs(int[] houses, int[][] cost, int m, int n, int target, int idx, int prevIdx, long[][][] memo) {

        if (idx == m) {
            if (target == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }
        if (target < 0) {
            return Integer.MAX_VALUE;
        }


        if (memo[idx][target][prevIdx] != -1) {
            return memo[idx][target][prevIdx];
        }
        long ans = Integer.MAX_VALUE;


        if (houses[idx] == 0) {
            for (int j = 1; j <= n; j++) {
                ans = Math.min(ans, cost[idx][j - 1] + dfs(houses, cost, m, n, target - ((j != prevIdx) ? 1 : 0), idx + 1, j, memo));
            }
        } else {
            ans = dfs(houses, cost, m, n, target - ((houses[idx] != prevIdx) ? 1 : 0), idx + 1, houses[idx], memo);
        }


        memo[idx][target][prevIdx] = ans;
        return ans;
    }

}
