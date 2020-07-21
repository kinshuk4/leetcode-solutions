package com.vaani.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * <p>Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * <p>All occurrences of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a character may map to
 * itself.
 *
 * <p>For example, Given "egg", "add", return true.
 *
 * <p>Given "foo", "bar", return false.
 *
 * <p>Given "paper", "title", return true.
 *
 * <p>Note: You may assume both s and t have the same length. Solution O(N): Maintain two hashmaps
 * and compare character by character.
 */
public class IsomorphicStrings {
    public static void main(String[] args) throws Exception {
        System.out.println(new IsomorphicStrings().isIsomorphic("abc", "dea"));
    }

    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> first = new HashMap<>();
        Map<Character, Character> second = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            if (first.containsKey(c)) {
                char dStored = first.get(c);
                if (d != dStored) {
                    return false;
                }
            } else {
                first.put(c, d);
                if (second.containsKey(d)) {
                    return false;
                }
                second.put(d, c);
            }
        }
        return true;
    }

    // slow but clean
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            map.put(c, d);
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            char dStored = map.get(c);
            if (dStored != d) {
                return false;
            }
        }
        return true;
    }
}
