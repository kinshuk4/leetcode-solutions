package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/longest-string-chain/
 * 1048. Longest String Chain
 * Medium
 * <p>
 * Given a list of words, each word consists of English lowercase letters.
 * <p>
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2. For example, "abc" is a predecessor of "abac".
 * <p>
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 * <p>
 * Return the longest possible length of a word chain with words chosen from the given list of words.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["a","b","ba","bca","bda","bdca"]
 * Output: 4
 * Explanation: One of the longest word chain is "a","ba","bda","bdca".
 * Example 2:
 * <p>
 * Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] only consists of English lowercase letters.
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));

        Map<String, Integer> dpMap = new HashMap<>();

        int ans = 0;

        for (String word : words) {
            int longestChainWithCurrWord = 0;

            for (int i = 0; i < word.length(); i++) {
                String predecessor = word.substring(0, i) + word.substring(i + 1, word.length());
                longestChainWithCurrWord = Math.max(longestChainWithCurrWord, dpMap.getOrDefault(predecessor, 0) + 1);
            }

            dpMap.put(word, longestChainWithCurrWord);

            ans = Math.max(ans, longestChainWithCurrWord);
        }

        return ans;
    }
}
