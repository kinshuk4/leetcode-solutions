package com.vaani.leetcode.dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/palindrome-partitioning-ii/
 * 132. Palindrome Partitioning II
 * Hard
 * <p>
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * <p>
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * Example 2:
 * <p>
 * Input: s = "a"
 * Output: 0
 * Example 3:
 * <p>
 * Input: s = "ab"
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 2000
 * s consists of lower-case English letters only.
 */
public class PalindromePartitioningII {

    public static void main(String[] args) {
        System.out.println(
                new PalindromePartitioningII()
                        .minCut(
                                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    private int[] dp;
    private boolean[][] palindrome;

    public int minCut(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        dp = new int[s.length()];
        Arrays.fill(dp, Integer.MAX_VALUE);
        char[] charArr = s.toCharArray();
        palindrome = new boolean[charArr.length][charArr.length];
        return doNext(charArr, 0) - 1;
    }

    private int doNext(char[] s, int p) {
        if (p >= s.length) {
            return 0;
        }
        if (dp[p] < Integer.MAX_VALUE) {
            return dp[p];
        }
        for (int i = p, l = s.length; i < l; i++) {
            if (p + 1 <= i - 1) {
                palindrome[p][i] = (palindrome[p + 1][i - 1] && (s[p] == s[i]));
            } else {
                palindrome[p][i] = (i == p) || (s[i] == s[p]);
            }
            if (palindrome[p][i]) {
                dp[p] = Math.min(doNext(s, i + 1) + 1, dp[p]);
            }
        }
        return dp[p];
    }
}
