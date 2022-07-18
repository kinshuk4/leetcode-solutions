package com.vaani.leetcode.string;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/determine-if-string-halves-are-alike/
 * 1704. Determine if String Halves Are Alike
 * Easy
 * <p>
 * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the first half and b be the second half.
 * <p>
 * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'). Notice that s contains uppercase and lowercase letters.
 * <p>
 * Return true if a and b are alike. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "book"
 * Output: true
 * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
 * Example 2:
 * <p>
 * Input: s = "textbook"
 * Output: false
 * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
 * Notice that the vowel o is counted twice.
 * Example 3:
 * <p>
 * Input: s = "MerryChristmas"
 * Output: false
 * Example 4:
 * <p>
 * Input: s = "AbCdEfGh"
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= s.length <= 1000
 * s.length is even.
 * s consists of uppercase and lowercase letters.
 */
public class DetermineIfStringHalvesAreAlike {
    private static final Set<Character> vowelSet = "aeiouAEIOU".chars().mapToObj(x -> (char) x).collect(Collectors.toSet());

    public boolean halvesAreAlike(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        int left = 0, right = 0;
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (vowelSet.contains(s.charAt(i))) {
                left++;
            }
            if (vowelSet.contains(s.charAt(n - 1 - i))) {
                right++;
            }
        }
        return left == right;


    }
}
