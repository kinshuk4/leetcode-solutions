package com.vaani.leetcode.two_pointers;

/**
 * https://leetcode.com/problems/long-pressed-name/
 * 925. Long Pressed Name
 * Easy
 * <p>
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 * <p>
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * <p>
 * Example 2:
 * <p>
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 * <p>
 * Example 3:
 * <p>
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 * <p>
 * Example 4:
 * <p>
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= name.length <= 1000
 * 1 <= typed.length <= 1000
 * The characters of name and typed are lowercase letters.
 */
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        int m = name.length();
        int n = typed.length();
        if (m > n) return false;
        while (i < m) {
            while (i < m && j < n && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            }
            while (j > 0 && j < n && typed.charAt(j - 1) == typed.charAt(j)) {
                j++;
            }
            if (i == m) {
                return j == n;
            } else if (j == n || name.charAt(i) != typed.charAt(j)) {
                return false;
            }
        }
        return j == n;
    }
}