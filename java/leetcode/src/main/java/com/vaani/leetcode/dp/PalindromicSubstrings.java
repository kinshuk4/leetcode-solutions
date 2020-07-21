package com.vaani.leetcode.dp;

import org.junit.Assert;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 *
 * <p>Given a string, your task is to count how many palindromic substrings in this string.
 *
 * <p>The substrings with different start indexes or end indexes are counted as different substrings
 * even they consist of same characters.
 *
 * <p>Example 1: Input: "abc" Output: 3 Explanation: Three palindromic strings: "a", "b", "c".
 * Example 2: Input: "aaa" Output: 6 Explanation: Six palindromic strings: "a", "a", "a", "aa",
 * "aa", "aaa". Note: The input string length won't exceed 1000.
 *
 * <p>Solution O(n ^ 2): Example abcba: Compare char at two indices each time for example if char at
 * index 0 and index 4 are equal and if substring 1 and 3 is a palindrome then, sub-string 0 and 4
 * is also a palindrome
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        PalindromicSubstrings underTest = new PalindromicSubstrings();

        Assert.assertEquals(3, underTest.countSubstrings2("abc"));
        Assert.assertEquals(6, underTest.countSubstrings2("aaa"));
    }

    public int countSubstrings1(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int count = s.length();
        for (int i = 0, j = 0; i < dp.length; i++, j++) {
            dp[i][j] = true;
        }

        for (int k = 1, col = s.length(); k < col; k++) {
            for (int i = 0, j = k; i < col && j < col; i++, j++) {
                if (k == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public int countSubstrings2(String str) {
        int n = str.length();
        if (n <= 1) {
            return n;
        }

        boolean[][] dp = new boolean[n][n];

        int count = 0;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        for (int i = 0; i < n - 1; ++i) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        for (int j = 2; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (dp[i + 1][j - 1] && str.charAt(i) == str.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }
}
