package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/
 * 522. Longest Uncommon Subsequence II
 * Medium
 * Given an array of strings strs, return the length of the longest uncommon subsequence between them. If the longest uncommon subsequence does not exist, return -1.
 * <p>
 * An uncommon subsequence between an array of strings is a string that is a subsequence of one string but not the others.
 * <p>
 * A subsequence of a string s is a string that can be obtained after deleting any number of characters from s.
 * <p>
 * For example, "abc" is a subsequence of "aebdc" because you can delete the underlined characters in "aebdc" to get "abc". Other subsequences of "aebdc" include "aebdc", "aeb", and "" (empty string).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: strs = ["aba","cdc","eae"]
 * Output: 3
 * Example 2:
 * <p>
 * Input: strs = ["aaa","aaa","aa"]
 * Output: -1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= strs.length <= 50
 * 1 <= strs[i].length <= 10
 * strs[i] consists of lowercase English letters.
 */
public class LongestUncommonSubsequenceII {
    public int findLUSlength(String[] s) {
        int ans = -1, n = s.length;
        for (int i = 0; i < n; i++) {
            if (s[i].length() > ans) {
                int j = -1;
                while (++j < n) {
                    if (i != j && !isUS(s[i], s[j])) {
                        break;
                    }
                }
                if (j == n) {
                    ans = s[i].length();
                }
            }
        }
        return ans;
    }

    private static boolean isUS(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j++)) {
                i++;
            }
        }
        return i != s1.length();
    }

}
