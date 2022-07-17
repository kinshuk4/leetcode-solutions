package com.vaani.leetcode.array;

import java.util.Arrays;

/**
 * 462. Minimum Moves to Equal Array Elements II
 * Medium
 * <p>
 * 850
 * <p>
 * 59
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 * <p>
 * In one move, you can increment or decrement an element of the array by 1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 2
 * Explanation:
 * Only two moves are needed (remember each move increments or decrements one element):
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * Example 2:
 * <p>
 * Input: nums = [1,10,2,9]
 * Output: 16
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinimumMovesToEqualArray2 {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 3};
        System.out.println(new MinimumMovesToEqualArray2().minMoves2(A));
    }

    public int minMoves2(int[] nums) {
        if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        Arrays.sort(nums);

        if ((nums.length % 2) == 1) {
            return getSum(nums, nums.length / 2);
        } else {
            return Math.min(getSum(nums, (nums.length / 2) - 1), getSum(nums, nums.length / 2));
        }
    }

    private int getSum(int[] nums, final int median) {
        return Arrays.stream(nums).map(i -> i - nums[median]).map(Math::abs).sum();
    }
}
