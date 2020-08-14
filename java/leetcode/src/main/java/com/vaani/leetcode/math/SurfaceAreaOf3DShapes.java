package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/surface-area-of-3d-shapes/
 * 892. Surface Area of 3D Shapes
 * Easy
 * <p>
 * On a N * N grid, we place some 1 * 1 * 1 cubes.
 *
 * <p>Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 *
 * <p>Return the total surface area of the resulting shapes.
 * <p>
 * Example 1:
 * <p>
 * Input: [[2]]
 * Output: 10
 *
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,2],[3,4]]
 * Output: 34
 * <p>
 * Example 3:
 * <p>
 * Input: [[1,0],[0,2]]
 * Output: 16
 * <p>
 * Example 4:
 * <p>
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 32
 * <p>
 * Example 5:
 * <p>
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 46
 */
/*
Problem explanation:
Surface area of 1 cube is equal to 6 according to description (1 * 1 * 1) * 6, cube have 6 sides.
 grid[i][j] is a count of cubes placed on top of grid cell. For instance, if we have a grid[i][j] = 2, it means we placed two cubes one on other and they have a common area is equal to 2, because we have two connected sides, hence 12 - 2 = 10.

 If the grid[i][j] = 3, then common area is equal to 4, and goes on
 */
public class SurfaceAreaOf3DShapes {
    public static void main(String[] args) {
        int[][] A = {{2}};
        System.out.println(new SurfaceAreaOf3DShapes.UsingDFS().surfaceArea(A));
    }

    static class UsingDFS {
        private final int[] R = {0, 0, -1, 1};
        private final int[] C = {1, -1, 0, 0};


        /**
         * Solution: O(N x M) For each cell, check each adjacent cell and sum the value of (current cell
         * adjacent cell) if the current cell value is greater than adjacent cell. For every cell which
         * has value grater then 0, the top surface area is by default 1 therefore add one to the sum of
         * each cell.
         */
        public int surfaceArea(int[][] grid) {
            int sum = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int cell = grid[i][j];
                    for (int k = 0; k < 4; k++) {
                        int newR = i + R[k];
                        int newC = j + C[k];
                        if (newR >= 0 && newC >= 0 && newR < grid.length && newC < grid[0].length) {
                            int adjacent = grid[newR][newC];
                            if (cell > adjacent) {
                                sum += (cell - adjacent);
                            }
                        } else if (newR < 0 || newR >= grid.length || newC < 0 || newC >= grid[0].length) {
                            sum += cell;
                        }
                    }
                    if (cell > 0) {
                        sum += 2;
                    }
                }
            }
            return sum;
        }
    }

    /**
     * grid[i][j] is a count of cubes placed on top of grid cell. For instance, if we have a grid[i][j] = 2, it means we placed two cubes one on other and they have a common area is equal to 2, because we have two connected sides. If the grid[i][j] = 3, then common area is equal to 4.
     * It turns out when grid[i][j] is equal to:
     * 2 cubes - 2
     * 3 cubes - 4
     * 4 cubes - 6
     * 5 cubes - 8 => 2 * (grid[i][j] - 1)
     * We also have to substract common area on y and x axis. We only compute up to n - 1 for both axis by calculating minimum of two connected Vs and multiply by 2.
     * Red squares is a common areas which we have to subtract from resulting total surface area.
     * The following depicted picture shows example: [[2, 3, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]. The answer is: 5 * 6 - 2 - 4 = 20.
     */
    static class UsingMathsCommonArea {
        public int surfaceArea(int[][] grid) {
            int n = grid.length;
            int res = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        continue;
                    }
                    res += grid[i][j] * 6 - 2 * (grid[i][j] - 1);
                    if (i < n - 1) res -= 2 * Math.min(grid[i][j], grid[i + 1][j]);
                    if (j < n - 1) res -= 2 * Math.min(grid[i][j], grid[i][j + 1]);
                }
            }
            return res;
        }
    }


}
