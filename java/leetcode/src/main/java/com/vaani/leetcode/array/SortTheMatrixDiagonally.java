package com.vaani.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * 1329. Sort the Matrix Diagonally
 * Medium
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
 * <p>
 * Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
 * Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * 1 <= mat[i][j] <= 100
 */
public class SortTheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diagonalKey = i - j;
                PriorityQueue<Integer> minHeap = map.getOrDefault(diagonalKey, new PriorityQueue<>());
                minHeap.offer(mat[i][j]);
                map.put(diagonalKey, minHeap);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int diagonalKey = i - j;
                PriorityQueue<Integer> minHeap = map.get(diagonalKey);

                mat[i][j] = minHeap.poll();
            }
        }

        return mat;

    }
}
