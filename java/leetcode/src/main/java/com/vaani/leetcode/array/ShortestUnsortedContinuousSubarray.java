package com.vaani.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/articles/shortest-unsorted-continous-subarray/
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 * <p>
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.
 * <p>
 * You need to find the shortest such subarray and output its length.
 * <p>
 * Example 1:
 * Input: [2, 6, 4, 8, 10, 9, 15]
 * Output: 5
 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
 * Note:
 * Then length of the input array is in range [1, 10,000].
 * The input array may contain duplicates, so ascending order here means <=.
 */
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        return findUnsortedSubarrayWithoutExtraSpace(nums);
    }

    public int findUnsortedSubarrayUsingSort(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        int start = sortedNums.length, end = 0;
        for (int i = 0; i < sortedNums.length; i++) {
            if (sortedNums[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }

    public int findUnsortedSubarrayWithoutExtraSpace(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        // Find the minimum element in the unsorted terrain
        boolean isSorted = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                isSorted = false;
            }
            if (!isSorted) {
                min = Math.min(min, nums[i]);
            }
        }
        // Find the max element in the unsorted terrain
        isSorted = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                isSorted = false;
            }
            if (!isSorted) {
                max = Math.max(max, nums[i]);
            }
        }


        // find position of insertion of min element
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }

        // find position of insertion of max element
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }

        return r - l < 0 ? 0 : r - l + 1;
    }


}
