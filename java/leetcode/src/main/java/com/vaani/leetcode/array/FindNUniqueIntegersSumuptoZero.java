package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
 * Given an integer n, return any array containing n unique integers such that they add up to 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 5
 * Output: [-7,-1,1,3,4]
 * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
 * Example 2:
 * <p>
 * Input: n = 3
 * Output: [-1,0,1]
 * Example 3:
 * <p>
 * Input: n = 1
 * Output: [0]
 */
public class FindNUniqueIntegersSumuptoZero {
    public int[] sumZero(int n) {
        int[] result = new int[n];

        if (n % 2 == 1) {
            n = n - 1; // as one of the element is 0, so we don't have to loop on all
            // we set last number as 0 in case of odd number n
        }

        for (int i = 0; i < n; i = i + 2) {
            int k = (i + 2) / 2;
            result[i] = k;
            result[i + 1] = -1 * k;
        }

        return result;
    }
}
