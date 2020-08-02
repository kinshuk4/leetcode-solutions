package com.vaani.leetcode.bit_manipulation;

/**
 * https://leetcode.com/problems/hamming-distance/
 * 461. Hamming Distance
 * Easy
 * The Hamming distance between two integers is the
 * number of positions at which the corresponding bits are different.
 *
 * <p>Given two integers x and y, calculate the Hamming distance.
 *
 * <p>Note: 0 ≤ x, y < 231.
 *
 * <p>Example:
 *
 * <p>Input: x = 1, y = 4
 *
 * <p>Output: 2
 *
 * <p>Explanation: 1 (0 0 0 1) 4 (0 1 0 0) ↑ ↑
 *
 * <p>The above arrows point to positions where the corresponding bits are different.
 *
 * <p>Solution O(1): XOR (x, y) and count the number of bits set
 */
public class HammingDistance {

    public static void main(String[] args) throws Exception {
    }

    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int count = 0;
        while (z != 0) {
            count++;
            z &= z - 1;
        }
        return count;
    }

    public int hammingDistance2(int x, int y) {
        int z = (x ^ y);
        int count = 0;
        for (int i = 0; i < 31; i++) {
            if ((z & (1 << i)) > 0) {
                count++;
            }
        }
        return count;
    }

    // using mod
    public int hammingDistance3(int x, int y) {
        int result = 0;
        while (x > 0 || y > 0) {
            result += (x % 2) ^ (y % 2); // % mod checks if number is even, if even last bit is 0 else 1. we are xoring them
            x >>= 1; // dividing the number by 2
            y >>= 1;
        }
        return result;
    }
}
