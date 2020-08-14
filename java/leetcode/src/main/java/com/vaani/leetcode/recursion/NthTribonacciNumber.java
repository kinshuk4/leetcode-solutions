package com.vaani.leetcode.recursion;

/**
 * https://leetcode.com/problems/n-th-tribonacci-number/
 * 1137. N-th Tribonacci Number
 * Easy
 * <p>
 * The Tribonacci sequence Tn is defined as follows:
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 * <p>
 * Given n, return the value of Tn.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * <p>
 * Example 2:
 * <p>
 * Input: n = 25
 * Output: 1389537
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int first = 0, second = 1, third = 1, result = 0;
        for (int i = 3; i <= n; i++) {
            result = first + second + third;
            first = second;
            second = third;
            third = result;
        }
        return result;
    }

}
