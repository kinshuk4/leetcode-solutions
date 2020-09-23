package com.vaani.leetcode.sort;

/**
 * https://leetcode.com/problems/sort-array-by-parity-ii/
 *
 * 922. Sort Array By Parity II
 * Easy
 * <p>
 * Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.
 * <p>
 * Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.
 * <p>
 * You may return any answer array that satisfies this condition.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [4,2,5,7]
 * Output: [4,5,2,7]
 * Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
 * <p>
 * <p>
 * Note:
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * <p>
 * <p>
 */
public class SortArrayByParity2 {
    public static void main(String[] args) {
        //
    }

    /**
     *  * Solution: O(N) straight forward
     *  * linear solution, keep track of odd and even indices and increment by 2 every time a value is
     *  * added at the index.
     */
    public int[] sortArrayByParityII(int[] A) {
        int[] ans = new int[A.length];
        int i = 0, j = 1;
        for (int a : A) {
            if (a % 2 == 0) {
                ans[i] = a;
                i += 2;
            } else {
                ans[j] = a;
                j += 2;
            }
        }
        return ans;
    }
}
