package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 * 1309. Decrypt String from Alphabet to Integer Mapping
 * Easy
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 * <p>
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 * <p>
 * It's guaranteed that a unique mapping will always exist.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 * Example 2:
 * <p>
 * Input: s = "1326#"
 * Output: "acz"
 * Example 3:
 * <p>
 * Input: s = "25#"
 * Output: "y"
 * Example 4:
 * <p>
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 */
public class DecryptStringFromAlphabetToIntegerMapping {
    public String freqAlphabets(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
                result.append((char) (Integer.parseInt(s.substring(i, i + 2)) - 1 + 'a'));
                i += 2;
            } else {
                result.append((char) (Integer.parseInt(s.substring(i, i + 1)) - 1 + 'a'));
            }
        }
        return result.toString();
    }
}
