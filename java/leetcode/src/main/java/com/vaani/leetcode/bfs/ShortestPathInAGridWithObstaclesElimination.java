package com.vaani.leetcode.bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * Hard
 * <p>
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.
 * <p>
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input:
 * grid =
 * [[0,0,0],
 * [1,1,0],
 * [0,0,0],
 * [0,1,1],
 * [0,0,0]],
 * k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 * Example 2:
 * <p>
 * Input:
 * grid =
 * [[0,1,1],
 * [1,1,1],
 * [1,0,0]],
 * k = 1
 * Output: -1
 * Explanation:
 * We need to eliminate at least two obstacles to find such a walk.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 40
 * 1 <= k <= m * n
 * grid[i][j] == 0 or 1
 * grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class ShortestPathInAGridWithObstaclesElimination {
    public static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][0] = true;
        q.offer(new int[]{0, 0, 0});
        int res = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] info = q.poll();
                int r = info[0], c = info[1], curK = info[2];
                if (r == m - 1 && c == n - 1) {
                    return res;
                }
                for (int[] dir : DIRS) {
                    int nextR = dir[0] + r;
                    int nextC = dir[1] + c;
                    int nextK = curK;
                    if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n) {
                        if (grid[nextR][nextC] == 1) {
                            nextK++;
                        }
                        if (nextK <= k && !visited[nextR][nextC][nextK]) {
                            visited[nextR][nextC][nextK] = true;
                            q.offer(new int[]{nextR, nextC, nextK});
                        }
                    }
                }
            }
            res++;
        }
        return -1;

    }
}
