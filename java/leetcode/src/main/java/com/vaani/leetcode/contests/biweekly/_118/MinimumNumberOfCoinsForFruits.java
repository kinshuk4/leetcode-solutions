package com.vaani.leetcode.contests.biweekly._118;

public class MinimumNumberOfCoinsForFruits {
    public static void main(String[] args) {
        int[] tree = {3, 1, 2};
        System.out.println(new MinimumNumberOfCoinsForFruits().minimumCoins(tree));
    }

    public int minimumCoins1(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n + 1];
        dp[0] = prices[0];


        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;

            for (int j = 1; j <= i; j++) {
                int cost = prices[i - 1] + dp[i - j];
                dp[i] = Math.min(dp[i], cost);
            }
        }

        return dp[n];
    }

    public static int minimumCoins(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n+1];
        dp[n] = 0;

        for(int i=n-1; i>=0; i--) {
            dp[i] = Integer.MAX_VALUE;
            int total = 0;
            for(int j=i; j<n && j<i+prices[i]; j++) {
                total += prices[j];
                dp[i] = Math.min(dp[i], total + (j+1<n ? dp[j+1] : 0));
            }
        }
        return dp[0];
    }
}
