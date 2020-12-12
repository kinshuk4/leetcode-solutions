package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/smallest-integer-divisible-by-k/
 * 1015. Smallest Integer Divisible by K
 * Medium
 * <p>
 * 258
 * <p>
 * 327
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a positive integer K, you need to find the length of the smallest positive integer N such that N is divisible by K, and N only contains the digit 1.
 * <p>
 * Return the length of N. If there is no such N, return -1.
 * <p>
 * Note: N may not fit in a 64-bit signed integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: K = 1
 * Output: 1
 * Explanation: The smallest answer is N = 1, which has length 1.
 * Example 2:
 * <p>
 * Input: K = 2
 * Output: -1
 * Explanation: There is no such positive integer N divisible by 2.
 * Example 3:
 * <p>
 * Input: K = 3
 * Output: 3
 * Explanation: The smallest answer is N = 111, which has length 3.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= K <= 10^5
 */
public class SmallestIntegerDivisibleByK {
    public int smallestRepunitDivByK(int K) {
        if (K % 2 == 0 || K % 5 == 0) {
            return -1;
        }

        int N = 1;
        int len = 1;

        while (N % K != 0) {
            int nextN = N * 10 + 1;
            N = nextN % K;
            len++;
        }

        return len;
    }
}
