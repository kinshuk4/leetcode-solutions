package com.vaani.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to compute all possible states of the string after one valid move.
 * For example, given s = "++++", after one move, it may become one of the following states:
 * [
 * "--++",
 * "+--+",
 * "++--"
 * ]
 * If there is no valid move, return an empty list [].
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>(); // possible states
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() - 1; i++) {
            if (chars[i] == chars[i + 1] && chars[i] == '+') {
                chars[i] = chars[i + 1] = '-';
                result.add(String.valueOf(chars));
                chars[i] = chars[i + 1] = '+';
            }
        }
        return result;
    }

    public List<String> generatePossibleNextMoves2(String s) {
        List<String> result = new ArrayList<>(); // possible states

        int i = 0;
        while (i < s.length()) {
            int nextMove = i == 0 ? s.indexOf("++") : s.indexOf("++", i);

            if (nextMove == -1) {
                return result;
            }

            String nextState = s.substring(0, nextMove) + "--" + s.substring(nextMove + 2);
            result.add(nextState);
            i = nextMove + 1;
        }

        return result;
    }
}
