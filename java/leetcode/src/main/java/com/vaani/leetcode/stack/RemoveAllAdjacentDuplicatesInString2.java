package com.vaani.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
 * 1209. Remove All Adjacent Duplicates in String II
 * Medium
 * <p>
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 * <p>
 * We repeatedly make k duplicate removals on s until we no longer can.
 * <p>
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 * Example 2:
 * <p>
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 * Example 3:
 * <p>
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * 2 <= k <= 104
 * s only contains lower case English letters.
 */
public class RemoveAllAdjacentDuplicatesInString2 {
    public String removeDuplicates(String s, int k) {
        int count = 0;
        Deque<Character> charStack = new ArrayDeque<>();
        Deque<Integer> countStack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!charStack.isEmpty() && charStack.peek() == c) {
                count = countStack.pop() + 1;
            } else {
                count = 1;
            }
            charStack.push(c);
            if (count == k) {
                while (count-- > 0) {
                    charStack.pop();
                }
            } else {
                countStack.push(count);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!charStack.isEmpty()) {
            ans.append(charStack.pop());
        }
        return ans.reverse().toString();
    }
}
