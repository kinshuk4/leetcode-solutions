package com.vaani.leetcode.string;

import static org.junit.Assert.assertArrayEquals;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/vowel-spellchecker/
 * 966. Vowel Spellchecker
 * Medium
 * <p>
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 * <p>
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 * <p>
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 * <p>
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= wordlist.length <= 5000
 * 1 <= queries.length <= 5000
 * 1 <= wordlist[i].length <= 7
 * 1 <= queries[i].length <= 7
 * All strings in wordlist and queries consist only of english letters.
 */
public class VowelSpellchecker {
    public static void main(String[] args) {
        VowelSpellchecker underTest = new VowelSpellchecker();

        String[] wordList = new String[]{"KiTe", "kite", "hare", "Hare"};
        String[] queries = new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"};
        String[] expected = {"kite", "KiTe", "KiTe", "Hare", "hare", "", "", "KiTe", "", "KiTe"};
        assertArrayEquals(expected, underTest.spellchecker(wordList, queries));

        wordList = new String[]{"Yellow"};
        queries = new String[]{"yellow"};
        expected = new String[]{"Yellow"};
        assertArrayEquals(expected, underTest.spellchecker(wordList, queries));
    }

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');
    private static final char VOWEL_MASK = '#';

    public String[] spellchecker(String[] wordlist, String[] queries) {

        Set<String> wordSet = new HashSet<>();
        Map<String, String> patternMap = new HashMap<>();


        for (String word : wordlist) {
            wordSet.add((word));
            patternMap.putIfAbsent(word.toLowerCase(), word);

            String vowelMaskedStr = getWordWithVowelMasked(word);
            patternMap.putIfAbsent(vowelMaskedStr, word);
        }

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (wordSet.contains(query)) {
                continue;
            }

            String lowerCaseQuery = query.toLowerCase();
            if (patternMap.containsKey(lowerCaseQuery)) {
                queries[i] = patternMap.get(lowerCaseQuery);
                continue;
            }

            String vowelMaskedQuery = getWordWithVowelMasked(query);
            if (patternMap.containsKey(vowelMaskedQuery)) {
                queries[i] = patternMap.get(vowelMaskedQuery);
                continue;
            }

            queries[i] = "";
        }

        return queries;
    }

    private String getWordWithVowelMasked(String word) {
        char[] arr = word.toLowerCase().toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char c = arr[i];
            if (VOWELS.contains(c)) {
                // Replacing with # to take care of string length
                arr[i] = VOWEL_MASK;
            }

        }
        return new String(arr);
    }

}
