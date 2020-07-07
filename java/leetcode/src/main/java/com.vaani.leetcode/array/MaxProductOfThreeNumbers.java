package com.vaani.leetcode.array;

import java.util.Arrays;

/**
 * 27/06/2017. Given an integer com.vaani.leetcode.array, find three numbers whose
 * product is maximum and output the maximum product.
 *
 * <p>Example 1: Input: [1,2,3] Output: 6 Example 2: Input: [1,2,3,4] Output: 24 Note: The length of
 * the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaxProductOfThreeNumbers {
    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        System.out.println(new MaxProductOfThreeNumbers().maximumProduct(A));
    }

    // O n lg n
    public int maximumProduct2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int prod1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int prod2 = nums[n - 1] * nums[0] * nums[1];
        return Math.max(prod1, prod2);
    }

    //https://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return -1;
        }

        int maxA = Integer.MIN_VALUE, maxB = Integer.MIN_VALUE, maxC = Integer.MIN_VALUE;
        int minA = Integer.MAX_VALUE, minB = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (nums[i] > maxA) {
                maxC = maxB;
                maxB = maxA;
                maxA = nums[i];
            } else if (nums[i] > maxB) {
                maxC = maxB;
                maxB = nums[i];
            } else if (nums[i] > maxC) {
                maxC = nums[i];
            }

            if (nums[i] < minA) {
                minB = minA;
                minA = nums[i];
            } else if (nums[i] < minB) {
                minB = nums[i];
            }
        }

        return Math.max(minA * minB * maxA, maxA * maxB * maxC);

    }

}
