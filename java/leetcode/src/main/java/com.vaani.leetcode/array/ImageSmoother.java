package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/image-smoother/
 * Given a 2D integer matrix M representing the gray
 * scale of an image, you need to com.vaani.leetcode.design a smoother to make the gray scale of each cell becomes the
 * average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less
 * than 8 surrounding cells, then use as many as you can.
 *
 * <p>Example 1: Input: [[1,1,1], [1,0,1], [1,1,1]] Output: [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
 * Explanation: For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0 For the point
 * (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0 For the point (1,1): floor(8/9) =
 * floor(0.88888889) = 0 Note: The value in the given matrix is in the range of [0, 255]. The length
 * and width of the given matrix are in the range of [1, 150].
 */
public class ImageSmoother {


    public static void main(String[] args) throws Exception {
        int[][] M = {
                {2, 3, 4},
                {5, 6, 7},
                {8, 9, 10},
                {11, 12, 13},
                {14, 15, 16}
        };

        ImageSmoother underTest = new ImageSmoother();

        System.out.println(underTest.imageSmoother(M));
    }

    public int[][] imageSmoother(int[][] M) {
        int m = M.length;
        int n = M[0].length;

        int[][] result = new int[m][n];
        // directions we can go in
        int[] dx = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                int totalCells = 0;
                for (int k = 0; k < dx.length; k++) {
                    int cellI = i + dx[k];
                    int cellJ = j + dy[k];
                    if (cellI >= 0 && cellJ >= 0 && cellI < m && cellJ < n) {
                        sum += M[cellI][cellJ];
                        totalCells++;
                    }
                }

                result[i][j] = sum / totalCells;
            }
        }
        return result;
    }
}
