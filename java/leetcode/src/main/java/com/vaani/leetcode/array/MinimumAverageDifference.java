package com.vaani.leetcode.array;

public class MinimumAverageDifference {
    public int minimumAverageDifference(int[] nums) {
        int n = nums.length;
        long[] prefixSumArr = new long[n];
        long prefixSum = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            prefixSumArr[i] = prefixSum;
        }

        prefixSum = 0;
        long sum = prefixSumArr[n - 1];

        long minAD = Long.MAX_VALUE;
        int minIdx = -1;

        for (int i = 0; i < n; i++) {
            long leftAvg = prefixSumArr[i] / (i + 1);
            long rightAvg = (i == n - 1) ? 0 : (sum - prefixSumArr[i]) / (n - i - 1);
            long currAD = Math.abs(leftAvg - rightAvg);
            if (currAD < minAD) {
                minAD = currAD;
                minIdx = i;
            }
        }
        return minIdx;

    }

}
