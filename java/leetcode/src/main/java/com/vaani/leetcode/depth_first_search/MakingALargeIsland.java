package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/making-a-large-island/
 * 827. Making A Large Island
 * Hard
 * <p>
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * <p>
 * Return the size of the largest island in grid after applying this operation.
 * <p>
 * An island is a 4-directionally connected group of 1s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 * <p>
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 * <p>
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == m
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 */
public class MakingALargeIsland {
    private final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // left..

    public int largestIsland(int[][] grid) {
        List<Set<Integer>> islands = new ArrayList<>(); // all island sets
        Map<Integer, Integer> islandToIndex = new HashMap<>(); // island cell to Index in islands list
        int ans = 0; // result

        int m = grid.length;
        int n = grid[0].length;

        //fill list of islands
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (islandToIndex.containsKey(getCellHash(i, j))) {
                    continue;
                }
                if (grid[i][j] == 1) {
                    Set<Integer> islandCellSet = new HashSet<>();
                    fillIsland(grid, islandToIndex, islandCellSet, i, j, islands.size());
                    islands.add(islandCellSet);
                    ans = Math.max(ans, islandCellSet.size());
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int max = 1;

                    Set<Integer> visited = new HashSet<>();

                    for (int[] dir : DIRECTIONS) {
                        int addressHash = getCellHash(i + dir[0], j + dir[1]);
                        if (islandToIndex.containsKey(addressHash)) {
                            int islandIndex = islandToIndex.get(addressHash);
                            if (!visited.contains(islandIndex)) {
                                max += islands.get(islandIndex).size();
                                visited.add(islandIndex);
                            }
                        }
                    }

                    ans = Math.max(ans, max);
                }
            }
        }

        return ans;
    }

    private void fillIsland(int[][] grid, Map<Integer, Integer> islandToIndex, Set<Integer> island, int i, int j, int islandIndex) {
        // already checked
        if (islandToIndex.containsKey(getCellHash(i, j))) {
            return;
        }
        // out of grid
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] == 1) {
            island.add(getCellHash(i, j));
            islandToIndex.put(getCellHash(i, j), islandIndex);

            for (int[] dir : DIRECTIONS) {
                fillIsland(grid, islandToIndex, island, i + dir[0], j + dir[1], islandIndex);
            }
        }
    }

    private int getCellHash(int i, int j) {
        return i * 1000 + j;
    }


}
