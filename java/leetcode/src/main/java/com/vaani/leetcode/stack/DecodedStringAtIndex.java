package com.vaani.leetcode.stack;

import lombok.ToString;

import java.util.Stack;

/**
 * 880. Decoded String at Index
 * Medium
 * An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:
 * <p>
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
 * Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: S = "leet2code3", K = 10
 * Output: "o"
 * Explanation:
 * The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 * Example 2:
 * <p>
 * Input: S = "ha22", K = 5
 * Output: "h"
 * Explanation:
 * The decoded string is "hahahaha".  The 5th letter is "h".
 * Example 3:
 * <p>
 * Input: S = "a2345678999999999999999", K = 1
 * Output: "a"
 * Explanation:
 * The decoded string is "a" repeated 8301530446056247680 times.  The 1st letter is "a".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= S.length <= 100
 * S will only contain lowercase letters and digits 2 through 9.
 * S starts with a letter.
 * 1 <= K <= 10^9
 * It's guaranteed that K is less than or equal to the length of the decoded string.
 * The decoded string is guaranteed to have less than 2^63 letters.
 */
public class DecodedStringAtIndex {

    public static void main(String[] args) {
//        System.out.println(new DecodedStringAtIndex().decodeAtIndex("ha22", 5));
        System.out.println(new DecodedStringAtIndex().decodeAtIndex("ixm5xmgo78", 711));
    }

    @ToString
    static class Node {
        String S;
        long count;
        int multiple;

        Node(String S, long count, int multiple) {
            this.S = S;
            this.count = count;
            this.multiple = multiple;
        }
    }

    public String decodeAtIndex(String S, int K) {
        if (K == 1){
            return String.valueOf(S.charAt(0));
        }
        Stack<Node> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char prev = ' ';
        for (char c : S.toCharArray()) {
            if (Character.isDigit(c)) {
                String currStr = sb.toString();
                long len = 0L;
                if (!stack.isEmpty()) {
                    len = stack.peek().count * stack.peek().multiple;
                }
                stack.push(
                        new Node(currStr, len + (currStr.length()), Integer.parseInt(String.valueOf(c))));
                if (((len + (currStr.length())) * Integer.parseInt(String.valueOf(c))) >= K) {
                    break;
                }
                prev = c;
            } else {
                if (Character.isDigit(prev)) {
                    sb = new StringBuilder();
                }
                sb.append(c);
                prev = c;
            }
        }
        while (!stack.isEmpty()) {
            Node top = stack.peek();
            long l = top.count;
            if (K < l) {
                return String.valueOf(top.S.charAt((int) l - K - 1));
            }
            if (K == l){
                return String.valueOf(top.S.charAt(0));
            }
            long mod = (K % l);
            if (mod == 0) {
                return String.valueOf(top.S.charAt(top.S.length() - 1));
            }
            if (l - top.S.length() < mod) {
                long i = l - mod;
                return String.valueOf(top.S.charAt(top.S.length() - (int) i - 1));
            } else {
                stack.pop();
            }
        }
        return String.valueOf(S.charAt(K-1));
    }
}
