package com.vaani.leetcode.breadth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 * 417. Pacific Atlantic Water Flow
 * Medium
 * <p>
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * <p>
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * <p>
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * <p>
 * Note:
 * <p>
 * The order of returned grid coordinates does not matter.
 * Both m and n are less than 150.
 * <p>
 * <p>
 * Example:
 * <p>
 * Given the following 5x5 matrix:
 * <p>
 * Pacific ~   ~   ~   ~   ~
 * ~  1   2   2   3  (5) *
 * ~  3   2   3  (4) (4) *
 * ~  2   4  (5)  3   1  *
 * ~ (6) (7)  1   4   5  *
 * ~ (5)  1   1   2   4  *
 * *   *   *   *   * Atlantic
 * <p>
 * Return:
 * <p>
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {
    static final class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return ans;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        Queue<Pair> pacificQueue = new ArrayDeque<>();  //pQue: pacific Queue
        Queue<Pair> atlanticQueue = new ArrayDeque<>();  //aQue: atlantic Queue

        //Add rows to respective queue
        for (int r = 0; r < m; r++) {
            Pair pp = new Pair(r, 0);  //pp: pacific pair
            Pair ap = new Pair(r, n - 1);  //ap : atlantic pair

            pacificQueue.add(pp);
            atlanticQueue.add(ap);
        }

        // Add cols to respective queue
        for (int c = 0; c < n; c++) {
            Pair pp = new Pair(0, c);
            Pair ap = new Pair(m - 1, c);

            pacificQueue.add(pp);
            atlanticQueue.add(ap);
        }

        boolean[][] pacificPoints = new boolean[m][n];
        boolean[][] atlanticPoints = new boolean[m][n];
        Bfs(pacificQueue, matrix, pacificPoints);
        Bfs(atlanticQueue, matrix, atlanticPoints);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (pacificPoints[r][c] && atlanticPoints[r][c]) {
                    //if present in both, water can flow
                    List<Integer> common = new ArrayList<>();
                    common.add(r);
                    common.add(c);
                    ans.add(common);
                }
            }
        }

        return ans;

    }

    private void Bfs(Queue<Pair> queue, int[][] matrix, boolean[][] visited) {

        while (queue.size() > 0) {
            Pair top = queue.remove();
            int r = top.r;
            int c = top.c;

            if (visited[r][c]) {
                continue;
            }

            visited[r][c] = true;

            for (int[] direction : DIRECTIONS) {
                int nr = r + direction[0];
                int nc = c + direction[1];

                if (isValid(nr, nc, visited)) {
                    // if new height is gte curr, water can flow
                    if (matrix[nr][nc] >= matrix[r][c]) {
                        Pair np = new Pair(nr, nc);
                        queue.add(np);
                    }
                }
            }
        }
    }


    private boolean isValid(int r, int c, boolean[][] visited) {
        return r >= 0 && c >= 0 && r < visited.length && c < visited[0].length && !visited[r][c];
    }


}
