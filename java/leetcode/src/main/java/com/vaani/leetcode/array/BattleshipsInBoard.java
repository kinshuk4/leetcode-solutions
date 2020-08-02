package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/battleships-in-a-board/
 * 419. Battleships in a Board
 * Medium
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
 * <p>
 * You receive a valid board, made of only battleships or empty slots.
 * Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * /
 */
/*
 *
Example:

X..X
...X
...X

In the above board there are 2 battleships.

Invalid Example:

...X
XXXX
...X

This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?

 */
public class BattleshipsInBoard {

    public static void main(String[] args) throws Exception {
        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        System.out.println(new O1MemorySolution1().countBattleships(board));
    }


    static class DFSWithShipSink {
        public int countBattleships(char[][] board) {
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'X') {
                        count++;
                        sink(board, i, j);
                    }
                }
            }
            return count;
        }

        private void sink(char[][] board, int i, int j) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != 'X') {
                return;
            }

            board[i][j] = '.';

            sink(board, i + 1, j);
            sink(board, i - 1, j);
            sink(board, i, j + 1);
            sink(board, i, j - 1);
        }
    }

    static class O1MemorySolution1 {

        //Solution: The below solution works in one pass using only O(1) memory. Iterate through each
        //  cell and add one to count if and only if the current cell equals 'X' and its adjacent upper and
        //  left cell does not contain 'X'
        public int countBattleships(char[][] board) {
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == 'X') {
                        if (i > 0) { // check for the boundary condition
                            if (board[i - 1][j] == 'X') { // adjacent upper
                                continue;
                            }
                        }
                        if (j > 0) {
                            if (board[i][j - 1] == 'X') { //adjacent left
                                continue;
                            }
                        }
                        count++;
                    }
                }
            }
            return count;
        }
    }

    static class O1MemorySolution2 {
        public int countBattleships(char[][] board) {
            int count = 0;
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == '.') {
                        continue;
                    }

                    if (board[i][j] == 'X') {
                        if (i > 0) { // check for the boundary condition
                            if (board[i - 1][j] == 'X') { // adjacent upper
                                continue;
                            }
                        }
                        if (j > 0) {
                            if (board[i][j - 1] == 'X') { //adjacent left
                                continue;
                            }
                        }
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
