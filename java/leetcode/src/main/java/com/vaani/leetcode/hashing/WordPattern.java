package com.vaani.leetcode.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/word-pattern/
 * 290. Word Pattern
 * Easy
 * <p>
 * Given a pattern and a string str, find if str follows the same pattern.
 * <p>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * <p>
 * Example 1:
 * <p>
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * <p>
 * Example 4:
 * <p>
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * <p>
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters that may be separated by a single space.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            // If the map is not associated with any value the return type is null else the previous associated value is returned.
            if (index.put(Character.toString(pattern.charAt(i)), i) != index.put(words[i], i)) {
                return false;
            }
        }
        return true;
    }

    // submitted
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<String, Character> wordToChar = new HashMap<>();
        Map<Character, String> charToWord = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char c = pattern.charAt(i);
            if(!wordToChar.containsKey(word) && !charToWord.containsKey(c)){
                wordToChar.put(word, c);
                charToWord.put(c, word);
            }else if(wordToChar.containsKey(word) && charToWord.containsKey(c)){
                char d = wordToChar.get(word);
                String word2 = charToWord.get(c);
                if(d != c || !word.equals(word2)){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

}
