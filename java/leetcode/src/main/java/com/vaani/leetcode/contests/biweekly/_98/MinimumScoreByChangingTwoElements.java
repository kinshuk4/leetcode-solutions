package com.vaani.leetcode.contests.biweekly._98;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MinimumScoreByChangingTwoElements {
    public static void main(String[] args) {
        MinimumScoreByChangingTwoElements underTest = new MinimumScoreByChangingTwoElements();
//        assertEquals(0, underTest.minimizeSum(new int[] {1,4,3}));
//        assertEquals(24, underTest.minimizeSum(new int[] {59,27,9,81,33}));
        assertEquals(30, underTest.minimizeSum(new int[] {58,42,8,75,28})); // 8, 28, 42, 58, 75
    }
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
//        int minDiff = 0;
        int maxDiff1 = nums[n-1] - nums[2]; // replacing 1st 2 numbers by maxnumber
        int maxDiff2 = nums[n-3] - nums[0]; // replacing last 2 numbers by min number
        int maxDiff3 = nums[n-2] - nums[1]; // replacing either 1st or last number - as it is atmost
        return Math.min(maxDiff1, Math.min(maxDiff2, maxDiff3));
    }
}
