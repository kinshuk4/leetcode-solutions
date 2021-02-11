package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/path-with-minimum-effort/
 * 1631. Path With Minimum Effort
 * Medium
 * <p>
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * <p>
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * <p>
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 * <p>
 * <p>
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 */
public class PathWithMinimumEffort {
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] heightDiff = new int[m][n];
        boolean[][] visited = new boolean[m][n];

        for (int[] hd : heightDiff) {
            Arrays.fill(hd, Integer.MAX_VALUE);
        }

        heightDiff[0][0] = 0;
        int[] startNode = {0, 0, 0}; // x,y,d = diff

        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        minHeap.add(startNode);

        while (!minHeap.isEmpty()) {

            int[] curr = minHeap.poll();
            int currX = curr[0];
            int currY = curr[1];
            visited[currX][currY] = true;
            if (currX == m - 1 && currY == n - 1) {
                return curr[2];
            }

            for (int[] d : dir) {
                int nextX = curr[0] + d[0];
                int nextY = curr[1] + d[1];

                // canMove?
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
                    int currDiff = Math.abs(heights[currX][currY] - heights[nextX][nextY]);
                    int maxDiffInCurrPath = Math.max(currDiff, heightDiff[currX][currY]);

                    // Math.min
                    if (maxDiffInCurrPath < heightDiff[nextX][nextY]) {
                        heightDiff[nextX][nextY] = maxDiffInCurrPath;
                        minHeap.add(new int[]{nextX, nextY, maxDiffInCurrPath});
                    }
                }
            }
        }
        return -1;
    }

}
