package com.vaani.leetcode.array;

import org.junit.Assert;

import java.util.Stack;

/**
 * https://leetcode.com/problems/toeplitz-matrix/
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * <p>
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * matrix = [
 * [1,2,3,4],
 * [5,1,2,3],
 * [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 * Example 2:
 * <p>
 * Input:
 * matrix = [
 * [1,2],
 * [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 */
public class ToeplitzMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        };

        ToeplitzMatrix underTest = new ToeplitzMatrix();

        Assert.assertTrue(underTest.isToeplitzMatrix(matrix));

        matrix = new int[][]{
                {1, 2},
                {2, 2}
        };
        Assert.assertFalse(underTest.isToeplitzMatrix(matrix));

        matrix = new int[][]{
                {11, 74, 0, 93},
                {40, 11, 74, 7}
        };
        Assert.assertFalse(underTest.isToeplitzMatrix(matrix));
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = m - 1; i >= 0; i--) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0, x = i; x < m && j < n; j++, x++) {
                int currElement = matrix[x][j];
                if (stack.isEmpty()) {
                    stack.push(currElement);
                } else {
                    int pop = stack.pop();

                    if (pop != currElement) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
