package com.vaani.leetcode.string;

import java.util.Stack;

/**
 * 316. Remove Duplicate Letters
 * Medium
 * <p>
 * Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * <p>
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "bcabc"
 * Output: "abc"
 * Example 2:
 * <p>
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] counts = new int[26];
        s.chars().forEach(c -> counts[c - 'a']++);

        //to keep track of visited character so that we don't use them if present in answer
        boolean[] added = new boolean[26];

        //Stack store resulting characters
        Stack<Character> stack = new Stack<>();
        //traverse through string and add characters
        for(int i = 0 ; i < s.length(); i++){
            char c = s.charAt(i);
            counts[c - 'a']--;

            // if already added to stack skip
            if(added[c - 'a']){
                continue;
            }

            // traverse through the stack and check for larger characters
            while(!stack.isEmpty() && c < stack.peek() && counts[stack.peek() - 'a'] > 0){
                //make the current character available for next operations
                added[stack.pop() - 'a'] = false;
            }
            stack.push(c);

            added[c - 'a'] = true;
        }

        //Now characters are in reverse order in stack
        //Use StringBuilder instead of String for storing result
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }
}
