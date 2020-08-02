package com.vaani.leetcode.breadth_first_search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/01-matrix/
 * 542. 01 Matrix
 * Medium
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * <p>
 * The distance between two adjacent cells is 1.
 * Example 1:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * Example 2:
 * Input:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right
 */
// Start from value is 0, BFS
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return matrix;
        }
        int n = matrix[0].length;

        int[][] result = new int[m][n];
        Queue<int[]> queue = new LinkedList<>(); // cells which has already been optimized
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int r = pair[0];
            int c = pair[1];
            for (int[] dir : dirs) {
                int newR = pair[0] + dir[0];
                int newC = pair[1] + dir[1];
                if (newR >= 0 && newR < m && newC >= 0 && newC < n) {
                    if (result[newR][newC] > result[r][c] + 1) { // check if neighbor is optimized or not
                        result[newR][newC] = result[r][c] + 1;
                        queue.add(new int[]{newR, newC});
                    }
                }
            }
        }
        return result;
    }
}

/*
We manage a queue of cell which has already been optimized
At the first glance, it will be a queue of all cells whose value is 0.

Then we loop until queue empty.
For each optimized cell, we check its neighbors to see if they are optimized or not
If they has not been optimized yet, we optimized & add it into the queue.

We should do BFS on '0' instead of '1' because if we do BFS on 1, we will need multiple BFS
*/
