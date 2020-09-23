package com.vaani.leetcode.stack;

/**
 * https://leetcode.com/problems/remove-outermost-parentheses/
 * 1021. Remove Outermost Parentheses
 * Easy
 * <p>
 * A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.
 * <p>
 * A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.
 * <p>
 * Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.
 * <p>
 * Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "(()())(())"
 * Output: "()()()"
 * Explanation:
 * The input string is "(()())(())", with primitive decomposition "(()())" + "(())".
 * After removing outer parentheses of each part, this is "()()" + "()" = "()()()".
 * <p>
 * Example 2:
 * <p>
 * Input: "(()())(())(()(()))"
 * Output: "()()()()(())"
 * Explanation:
 * The input string is "(()())(())(()(()))", with primitive decomposition "(()())" + "(())" + "(()(()))".
 * After removing outer parentheses of each part, this is "()()" + "()" + "()(())" = "()()()()(())".
 * <p>
 * Example 3:
 * <p>
 * Input: "()()"
 * Output: ""
 * Explanation:
 * The input string is "()()", with primitive decomposition "()" + "()".
 * After removing outer parentheses of each part, this is "" + "" = "".
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * S.length <= 10000
 * S[i] is "(" or ")"
 * S is a valid parentheses string
 */
public class RemoveOutermostParentheses {
    static class UsingCount {
        public String removeOuterParentheses(String S) {
            StringBuilder sb = new StringBuilder();
            int opened = 0;
            for (char c : S.toCharArray()) {
                if (c == '(' && opened++ > 0) {
                    sb.append(c);
                }
                if (c == ')' && opened-- > 1) {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
}
