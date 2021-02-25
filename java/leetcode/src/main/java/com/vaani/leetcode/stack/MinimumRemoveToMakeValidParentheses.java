package com.vaani.leetcode.stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Given a string s of '(' , ')' and lowercase English characters.
 * <p>
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 * <p>
 * Formally, a parentheses string is valid if and only if:
 * <p>
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 * <p>
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 * <p>
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 * <p>
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        int open = 0;
        int close = 0;

        for (char c : s.toCharArray()) {
            if (c == ')') {
                ++open;
            }
        }


        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == ')') {
                --open;

                if (close > 0) {
                    sb.append(c);
                    --close;
                }
            } else if (c == '(') {
                if (close < open) {
                    ++close;
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }

        }

        return sb.toString();
    }

}
