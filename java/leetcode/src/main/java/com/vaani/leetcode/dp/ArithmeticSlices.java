package com.vaani.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/arithmetic-slices/
 * 413. Arithmetic Slices
 * Medium
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 * <p>
 * For example, these are arithmetic sequences:
 * <p>
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 * <p>
 * 1, 1, 2, 5, 7
 * <p>
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
 * <p>
 * A slice (P, Q) of the array A is called arithmetic if the sequence:
 * A[P], A[P + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
 * <p>
 * The function should return the number of arithmetic slices in the array A.
 * <p>
 * <p>
 * Example:
 * <p>
 * A = [1, 2, 3, 4]
 * <p>
 * return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
public class ArithmeticSlices {
    public boolean isAP(int[] A, int a, int b, int c) {
        return A[c] - A[b] == A[b] - A[a];
    }

    public int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if (n <= 1) {
            return 0;
        }

        int[] dp = new int[n];
        dp[0] = dp[1] = 0;

        for (int i = 2; i < n; i++) {
            if (isAP(A, i - 2, i - 1, i)) {
                dp[i] = 1 + dp[i - 1];
            }
        }

        return Arrays.stream(dp).sum();
    }

}
