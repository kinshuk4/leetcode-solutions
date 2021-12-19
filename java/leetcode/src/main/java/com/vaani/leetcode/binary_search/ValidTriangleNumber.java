package com.vaani.leetcode.binary_search;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number
 * Medium
 * <p>
 * Given an integer array nums, return the number of triplets chosen from the array that can make triangles if we take them as side lengths of a triangle.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,2,3,4]
 * Output: 3
 * Explanation: Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 * Example 2:
 * <p>
 * Input: nums = [4,2,3,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 */
public class ValidTriangleNumber {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;

        for (int i = nums.length - 1; i >= 2; i--) {
            int low = 0;
            int high = i - 1;
            while (low < high) {
                if (nums[low] + nums[high] > nums[i]) {
                    ans += high - low;
                    high--;
                } else {
                    low++;
                }
            }
        }

        return ans;
    }
}
