package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 * 1192. Critical Connections in a Network
 * Hard
 * <p>
 * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
 * <p>
 * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
 * <p>
 * Return all critical connections in the network in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
 * Output: [[1,3]]
 * Explanation: [[3,1]] is also accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 10^5
 * n-1 <= connections.length <= 10^5
 * connections[i][0] != connections[i][1]
 * There are no repeated connections.
 */
public class CriticalConnectionInANetwork {
    public static void main(String[] args) {
        CriticalConnectionInANetwork task = new CriticalConnectionInANetwork();
        List<Integer> c = new ArrayList<>();
        c.add(0);
        c.add(1);

        List<Integer> c1 = new ArrayList<>();
        c1.add(1);
        c1.add(2);

        List<Integer> c2 = new ArrayList<>();
        c2.add(2);
        c2.add(0);

        List<Integer> c3 = new ArrayList<>();
        c3.add(1);
        c3.add(3);

        List<List<Integer>> connections = new ArrayList<>();
        connections.add(c);
        connections.add(c1);
        connections.add(c2);
        connections.add(c3);
        List<List<Integer>> result = task.criticalConnections(4, connections);
        System.out.println();
    }

    private int[] dLow;
    private int[] dNum;
    private int num = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        dLow = new int[n];
        dNum = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Arrays.fill(dLow, -1);
        Arrays.fill(dNum, -1);
        for (List<Integer> connection : connections) {
            graph.putIfAbsent(connection.get(0), new ArrayList<>());
            graph.putIfAbsent(connection.get(1), new ArrayList<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.get(connection.get(1)).add(connection.get(0));
        }
        dfs(-1, 0, graph);
        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> connection : connections) {
            if (dLow[connection.get(1)] > dNum[connection.get(0)]
                    || dLow[connection.get(0)] > dNum[connection.get(1)]) {
                ans.add(connection);
            }
        }
        return ans;
    }

    private int dfs(int u, int v, Map<Integer, List<Integer>> graph) {
        int n = num++;
        dNum[v] = n;
        dLow[v] = n;
        List<Integer> children = graph.get(v);
        if (children != null) {
            for (int c : children) {
                if (c != u) {
                    if (dNum[c] == -1) {
                        dLow[v] = Math.min(dLow[v], dfs(v, c, graph));
                    } else {
                        dLow[v] = Math.min(dLow[c], dLow[v]);
                    }
                }
            }
        }
        return dLow[v];
    }

    static class UsingTarjan2 {
        int time = 0;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            LinkedList<Integer>[] graph = new LinkedList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new LinkedList<>();
            }

            for (List<Integer> oneConnection : connections) {
                graph[oneConnection.get(0)].add(oneConnection.get(1));
                graph[oneConnection.get(1)].add(oneConnection.get(0));
            }
            List<List<Integer>> ans = new ArrayList<>();
            boolean[] visited = new boolean[n];
            int[] timeStampAtThatNode = new int[n];
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(graph, i, -1, visited, ans, timeStampAtThatNode);
                }
            }

            return ans;
        }


        public void dfs(List<Integer>[] graph, int u, int parent, boolean[] visited, List<List<Integer>> ans, int[] lowTime) {
            visited[u] = true;
            lowTime[u] = time++;
            int discoveryTime = lowTime[u];

            for (int v : graph[u]) {
                if (v == parent) continue;
                if (!visited[v]) {
                    dfs(graph, v, u, visited, ans, lowTime);
                }
                lowTime[u] = Math.min(lowTime[u], lowTime[v]);
                if (discoveryTime < lowTime[v]) ans.add(Arrays.asList(u, v));
            }
        }

    }
}
