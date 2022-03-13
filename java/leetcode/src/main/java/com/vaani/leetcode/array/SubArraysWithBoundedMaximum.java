package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/number-of-subarrays-with-bounded-maximum/
 * 795. Number of Subarrays with Bounded Maximum
 * Medium
 * <p>
 * We are given an array nums of positive integers, and two positive integers left and right (left <= right).
 * <p>
 * Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least left and at most right.
 * <p>
 * Example:
 * Input:
 * nums = [2, 1, 4, 3]
 * left = 2
 * right = 3
 * Output: 3
 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
 * Note:
 * <p>
 * left, right, and nums[i] will be an integer in the range [0, 10^9].
 * The length of nums will be in the range of [1, 50000].
 */
public class SubArraysWithBoundedMaximum {
    public static void main(String[] args) {
        int[] A = {2, 1, 4, 3};
        System.out.println(new SubArraysWithBoundedMaximum().numSubarrayBoundedMax(A, 2, 3));
    }

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        final int n = nums.length;
        int[] dp = new int[n];
        int v = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] >= left && nums[i] <= right) {
                if (v != -1) {
                    dp[i] = v - i + 1;
                } else {
                    dp[i] = 1;
                    v = i;
                }
            } else if (nums[i] < left) {
                if (v == -1) {
                    v = i;
                }
                if (i + 1 < n) {
                    if (nums[i + 1] < left || (nums[i + 1] >= left && nums[i + 1] <= right)) {
                        dp[i] = dp[i + 1];
                    } else {
                        dp[i] = 0;
                    }
                }
            } else {
                v = -1;
            }
        }
        int sum = 0;
        for (int j : dp) {
            sum += j;
        }
        return sum;
    }
}
