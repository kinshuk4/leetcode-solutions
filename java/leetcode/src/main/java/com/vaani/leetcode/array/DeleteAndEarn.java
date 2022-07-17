package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/delete-and-earn/
 * 740. Delete and Earn
 * Medium
 * You are given an integer array nums. You want to maximize the number of points you get by performing the following operation any number of times:
 * <p>
 * Pick any nums[i] and delete it to earn nums[i] points. Afterwards, you must delete every element equal to nums[i] - 1 and every element equal to nums[i] + 1.
 * Return the maximum number of points you can earn by applying the above operation some number of times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,4,2]
 * Output: 6
 * Explanation: You can perform the following operations:
 * - Delete 4 to earn 4 points. Consequently, 3 is also deleted. nums = [2].
 * - Delete 2 to earn 2 points. nums = [].
 * You earn a total of 6 points.
 * Example 2:
 * <p>
 * Input: nums = [2,2,3,3,3,4]
 * Output: 9
 * Explanation: You can perform the following operations:
 * - Delete a 3 to earn 3 points. All 2's and 4's are also deleted. nums = [3,3].
 * - Delete a 3 again to earn 3 points. nums = [3].
 * - Delete a 3 once more to earn 3 points. nums = [].
 * You earn a total of 9 points.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 104
 */
public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] sumArr = new int[10001];
        for (int i : nums) {
            sumArr[i] += i;
        }

        for (int i = 2; i < 10001; ++i) {
            sumArr[i] = Math.max(sumArr[i - 1], sumArr[i - 2] + sumArr[i]);
        }

        return sumArr[10000];
    }
}
