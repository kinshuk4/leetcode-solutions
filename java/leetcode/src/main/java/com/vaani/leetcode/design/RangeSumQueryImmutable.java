package com.vaani.leetcode.design;

/**
 * 303. Range Sum Query - Immutable
 * Easy
 * <p>
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * <p>
 * Example:
 * <p>
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 * 0 <= nums.length <= 10^4
 * -10^5 <= nums[i] <= 10^5
 * 0 <= i <= j < nums.length
 * <p>
 * <p>
 * <p>
 * Tags: Dynamic Programming
 * <p>
 * Similar Problems: (M) Range Sum Query 2D - Immutable, (M) Range Sum Query - Mutable, (E) Maximum Size Subarray Sum
 * Equals k
 */
public class RangeSumQueryImmutable {

    static class MutableSolution {
        static class NumArray {

            private final int[] nums;

            public NumArray(int[] nums) {
                for (int i = 1; i < nums.length; i++) {
                    nums[i] += nums[i - 1];
                }
                this.nums = nums;
            }

            public int sumRange(int i, int j) {
                return i == 0 ? nums[j] : nums[j] - nums[i - 1];
            }

        }
    }

    static class ExtraSpaceArray {
        static class NumArray {

            private final int[] sums;

            public NumArray(int[] nums) {
                for (int i = 1; i < nums.length; i++) {
                    nums[i] += nums[i - 1];
                }
                this.sums = nums;
            }

            public int sumRange(int i, int j) {
                return i == 0 ? sums[j] : sums[j] - sums[i - 1];
            }

        }
    }


}
