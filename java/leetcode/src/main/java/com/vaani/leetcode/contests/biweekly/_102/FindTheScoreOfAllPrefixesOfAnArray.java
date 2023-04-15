package com.vaani.leetcode.contests.biweekly._102;

public class FindTheScoreOfAllPrefixesOfAnArray {
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        int[] max = new int[n];
        int localMax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            localMax = Math.max(localMax, nums[i]);
            max[i] = localMax;
        }

        long[] ans = new long[n];
        long sum = 0;

        for(int i = 0; i < n; i++) {
            long converI = nums[i] + max[i];
            sum += converI;
            ans[i] = sum;
        }

        return ans;
    }
}
