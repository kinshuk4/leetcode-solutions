package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/
 * 668. Kth Smallest Number in Multiplication Table
 * Hard
 * <p>
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 * <p>
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 * Example 2:
 * <p>
 * <p>
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= m, n <= 3 * 104
 * 1 <= k <= m * n
 */
public class KthSmallestNumberInMultiplicationTable {
    public int findKthNumber(int m, int n, int k) {
        int lo = 1, hi = m * n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2, count = 0;
            for (int i = 1, j = n; i <= m; i++) {
                while (j >= 1 && i * j > mid) {
                    j--;
                }
                count += j;
            }
            if (count >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
}
