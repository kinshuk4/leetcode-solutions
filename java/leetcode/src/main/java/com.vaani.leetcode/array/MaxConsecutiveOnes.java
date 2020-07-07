package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/max-consecutive-ones/
 * Given a binary com.vaani.leetcode.array, find the maximum number of
 * consecutive 1s in this array.
 *
 * <p>Example 1: Input: [1,1,0,1,1,1] Output: 3 Explanation: The first two digits or the last three
 * digits are consecutive 1s. The maximum number of consecutive 1s is 3. Note:
 *
 * <p>The input array will only contain 0 and 1. The length of input array is a positive integer and
 * will not exceed 10,000
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        //
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 0;
            }
        }
        return max;
    }
}
