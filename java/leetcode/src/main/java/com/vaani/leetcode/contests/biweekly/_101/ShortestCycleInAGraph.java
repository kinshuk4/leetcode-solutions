package com.vaani.leetcode.contests.biweekly._101;

import java.util.*;

public class ShortestCycleInAGraph {
    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) {
            g.put(i, new HashSet<>());
        }

        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {

            int[] dist = new int[n];
            Arrays.fill(dist, (int) 1e9);

            int[] par = new int[n];
            Arrays.fill(par, -1);

            dist[i] = 0;
            Queue<Integer> q = new LinkedList<>();

            q.add(i);

            while (!q.isEmpty()) {
                int x = q.poll();

                for (int child : g.get(x)) {
                    // If it is not visited yet
                    if (dist[child] == (int) (1e9)) {

                        // Increase distance by 1
                        dist[child] = 1 + dist[x];

                        // Change parent
                        par[child] = x;

                        // Push into the queue
                        q.add(child);
                    } else if (par[x] != child && par[child] != x)
                        ans = Math.min(ans, dist[x] + dist[child] + 1);
                }
            }
        }

        if (ans == Integer.MAX_VALUE)
            return -1;
        else
            return ans;

    }
}
