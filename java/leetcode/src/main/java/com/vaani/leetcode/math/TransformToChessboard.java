package com.vaani.leetcode.math;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/transform-to-chessboard/
 * <p>
 * 782. Transform to Chessboard
 * Hard
 * <p>
 * You are given an n x n binary grid board. In each move, you can swap any two rows with each other, or any two columns with each other.
 * <p>
 * Return the minimum number of moves to transform the board into a chessboard board. If the task is impossible, return -1.
 * <p>
 * A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * Output: 2
 * Explanation: One potential sequence of moves is shown.
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 * Example 2:
 * <p>
 * <p>
 * Input: board = [[0,1],[1,0]]
 * Output: 0
 * Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
 * Example 3:
 * <p>
 * <p>
 * Input: board = [[1,0],[1,0]]
 * Output: -1
 * Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j] is either 0 or 1.
 */
public class TransformToChessboard {
    public int movesToChessboard(int[][] board) {
        int n = board.length, ans = 0;
        var vecs = new long[2][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                vecs[0][i] = vecs[0][i] * 2 + board[i][j];
                vecs[1][j] = vecs[1][j] * 2 + board[i][j];
            }
        }
        for (var vec : vecs) {
            var tiles = new Long[]{-1L, -1L};
            long cntMax = 0;
            for (long v : vec) {
                if (tiles[1] == -1 || tiles[1] == v) {
                    ++cntMax;
                    tiles[1] = v;
                } else {
                    if (!(tiles[0] == -1 || tiles[0] == v)) return -1;
                    tiles[0] = v;
                }
            }
            if (cntMax < n - cntMax) {
                Collections.swap(Arrays.asList(tiles), 0, 1);
                cntMax = n - cntMax;
            }
            if (cntMax != n - n / 2) {
                return -1;
            }
            int mism = 0;
            for (int a = 1, i = 0; i < n; ++i, a ^= 1) {
                mism += tiles[a] == vec[i] ? 0 : 1;
            }
            ans += (n % 2 == 1 ? mism : Math.min((n - mism), mism)) / 2;
        }
        return ans;
    }


}
