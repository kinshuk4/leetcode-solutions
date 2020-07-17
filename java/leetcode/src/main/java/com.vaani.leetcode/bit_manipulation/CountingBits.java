package com.vaani.leetcode.bit_manipulation;

/**
 * https://leetcode.com/problems/counting-bits/
 * <p>
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: [0,1,1]
 * Example 2:
 * <p>
 * Input: 5
 * Output: [0,1,1,2,1,2]
 * Follow up:
 * <p>
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 * Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;

        for (int i = 1; i < num + 1; i++) {
            // if i is odd, then number of set bits is 1 + numBits(i/2)
            if (i % 2 == 1) {
                result[i] = result[i / 2] + 1;
            } else { // when even, bits are same as numBits(i/2)
                result[i] = result[i / 2];
            }

            // one liner:
//            result[i] = result[i / 2] + i % 2;
        }


        return result;

    }
}
