package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/complement-of-base-10-integer/
 * 1009. Complement of Base 10 Integer
 * Easy
 * <p>
 * Every non-negative integer N has a binary representation.  For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.  Note that except for N = 0, there are no leading zeroes in any binary representation.
 * <p>
 * The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.  For example, the complement of "101" in binary is "010" in binary.
 * <p>
 * For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 5
 * Output: 2
 * Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
 * <p>
 * Example 2:
 * <p>
 * Input: 7
 * Output: 0
 * Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
 * <p>
 * Example 3:
 * <p>
 * Input: 10
 * Output: 5
 * Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= N < 10^9
 * This question is the same as 476: https://leetcode.com/problems/number-complement/
 */
public class ComplementofBase10Integer {
    public int bitwiseComplement(int N) {
        String src = Integer.toBinaryString(N);
        int res = 0;
        for (int i = 0; i < src.length(); i++) {
            res = (res << 1) + (src.charAt(i) == '0' ? 1 : 0);
        }
        return res;
    }

    // better
    public int bitwiseComplement2(int N) {
        if (N == 0) {
            return 1;
        }
        int complement = 0, mask = 1;
        while (N > 0) {
            if ((N & 1) == 0) {
                complement |= mask;
            }
            mask <<= 1;
            N = N >> 1;
        }
        return complement;
    }
}
