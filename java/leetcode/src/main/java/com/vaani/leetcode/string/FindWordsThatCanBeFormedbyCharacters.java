package com.vaani.leetcode.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/
 * 1160. Find Words That Can Be Formed by Characters
 * Easy
 *
 * You are given an array of strings words and a chars.
 *
 * <p>A string is good if it can be formed by characters from chars (each character can only be used
 * once).
 *
 * <p>Return the sum of lengths of all good strings in words.
 *
 * <p>Example 1:
 *
 * <p>Input: words = ["cat","bt","hat","tree"], chars = "atach" Output: 6 Explanation: The strings
 * that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6. Example 2:
 *
 * <p>Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr" Output: 10 Explanation:
 * The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 *
 * <p>Note:
 *
 * <p>1 <= words.length <= 1000 1 <= words[i].length, chars.length <= 100 All strings contain
 * lowercase English letters only.
 */
public class FindWordsThatCanBeFormedbyCharacters {
    public static void main(String[] args) {
        String[] A = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        new FindWordsThatCanBeFormedbyCharacters().countCharacters(A, chars);
    }

    /**
     * <p>Solution Do a linear check for each of the words and each of the characters and sum up the
     * lengths. Keep a hashmap of key-values to avoid picking the same character again.
     */
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : chars.toCharArray()) {
            countMap.putIfAbsent(c, 0);
            countMap.put(c, countMap.get(c) + 1);
        }
        int ans = 0;
        for (String s : words) {
            Map<Character, Integer> subMap = new HashMap<>();
            for (char c : s.toCharArray()) {
                subMap.putIfAbsent(c, 0);
                subMap.put(c, subMap.get(c) + 1);
            }
            boolean possible = true;
            for (char k : subMap.keySet()) {
                if (!countMap.containsKey(k) || subMap.get(k) > countMap.get(k)) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                ans += s.length();
            }
        }
        return ans;
    }

    // Using char array
    public int countCharacters2(String[] words, String chars) {
        int count = 0;
        int[] seen = new int[26];
        //Count char of Chars String
        chars.chars().forEach(c -> ++seen[c - 'a']);


        // Comparing each word in words
        for (String word : words) {
            // simple making copy of seen arr
            int[] seenCopy = Arrays.copyOf(seen, seen.length);
            int charPresentCnt = 0;
            for (char c : word.toCharArray()) {
                if (seenCopy[c - 'a'] > 0) {
                    seenCopy[c - 'a']--;
                    charPresentCnt++;
                } else {
                    break;
                }
            }
            if (charPresentCnt == word.length()) {
                count += charPresentCnt;
            }
        }
        return count;
    }

    // submitted
    public int countCharacters3(String[] words, String chars) {
        int count = 0;
        int[] seen = new int[26];
        //Count char of Chars String
        chars.chars().forEach(c -> ++seen[c - 'a']);


        // Comparing each word in words
        for (String word : words) {
            // simple making copy of seen arr
            int[] seenCopy = Arrays.copyOf(seen, seen.length);
            boolean isFound = true;
            for (char c : word.toCharArray()) {
                if (seenCopy[c - 'a'] > 0) {
                    seenCopy[c - 'a']--;
                } else {
                    isFound = false;
                    break;
                }
            }
            if (isFound) {
                count += word.length();
            }
        }
        return count;
    }

    // Slower - even with faster processing
    public int countCharacters4(String[] words, String chars) {
        int count = 0;
        int[] seen = new int[26];
        //Count char of Chars String
        chars.chars().forEach(c -> ++seen[c - 'a']);


        // Comparing each word in words
        for (String word : words) {
            // simple making copy of seen arr
            int[] currSeen = new int[26];
            word.chars().forEach(c -> ++currSeen[c - 'a']);

            boolean isFound = true;
            for (int i = 0; i < seen.length; i++) {
                if (seen[i] < currSeen[i]) {
                    isFound = false;
                    break;
                }
            }

            if (isFound) {
                count += word.length();
            }
        }
        return count;
    }
}
