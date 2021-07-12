package com.vaani.leetcode.graph;

/**
 * https://leetcode.com/problems/redundant-connection/
 * 684. Redundant Connection
 * Medium
 * <p>
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * <p>
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 * <p>
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {
    private int[] array;

    public int[] findRedundantConnection(int[][] edges) {
        array = new int[edges.length + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (int[] e : edges) {
            if (find(e[0]) == find(e[1])) {
                return e;
            } else {
                union(e[0], e[1]);
            }
        }
        return edges[0];
    }


    private int find(int x) {
        if (x != array[x]) {
            array[x] = find(array[x]);
        }
        return array[x];
    }

    private void union(int x, int y) {
        array[find(y)] = find(x);
    }

}
