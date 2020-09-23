package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/make-the-string-great/
 * 1544. Make The String Great
 * Easy
 * <p>
 * Given a string s of lower and upper case English letters.
 * <p>
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 * <p>
 * 0 <= i <= s.length - 2
 * s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
 * <p>
 * To make the string good, you can choose two adjacent characters that make the string bad and remove them. You can keep doing this until the string becomes good.
 * <p>
 * Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
 * <p>
 * Notice that an empty string is also good.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leEeetcode"
 * Output: "leetcode"
 * Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be reduced to "leetcode".
 * <p>
 * Example 2:
 * <p>
 * Input: s = "abBAcC"
 * Output: ""
 * Explanation: We have many possible scenarios, and all lead to the same answer. For example:
 * "abBAcC" --> "aAcC" --> "cC" --> ""
 * "abBAcC" --> "abBA" --> "aA" --> ""
 * <p>
 * Example 3:
 * <p>
 * Input: s = "s"
 * Output: "s"
 */
public class MakeTheStringGood {
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty() && Math.abs(stack.peek() - s.charAt(i)) == 32) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        int n = stack.size();
        char[] ans = new char[n];
        int index = n - 1;
        while (!stack.isEmpty()) {
            ans[index--] = stack.pop();
        }
        return new String(ans);
    }
}
