package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * <p>The expression string may contain open ( and closing parentheses ), the plus + or minus sign
 * -, non-negative integers and empty spaces .
 *
 * <p>You may assume that the given expression is always valid.
 *
 * <p>Some examples: "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23 Note: Do not use the
 * eval built-in library function.
 *
 */
public class BasicCalculator {

    public static void main(String[] args) throws Exception {
        System.out.println(
                new BasicCalculator().calculate("2-1 + (2 - 3) - ((2 - (2 - (3 - (4 - 5)))))"));
    }

    /**
     *  * Solution: O(n) where n is the length of the string. Maintain a stack and push each character
     *  * from the string (ignore space). As soon as a close parentheses ')' is encountered, start to pop
     *  * values and sum-up the total until '(' is poped. Push the total back to stack and continue to
     *  * iterate. The final result will be in the top of the stack which is the last and only element in
     *  * stack.
     */
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder num = new StringBuilder();
        s = "(" + s + ")";
        for (char ch : s.toCharArray()) {
            switch (ch) {
                case ' ':
                case '(':
                case '+':
                case '-':
                    if (!num.toString().equals("")) {
                        stack.push(num.toString());
                        num = new StringBuilder();
                    }
                    if (ch != ' ') { // ignore blank
                        stack.push(String.valueOf(ch));
                    }
                    break;
                case ')':
                    if (!num.toString().equals("")) {
                        stack.push(num.toString());
                        num = new StringBuilder();
                    }
                    int sum = 0;
                    int prev = 0; // maintain a prev value inorder to handle minus '-'
                    while (!stack.isEmpty()) {
                        String top = stack.pop();
                        if (top.equals("-")) {
                            sum -= (prev * 2);
                            prev = 0;
                        } else if (top.equals("+")) {
                            // ignore
                        } else if (top.equals("(")) {
                            stack.push(String.valueOf(sum));
                            break;
                        } else {
                            sum += Integer.parseInt(top);
                            prev = Integer.parseInt(top);
                        }
                    }
                    break;
                default:
                    num.append(ch);
                    break;
            }
        }
        return Integer.parseInt(stack.peek());
    }
}
