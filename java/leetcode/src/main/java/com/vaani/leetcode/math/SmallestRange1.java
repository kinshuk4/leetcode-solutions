package com.vaani.leetcode.math;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/smallest-range-i/
 * 908. Smallest Range I
 * Easy
 * <p>
 * Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, and add x to A[i].
 * <p>
 * After this process, we have some array B.
 * <p>
 * Return the smallest possible difference between the maximum value of B and the minimum value of B.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [1], K = 0
 * Output: 0
 * Explanation: B = [1]
 * <p>
 * Example 2:
 * <p>
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * <p>
 * Example 3:
 * <p>
 * Input: A = [1,3,6], K = 3
 * Output: 0
 * Explanation: B = [3,3,3] or B = [4,4,4]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 */
public class SmallestRange1 {
    public static void main(String[] args) {
        //
    }

    // readable but nlogn
    public int smallestRangeI(int[] A, int K) {
        if (A.length == 0 || A.length == 1) {
            return 0;
        }

        Arrays.sort(A);

        int min = A[0];
        int max = A[A.length - 1];
        int l = min + (K);
        int r = max - (K);
        return r > l ? r - l : 0;
    }

    // less readable but On
    public int smallestRangeI2(int[] A, int K) {
        if (A.length == 0 || A.length == 1) {
            return 0;
        }
        int min = A[0];
        int max = A[0];

        for (int i:A){
            min = Math.min(min, i);
            max = Math.max(max, i);
        }

        int l = min + (K);
        int r = max - (K);
        return r > l ? r - l : 0;
    }
}
