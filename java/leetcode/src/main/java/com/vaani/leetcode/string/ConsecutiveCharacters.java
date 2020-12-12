package com.vaani.leetcode.string;

/**
 * 1446. Consecutive Characters
 * Easy
 * <p>
 * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
 * <p>
 * Return the power of the string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 * Example 2:
 * <p>
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 * Example 3:
 * <p>
 * Input: s = "triplepillooooow"
 * Output: 5
 * Example 4:
 * <p>
 * Input: s = "hooraaaaaaaaaaay"
 * Output: 11
 * Example 5:
 * <p>
 * Input: s = "tourist"
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 500
 * s contains only lowercase English letters.
 */
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        int max = 1;
        int cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
                max = Math.max(max, cur);
            } else {
                cur = 1;
            }
        }
        return max;
    }

}
