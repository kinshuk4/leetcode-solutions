package com.vaani.leetcode.hashing;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/shortest-completing-word/
 * 748. Shortest Completing Word
 * Easy
 * <p>
 * Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given string licensePlate
 * <p>
 * Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
 * <p>
 * It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
 * <p>
 * The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate, but the word "supper" does.
 * <p>
 * Example 1:
 * <p>
 * Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * Output: "steps"
 * Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
 * Note that the answer is not "step", because the letter "s" must occur in the word twice.
 * Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
 * <p>
 * Example 2:
 * <p>
 * Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * Output: "pest"
 * Explanation: There are 3 smallest length words that contains the letters "s".
 * We return the one that occurred first.
 * <p>
 * Note:
 * <p>
 * licensePlate will be a string with length in range [1, 7].
 * licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
 * words will have a length in the range [10, 1000].
 * Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 */
public class ShortestCompletingWord {

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] frequency = new int[26];
        licensePlate.toLowerCase().chars().filter(Character::isLetter).forEach(c -> frequency[c - 'a']++);

        String ans = "";
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (String word : words) {
            int[] currFrequency = new int[26];
            word.toLowerCase().chars().filter(Character::isLetter).forEach(c -> currFrequency[c - 'a']++);
            if (compare(currFrequency, frequency)) {
                return word;
            }
        }
        return ans;
    }

    public boolean compare(int[] count1, int[] count2) {
        for (int i = 0; i < 26; ++i) {
            if (count1[i] < count2[i]) {
                return false;
            }
        }
        return true;
    }

}
