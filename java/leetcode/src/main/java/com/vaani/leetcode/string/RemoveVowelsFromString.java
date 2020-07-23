package com.vaani.leetcode.string;

import java.util.Set;

/**
 * Given a string S, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "leetcodeisacommunityforcoders"
 * Output: "ltcdscmmntyfrcdrs"
 * Example 2:
 * <p>
 * Input: "aeiou"
 * Output: ""
 * <p>
 * <p>
 * Note:
 * <p>
 * S consists of lowercase English letters only.
 * 1 <= S.length <= 1000
 */
public class RemoveVowelsFromString {
    public String removeVowels(String s) {
        if (s == null || s.length() == 0)
            return s;
        Set<Character> set = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
