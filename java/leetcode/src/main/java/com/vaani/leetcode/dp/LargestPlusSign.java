package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/largest-plus-sign/
 * 764. Largest Plus Sign
 * Medium

 * You are given an integer n. You have an n x n binary grid grid with all values initially 1's except for some indices given in the array mines. The ith element of the array mines is defined as mines[i] = [xi, yi] where grid[xi][yi] == 0.
 *
 * Return the order of the largest axis-aligned plus sign of 1's contained in grid. If there is none, return 0.
 *
 * An axis-aligned plus sign of 1's of order k has some center grid[r][c] == 1 along with four arms of length k - 1 going up, down, left, and right, and made of 1's. Note that there could be 0's or 1's beyond the arms of the plus sign, only the relevant area of the plus sign is checked for 1's.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 5, mines = [[4,2]]
 * Output: 2
 * Explanation: In the above grid, the largest plus sign can only be of order 2. One of them is shown.
 * Example 2:
 *
 *
 * Input: n = 1, mines = [[0,0]]
 * Output: 0
 * Explanation: There is no plus sign, so return 0.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * All the pairs (xi, yi) are unique.
 *
 * <p>Solution O(N x N) for each cell containing 1 find the nearest cell containing 0 in both
 * vertical and horizontal direction - save this value in a 2d array for each cell. The answer is
 * max value saved in 2d array.
 */
public class LargestPlusSign {

    public static void main(String[] args) throws Exception {
        int[][] M = {{4, 2}};
        System.out.println(new LargestPlusSign().orderOfLargestPlusSign(5, M));
    }

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] A = new int[n][n]; // array to save the mines information.
        int[][] B = new int[n][n]; // array to save the minimum distance to the cell containing 0
        for (int[] row : mines) {
            int r = row[0];
            int c = row[1];
            A[r][c] = 1;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 0) {
                    A[i][j] = 1;
                } else {
                    A[i][j] = 0;
                }
                B[i][j] = Integer.MAX_VALUE;
            }
        }
        // For each row
        for (int i = 0; i < A.length; i++) {
            int prev = 0;
            // forward direction
            for (int j = 0; j < A[0].length; j++) {
                if (A[i][j] == 0) {
                    prev = 0;
                    B[i][j] = 0;
                } else {
                    prev++;
                    B[i][j] = Math.min(B[i][j], prev);
                }
            }
            prev = 0;
            // backward direction
            for (int j = n - 1; j >= 0; j--) {
                if (A[i][j] == 0) {
                    prev = 0;
                    B[i][j] = 0;
                } else {
                    prev++;
                    B[i][j] = Math.min(B[i][j], prev);
                }
            }
        }

        // for each column
        for (int j = 0; j < B[0].length; j++) {
            int prev = 0;
            // downward direction
            for (int i = 0; i < B.length; i++) {
                if (A[i][j] == 0) {
                    prev = 0;
                    B[i][j] = 0;
                } else {
                    prev++;
                    B[i][j] = Math.min(B[i][j], prev);
                }
            }
            prev = 0;
            // upward direction
            for (int i = n - 1; i >= 0; i--) {
                if (A[i][j] == 0) {
                    prev = 0;
                    B[i][j] = 0;
                } else {
                    prev++;
                    B[i][j] = Math.min(B[i][j], prev);
                }
            }
        }

        int ans = 0;
        for (int[] b : B) {
            for (int j = 0; j < B[0].length; j++) {
                ans = Math.max(ans, b[j]);
            }
        }
        return ans;
    }
}
