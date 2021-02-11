package com.vaani.leetcode.heap;

import java.util.TreeSet;

/**
 * 1675. Minimize Deviation in Array
 * Hard
 * <p>
 * You are given an array nums of n positive integers.
 * <p>
 * You can perform two types of operations on any element of the array any number of times:
 * <p>
 * If the element is even, divide it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
 * If the element is odd, multiply it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
 * The deviation of the array is the maximum difference between any two elements in the array.
 * <p>
 * Return the minimum deviation the array can have after performing some number of operations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
 * Example 2:
 * <p>
 * Input: nums = [4,1,5,20,3]
 * Output: 3
 * Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
 * Example 3:
 * <p>
 * Input: nums = [2,10,8]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 2 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 */
public class MinimizeDeviationInArray {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            if (num % 2 == 0) {
                set.add(num);
            } else {
                set.add(num * 2);
            }Next Permutation
        }

        int ans = Integer.MAX_VALUE;
        while (true) {
            int max = set.last();
            int min = set.first();
            int curDiff = max - min;

            ans = Math.min(ans, curDiff);

            if (max % 2 == 0) {
                set.remove(max);
                set.add(max / 2);
            } else {
                // terminal state;
                return ans;
            }
        }
    }
}
