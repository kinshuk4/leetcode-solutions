package com.vaani.leetcode.string;

/**
 * 583. Delete Operation for Two Strings
 * Medium
 * <p>
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 * <p>
 * In one step, you can delete exactly one character in either string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 * <p>
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 */
public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return m + n - 2 * dp[m][n];
    }
}
