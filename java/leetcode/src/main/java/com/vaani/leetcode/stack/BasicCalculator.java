package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 224. Basic Calculator
 Hard


 Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.

 Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



 Example 1:

 Input: s = "1 + 1"
 Output: 2
 Example 2:

 Input: s = " 2-1 + 2 "
 Output: 3
 Example 3:

 Input: s = "(1+(4+5+2)-3)+(6+8)"
 Output: 23


 Constraints:

 1 <= s.length <= 3 * 10^5
 s consists of digits, '+', '-', '(', ')', and ' '.
 s represents a valid expression.
 '+' is not used as a unary operation.
 '-' could be used as a unary operation but it has to be inside parentheses.
 There will be no two consecutive operators in the input.
 Every number and running calculation will fit in a signed 32-bit integer.

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
                case ' ', '(', '+', '-' -> {
                    if (!num.toString().equals("")) {
                        stack.push(num.toString());
                        num = new StringBuilder();
                    }
                    if (ch != ' ') { // ignore blank
                        stack.push(String.valueOf(ch));
                    }
                }
                case ')' -> {
                    if (!num.toString().equals("")) {
                        stack.push(num.toString());
                        num = new StringBuilder();
                    }
                    int sum = 0;
                    int prev = 0; // maintain a prev value inorder to handle minus '-'
                    label:
                    while (!stack.isEmpty()) {
                        String top = stack.pop();
                        switch (top) {
                            case "-":
                                sum -= (prev * 2);
                                prev = 0;
                                break;
                            case "+":
                                // ignore
                                break;
                            case "(":
                                stack.push(String.valueOf(sum));
                                break label;
                            default:
                                sum += Integer.parseInt(top);
                                prev = Integer.parseInt(top);
                                break;
                        }
                    }
                }
                default -> num.append(ch);
            }
        }
        return Integer.parseInt(stack.peek());
    }
}
