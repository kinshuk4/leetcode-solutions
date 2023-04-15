package com.vaani.leetcode.contests.biweekly._102;

import java.util.*;

public class DesignGraphWithShortestPathCalculator {
    static class Graph {
        Map<Integer, Map<Integer, Integer>> adjList;
        private final int N;
        public Graph(int n, int[][] edges) {
            this.N = n;
            adjList = new HashMap<>();
            this.buildGraph(edges);
        }

        private void buildGraph(int[][] edges) {
            for (int[] tuple : edges) {
                addEdge(tuple);
            }
        }

        public void addEdge(int[] edge) {
            int u = edge[0], v = edge[1], w = edge[2];
            adjList.putIfAbsent(u, new HashMap<>());
            adjList.get(u).put(v, w);
            adjList.putIfAbsent(v, new HashMap<>());
        }

        public int shortestPath(int node1, int node2) {
            // distTo[i] is shortest distance from K to i
            int[] distTo = new int[N + 1];
            // Initialize distTo
            Arrays.fill(distTo, Integer.MAX_VALUE);
            distTo[node2] = 0;

            // Construct min heap
            PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> distTo[a] - distTo[b]);
            for (int i = 1; i <= N; i++) {
                minHeap.offer(i);
            }

            // Calculate shortest distance from min distTo
            while (!minHeap.isEmpty()) {
                int u = minHeap.poll();

                for (Map.Entry<Integer, Integer> edge : adjList.get(u).entrySet()) {
                    relax(edge, u, distTo, minHeap);
                }
            }

            int maxDist = 0;
            for (int i = 1; i <= N; i++) {
                if (distTo[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                maxDist = Math.max(maxDist, distTo[i]);
            }

            return maxDist;

        }

        private void relax(Map.Entry<Integer, Integer> edge, int u, int[] distTo, PriorityQueue<Integer> minHeap) {
            int v = edge.getKey(), w = edge.getValue();
            if (distTo[u] + w < distTo[v]) {
                distTo[v] = distTo[u] + w;
                if (!minHeap.contains(v)) {
                    minHeap.offer(v);
                }
            }
        }

    }
}
