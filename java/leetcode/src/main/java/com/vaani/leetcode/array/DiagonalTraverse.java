package com.vaani.leetcode.array;

/**
 * 498. Diagonal Traverse
 * Medium
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
 * <p>
 * Example:
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output:  [1,2,4,7,5,3,6,8,9]
 * Explanation:
 * <p>
 * Note:
 * The total number of elements of the given matrix will not exceed 10,000.
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 111}, {4, 5, 6, 222}, {7, 8, 9, 333}};
        int[] res = new DiagonalTraverse.UsingBoolean().findDiagonalOrder(matrix);
        for (int re : res) {
            System.out.print(re + ",");
        }
    }

    static class UsingIndexSum {
        // https://leetcode.com/problems/diagonal-traverse/discuss/97711/Java-15-lines-without-using-boolean
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) {
                return new int[0];
            }

            int m = matrix.length;
            int n = matrix[0].length;
            int[] result = new int[m * n];

            int r = 0;
            int c = 0;

            for (int i = 0; i < result.length; i++) {
                result[i] = matrix[r][c];
                if ((r + c) % 2 == 0) { // moving up
                    if (c == n - 1) { // columns are done, move to new row
                        r++;
                    } else if (r == 0) { // new row
                        c++;
                    } else { // simply move up and right
                        r--;
                        c++;
                    }
                } else {                // moving down
                    if (r == m - 1) { // move to new column as all the rows are done
                        c++;
                    } else if (c == 0) { // new column
                        r++;
                    } else { // move down and left
                        r++;
                        c--;
                    }
                }
            }
            return result;
        }
    }

    static class UsingDirection {
        // https://leetcode.com/problems/diagonal-traverse/discuss/97712/Concise-Java-Solution
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return new int[0];
            }

            int m = matrix.length, n = matrix[0].length;

            int[] result = new int[m * n];
            int row = 0, col = 0, d = 0;
            int[][] dirs = {{-1, 1}, {1, -1}};

            for (int i = 0; i < m * n; i++) {
                result[i] = matrix[row][col];
                row += dirs[d][0];
                col += dirs[d][1];

                if (row >= m) {
                    row = m - 1;
                    col += 2;
                    d = 1 - d;
                }
                if (col >= n) {
                    col = n - 1;
                    row += 2;
                    d = 1 - d;
                }
                if (row < 0) {
                    row = 0;
                    d = 1 - d;
                }
                if (col < 0) {
                    col = 0;
                    d = 1 - d;
                }
            }

            return result;
        }
    }

    static class UsingBoolean {
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) {
                return new int[0];
            }
            int[] res = new int[matrix.length * matrix[0].length];
            int xstart = 0, xend = 0, ystart = 0, yend = 0;
            int i = 0;
            boolean flag = false;
            while (i < res.length) {
                int num = 0;
                if (flag) {
                    // from top to bottom
                    int x = xstart;
                    int y = yend;
                    int count = 0;
                    while (y >= ystart && x <= xend) {
                        // System.out.println("haha"+res[i] + ',' + i);
                        res[i] = matrix[x][y];
                        // System.out.println("oo"+res[i] + ',' + i);
                        i++;
                        x++;
                        y--;
                        count++;
                    }
                    num = count;
                } else {
                    // from bottom to top
                    int x = xend;
                    int y = ystart;
                    //System.out.println("xend is "+ xend+"ystart is "+ystart+"xstart is "+xstart+"yend is "+ yend);
                    int count = 0;
                    while (x >= xstart && y <= yend) {
                        res[i] = matrix[x][y];
                        // System.out.print(res[i] + ":" + i);
                        i++;
                        x--;
                        y++;
                        count++;
                    }
                    num = count;
                }
                if (num == Math.min(matrix.length, matrix[0].length) && yend != matrix[0].length) {
                    ystart = ystart + 1 < matrix[0].length ? ystart + 1 : ystart;
                    yend = yend + 1 < matrix[0].length ? yend + 1 : yend;
                } else if (i > res.length / 2) {
                    xstart = xstart + 1 < matrix.length ? xstart + 1 : xstart;
                    ystart = ystart + 1 < matrix[0].length ? ystart + 1 : ystart;
                } else if (i <= res.length / 2) {
                    xend = xend + 1 < matrix.length ? xend + 1 : xend;
                    yend = yend + 1 < matrix[0].length ? yend + 1 : yend;
                }
                flag = !flag;
            }
            return res;
        }
    }


}
