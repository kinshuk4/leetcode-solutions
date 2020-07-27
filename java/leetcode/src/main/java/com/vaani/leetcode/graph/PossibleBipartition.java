package com.vaani.leetcode.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/possible-bipartition/
 * 886. Possible Bipartition
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * <p>
 * Each person may dislike some other people, and they should not go into the same group.
 * <p>
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * <p>
 * Return true if and only if it is possible to split everyone into two groups in this way.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 * Example 2:
 * <p>
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 * Example 3:
 * <p>
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * Output: false
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class PossibleBipartition {
    static class CheckBipartite1 {
        public boolean possibleBipartition(int N, int[][] dislikes) {
            // using adjacency list representation but nodes being index in Array, so list of array
            List<Integer>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; ++i) {
                graph[i] = new ArrayList<>();
            }

            // Find all dislike person for each person
            // 1 dislike a,b,c
            // 2 dislike d,e,f
            for (int[] edge : dislikes) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }

            Map<Integer, Integer> colorMap = new HashMap<>();
            for (int node = 1; node <= N; node++) {
                if (!colorMap.containsKey(node) && !dfs(node, 0, colorMap, graph)) {
                    return false;
                }
            }
            return true;
        }

        public boolean dfs(int node, int c, Map<Integer, Integer> color, List<Integer>[] graph) {
            if (color.containsKey(node)) {
                return color.get(node) == c;
            }
            color.put(node, c);

            for (int nei : graph[node]) {
                if (!dfs(nei, c ^ 1, color, graph)) {
                    return false;
                }
            }
            return true;
        }
    }

    // submitted - https://www.youtube.com/watch?v=0ACfAqs8mm0
    static class CheckBipartite2 {
        public boolean possibleBipartition(int N, int[][] dislikes) {

            List<Integer>[] graph = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++)
                graph[i] = new ArrayList<>();

            for (int[] dislike : dislikes) {
                int u = dislike[0];
                int v = dislike[1];
                graph[u].add(v);
                graph[v].add(u);
            }

            // color = 0 => no color
            int[] colors = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                if (colors[i] == 0) {
                    if (!isBipartite(graph, colors, i)) {
                        return false;
                    }
                }
            }

            return true;
        }

        // bfs - colors -0, 1, -1
        private boolean isBipartite(List<Integer>[] graph, int[] colors, int currNode) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(currNode);
            colors[currNode] = 1; // first color = 1, second color = -1;

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int i : graph[node]) {
                    if (colors[i] == colors[node]) {
                        return false;
                    }
                    // not yet colored
                    if (colors[i] == 0) {
                        colors[i] = -1 * colors[node];
                        queue.offer(i);
                    }
                }
            }
            return true;
        }

    }

}
