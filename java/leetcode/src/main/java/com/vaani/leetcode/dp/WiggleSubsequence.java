package com.vaani.leetcode.dp;

/**
 * 376. Wiggle Subsequence
 * Medium
 * <p>
 * Given an integer array nums, return the length of the longest wiggle sequence.
 * <p>
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.
 * <p>
 * For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) are alternately positive and negative.
 * In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.
 * A subsequence is obtained by deleting some elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence.
 * Example 2:
 * <p>
 * Input: nums = [1,17,5,10,13,15,10,5,16,8]
 * Output: 7
 * Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 * Example 3:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7,8,9]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * <p>
 * <p>
 * Follow up: Could you solve this in O(n) time?
 */
public class WiggleSubsequence {

    // Greedy
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }

        int up = 1;
        int down = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < nums[i]) {
                up = down + 1;
            } else if (nums[i - 1] > nums[i]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);

    }


}
