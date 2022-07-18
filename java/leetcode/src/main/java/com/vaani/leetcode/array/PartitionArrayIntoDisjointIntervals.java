package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/partition-array-into-disjoint-intervals/
 * <p>
 * Given an array nums, partition it into two (contiguous) subarrays left and right so that:
 * <p>
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.  It is guaranteed that such a partitioning exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,0,3,8,6]
 * Output: 3
 * Explanation: left = [5,0,3], right = [8,6]
 * Example 2:
 * <p>
 * Input: nums = [1,1,1,0,6,12]
 * Output: 4
 * Explanation: left = [1,1,1,0], right = [6,12]
 * <p>
 * <p>
 * Note:
 * <p>
 * 2 <= nums.length <= 30000
 * 0 <= nums[i] <= 10^6
 * It is guaranteed there is at least one way to partition nums as described.
 */
public class PartitionArrayIntoDisjointIntervals {
    public int partitionDisjoint(int[] nums) {
        int ansVal = nums[0], ansIdx = 1, max = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (ansVal > nums[i]) {
                ansIdx = i + 1;
                ansVal = Math.max(max, ansVal);
            } else {
                max = Math.max(max, nums[i]);
            }
        }
        return ansIdx;
    }
}
