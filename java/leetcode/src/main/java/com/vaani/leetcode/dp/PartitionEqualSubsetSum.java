package com.vaani.leetcode.dp;

import java.util.Arrays;
import java.util.HashMap;

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

    // https://www.youtube.com/watch?v=3N47yKRDed0&t=76s
    public boolean canPartitionRecursive(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // if sum is odd, we cant divide in equal 2 halfs
        if (sum % 2 == 1) {
            return false;
        }
        return canPartitionHelper(nums, sum, 0, 0);
    }

    private boolean canPartitionHelper(int[] nums, int sum, int idx, int currSum) {
        if (currSum * 2 == sum) {
            return true;
        }

        if (currSum > sum / 2 || idx >= nums.length) {
            return false;
        }

        return canPartitionHelper(nums, sum, idx + 1, currSum + nums[idx]);
    }

    //https://www.youtube.com/watch?v=3N47yKRDed0&t=76s
    public boolean canPartitionMemo(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // if sum is odd, we cant divide in equal 2 halfs
        if (sum % 2 == 1) {
            return false;
        }
        return canPartitionMemoHelper(nums, sum, 0, 0, new HashMap<String, Boolean>());
    }

    private boolean canPartitionMemoHelper(int[] nums, int sum, int idx, int currSum, HashMap<String, Boolean> map) {
        String current = idx + "" + currSum;
        if (map.containsKey(current)) {
            return map.get(current);
        }
        if (currSum * 2 == sum) {
            return true;
        }

        if (currSum > sum / 2 || idx >= nums.length) {
            return false;
        }

        boolean found = canPartitionMemoHelper(nums, sum, idx + 1, currSum + nums[idx], map);
        map.put(current, found);
        return found;
    }


    public static void main(String[] args) {

    }
}

