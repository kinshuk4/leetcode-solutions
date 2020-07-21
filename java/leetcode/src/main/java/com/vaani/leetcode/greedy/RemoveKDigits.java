package com.vaani.leetcode.greedy;

import org.junit.Assert;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-k-digits/
 * <p>
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 */
public class RemoveKDigits {

    public static void main(String[] args) {
        RemoveKDigits underTest = new RemoveKDigits();
        Assert.assertEquals("1219", underTest.removeKdigits("1432219", 3));
    }

    // dry run with 14301620
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        //Store the final string in stack
        for (char c : num.toCharArray()) {
            while (!stack.empty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k -= 1;
            }

            if (!stack.isEmpty() || c != '0') {
                stack.push(c);
            }
        }

        //Now remove the largest values from the top of the stack
        // solves cases like 1001 or 1234 and k = 2
        while (!stack.isEmpty() && k-- > 0) {
            stack.pop();
        }

        if (stack.isEmpty()) {
            return "0";
        }

        //Now retrieve the number from stack into a string (reusing num)
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop()); // string is reversed, so we have to isnert and not append
        }
        return sb.toString();

    }
}
