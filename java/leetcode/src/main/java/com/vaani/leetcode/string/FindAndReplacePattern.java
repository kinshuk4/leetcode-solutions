package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/find-and-replace-pattern/
 * 890. Find and Replace Pattern
 * Medium
 * <p>
 * Given a list of strings words and a string pattern, return a list of words[i] that match pattern. '
 * You may return the answer in any order.
 * <p>
 * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x),
 * we get the desired word.
 * <p>
 * Recall that a permutation of letters is a bijection from letters to letters:
 * every letter maps to another letter, and no two letters map to the same letter.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * Output: ["mee","aqq"]
 * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
 * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation, since a and b map to the same letter.
 * Example 2:
 * <p>
 * Input: words = ["a","b","c"], pattern = "a"
 * Output: ["a","b","c"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= pattern.length <= 20
 * 1 <= words.length <= 50
 * words[i].length == pattern.length
 * pattern and words[i] are lowercase English letters.
 */
public class FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        for (String s : words) {
            if (isPatternMatched(s, pattern)) {
                ans.add(s);
            }
        }
        return ans;

    }

    public boolean isPatternMatched(String word, String pattern) {
        Map<Character, Character> patternToWordMap = new HashMap<>();
        Map<Character, Character> wordToPatternMap = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            char p = pattern.charAt(i);
            if (patternToWordMap.containsKey(p) && patternToWordMap.get(p) != c) {
                return false;
            }
            patternToWordMap.put(p, c);

            if (wordToPatternMap.containsKey(c) && wordToPatternMap.get(c) != p) {
                return false;
            }
            wordToPatternMap.put(c, p);
        }


        return true;
    }

}
