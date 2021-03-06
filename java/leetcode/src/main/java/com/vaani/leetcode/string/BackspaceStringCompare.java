package com.vaani.leetcode.string;

import java.util.Stack;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 * <p>
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 * <p>
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 * <p>
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 * <p>
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 * <p>
 * Can you solve it in O(N) time and O(1) space?
 */
public class BackspaceStringCompare {

    // O(m*n) space solution
    public boolean backspaceCompare1(String S, String T) {
        String resS = build(S);
        String resT = build(T);
        return resS.equals(resT);
    }

    public String build(String S) {
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c != '#')
                sb.append(c);
            else {
                if (sb.length() != 0)
                    sb.deleteCharAt(sb.length() - 1);
            }
        }
        return sb.toString();
    }

    // start from back of the string, as backspace would have already taken affect
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) { // While there may be chars in build(S) or build (T)
            while (i >= 0) { // Find position of next possible char in build(S)
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) { // Find position of next possible char in build(T)
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            // If two actual characters are different
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) {
                return false;
            }
            // If expecting to compare char vs nothing
            if (((i >= 0) && (j == -1)) || ((i == -1) && j >= 0)) {
                return false;
            }
            i--;
            j--;
        }
        return true;
    }

    static class UsingStack {
        public boolean backspaceCompare(String S, String T) {
            Stack<Character> sStack = new Stack<>();
            Stack<Character> tStack = new Stack<>();

            for (char c : S.toCharArray()) {
                if (c != '#') {
                    sStack.push(c);
                } else if (!sStack.isEmpty()) {
                    sStack.pop();
                }
            }

            for (char c : T.toCharArray()) {
                if (c != '#') {
                    tStack.push(c);
                } else if (!tStack.isEmpty()) {
                    tStack.pop();
                }
            }

            while (!sStack.isEmpty()) {
                char curr = sStack.pop();
                if (tStack.isEmpty() || tStack.pop() != curr) {
                    return false;
                }
            }

            return tStack.isEmpty(); // if tStack is not empty, it is false
        }
    }

}
