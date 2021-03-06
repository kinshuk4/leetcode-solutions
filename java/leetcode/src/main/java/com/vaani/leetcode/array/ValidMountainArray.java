package com.vaani.leetcode.array;

/**
 * Given an array A of integers, return true if and only if it is a valid mountain array.
 * <p>
 * Recall that A is a mountain array if and only if:
 * <p>
 * A.length >= 3
 * There exists some i with 0 < i < A.length - 1 such that:
 * A[0] < A[1] < ... A[i-1] < A[i]
 * A[i] > A[i+1] > ... > A[B.length - 1]
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [2,1]
 * Output: false
 * Example 2:
 * <p>
 * Input: [3,5,5]
 * Output: false
 * Example 3:
 * <p>
 * Input: [0,3,2,1]
 * Output: true
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 */
public class ValidMountainArray {
    // simple to read
    public boolean validMountainArray(int[] A) {
        boolean up = false, down = false;
        int i = 0;
        for (; i < A.length - 1; i++) {
            if (A[i] < A[i + 1]) {
                up = true;
            } else {
                break;
            }
        }

        for (; i < A.length - 1; i++) {
            if (A[i] > A[i + 1]) {
                down = true;
            } else {
                break;
            }
        }
        return i == A.length - 1 && up & down;
    }

    public boolean validMountainArray2(int[] A) {
        int i = 0;
        int n = A.length;
        while (i < n && i + 1 < n && A[i] < A[i + 1]) {
            i++;
        }

        if (i == 0 || i + 1 >= n) {
            return false;
        }

        while (i < n && i + 1 < n) {
            if (A[i] <= A[i + 1]) {
                return false;
            }
            i++;
        }

        return true;

    }
}
