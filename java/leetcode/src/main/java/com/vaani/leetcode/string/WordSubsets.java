package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/word-subsets/
 * 916. Word Subsets
 * Medium
 * <p>
 * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
 * <p>
 * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".
 * <p>
 * Now say a word a from A is universal if for every b in B, b is a subset of a.
 * <p>
 * Return a list of all universal words in A.  You can return the words in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
 * Output: ["apple","google","leetcode"]
 * Example 3:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
 * Output: ["facebook","google"]
 * Example 4:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
 * Output: ["google","leetcode"]
 * Example 5:
 * <p>
 * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
 * Output: ["facebook","leetcode"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length, B.length <= 10000
 * 1 <= A[i].length, B[i].length <= 10
 * A[i] and B[i] consist only of lowercase letters.
 * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
 */
public class WordSubsets {
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] bMaxFreq = new int[26];
        for (String word : B) {
            for (char ch : word.toCharArray()) {
                int[] bWordFreq = getFreq(word);
                bMaxFreq[ch - 'a'] = Math.max(bMaxFreq[ch - 'a'], bWordFreq[ch - 'a']);
            }
        }
        List<String> ans = new ArrayList<>();
        for (String word : A) {
            int[] aWordFreq = getFreq(word);
            if (compare(aWordFreq, bMaxFreq)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private int[] getFreq(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }

    private boolean compare(int[] a, int[] b) {
        for (int i = 0; i < 26; i++) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }
}
