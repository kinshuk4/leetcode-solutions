package com.vaani.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
 * 1178. Number of Valid Words for Each Puzzle
 * Hard
 * <p>
 * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
 * word contains the first letter of puzzle.
 * For each letter in word, that letter is in puzzle.
 * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
 * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
 * Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
 * Output: [1,1,3,2,4,0]
 * Explanation:
 * 1 valid word for "aboveyz" : "aaaa"
 * 1 valid word for "abrodyz" : "aaaa"
 * 3 valid words for "abslute" : "aaaa", "asas", "able"
 * 2 valid words for "absoryz" : "aaaa", "asas"
 * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
 * There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
 * Example 2:
 * <p>
 * Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
 * Output: [0,1,3,2,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 105
 * 4 <= words[i].length <= 50
 * 1 <= puzzles.length <= 104
 * puzzles[i].length == 7
 * words[i] and puzzles[i] consist of lowercase English letters.
 * Each puzzles[i] does not contain repeated characters.
 */
public class NumberOfValidWordsForEachPuzzle {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        int W = words.length, P = puzzles.length;
        int[] wBitMask = new int[W], pBitMask = new int[P], ansBitMask = new int[P];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < W; i++) {
            for (char ch : words[i].toCharArray())
                wBitMask[i] |= 1 << (ch - 'a');
        }

        for (int i = 0; i < P; i++) {
            ansBitMask[i] |= 1 << (puzzles[i].charAt(0) - 'a');
            for (char ch : puzzles[i].toCharArray()) {
                pBitMask[i] |= 1 << (ch - 'a');
            }
        }

        for (int i = 0; i < P; i++) {
            int count = 0;
            for (int j = 0; j < W; j++) {
                if ((wBitMask[j] & pBitMask[i]) == wBitMask[j] && (wBitMask[j] & ansBitMask[i]) == ansBitMask[i]) {
                    count++;
                }
            }
            list.add(count);
        }

        return list;
    }
}
