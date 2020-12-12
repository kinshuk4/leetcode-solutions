package com.vaani.leetcode.math;

/**
 * 1492. The kth Factor of n
 * Medium
 * Given two positive integers n and k.
 * <p>
 * A factor of an integer n is defined as an integer i where n % i == 0.
 * <p>
 * Consider a list of all factors of n sorted in ascending order, return the kth factor in this list or return -1 if n has less than k factors.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12, k = 3
 * Output: 3
 * Explanation: Factors list is [1, 2, 3, 4, 6, 12], the 3rd factor is 3.
 * Example 2:
 * <p>
 * Input: n = 7, k = 2
 * Output: 7
 * Explanation: Factors list is [1, 7], the 2nd factor is 7.
 * Example 3:
 * <p>
 * Input: n = 4, k = 4
 * Output: -1
 * Explanation: Factors list is [1, 2, 4], there is only 3 factors. We should return -1.
 * Example 4:
 * <p>
 * Input: n = 1, k = 1
 * Output: 1
 * Explanation: Factors list is [1], the 1st factor is 1.
 * Example 5:
 * <p>
 * Input: n = 1000, k = 3
 * Output: 4
 * Explanation: Factors list is [1, 2, 4, 5, 8, 10, 20, 25, 40, 50, 100, 125, 200, 250, 500, 1000].
 */
public class TheKthFactorOfN {
    public int kthFactor(int n, int k) {
        for (int i = 1; i * i < n; i++) {
            if (n % i == 0 && --k == 0) {
                return i;
            }
        }
        for (int i = (int) Math.sqrt(n); i >= 1; i--) {
            if (n % i == 0 && --k == 0) {
                return n / i;
            }
        }
        return -1;
    }
}
