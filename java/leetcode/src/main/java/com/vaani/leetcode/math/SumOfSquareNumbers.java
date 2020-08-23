package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/sum-of-square-numbers/
 * 633. Sum of Square Numbers
 * Easy
 * <p>
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 * <p>
 * Example 1:
 * <p>
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * <p>
 * <p>
 * <p>
 * Example 2:
 * <p>
 * Input: 3
 * Output: False
 */

public class SumOfSquareNumbers {
    // TLE
    static class BruteForce {
        public boolean judgeSquareSum(int c) {
            for (long a = 0; a * a <= c; a++) {
                for (long b = 0; b * b <= c; b++) {
                    if (a * a + b * b == c)
                        return true;
                }
            }
            return false;
        }
    }

    // 2nd fastest
    static class SingleLoopWithSqrt {
        public boolean judgeSquareSum(int c) {
            for (long a = 0; a * a <= c; a++) {
                double b = Math.sqrt(c - a * a);
                if (b == (int) b)
                    return true;
            }
            return false;
        }
    }

    // slow
    static class SingleLoopBinarySearch {
        public boolean judgeSquareSum(int c) {
            for (long a = 0; a * a <= c; a++) {
                int b = c - (int) (a * a);
                if (binarySearchIsSquare(0, b, b)) {
                    return true;
                }
            }
            return false;
        }

        public boolean binarySearchIsSquare(long s, long e, int n) {
            if (s > e) {
                return false;
            }
            long mid = s + (e - s) / 2;
            if (mid * mid == n) {
                return true;
            }
            if (mid * mid > n) {
                return binarySearchIsSquare(s, mid - 1, n);
            }
            return binarySearchIsSquare(mid + 1, e, n);
        }
    }

    /**
     * Any positive number nn is expressible as a sum of two squares if and only if the prime factorization of nn, every prime of the form (4k+3)(4k+3) occurs an even number of times.
     */
    static class PureMaths {
        public boolean judgeSquareSum(int c) {
            for (int i = 2; i * i <= c; i++) {
                int count = 0;
                if (c % i == 0) {
                    while (c % i == 0) {
                        count++;
                        c /= i;
                    }
                    if (i % 4 == 3 && count % 2 != 0)
                        return false;
                }
            }
            return c % 4 != 3;
        }
    }

}
