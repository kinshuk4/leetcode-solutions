package com.vaani.leetcode.stack;

import java.util.Set;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BasicCalculator3 {
    public static void main(String[] args) {
        BasicCalculator3.OneOperationAtTime obj = new OneOperationAtTime();
        System.out.println(obj.calculate("2*(5+5*2)/3+(6/2+8)"));
    }
    static class OneOperationAtTime {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            Stack<Character> opStack = new Stack<>();
            Stack<Integer> numStack = new Stack<>();

            final Set<Character> operators = Set.of('+', '-', '*', '/');
            Predicate<Character> isOperator = (i) -> {
                return operators.contains(i);
            };
            // check if op2 has higher precendence than op1
            BiFunction<Character, Character, Boolean> hasPrecedence = (op1, op2) -> {
                if (op2 == ')' || op2 == '(') {
                    return false;
                }

                if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
                    return false;
                }
                return true;
            };

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == ' ') continue;
                else if (Character.isDigit(s.charAt(i))) {
                    int num = ch - '0';
                    while(i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                        num = num * 10 + s.charAt(i+1) - '0';
                        i++;
                    }
                    numStack.push(num);
                } else if (isOperator.test(ch)) {
                    while (!opStack.isEmpty() && hasPrecedence.apply(ch, opStack.peek())) {
                        numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                    }
                    opStack.push(ch); // all the low prio ops removed, so now add
                } else if (ch == '(') {
                    opStack.push(ch);
                } else if (ch == ')') {
                    while (!opStack.isEmpty() && opStack.peek() != '(') {
                        numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
                    }
                    opStack.pop(); //remove that '('
                }
            }

            while (!opStack.isEmpty()) {
                numStack.push(calculate(opStack.pop(), numStack.pop(), numStack.pop()));
            }
            return numStack.isEmpty() ? 0 : numStack.pop();
        }

        public int calculate(char operator, int num1, int num2) {
            int res = 0;

            switch (operator) {
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num2 - num1;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num2 / num1;
                    break;
            }
            return res;
        }

    }
}

