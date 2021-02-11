package com.vaani.leetcode.stack;

import com.vaani.dsa.ds.utils.generic.ArrayUtils;

/**
 * https://leetcode.com/problems/next-greater-element-iii/
 * 556. Next Greater Element III
 * Medium
 * <p>
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 * <p>
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 21
 * Example 2:
 * <p>
 * Input: n = 21
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 2^31 - 1
 */
public class NextGreaterElement3 {
    public int nextGreaterElement(int n) {
        char[] numArr = String.valueOf(n).toCharArray();

        int len = numArr.length;
        int i = len - 2; // inversion point
        while (i >= 0 && numArr[i] >= numArr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return -1;
        }

        for (int j = len - 1; j > i; j--) {
            if (numArr[j] > numArr[i]) {
                ArrayUtils.swap(numArr, j, i);
                break;
            }
        }

        ArrayUtils.reverseBetweenRange(numArr, i + 1, len - 1);
        long result = Long.parseLong(new String(numArr)); // using long for int overflow.

        return result > Integer.MAX_VALUE ? -1 : (int) result;
    }


}
