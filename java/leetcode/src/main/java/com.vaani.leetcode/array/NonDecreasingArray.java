package com.vaani.leetcode.array;

import org.junit.Assert;

import java.util.*;

/**
 * https://leetcode.com/problems/non-decreasing-array/
 * Non-decreasing Array
 * <p>
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
 * <p>
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * Example 2:
 * <p>
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        NonDecreasingArray underTest = new NonDecreasingArray();
        Assert.assertTrue(underTest.checkPossibility(new int[]{4, 2, 3}));
        Assert.assertFalse(underTest.checkPossibility(new int[]{4, 2, 1}));
        Assert.assertFalse(underTest.checkPossibility(new int[]{3, 4, 2, 3}));
    }


    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1 && count <= 1; i++) {
            if (nums[i] > nums[i + 1]) {  // e.g. [2,2,3,2,4] 3>2
                if (i > 0) {
                    if (nums[i - 1] <= nums[i + 1]) {// if number is lesser or equal modify nums[i]
                        nums[i] = nums[i - 1];   // to the previous number. 2<=2 modify nums[i] =2
                    } else {                        // in all other cases modify the next number
                        nums[i + 1] = nums[i]; // to the current number to match// the sequence
                    }
                }
                count++;
            }
        }

        return count <= 1;
    }

//    public boolean checkPossibility(int[] nums) {
//        int count = 0;
//        int max = Integer.MIN_VALUE;
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i - 1] > nums[i]) {
//                // already changed 1 number
//                if (count == 1) {
//                    return false;
//                }
//
//                nums[i - 1] = nums[i];
//                if (nums[i - 1] < max) {
//                    return false;
//                }
//                count++;
//            } else {
//                max = Math.max(max, nums[i - 1]);
//            }
//        }
//
//        return true;
//    }
}
