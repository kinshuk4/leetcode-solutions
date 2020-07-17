package com.vaani.leetcode.dp;

import java.util.Arrays;

import static com.vaani.dsa.algo.paradigm.backtracking.IsSubsetSum.isSubsetSumDP;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * <p>
 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 * <p>
 * Note:
 * <p>
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1, 5, 11, 5]
 * <p>
 * Output: true
 * <p>
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // if sum is odd, we cant divide in equal 2 halfs
        if (sum % 2 == 1) {
            return false;
        }
        return isSubsetSumDP(nums, sum / 2);
    }

    public static void main(String[] args) {

    }
}

