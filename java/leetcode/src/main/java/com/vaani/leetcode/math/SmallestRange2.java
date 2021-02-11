package com.vaani.leetcode.math;

import java.util.Arrays;

/**
 * 910. Smallest Range II
 * Medium
 * <p>
 * Given an array A of integers, for each integer A[i] we need to choose either x = -K or x = K, and add x to A[i] (only once).
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
 * Example 2:
 * <p>
 * Input: A = [0,10], K = 2
 * Output: 6
 * Explanation: B = [2,8]
 * Example 3:
 * <p>
 * Input: A = [1,3,6], K = 3
 * Output: 3
 * Explanation: B = [4,6,3]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 * 0 <= K <= 10000
 */
public class SmallestRange2 {
    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int len = A.length - 1;
        int lowest = A[0];
        int highest = A[len];
        int res = highest - lowest;
        lowest += K;
        highest -= K;
        for (int i = 0; i < len; i++) { //O(n)
            int min = Math.min(lowest, A[i + 1] - K);
            int max = Math.max(highest, A[i] + K);
            res = Math.min(max - min, res);
        }
        return res;
    }
}
