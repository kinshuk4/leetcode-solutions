package com.vaani.leetcode.array;

import org.junit.Assert;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-average-subarray-i/
 *
 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.
 *
 * Example 1:
 *
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 *
 *
 * Note:
 *
 * 1 <= k <= n <= 30,000.
 * Elements of the given array will be in the range [-10,000, 10,000].
 */
public class MaximumAverageSubarray {

    public static void main(String[] args) {
        MaximumAverageSubarray underTest = new MaximumAverageSubarray();
        Assert.assertEquals(12.75, underTest.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4), 0.0001);
    }

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int currSum = 0;
        for (int i = 0; i< k; i++){
            currSum += nums[i];
        }
        int max = currSum;
        for(int i = 1; i <= n - k; i++){
            currSum = currSum - nums[i-1] + nums[i+k-1];
             max = Math.max(currSum, max);
        }

        return (double) max / (double)k;
    }

}
