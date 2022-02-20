package com.vaani.leetcode.design;

/**
 * https://leetcode.com/problems/range-sum-query-mutable/
 * 307. Range Sum Query - Mutable
 * Medium
 * <p>
 * <p>
 * Given an integer array nums, handle multiple queries of the following types:
 * <p>
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 10^4 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {
    static class NumArray {
        private final int[] nums;
        private int sum;

        public NumArray(int[] nums) {
            this.nums = nums;
            for (int i : this.nums) {
                sum += i;
            }
        }

        public void update(int index, int val) {
            sum += val - nums[index];
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int diff = 0;
            for (int i = 0; i < left; i++) {
                diff += nums[i];
            }
            for (int j = right + 1; j < nums.length; j++) {
                diff += nums[j];
            }
            return sum - diff;
        }
    }
}
