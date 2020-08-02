package com.vaani.leetcode.design;

public class DesignTicTacToe {

    public static class TicTacToe {
        private final int[][] board;
        private final int N;
        public TicTacToe(int n) {
            board = new int[n][n];
            N = n;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    board[i][j] = -1;
                }
            }
        }

        public int move(int row, int col, int player) {
            board[row][col] = player;
            return isWin(player);
        }

        public int isWin(int p) {
            // check each row
            for (int i = 0; i < N; i++) {
                int count = 0;
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != p)
                        break;
                    count++;
                }
                if (count == N) {
                    return p;
                }
            }
            // check each column
            for (int i = 0; i < N; i++) {
                int count = 0;
                for (int j = 0; j < N; j++) {
                    if (board[j][i] != p)
                        break;
                    count++;
                }
                if (count == N) {
                    return p;
                }
            }
            // check dignose
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (board[i][i] != p)
                    break;
                count++;
            }
            if (count == N) {
                return p;
            }
            // check dignose
            count = 0;
            for (int i = 0; i < N; i++) {
                if (board[i][N - 1 - i] != p)
                    break;
                count++;
            }
            if (count == N) {
                return p;
            }
            return 0;
        }
    }
}
