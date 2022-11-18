package com.vaani.leetcode.hashing;

import org.junit.Assert;

import java.util.*;

public class WordPattern2 {
    public static void main(String[] args) {
        WordPattern2 wp2 = new WordPattern2();
        Assert.assertTrue(wp2.wordPattern("abab", "redblueredblue"));
        Assert.assertTrue(wp2.wordPattern("aaaa", "asdasdasdasd"));
        Assert.assertFalse(wp2.wordPattern("aabb", "xyzabcxzyabc"));
    }
    public boolean wordPattern(String pattern, String str) {

        Map<Character, String> map = new HashMap<>();
        Map<String, Character> inverseMap = new HashMap<>();

        return dfs(pattern, str, 0, 0, map, inverseMap);
    }

    // p: pattern length
    // r: word length
    boolean dfs(String pat, String str, int patIdx, int strIdx, Map<Character, String> map, Map<String, Character> inverseMap) {

        if (patIdx == pat.length() && strIdx == str.length()) {
            return true;
        }
        if (patIdx == pat.length() || strIdx == str.length()) { // didn't hit above if but this one
            return false;
        }

        char patChar = pat.charAt(patIdx);

        for (int i = strIdx; i < str.length(); ++i) {
            String tentativeMatchString = str.substring(strIdx, i + 1);

            if (map.containsKey(patChar) && map.get(patChar).equals(tentativeMatchString)) {
                if (dfs(pat, str, patIdx + 1, i + 1, map, inverseMap)) {
                    return true;
                }
            } else if (!map.containsKey(patChar)) {
                if (!inverseMap.containsKey(tentativeMatchString)) { // new pattern needed
                    map.put(patChar, tentativeMatchString);
                    inverseMap.put(tentativeMatchString, patChar);

                    if (dfs(pat, str, patIdx + 1, i + 1, map, inverseMap)) {
                        return true;
                    }

                    map.remove(patChar); // @note: need to remove, since this attempt is failed
                    inverseMap.remove(tentativeMatchString);
                }
            }
        }
        return false;
    }
}
