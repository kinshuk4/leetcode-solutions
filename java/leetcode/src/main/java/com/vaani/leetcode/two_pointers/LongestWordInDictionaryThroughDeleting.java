package com.vaani.leetcode.two_pointers;

import com.vaani.dsa.algo.ds.string.IsSubsequence;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/
 * 524. Longest Word in Dictionary through Deleting
 * Medium
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 * <p>
 * Example 1:
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * <p>
 * Output:
 * "apple"
 * Example 2:
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 * <p>
 * Output:
 * "a"
 * Note:
 * All the strings in the input will only contain lower-case letters.
 * The size of the dictionary won't exceed 1,000.
 * The length of all the strings in the input won't exceed 1,000.
 */
public class LongestWordInDictionaryThroughDeleting {
    public String findLongestWord(String s, List<String> d) {
        String ans = "";
        for (String word : d) {
            if (IsSubsequence.isSubsequence(word, s)) {
                if (ans.length() < word.length()) {
                    ans = word;
                } else if (ans.length() == word.length()) {
                    ans = ans.compareTo(word) > 0 ? word : ans;
                }

            }
        }
        return ans;
    }
}
