package com.vaani.leetcode.string;
/**
 * https://leetcode.com/problems/flip-game-ii/
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * Write a function to determine if the starting player can guarantee a win.
 * For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
 * Follow up:
 * Derive your algorithm's runtime complexity.
 */
public class FlipGame2 {

    static class Approach1 {
        public boolean canWin(String s) {
            if (s == null || s.length() == 0) {
                return false;
            }

            return canWinHelper(s.toCharArray());
        }

        public boolean canWinHelper(char[] arr) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] == '+' && arr[i + 1] == '+') {
                    arr[i] = '-';
                    arr[i + 1] = '-';

                    boolean win = canWinHelper(arr);

                    arr[i] = '+';
                    arr[i + 1] = '+';

                    //if there is a flip which makes the other player lose, the first play wins
                    if (!win) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    static class Approach2 {
        public boolean canWin(String s) {
            if (s == null || s.length() == 0) {
                return false;
            }

            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                    String nextState = s.substring(0, i) + "--" + s.substring(i + 2);

                    boolean win = canWin(nextState);

                    // if opponent cannot win this game, than we have won
                    if (!win) {
                        return true;
                    }
                }
            }

            return false;
        }

    }

}
