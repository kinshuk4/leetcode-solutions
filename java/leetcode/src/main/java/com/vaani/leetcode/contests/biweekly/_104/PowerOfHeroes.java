package com.vaani.leetcode.contests.biweekly._104;

import java.util.Arrays;

public class PowerOfHeroes {
    public int sumOfPower(int[] nums) {
        int n = nums.length;
        int MOD = 1000000007;
        int sum = 0;
        for (int i = 1; i < (1 << n); i++) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);
                }
            }
            max = max % MOD;
            min = min % MOD;
            int currPower = (((max * max) % MOD) *  min) % MOD;
            sum = (sum + currPower) % MOD;
        }
        return sum;

    }

    public int sumOfPower2(int[] nums ) {
        int n = nums.length;
        int mod = 1000000007;
        long ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < (1 << n); i++) {
            if (i == 0) {
                continue;
            }
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    max = Math.max(max, nums[j]);
                    min = Math.min(min, nums[j]);
                }
            }
            max = max % mod;
            min = min % mod;
            ans = (ans + (((long) max * max % mod) * min % mod) % mod) % mod;
        }
        return (int) ans;

    }
}
