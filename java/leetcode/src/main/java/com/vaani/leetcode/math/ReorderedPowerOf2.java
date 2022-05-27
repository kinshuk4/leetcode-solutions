package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/reordered-power-of-2/
 * 869. Reordered Power of 2
 * Medium
 * <p>
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero.
 * <p>
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: true
 * Example 2:
 * <p>
 * Input: 10
 * Output: false
 * Example 3:
 * <p>
 * Input: 16
 * Output: true
 * Example 4:
 * <p>
 * Input: 24
 * Output: false
 * Example 5:
 * <p>
 * Input: 46
 * Output: true
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10^9
 */
public class ReorderedPowerOf2 {
    public boolean reorderedPowerOf2(int N) {
        int[] inputDigitFrequency = getDigitFrequency(N);
        for (int i = 0; i < 31; i++) {
            int twoToPowerOfI = (int) Math.pow(2, i);
            int[] powerOf2FreqCount = getDigitFrequency(twoToPowerOfI);
            if (compareArray(inputDigitFrequency, powerOf2FreqCount)) {
                return true;
            }

        }
        return false;
    }

    private int[] getDigitFrequency(int n) {
        int[] digitFreq = new int[10];
        while (n > 0) {
            digitFreq[n % 10]++;
            n = n / 10;
        }
        return digitFreq;
    }

    private boolean compareArray(int[] freqCount1, int[] freqCount2) {
        for (int i = 0; i < 10; i++) {
            if (freqCount1[i] != freqCount2[i]) {
                return false;
            }
        }
        return true;
    }
}
