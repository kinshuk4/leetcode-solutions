package com.vaani.leetcode.graph;

import java.util.*;

public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    // custom class for keeping the destination from a source
    static class Dest {
        private int v;
        private boolean isArtificial;     // true if this edge was added artificially and was not in the original Tree

        public Dest(int v, boolean artificial) {
            this.v = v;
            this.isArtificial = artificial;
        }
    }

    public int minReorder(int n, int[][] connections) {
        // build graph
        Map<Integer, Set<Dest>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        // directed graph, but we save augmented bidirectional graph
        for (int[] e : connections) {
            int u = e[0], v = e[1];
            graph.get(v).add(new Dest(u, false));
            graph.get(u).add(new Dest(v, true));
        }

        int ans = 0;

        boolean[] visited = new boolean[n];
        // visit first and get adjacent
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        visited[0] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (Dest nei : graph.get(curr)) {

                if (visited[nei.v]) continue;
                if(nei.isArtificial){
                    ans++;
                }
                visited[nei.v] = true;
                q.offer(nei.v);
            }
        }
        return ans;
    }
}
