package com.vaani.leetcode.design;

/**
 * 304. Range Sum Query 2D - Immutable
 * Medium
 * <p>
 * 1634
 * <p>
 * 211
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 * <p>
 * Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Implement the NumMatrix class:
 * <p>
 * NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 * int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
 * Output
 * [null, 8, 11, 12]
 * <p>
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
 * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
 * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * -10^5 <= matrix[i][j] <= 10^5
 * 0 <= row1 <= row2 < m
 * 0 <= col1 <= col2 < n
 * At most 104 calls will be made to sumRegion.
 */
public class RangeSumQuery2DImmutable {
    static class SimpleSolution {
        static class NumMatrix {
            int[][] columnSum;

            public NumMatrix(int[][] matrix) {
                if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                    return;
                }

                int m = matrix.length;
                int n = matrix[0].length;

                columnSum = new int[m][n];

                for (int i = 0; i < n; i++) {
                    columnSum[0][i] = matrix[0][i];
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 1; j < m; j++) {
                        columnSum[j][i] = columnSum[j - 1][i] + matrix[j][i];
                    }
                }
            }

            public int sumRegion(int row1, int col1, int row2, int col2) {
                int ans = 0;

                if (row1 == 0) {
                    for (; col1 <= col2; col1++) {
                        ans += columnSum[row2][col1];
                    }
                } else {
                    for (; col1 <= col2; col1++) {
                        ans += columnSum[row2][col1] - columnSum[row1 - 1][col1];
                    }
                }
                return ans;
            }
        }
    }
}
