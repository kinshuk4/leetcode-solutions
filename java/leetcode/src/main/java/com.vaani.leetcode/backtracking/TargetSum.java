package com.vaani.leetcode.backtracking;

import java.util.Arrays;

import static com.vaani.dsa.algo.paradigm.backtracking.CountSubsetSumEqualK.countSubsetDP;

/**
 * https://leetcode.com/problems/target-sum/
 * You are given a list of non-negative integers, a1,
 * a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose
 * one from + and - as its new symbol.
 *
 * <p>Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * <p>Example 1: Input: nums is [1, 1, 1, 1, 1], S is 3. Output: 5 Explanation:
 *
 * <p>-1+1+1+1+1 = 3 +1-1+1+1+1 = 3 +1+1-1+1+1 = 3 +1+1+1-1+1 = 3 +1+1+1+1-1 = 3
 *
 * <p>There are 5 ways to assign symbols to make the sum of nums be target 3. Note: The length of
 * the given array is positive and will not exceed 20. The sum of elements in the given array will
 * not exceed 1000. Your output answer is guaranteed to be fitted in a 32-bit integer.
 */
public class TargetSum {
    private static int n;

    public static void main(String[] args) throws Exception {
        int[] A = {1, 1, 1, 1, 1};
        n = 0;
        new TargetSum().findTargetSumWays(A, 3);
        System.out.println(n);

        System.out.println(findTargetSumWaysDP(A, 3));
    }

    public int findTargetSumWays(int[] nums, int S) {
        backtrack(nums, S, 0, 0);
        return n;
    }

    private void backtrack(int[] nums, int target, int sum, int i) {
        if (i == nums.length) {
            if (sum == target) {
                n++;
            }
        } else {
            backtrack(nums, target, sum + nums[i], i + 1);
            backtrack(nums, target, sum - nums[i], i + 1);
        }
    }

    //Lets S1 and S2 be 2 sets; S1 - S2 = diff, S1+S2 = sum => 2S1 = sum + diff => S1 = (sum + diff)/2
    public static int findTargetSumWaysDP(int[] arr, int sum) {
        if(arr.length == 0) {
            return 0;
        }

        int sumOfArrayElement = Arrays.stream(arr).sum();


        if(sumOfArrayElement < sum || (sumOfArrayElement + sum) % 2 != 0) {
            return 0;
        }

        int sumToCheck = (sumOfArrayElement + sum) / 2;

        return countSubsetDP(arr, sumToCheck);
    }
}
