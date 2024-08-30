package com.vaani.leetcode.graph.sp;

import java.util.*;

public class ModifyGraphEdgeWeights {
    public class Solution {
        static class Edge {
            int node;
            int wt;

            Edge(int node, int wt) {
                this.node = node;
                this.wt = wt;
            }
        }

        private static final int INF = Integer.MAX_VALUE;
        private int source;
        private Map<Integer, List<Edge>> graph;
        private int[] dist;


        public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
            this.source = source;
            this.graph = buildGraph(edges);

            this.dist = new int[n];

            runDijkstra();

            if (dist[destination] < target) {
                return new int[0][0];
            } else if (dist[destination] == target) {
                return fill(edges);
            }

            for (int[] edge : edges) {
                if (edge[2] == -1) {
                    int u = edge[0];
                    int v = edge[1];
                    int wt = 1;
                    edge[2] = wt;

                    graph.putIfAbsent(u, new ArrayList<>());
                    graph.putIfAbsent(v, new ArrayList<>());

                    graph.get(u).add(new Edge(v, wt));
                    graph.get(v).add(new Edge(u, wt));

                    // Recalculate shortest path
                    runDijkstra();

                    if (dist[destination] == target) {
                        return fill(edges); // found the target
                    } else if (dist[destination] < target) {
                        edge[2] += target - dist[destination];
                        updateGraph(u, v, edge[2]);
                        return fill(edges);
                    }

                }
            }

            return new int[0][0];
        }

        private Map<Integer, List<Edge>> buildGraph(int[][] edges) {
            Map<Integer, List<Edge>> graph = new HashMap<Integer, List<Edge>>();
            for (int[] edge : edges) {
                if (edge[2] == -1) {
                    continue;
                }
                int u = edge[0];
                int v = edge[1];
                int wt = edge[2];
                graph.putIfAbsent(u, new ArrayList<Edge>());
                graph.putIfAbsent(v, new ArrayList<Edge>());

                graph.get(u).add(new Edge(v, wt));
                graph.get(v).add(new Edge(u, wt));
            }

            return graph;
        }

        private void runDijkstra() {
            Arrays.fill(dist, INF);
            dist[source] = 0;
            PriorityQueue<Edge> pq  = new PriorityQueue<>(Comparator.comparingInt(i -> i.wt));
            pq.add(new Edge(source, 0));

            while (!pq.isEmpty()) {
                int node = pq.poll().node;

                for (Edge nei : graph.getOrDefault(node, new ArrayList<>())) {
                    int d = dist[node] + nei.wt;
                    if (d < dist[nei.node]) {
                        dist[nei.node] = d;
                        pq.add(new Edge(nei.node, d));
                    }
                }
            }
        }

        private int[][] fill(int[][] edges) {
            for (int[] edge : edges) {
                if (edge[2] == -1) {
                    edge[2] = (int) (2 * 1e9);
                }
            }
            return edges;
        }


        private void updateGraph(int u, int v, int wt) {
            graph.get(u).removeIf(e -> e.node == v);
            graph.get(v).removeIf(e -> e.node == u);
            graph.get(u).add(new Edge(v, wt));
            graph.get(v).add(new Edge(u, wt));
        }
    }
}
