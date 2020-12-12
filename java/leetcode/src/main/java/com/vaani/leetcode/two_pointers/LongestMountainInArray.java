package com.vaani.leetcode.two_pointers;

/**
 * https://leetcode.com/problems/longest-mountain-in-array/
 * 845. Longest Mountain in Array
 * Medium
 * Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
 * <p>
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 * <p>
 * Given an array A of integers, return the length of the longest mountain.
 * <p>
 * Return 0 if there is no mountain.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 * Example 2:
 * <p>
 * Input: [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 * Note:
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * Follow up:
 * <p>
 * Can you solve it using only one pass?
 * Can you solve it in O(1) space?
 */
public class LongestMountainInArray {
    public int longestMountain(int[] A) {
        int ascend = 0, descend = 0, max = 0;

        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {       // Current number greater than the previous
                if (descend > 0) {
                    ascend = 0; // Reset inc if we had a decreasing sequence until the previous
                }
                ascend++;               // Increment inc
                descend = 0;             // Reset dec
            } else if (A[i] < A[i - 1]) {  // Current number smaller than the previous
                if (ascend > 0) {         // No need to do anything if we did not have an increasing sequence
                    descend++;           // Increment dec
                    max = Math.max(max, ascend + descend + 1);  // Determine max for the current mountain
                }
            } else {
                ascend = descend = 0;      // Current number same as the previous, reset inc and dec
            }
        }

        return max;

    }
}
