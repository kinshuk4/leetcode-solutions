package com.vaani.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/flower-planting-with-no-adjacent/
 * <p>
 * 1042. Flower Planting With No Adjacent
 * Easy
 * <p>
 * You have N gardens, labelled 1 to N.  In each garden, you want to plant one of 4 types of flowers.
 * <p>
 * paths[i] = [x, y] describes the existence of a bidirectional path from garden x to garden y.
 * <p>
 * Also, there is no garden that has more than 3 paths coming into or leaving it.
 * <p>
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.
 * <p>
 * Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)-th garden.  The flower types are denoted 1, 2, 3, or 4.  It is guaranteed an answer exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: N = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * <p>
 * Example 2:
 * <p>
 * Input: N = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 * <p>
 * Example 3:
 * <p>
 * Input: N = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 10000
 * 0 <= paths.size <= 20000
 * No garden has 4 or more paths coming into or leaving it.
 * It is guaranteed an answer exists.
 */
public class FlowerPlantingWithNoAdjacent {
    public int[] gardenNoAdj(int N, int[][] paths) {
        // Build graph
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        // Note 1 based graph, not 0 based index
        for (int i = 1; i <= N; i++) {
            adj.put(i, new HashSet<>());
        }
        for (int[] path : paths) {
            int x = path[0];
            int y = path[1];
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        int[] ans = new int[N]; // Note answer is 0 based
        int numColors = 4;
        //Just mark all the colors that have been used by the neighbours
        //and then paint color which is not used by the neigbours.
        //there will always be atleast one unused color.
        for (int u = 1; u <= N; u++) {
            boolean[] colorUsed = new boolean[numColors + 1]; //Use 5 instead of 4 so we can easily use 1-based indexing
            for (int v : adj.get(u)) {
                colorUsed[ans[v - 1]] = true;//Mark the color as used if neighbor has used it before.
            }

            for (int c = 1; c <= numColors; c++) {
                if (!colorUsed[c]) {
                    ans[u - 1] = c;
                    break;//not necessary
                }
            }
        }

        return ans;
    }
}
