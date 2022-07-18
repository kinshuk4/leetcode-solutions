package com.vaani.leetcode.dp;

/**
 * 474. Ones and Zeroes
 * Medium
 * <p>
 * You are given an array of binary strings strs and two integers m and n.
 * <p>
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 * <p>
 * A set x is a subset of a set y if all elements of x are also elements of y.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * Example 2:
 * <p>
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 */
public class OnesAndZeroes {
    public static void main(String[] args) {
        String[] str = {"10", "0", "1"};
        System.out.println(new OnesAndZeroes().findMaxForm(str, 1, 1));
    }

    /**
     * * <p>Solution: O(S x m x n) For every string array position we have two choices i. pick this value
     * * or ii. not pick this value. Evaluate both these cases and cache the result in a dp array.
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int l = strs.length;
        int[][][] dp = new int[l + 1][m + 1][n + 1];
        for (int i = l - 1; i >= 0; i--) {
            String string = strs[i];
            int zero = 0;
            int one = 0;
            for (char c : string.toCharArray()) {
                if (c == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    dp[i][j][k] = dp[i + 1][j][k];
                    if (j - zero >= 0 && k - one >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i + 1][j - zero][k - one] + 1);
                    }
                }
            }
        }
        return dp[0][m][n];
    }
}
