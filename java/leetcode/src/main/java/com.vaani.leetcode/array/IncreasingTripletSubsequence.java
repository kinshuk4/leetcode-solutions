package com.vaani.leetcode.array;

import java.util.Arrays;

/** https://leetcode.com/problems/increasing-triplet-subsequence/
 * Given an unsorted array return whether an
 * increasing subsequence of length 3 exists or not in the array.
 *
 * <p>Formally the function should: Return true if there exists i, j, k such that arr[i] < arr[j] <
 * arr[k] given 0 ≤ i < j < k ≤ n-1 else return false. Your algorithm should run in O(n) time
 * complexity and O(1) space complexity.
 *
 * <p>Examples: Given [1, 2, 3, 4, 5], return true.
 *
 * <p>Given [5, 4, 3, 2, 1], return false.
 */
public class IncreasingTripletSubsequence {

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 3, 4, 5};
        System.out.println(new IncreasingTripletSubsequence().increasingTriplet(A));
    }

    public boolean increasingTriplet(int[] nums) {
        // create array in increasing sequence - min0< min1< min2
        int[] A = new int[3];
        Arrays.fill(A, Integer.MAX_VALUE);
        for (int num : nums) {
            if (num < A[0]) {
                A[0] = num;
            } else if (num < A[1] && num > A[0]) {
                A[1] = num;
            } else if (num < A[2] && num > A[1]) {
                return true;
            }
        }
        return false;
    }

    // even better
    public boolean increasingTriplet2(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= small) { small = n; } // update small if n is smaller than both
            else if (n <= big) { big = n; } // update big only if greater than small but smaller than big
            else return true; // return if you find a number bigger than both
        }
        return false;
    }
}
