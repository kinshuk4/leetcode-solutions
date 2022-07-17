package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * 1004. Max Consecutive Ones III
 * Medium
 * <p>
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */
public class MaxConsecutiveOnes3 {
    public int longestOnes(int[] nums, int k) {
        int right = 0;
        int left = 0;
        int ans = 0;
        int zeroCount = 0;

        while (right < nums.length) {
            if (zeroCount <= k) {
                if (nums[right] == 0) {
                    zeroCount++;
                }
                right++;
            }

            if (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            if ((right - left) > ans && zeroCount <= k) {
                ans = right - left;
            }
        }
        return ans;
    }
}
