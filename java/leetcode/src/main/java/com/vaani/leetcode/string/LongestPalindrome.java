package com.vaani.leetcode.string;
/**
 * https://leetcode.com/problems/longest-palindrome/
 * 409. Longest Palindrome
 * Easy
 *
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 *
 * <p>This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * <p>Note: Assume the length of given string will not exceed 1,010.
 *
 * <p>Example:
 *
 * <p>Input: "abccccdd"
 *
 * <p>Output: 7
 *
 * <p>Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    public static void main(String[] args) {
        int result = new LongestPalindrome().longestPalindrome("asdfasdf");
        System.out.println(result);
    }

    public int longestPalindrome(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        s.chars().forEach(c -> freqMap.put((char) c, freqMap.getOrDefault((char) c, 0) + 1));

        int max = 0;
        boolean odd = false;
        for (char c : freqMap.keySet()) {
            int count = freqMap.get(c);
            max += count;
            if ((count % 2) != 0) {
                max--;
                odd = true;
            }
        }
        // allow 1 odd character in palindrome
        if (odd) {
            max++;
        }
        return max;
    }
}
