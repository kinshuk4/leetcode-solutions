package com.vaani.leetcode.graph;

import java.util.*;

/**
 * 785. Is Graph Bipartite?
 * Medium
 * <p>
 * Given an undirected graph, return true if and only if it is bipartite.
 * <p>
 * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such that every edge in the graph has one node in A and another node in B.
 * <p>
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists. Each node is an integer between 0 and graph.length - 1. There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= graph.length <= 100
 * 0 <= graph[i].length < 100
 * 0 <= graph[i][j] <= graph.length - 1
 * graph[i][j] != i
 * All the values of graph[i] are unique.
 * The graph is guaranteed to be undirected.
 */
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        Queue<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];


        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }

            list.add(i);
            color[i] = 1;
            visited[i] = true;

            while (!list.isEmpty()) {
                int u = list.poll();
                int uColor = color[u];

                for (int v : graph[u]) {
                    int vColor = color[v];
                    if (vColor != 0 && vColor == uColor) {
                        return false;
                    }

                    if (uColor == 1) {
                        vColor = 2;
                    } else {
                        vColor = 1;
                    }

                    if (!visited[v]) {
                        list.add(v);
                    }

                    color[v] = vColor;
                    visited[v] = true;

                }
            }
        }
        return true;

    }
}
