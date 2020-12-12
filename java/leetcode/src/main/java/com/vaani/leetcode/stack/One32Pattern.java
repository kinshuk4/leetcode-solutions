package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * 456. 132 Pattern
 * Medium
 * <p>
 * 1638
 * <p>
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 * <p>
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 * <p>
 * Follow up: The O(n^2) is trivial, could you come up with the O(n logn) or the O(n) solution?
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 * Example 2:
 * <p>
 * Input: nums = [3,1,4,2]
 * Output: true
 * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
 * Example 3:
 * <p>
 * Input: nums = [-1,3,2,0]
 * Output: true
 * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class One32Pattern {
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();

        int two = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int oneOrThree = nums[i];
            if (oneOrThree < two) {
                return true;
            }
            while (!stack.isEmpty() && oneOrThree > stack.peek()) {
                two = stack.pop();
            }
            stack.push(oneOrThree);
        }
        return false;

    }
}
