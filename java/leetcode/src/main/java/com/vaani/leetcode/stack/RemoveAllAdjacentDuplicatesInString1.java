package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * 1047. Remove All Adjacent Duplicates In String
 * Easy
 * <p>
 * Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.
 * <p>
 * We repeatedly make duplicate removals on S until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= S.length <= 20000
 * S consists only of English lowercase letters.
 */
public class RemoveAllAdjacentDuplicatesInString1 {
    static class UsingStringBuilderAsStack {
        public String removeDuplicates(String S) {
            StringBuilder sb = new StringBuilder();
            for (char c : S.toCharArray()) {
                int size = sb.length();
                if (size > 0 && sb.charAt(size - 1) == c) {
                    sb.deleteCharAt(size - 1);
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

    static class WithoutUsingStack {
        public String removeDuplicates(String s) {
            int i = 0, n = s.length();
            char[] res = s.toCharArray();
            for (int j = 0; j < n; ++j, ++i) {
                res[i] = res[j];
                if (i > 0 && res[i - 1] == res[i]) // count = 2
                    i -= 2;
            }
            return new String(res, 0, i);
        }
    }

    static class UsingProperStack {
        public String removeDuplicates(String S) {
            Stack<Character> stack = new Stack<>();
            for (char c : S.toCharArray()) {
                if (!stack.isEmpty() && stack.peek() == c) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
            StringBuilder sb = new StringBuilder();
            stack.forEach(sb::append);
            return sb.toString();
        }
    }

}
