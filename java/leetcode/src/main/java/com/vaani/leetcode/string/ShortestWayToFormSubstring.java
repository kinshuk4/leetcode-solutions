package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/shortest-way-to-form-string/
 * <p>
 * From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).
 * <p>
 * Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: source = "abc", target = "abcbc"
 * Output: 2
 * Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
 * <p>
 * Example 2:
 * <p>
 * Input: source = "abc", target = "acdbc"
 * Output: -1
 * Explanation: The target string cannot be constructed from the subsequences of source string due to the character "d" in target string.
 * <p>
 * Example 3:
 * <p>
 * Input: source = "xyz", target = "xzyxz"
 * Output: 3
 * Explanation: The target string can be constructed as follows "xz" + "y" + "xz".
 * <p>
 * Constraints:
 * <p>
 * Both the source and target strings consist of only lowercase English letters from “a”-“z”.
 * The lengths of source and target string are between 1 and 1000.
 */

public class ShortestWayToFormSubstring {
    // Time - O (n*m), Space - O(m)
    //https://www.youtube.com/watch?v=evesA3gr9BE
    public int shortestWay(String source, String target) {
        int numSubsequences = 0;
        String remaining = target;

        while (remaining.length() > 0) {
            StringBuilder subsequenceSB = new StringBuilder();
            int i = 0;
            int j = 0;
            while (i < source.length() && j < remaining.length()) {
                if (source.charAt(i++) == remaining.charAt(j)) {
                    subsequenceSB.append(remaining.charAt(j++));
                }
            }

            if (subsequenceSB.length() == 0) {
                return -1;
            }
            numSubsequences++;
            remaining = remaining.substring(subsequenceSB.length());
        }

        return numSubsequences;
    }
}
