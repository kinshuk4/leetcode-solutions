package com.vaani.leetcode.two_pointers;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/move-zeroes/
 * 283. Move Zeroes
 * Easy
 * Accepted Given an array nums, write a function to
 * move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * <p>For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3,
 * 12, 0, 0].
 *
 * <p>Note: You must do this in-place without making a copy of the array. Minimize the total number
 * of operations.
 * <p>
 * https://www.youtube.com/watch?v=1PEncepEIoE
 */
public class MoveZeroes {
    public static void main(String[] args) throws Exception {
        int[] nums = {0, 0, 0, 0, 1, 0, 1, 0, 2};
        new MoveZeroes().moveZeroes(nums);
        for (int n : nums) System.out.print(n);
    }

    public void moveZeroes(int[] nums) {
        int k = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k++);
            }
        }
        while (k < nums.length) {
            nums[k++] = 0;
        }
    }


    public void moveZeroes2(int[] nums) {
        int k = 0;
        int n = nums.length;
        for (int i = 0; i < n; ) {
            if (nums[i] == 0) {
                i++;
            } else {
                swap(nums, i++, k++);
            }
        }
        while (k < nums.length) {
            nums[k++] = 0;
        }
    }
}
