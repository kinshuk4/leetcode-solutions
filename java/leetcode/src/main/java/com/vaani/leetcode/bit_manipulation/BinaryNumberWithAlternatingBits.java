package com.vaani.leetcode.bit_manipulation;

/**
 * https://leetcode.com/problems/binary-number-with-alternating-bits/
 * 693. Binary Number with Alternating Bits
 * Easy
 * <p>
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 * <p>
 * Example 1:
 * <p>
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 * <p>
 * Example 2:
 * <p>
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 * <p>
 * Example 3:
 * <p>
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 * <p>
 * Example 4:
 * <p>
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.
 */
public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {
        System.out.println(new BinaryNumberWithAlternatingBits().hasAlternatingBits(18));
    }

    public boolean hasAlternatingBits(int n) {
        int curr = n & 1;
        int pos = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) > 0) {
                pos = i;
            }
        }

        for (int i = 1; i <= pos; i++) {
            int temp = (1 << i) & n;
            if ((temp > 0 && curr > 0) || (temp == 0 && curr == 0)) return false;
            curr = temp;
        }
        return true;
    }

    public boolean hasAlternatingBits2(int n) {
        int cur = n % 2;
        n /= 2;
        while (n > 0) {
            if (cur == n % 2)
                return false;
            cur = n % 2;
            n /= 2;
        }
        return true;
    }

    public boolean hasAlternatingBits3(int n) {
        String bits = Integer.toBinaryString(n);
        for (int i = 0; i < bits.length() - 1; i++) {
            if (bits.charAt(i) == bits.charAt(i+1)) {
                return false;
            }
        }
        return true;
    }
}
