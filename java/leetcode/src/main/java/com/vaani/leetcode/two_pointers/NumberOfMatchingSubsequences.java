package com.vaani.leetcode.two_pointers;

/**
 * 04/04/2019 Given string S and a dictionary of words words, find
 * the number of words[i] that is a subsequence of S.
 *
 * <p>Example : Input: S = "abcde" words = ["a", "bb", "acd", "ace"] Output: 3 Explanation: There
 * are three words in words that are a subsequence of S: "a", "acd", "ace". Note:
 *
 * <p>All words in words and S will only consists of lowercase letters. The length of S will be in
 * the range of [1, 50000]. The length of words will be in the range of [1, 5000]. The length of
 * words[i] will be in the range of [1, 50].
 *
 * <p>Solution: O((w + S) x N (no of words)) Using two pointers technique check if each of the given
 * string is a sub-sequence of the main string.
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

    public int numMatchingSubseq(String S, String[] words) {
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (isSubsequence(S, w)) {
                count++;
            }
        }
        return count;
    }

    private boolean isSubsequence(String word, String inputString) {
        int prevCharIndex = 0;

        for (char ch : word.toCharArray()) {

            int index = inputString.indexOf(ch, prevCharIndex);
            if (index == -1) {
                return false;
            }

            prevCharIndex = index + 1;
        }

        return true;
    }
}
