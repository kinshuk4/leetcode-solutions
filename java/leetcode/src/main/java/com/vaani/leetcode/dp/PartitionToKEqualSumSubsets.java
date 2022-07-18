package com.vaani.leetcode.dp;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * 698. Partition to K Equal Sum Subsets
 * Medium
 * <p>
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 16
 * 1 <= nums[i] <= 104
 * The frequency of each element is in the range [1, 4].
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        var sum = IntStream.of(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        return canPartitionKSubsets(nums, k, 0, 0, sum / k, new boolean[nums.length]);

    }

    private boolean canPartitionKSubsets(int[] nums, int k, int start, int currSum, final int targetSum, boolean[] visited) {
        if (k == 1) {
            return true;
        }
        if (currSum > targetSum) {
            return false;
        }
        if (currSum == targetSum) {
            return canPartitionKSubsets(nums, k - 1, 0, 0, targetSum, visited);
        }
        for (var i = start; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            if (canPartitionKSubsets(nums, k, i + 1, currSum + nums[i], targetSum, visited)) {
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

}
