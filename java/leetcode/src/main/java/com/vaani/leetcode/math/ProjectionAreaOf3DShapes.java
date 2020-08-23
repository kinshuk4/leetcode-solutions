package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/projection-area-of-3d-shapes/
 * 883. Projection Area of 3D Shapes
 * Easy
 * <p>
 * On a N * N grid, we place some 1 * 1 * 1 cubes that are axis-aligned with the x, y, and z axes.
 * <p>
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 * <p>
 * Now we view the projection of these cubes onto the xy, yz, and zx planes.
 * <p>
 * A projection is like a shadow, that maps our 3 dimensional figure to a 2 dimensional plane.
 * <p>
 * Here, we are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 * <p>
 * Return the total area of all three projections.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [[2]]
 * Output: 5
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,2],[3,4]]
 * Output: 17
 * Explanation:
 * Here are the three projections ("shadows") of the shape made with each axis-aligned plane.
 * <p>
 * Example 3:
 * <p>
 * Input: [[1,0],[0,2]]
 * Output: 8
 * <p>
 * Example 4:
 * <p>
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 14
 * <p>
 * Example 5:
 * <p>
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 21
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= grid.length = grid[0].length <= 50
 * 0 <= grid[i][j] <= 50
 */
/*
Explanation - Grid[i][j] = k => k is height of the object or item. i, j are x,y coordinates.
so, i,j,k  are x,y,z in 3D.
 */
public class ProjectionAreaOf3DShapes {
    public static void main(String[] args) {
        //
    }

    /*
     Solution O(N x N) project the view on all three different planes. For top view its pretty
      simple because area of each cube is just 1 * 1, for all other planes take the maximum value of
     each grid. Sum up values on each planes
     */
    public int projectionArea(int[][] grid) {
        int area = 0;
        // Top view
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                area += (grid[i][j] > 0 ? 1 : 0);
            }
        }

        // x view - view from x axis
        for (int i = 0; i < grid.length; i++) {
            int max = 0;
            for (int j = 0; j < grid[0].length; j++) {
                max = Math.max(max, grid[i][j]);
            }
            area += max;
        }

        // y view - view from y axis
        for (int j = 0; j < grid[0].length; j++) {
            int max = 0;
            for (int i = 0; i < grid.length; i++) {
                max = Math.max(max, grid[i][j]);
            }
            area += max;
        }

        return area;
    }
}
