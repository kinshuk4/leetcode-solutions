package com.vaani.leetcode.depth_first_search;

import org.junit.Assert;

import java.util.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * <p>
 * Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 * <p>
 */
/*

Example 1:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph looks like this:
0 -> 1
 \  / (arrow pointing downwards
   2

Example 2:
Input:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]] (same as above example)
src = 0, dst = 2, k = 0 (k is different)
Output: 500
 */

/**
 * Constraints:
 * <p>
 * The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * The size of flights will be in range [0, n * (n - 1) / 2].
 * The format of each flight will be (src, dst, price).
 * The price of each flight will be in the range [1, 10000].
 * k is in the range of [0, n - 1].
 * There will not be any duplicated flights or self cycles.
 */


public class CheapestFlightsWithinKStops {

    public static void main(String[] args) {
        int[][] flights = new int[][]{
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };
        UsingDFS.findCheapestPrice(3, flights, 0, 2, 1);
        System.out.println(UsingDFS.min);
        UsingDFS.findCheapestPrice(3, flights, 0, 2, 0);
        System.out.println(UsingDFS.min);

        UsingDFSWithPruning underTest = new UsingDFSWithPruning();
        Assert.assertEquals(200, underTest.findCheapestPrice(3, flights, 0, 2, 1));
        Assert.assertEquals(500, underTest.findCheapestPrice(3, flights, 0, 2, 0));
    }

    // TLE DFS
    static class UsingDFS {
        public static int min;

        public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            min = Integer.MAX_VALUE;
            int[] sum = new int[1];
            boolean[] visited = new boolean[n];
            visited[src] = true;
            helper(flights, src, dst, -1, K, sum, visited);
            return min == Integer.MAX_VALUE ? -1 : min;
        }

        private static void helper(int[][] flights, int cur, int dst, int stop, int K, int[] sum, boolean[] visited) {
            if (cur == dst) {
                min = Math.min(min, sum[0]);
                return;
            }

            if (stop == K) {
                if (cur == dst) {
                    min = Math.min(min, sum[0]);
                }
                return;
            }

            for (int i = 0; i < flights.length; i++) {
                if (flights[i][0] == cur) {
                    sum[0] += flights[i][2];
                    if (!visited[flights[i][1]]) {
                        visited[flights[i][1]] = true;
                        helper(flights, flights[i][1], dst, stop + 1, K, sum, visited);
                        visited[flights[i][1]] = false;
                    }
                    sum[0] -= flights[i][2];
                }
            }
        }
    }

    // https://www.youtube.com/watch?v=60RbWlDFsmI&t=341s
    static class UsingDFSWithPruning {
        // adjacency matrix approach
        public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
            int[][] graph = new int[n][];
            for (int[] flight : flights) {
                int v1 = flight[0];
                int v2 = flight[1];
                int cost = flight[2];
                graph[v1][v2] = cost;


            }
            return -1;
        }

        static class Edge {
            int vertex;
            int cost;

            public Edge(int vertex, int cost) {
                this.vertex = vertex;
                this.cost = cost;
            }
        }

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            Map<Integer, List<Edge>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new LinkedList<>());
            }
            // build graph
            for (int[] flight : flights) {
                int v1 = flight[0];
                int v2 = flight[1];
                int cost = flight[2];
                // use below for fully connected graph and remove above fori loop, but if graph is not connected, this will not work
//                graph.computeIfAbsent(v1, k -> new LinkedList<>()).add(new Edge(v2, cost));
                List<Edge> adj = graph.get(v1);
                adj.add(new Edge(v2, cost));
                graph.put(v1, adj);
            }

            boolean[] visited = new boolean[n];

            // I am passing fare as array object as Java is pass by value
            int[] fare = new int[1];
            fare[0] = Integer.MAX_VALUE;
            solve(graph, src, dst, K, fare, 0, visited);

            return fare[0] == Integer.MAX_VALUE ? -1 : fare[0];
        }

        private void solve(Map<Integer, List<Edge>> graph, int src, int dst, int K, int[] fare, int currentCost, boolean[] visited) {
            if (K < -1) { // note checking for -1 - as we are counting destination node extra. which in the problem shouldn't be counted.
                return;
            }
            if (src == dst) {
                fare[0] = Math.min(fare[0], currentCost);
                return;
            }

            visited[src] = true;

            for (Edge e : graph.get(src)) {
                if (!visited[e.vertex] && currentCost + e.cost <= fare[0]) {
                    solve(graph, e.vertex, dst, K - 1, fare, currentCost + e.cost, visited);
                }
            }

            visited[src] = false;
        }


    }

    static class UsingDjikstra{
        static class Pair {
            int city, cost;

            Pair(int city, int cost) {
                this.city = city;
                this.cost = cost;
            }
        }

        static class City {
            int city, distFromSrc, costFromSrc;

            City(int city, int distFromSrc, int cost) {
                this.city = city;
                this.distFromSrc = distFromSrc;
                this.costFromSrc = cost;
            }

        }

        public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
            // BFS
            if (n <= 0 || flights == null || flights.length == 0 || K < 0)
                return -1;

            List<List<Pair>> graph = new ArrayList<>();
            buildGraph(graph, n, flights);

            Queue<City> pQueue = new PriorityQueue<>(Comparator.comparingInt((City c) -> c.costFromSrc));
            pQueue.offer(new City(src, 0, 0));

            while (!pQueue.isEmpty()) {
                City top = pQueue.poll();

                if (top.city == dst) {
                    return top.costFromSrc;
                }

                if (top.distFromSrc > K) {
                    continue;
                }

                for (Pair neighbor : graph.get(top.city)) {
                    pQueue.offer(new City(neighbor.city, top.distFromSrc + 1, top.costFromSrc + neighbor.cost));
                }
            }

            return -1;
        }

        private static void buildGraph(List<List<Pair>> graph, int n, int[][] flights) {
            for (int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
            }

            for (int[] flight : flights) {
                graph.get(flight[0]).add(new Pair(flight[1], flight[2]));
            }
        }
    }


}
