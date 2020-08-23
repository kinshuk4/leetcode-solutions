package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/binary-gap/
 * 868. Binary Gap
 * Easy
 * <p>
 * Given a positive integer N, find and return the longest distance between two consecutive 1's in the binary representation of N.
 * <p>
 * If there aren't two consecutive 1's, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 22
 * Output: 2
 * Explanation:
 * 22 in binary is 0b10110.
 * In the binary representation of 22, there are three ones, and two consecutive pairs of 1's.
 * The first consecutive pair of 1's have distance 2.
 * The second consecutive pair of 1's have distance 1.
 * The answer is the largest of these two distances, which is 2.
 * <p>
 * Example 2:
 * <p>
 * Input: 5
 * Output: 2
 * Explanation:
 * 5 in binary is 0b101.
 * <p>
 * Example 3:
 * <p>
 * Input: 6
 * Output: 1
 * Explanation:
 * 6 in binary is 0b110.
 * <p>
 * Example 4:
 * <p>
 * Input: 8
 * Output: 0
 * Explanation:
 * 8 in binary is 0b1000.
 * There aren't any consecutive pairs of 1's in the binary representation of 8, so we return 0.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10^9
 */


public class BinaryGap {

    /**
     * Intuition
     * <p>
     * Since we wanted to inspect the distance between consecutive 1s in the binary representation of N, let's write down the index of each 1 in that binary representation. For example, if N = 22 = 0b10110, then we'll write A = [1, 2, 4]. This makes it easier to proceed, as now we have a problem about adjacent values in an array.
     * <p>
     * Algorithm
     * <p>
     * Let's make a list A of indices i such that N has the ith bit set.
     * <p>
     * With this array A, finding the maximum distance between consecutive 1s is much easier: it's the maximum distance between adjacent values of this array.
     * Complexity Analysis
     * <p>
     * Time Complexity: O(\log N)O(logN). Note that \log NlogN is the number of digits in the binary representation of NN.
     * <p>
     * Space Complexity: O(\log N)O(logN), the space used by A.
     */
    public int binaryGap(int N) {
        int[] A = new int[32];
        int ans = 0, idx = 0;
        for (int i = 0; i < 32; ++i)
            if (((N >> i) & 1) != 0)
                A[idx++] = i;


        for (int i = 0; i < idx - 1; i++)
            ans = Math.max(ans, A[i + 1] - A[i]);
        return ans;
    }

    // submitted
    public int binaryGap2(int N) {
        int ans = 0, count = -32;
        while (N != 0) {
            if ((N & 1) == 1) {
                ans = Math.max(ans, count);
                count = 0; // when we find the 1, we reset count to 0 to find next distance
            }
            count++;
            N >>= 1;
        }
        return ans;
    }
}
