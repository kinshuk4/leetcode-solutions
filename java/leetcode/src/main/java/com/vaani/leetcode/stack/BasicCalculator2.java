package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: "3+2*2"
 * Output: 7
 * <p>
 * Example 2:
 * <p>
 * Input: " 3/2 "
 * Output: 1
 * <p>
 * Example 3:
 * <p>
 * Input: " 3+5 / 2 "
 * Output: 5
 * <p>
 * Note:
 * <p>
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator2 {

    public static void main(String[] args) throws Exception {
        System.out.println(
                new BasicCalculator2().calculate("2-1 + (2 - 3) - ((2 - (2 - (3 - (4 - 5)))))"));
    }

    /**
     * * Solution: O(n) where n is the length of the string. Maintain a stack and push each character
     * * from the string (ignore space). As soon as a close parentheses ')' is encountered, start to pop
     * * values and sum-up the total until '(' is poped. Push the total back to stack and continue to
     * * iterate. The final result will be in the top of the stack which is the last and only element in stack.
     */
    // using stack
    public int calculate(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) {
            return 0;
        }
        Stack<Integer> numberStack = new Stack<>();
        int num = 0;
        char lastOperator = '+';
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
            }
            if ((!Character.isDigit(ch) && ' ' != ch) || i == len - 1) {
                switch (lastOperator) {
                    case '+':
                        numberStack.push(numberStack.pop() + num);
                        break;
                    case '-':
                        numberStack.push(-num);
                        break;
                    case '*':
                        numberStack.push(numberStack.pop() * num);
                        break;
                    case '/':
                        numberStack.push(numberStack.pop() / num);
                        break;
                }

                lastOperator = ch;
                num = 0;
            }
        }

        int result = 0;
        for (int i : numberStack) {
            result += i;
        }
        return result;
    }

    //30+3-7*2*2
    public int calculate2(String s) {
        int result = 0;
        int tempResult = 0;
        int currentNum = 0;
        char currentOp = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentNum = currentNum * 10 + c - '0';
            }
            if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
                switch (currentOp) {
                    case '+':
                        result += tempResult;
                        tempResult = currentNum;
                        break;
                    case '-':
                        result += tempResult;
                        tempResult = -currentNum;
                        break;
                    case '*':
                        tempResult *= currentNum;
                        break;
                    case '/':
                        tempResult /= currentNum;
                        break;
                }
                currentOp = c;
                currentNum = 0;
            }
        }
        result += tempResult;
        return result;
    }
}
