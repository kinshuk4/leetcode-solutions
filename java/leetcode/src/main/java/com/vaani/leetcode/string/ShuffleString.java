package com.vaani.leetcode.string;

import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/shuffle-string/
 * 1528. Shuffle String
 * Easy
 * <p>
 * Given a string s and an integer array indices of the same length.
 * <p>
 * The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 * <p>
 * Return the shuffled string.
 * <p>
 * Example 1
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 */
public class ShuffleString {
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] chars = new char[n];
        IntStream.range(0, n).forEach(i -> chars[indices[i]] = s.charAt(i));
        return new String(chars);
    }
}
