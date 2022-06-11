package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/number-of-matching-subsequences/
 * 792. Number of Matching Subsequences
 * Medium
 * <p>
 * <p>
 * Given a string s and an array of strings words, return the number of words[i] that is a subsequence of s.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * <p>
 * For example, "ace" is a subsequence of "abcde".
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcde", words = ["a","bb","acd","ace"]
 * Output: 3
 * Explanation: There are three strings in words that are a subsequence of s: "a", "acd", "ace".
 * Example 2:
 * <p>
 * Input: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * s and words[i] consist of only lowercase English letters.
 */
public class NumberOfMatchingSubsequences {

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] A = {"a", "bb", "acd", "ace"};
        System.out.println(new NumberOfMatchingSubsequences().numMatchingSubseq("abcde", A));
    }

    public int numMatchingSubseq(String s, String[] words) {
        int count = 0;
        for (String word : words) {
            if (isSubsequence(word, s)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSubsequence(String word, String s) {
        int prevCharIndex = 0;

        for (char ch : word.toCharArray()) {

            int index = s.indexOf(ch, prevCharIndex);
            if (index == -1) {
                return false;
            }

            prevCharIndex = index + 1;
        }

        return true;
    }
}
