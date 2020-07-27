package com.vaani.leetcode.dp;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 * [0,1,1,1],
 * [1,1,1,1],
 * [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 * [1,0,1],
 * [1,1,0],
 * [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */

/**
 * dp[i][j] represents the length of the square which lower right corner is located at (i, j).
 * If the value of this cell is also 1, then the length of the square is the minimum of: the one above, its left, and diagonal up-left value +1. Because if one side is short or missing, it will not form a square.
 */
/**
 * if matrix[i][j]==0 : do nothing
 * if matrix[i][j]==1 : count that cell itself as it forms a '1' cell matrix, and also count previous matrix depending on whether it forms a square or not and store the same value in the matrix[i][j] to calculate further submatrices
 */
public class CountSquareSubmatricesWithAllOnes {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = m > 0 ? matrix[0].length : 0;
        if (m == 0 || n == 0){
            return 0;}
        int result = 0;

        int[][] dp = new int[m + 1][n + 1]; // first row and col are all 0s, as we cant have sq matrix from them
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
                }
                result += dp[i][j]; // if 0, just add 0 otherwise add the current count for i, j
            }
        }
        return result;
    }
}
