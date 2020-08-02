package com.vaani.leetcode.string;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 * 387. First Unique Character in a String
 * Easy
 * = Given a string, find the first non-repeating
 * character in it and return it's index. If it doesn't exist, return -1.
 *
 * <p>Examples:
 *
 * <p>s = "leetcode" return 0.
 *
 * <p>s = "loveleetcode", return 2. Note: You may assume the string contain only lowercase letters.
 */
public class FirstUniqueCharacterInAString {

    public static void main(String[] args) throws Exception {
        System.out.println(new FirstUniqueCharacterInAString().firstUniqChar("loveleetcode"));
        System.out.println(new FirstUniqueCharacterInAString().firstUniqChar2("cc"));
    }

    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        int[] charMap = new int[256];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            charMap[s.charAt(i)]++;
        }

        for (int i = 0; i < n; i++) {
            if (charMap[s.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;
    }

    // Using LinkedHashMap
    public int firstUniqChar2(String s) {
        if (s == null || s.isEmpty()) {
            return -1;
        }
        // Map char and index
        LinkedHashMap<Character, Integer> uniqueMap = new LinkedHashMap<>();
        Set<Character> seen = new HashSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if(seen.contains(c)){
                continue;
            }else if (uniqueMap.containsKey(c)){
                uniqueMap.remove(c);
                seen.add(c);
                continue;
            }

            uniqueMap.put(s.charAt(i), i);
        }

        return uniqueMap.size() == 0 ? -1: uniqueMap.entrySet().iterator().next().getValue();
    }
}
