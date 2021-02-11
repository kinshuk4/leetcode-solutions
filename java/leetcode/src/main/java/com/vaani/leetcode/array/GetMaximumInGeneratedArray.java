package com.vaani.leetcode.array;

/**
 * 1646. Get Maximum in Generated Array
 * Easy
 * You are given an integer n. An array nums of length n + 1 is generated in the following way:
 * <p>
 * nums[0] = 0
 * nums[1] = 1
 * nums[2 * i] = nums[i] when 2 <= 2 * i <= n
 * nums[2 * i + 1] = nums[i] + nums[i + 1] when 2 <= 2 * i + 1 <= n
 * Return the maximum integer in the array nums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 7
 * Output: 3
 * Explanation: According to the given rules:
 * nums[0] = 0
 * nums[1] = 1
 * nums[(1 * 2) = 2] = nums[1] = 1
 * nums[(1 * 2) + 1 = 3] = nums[1] + nums[2] = 1 + 1 = 2
 * nums[(2 * 2) = 4] = nums[2] = 1
 * nums[(2 * 2) + 1 = 5] = nums[2] + nums[3] = 1 + 2 = 3
 * nums[(3 * 2) = 6] = nums[3] = 2
 * nums[(3 * 2) + 1 = 7] = nums[3] + nums[4] = 2 + 1 = 3
 * Hence, nums = [0,1,1,2,1,3,2,3], and the maximum is 3.
 * Example 2:
 * <p>
 * Input: n = 2
 * Output: 1
 * Explanation: According to the given rules, the maximum between nums[0], nums[1], and nums[2] is 1.
 * Example 3:
 * <p>
 * Input: n = 3
 * Output: 2
 * Explanation: According to the given rules, the maximum between nums[0], nums[1], nums[2], and nums[3] is 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 100
 */
public class GetMaximumInGeneratedArray {
    public int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }

        int[] nums = new int[n + 1];

        nums[0] = 0;
        nums[1] = 1;

        int max = 1;

        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            max = Math.max(max, nums[i]);
        }
        return max;
    }


}
