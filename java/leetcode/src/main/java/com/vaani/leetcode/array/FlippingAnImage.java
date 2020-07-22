package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/flipping-an-image/
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 * <p>
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 * <p>
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 * <p>
 * Example 1:
 * <p>
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 * Example 2:
 * <p>
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Notes:
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 */
public class FlippingAnImage {
    public int[][] flipAndInvertImage1(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = A[i][j];
                A[i][j] = A[i][n - i - j] == 1 ? 0 : 1;
                A[i][n - i - j] = temp == 1 ? 0 : 1;
            }
        }
        return A;
    }

    public int[][] flipAndInvertImage2(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        for (int[] row : A) {
            // (n+1)/2 as we wnat to process the middle element. In simple reversal, n/2 was enough
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = row[j];
                row[j] = row[n - 1 - j] == 1 ? 0 : 1;
                row[n - 1 - j] = temp == 1 ? 0 : 1;
            }
        }

       return A;
    }
}
