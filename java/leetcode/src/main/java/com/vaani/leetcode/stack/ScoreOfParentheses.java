package com.vaani.leetcode.stack;

import java.util.*;

/**
 * https://leetcode.com/problems/score-of-parentheses/
 * 856. Score of Parentheses
 * Medium
 * Given a balanced parentheses string S, compute the score of the string based on the following rule:
 * <p>
 * () has score 1
 * AB has score A + B, where A and B are balanced parentheses strings.
 * (A) has score 2 * A, where A is a balanced parentheses string.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: 1
 * Example 2:
 * <p>
 * Input: "(())"
 * Output: 2
 * Example 3:
 * <p>
 * Input: "()()"
 * Output: 2
 * Example 4:
 * <p>
 * Input: "(()(()))"
 * Output: 6
 * <p>
 * <p>
 * Note:
 * <p>
 * S is a balanced parentheses string, containing only ( and ).
 * 2 <= S.length <= 50
 */
public class ScoreOfParentheses {
    public int scoreOfParentheses(String S) {
        Deque<Integer> dq = new ArrayDeque<>();

        for (char c : S.toCharArray()) {
            if (c == '(') {
                dq.push(-1);
            } else {
                int score = 0;
                while (dq.peek() != -1) {
                    score += dq.pop();
                }
                dq.pop();
                dq.push(score == 0 ? 1 : 2 * score);
            }
        }

        int score = 0;
        while (!dq.isEmpty()) {
            score += dq.pop();
        }
        return score;
    }
}
