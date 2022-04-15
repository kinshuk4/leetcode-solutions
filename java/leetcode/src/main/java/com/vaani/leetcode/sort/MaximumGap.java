package com.vaani.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode.com/problems/maximum-gap/
 * 164. Maximum Gap
 * Hard
 * <p>
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form.
 * If the array contains less than two elements, return 0.
 * <p>
 * You must write an algorithm that runs in linear time and uses linear extra space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 * <p>
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 109
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        if (nums.length == 2) {
            return Math.abs(nums[1] - nums[0]);
        }
        Arrays.sort(nums);
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int diff = nums[i] - nums[i - 1];

            maxDiff = Math.max(maxDiff, diff);
        }

        return maxDiff;

    }

    // memlimit exceeds
    void countingSort(int[] array) {
        int size = array.length;
        int[] output = new int[size + 1];

        // Find the largest element of the array
        int max = array[0];
        for (int i = 1; i < size; i++) {
            if (array[i] > max)
                max = array[i];
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }

        // Store the count of each element
        for (int j : array) {
            count[j]++;
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = size - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the sorted elements into original array
        System.arraycopy(output, 0, array, 0, size);
    }



}
