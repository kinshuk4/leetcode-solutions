package com.vaani.leetcode.bit_manipulation;

import java.util.*;

/**
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * 318. Maximum Product of Word Lengths
 * Medium
 * <p>
 * <p>
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 * <p>
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 * <p>
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists only of lowercase English letters.
 */
public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        Map<Integer, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < word.length(); j++) {
                set.add(word.charAt(j));
            }
            map.put(i, set);
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                Set<Character> jSet = new HashSet<>(map.get(j));

                jSet.retainAll(map.get(i));

                if (jSet.isEmpty()) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
