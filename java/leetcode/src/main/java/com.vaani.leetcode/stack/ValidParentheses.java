package com.vaani.leetcode.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static com.vaani.dsa.ds.algos.stack.ValidParentheses.isValid3;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */


public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid3("(h[e"));
    }

}
