package com.vaani.leetcode.hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * <p>
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * <p>
 * The order of output does not matter.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * Output:
 * [0, 6]
 * <p>
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> result = findAnagramsSlidingWindowHashTable(s, p);
        result.forEach(System.out::println);
    }

    // very efficient but not wworking
    public static List<Integer> findAnagramsSlidingWindow(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        int[] pMap = new int[26];

        for (char c : p.toCharArray()) {
            pMap[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        int w = p.length();
        int count = w;

        while (right < s.length()) {
            char inChar = s.charAt(right);
            if (pMap[inChar - 'a'] >= 1) {
                pMap[inChar - 'a']--;
                right++;
                count--;
            }
            if (count == 0) {
                result.add(left);
            }

            if (right - left == w) {
                left++;
                char outChar = s.charAt(left);
                if (pMap[outChar - 'a'] >= 0) {
                    pMap[outChar - 'a']++;
                    count++;
                }

            }
        }

        return result;
    }

    public List<Integer> findAnagramsSlidingWindow2(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }
        int w = p.length();


        int[] pMap = new int[26];
        for (int i = 0; i < w; i++) {
            char c = p.charAt(i);
            pMap[c - 'a']++;
        }

        for (int i = 0; i <= s.length() - w; i++) {
            char c = s.charAt(i);
            if (pMap[c - 'a'] == 0) continue;
            if (isAnagram(s, i, i + w - 1, pMap)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isAnagram(String s, int start, int end, int[] pArr) {
        int[] sArr = new int[26];
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            if (pArr[c - 'a'] == 0) return false;
            sArr[c - 'a']++;
            if (pArr[(c - 'a')] < sArr[(c - 'a')]) return false;
        }

        return true;
    }


    public static List<Integer> findAnagramsSlidingWindowHashTable(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        int pHash = p.chars().sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()
                .hashCode();

        int w = p.length();


        for (int i = 0; i < s.length() - w; i++) {
            int sHash = s.substring(i, i + w).chars().sorted()
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString()
                    .hashCode();
            if (sHash == pHash) {
                result.add(i);
            }

        }

        return result;
    }
}
