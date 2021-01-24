package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/game-of-life/
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 * <p>
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 * <p>
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * <p>
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * Output:
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * <p>
 * Follow up:
 * <p>
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 */
public class GameOfLife {
    private static final int die = 2;
    private static final int live = 3;

    // just use two vars and we can get it done by flipping the value.
    public void gameOfLife(int[][] board) {
        // we only flip the 1 to die and 0 to live
        // so when we find a die around, it must be a previous 1
        // then we can count the 1s without being affected
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int livesAround = countLive(i, j, board);
                // if cell is dead
                if (board[i][j] == 0 && livesAround == 3) {
                    board[i][j] = live;
                } else if (board[i][j] == 1) { // if cell is alive
                    if (livesAround == 2 || livesAround == 3) {
                        continue;
                    }
                    board[i][j] = die;
                }
            }
        }

        // fix the board again
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == die)
                    board[i][j] = 0;
                if (board[i][j] == live)
                    board[i][j] = 1;
            }
        }

    }

    private int countLive(int i, int j, int[][] board) {
        int count = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x >= 0 && y >= 0 && x < board.length && y < board[0].length) {

                if (board[x][y] == 1 || board[x][y] == die) {
                    count++;
                }
            }
        }

        return count;

    }
}
