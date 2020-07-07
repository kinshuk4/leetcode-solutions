package com.vaani.leetcode.array;

import org.junit.Assert;

/**
 * https://leetcode.com/problems/reshape-the-matrix/
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size but keep its original data.
 * <p>
 * You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
 * <p>
 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
 * <p>
 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output the original matrix.
 * <p>
 * Example 1:
 * Input:
 * nums =
 * [[1,2],
 * [3,4]]
 * r = 1, c = 4
 * Output:
 * [[1,2,3,4]]
 * Explanation:
 * The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
 * Example 2:
 * Input:
 * nums =
 * [[1,2],
 * [3,4]]
 * r = 2, c = 4
 * Output:
 * [[1,2],
 * [3,4]]
 * Explanation:
 * There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
 */
public class ReshapeTheMatrix {
    public static void main(String[] args) {
        int[][] nums = {
                {1, 2},
                {3, 4},
                {5, 6}
        };

        int[][] expected = {
                {1, 2, 3, 4, 5, 6}
        };

        ReshapeTheMatrix underTest = new ReshapeTheMatrix();
        Assert.assertArrayEquals(expected, underTest.matrixReshape(nums, 1, 6));
    }

    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int expectedDim = r * c;

        int m = nums.length;
        int n = nums[0].length;
        int currentDim = m * n;

        if (currentDim != expectedDim) {
            return nums;
        }

        int[][] result = new int[r][c];

        int rows = 0, cols = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[rows][cols] = nums[i][j];
                cols++;
                if (cols == c) {
                    rows++;
                    cols = 0;
                }

                // we can also do:
                //                res[count / c][count % c] = nums[i][j];
                //                count++;
            }
        }
//
//        for (int i = 0; i < currentDim; i++) {
//            int a = i / c;
//            int b = i % c;
//            int x = i / n;
//            int y = i % n;
//
//            result[a][b] = nums[x][y];
//        }

        return result;
    }
}
