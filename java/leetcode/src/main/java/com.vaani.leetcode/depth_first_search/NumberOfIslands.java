package com.vaani.leetcode.depth_first_search;

/**
 * https://leetcode.com/problems/number-of-islands/
 * Given a 2d grid map of '1's (land) and '0's
 * (water), count the number of islands. An island is surrounded by water and is formed by
 * connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid
 * are all surrounded by water.
 *
 * <p>Example 1:
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * Output: 1
 *
 * <p>Example 2:
 * <p>
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * Output: 3
 */
public class NumberOfIslands {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        NumberOfIslands underTest = new NumberOfIslands();
        System.out.println(underTest.numIslands3(grid));
    }

    public int numIslands1(char[][] grid) {
        int M = grid.length;
        if (M == 0) return 0;
        int N = grid[0].length;

        char[][] visited = new char[M][N];
        int count = 0;

        for (int i = 0; i < M; i++) {
            System.arraycopy(grid[i], 0, visited[i], 0, N);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == '1') {
                    ++count;
                    dfs1(i, j, visited, M, N);
                }
            }
        }

        return count;
    }

    private void dfs1(int r, int c, char[][] visited, int M, int N) {
        visited[r][c] = '0';
        for (int i = 0; i < 4; i++) {
            int newR = r + dx[i];
            int newC = c + dy[i];
            if (newR >= 0 && newC >= 0 && newR < M && newC < N) {
                if (visited[newR][newC] != '0') {// not visited
                    dfs1(newR, newC, visited, M, N);
                }
            }
        }
    }

    // second way can be using normal boolean array for visited
    public int numIslands2(char[][] grid) {
        return -1;
    }

    // Without extra visited array - increment the number by 1 if visited
    // 2 means island point is visited, 1 means initial value
    @SuppressWarnings("Duplicates")
    public int numIslands3(char[][] grid) {
        int M = grid.length;
        if (M == 0) return 0;
        int N = grid[0].length;

        int count = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '1') { // if it is part of island
                    ++count;
                    dfs3MarkIsland(i, j, grid, M, N);
                }
            }
        }

        return count;
    }

    @SuppressWarnings("Duplicates")
    private void dfs3MarkIsland(int r, int c, char[][] grid, int M, int N) {
        // validate boundary condition and
        if (r >= 0 && c >= 0 && r < M && c < N && grid[r][c] == '1') {
            grid[r][c] = '2';

            for (int i = 0; i < 4; i++) {
                int newR = r + dx[i];
                int newC = c + dy[i];
                dfs3MarkIsland(newR, newC, grid, M, N);
            }
        }


    }

}
