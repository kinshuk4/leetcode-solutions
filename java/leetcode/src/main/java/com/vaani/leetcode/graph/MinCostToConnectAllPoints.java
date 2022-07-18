package com.vaani.leetcode.graph;

import java.util.List;
import java.util.*;

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/
 */
public class MinCostToConnectAllPoints {
    static class UsingEdgeTry1 {
        static class Edge {
            int end;
            int weight;

            public Edge(int end, int weight) {
                this.end = end;
                this.weight = weight;
            }
        }

        public int minCostConnectPoints(int[][] points) {
            // we are creating graph with indices being the vertex.
            // Actually vertex are point, but storing index in graph is easier
            Map<Integer, Set<Edge>> graph = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                graph.put(i, new HashSet<>());
            }
            for (int i = 0; i < points.length; i++){
                for (int j = 1; j < points.length; j++) {
                    int dist = manhattanDist(points, i, j);
                    graph.get(i).add(new Edge(j, dist));
                    graph.get(j).add(new Edge(i, dist));
                }
            }

            // # PRims
            PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
            boolean[] visited = new boolean[graph.size()];

            int source = 0;
            visited[source] = true;
            for(Edge e: graph.get(source)){
                minHeap.add(e);
            }

            int ans = 0;
            while(!minHeap.isEmpty()){
                Edge e = minHeap.poll();
                if(visited[e.end]){
                    continue;
                }
                visited[e.end] = true;
                for(Edge nei: graph.get(e.end)){
                    // helps improving in TLE, but not in solving
                    if(!visited[nei.end]){
                        minHeap.add(nei);
                    }

                }
                ans += e.weight;
            }

            for (boolean b : visited) {
                if (!b) {
                    return -1;
                }
            }
            return ans;
        }

        private int manhattanDist(int[][] points, int a, int b) {
            return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
        }
    }

    // TLE again
    static class UsingArrayAsEdge {
        private int manhattanDist(int[][] points, int a, int b) {
            return Math.abs(points[a][0] - points[b][0]) + Math.abs(points[a][1] - points[b][1]);
        }
        public int minCostConnectPoints(int[][] points) {
            // we are creating graph with indices being the vertex.
            // Actually vertex are point, but storing index in graph is easier
            Map<Integer, Set<int[]>> graph = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                graph.put(i, new HashSet<>());
            }
            for (int i = 0; i < points.length; i++){
                for (int j = 1; j < points.length; j++) {

                    int dist = manhattanDist(points, i, j);
                    graph.get(i).add(new int[] {j, dist});
                    graph.get(j).add(new int[] {i, dist});
                }
            }

            // # PRims
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
            boolean[] visited = new boolean[graph.size()];

            int source = 0;
            visited[source] = true;
            for(int[] nei: graph.get(source)){
                minHeap.add(nei);
            }

            int ans = 0;
            while(!minHeap.isEmpty()){
                int[] e = minHeap.poll();
                int node = e[0];
                int w = e[1];
                if(visited[node]){
                    continue;
                }
                visited[node] = true;
                for(int[] nei: graph.get(node)){
                    // helps improving in TLE, but not in solving
                    if(!visited[nei[0]]){
                        minHeap.add(nei);
                    }

                }
                ans += w;
            }

            for (boolean b : visited) {
                if (!b) {
                    return -1;
                }
            }
            return ans;
        }
    }

    // Pick the min edge during building graph
    static class UsingEdgeTry2 {
        static class Edge {
            int end;
            int weight;

            public Edge(int end, int weight) {
                this.end = end;
                this.weight = weight;
            }
        }

        public int minCostConnectPoints(int[][] points) {
            // we are creating graph with indices being the vertex.
            // Actually vertex are point, but storing index in graph is easier
            Map<Integer, Set<Edge>> graph = new HashMap<>();
            for (int i = 0; i < points.length; i++) {
                graph.put(i, new HashSet<>());
            }
            PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

            boolean[] visited = new boolean[graph.size()];
            int index = 0;
            visited[index] = true;


            int ans = 0;
            for (int count = 1; count < points.length; count++){
                for (int j = 1; j < points.length; j++) {
                    if(!visited[j]){
                        int dist = Math.abs(points[index][0] - points[j][0]) + Math.abs(points[index][1] - points[j][1]);
                        minHeap.add(new Edge(j, dist));
                    }
                }
                while(visited[minHeap.peek().end]){
                    //this point has already visited, poll it
                    minHeap.poll();
                }
                Edge polled = minHeap.poll();
                ans += polled.weight;
                visited[polled.end] = true;
                index = polled.end;
            }
            return ans;
        }

    }
}
