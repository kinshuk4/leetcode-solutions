package com.vaani.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-word-in-dictionary/
 * 720. Longest Word in Dictionary
 * Easy
 * <p>
 * Given a string and a string dictionary, find the longest string in the dictionary
 * that can be formed by deleting some characters of the given
 * string. If there are more than one possible results, return the longest word with the smallest
 * lexicographical order. If there is no possible result, return the empty string.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * <p>
 * Example 2:
 * <p>
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 * <p>
 * Note:
 * All the strings in the input will only contain lowercase letters.
 * The length of words will be in the range [1, 1000].
 * The length of words[i] will be in the range [1, 30].
 */
public class LongestWordInDictonary {

    public static void main(String[] args) throws Exception {
        String[] arr = {"ale", "apple", "monkey", "plea"};
        System.out.println(new LongestWordInDictonary().findLongestWord(arr));
    }

    public String findLongestWord(String[] words) {
        Arrays.sort(words);
        Set<String> set = new HashSet<>();
        String ans = "";
        for (String w : words) {
            if (w.length() == 1 || set.contains(w.substring(0, w.length() - 1))) {
                ans = w.length() > ans.length() ? w : ans;
                set.add(w);
            }
        }
        return ans;
    }
}
