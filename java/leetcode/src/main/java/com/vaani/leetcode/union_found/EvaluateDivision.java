package com.vaani.leetcode.union_found;

import java.util.*;

/**
 * https://leetcode.com/problems/evaluate-division/
 * 399. Evaluate Division
 * Medium
 * <p>
 * You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * Example 2:
 * <p>
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 * <p>
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */
public class EvaluateDivision {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Vertex>> graph = buildGraph(equations, values);

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            res[i] = dfs(graph, new HashSet<>(), q.get(0), q.get(1));
        }

        return res;
    }

    static class Vertex {
        public String e; // variable
        public Double v; // value

        public Vertex(String e, Double v) {
            this.e = e;
            this.v = v;
        }
    }

    private void addEdge(Map<String, List<Vertex>> g, String s, String e, double v) {
        g.putIfAbsent(s, new ArrayList<>());
        g.get(s).add(new Vertex(e, v));
    }

    private Map<String, List<Vertex>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Vertex>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);
            addEdge(graph, eq.get(0), eq.get(1), values[i]);
            addEdge(graph, eq.get(1), eq.get(0), 1 / values[i]);
        }
        return graph;
    }

    private double dfs(Map<String, List<Vertex>> graph, Set<String> visited, String u, String v) {
        if (visited.contains(u)) {
            return -1;
        }
        if (!graph.containsKey(u) || !graph.containsKey(v)) {
            return -1;
        }
        if (u.equals(v)) {
            return 1;
        }
        visited.add(u);

        for (Vertex vertex : graph.get(u)) {
            if (vertex.e.equals(v)) {
                return vertex.v;
            }
            double d = dfs(graph, visited, vertex.e, v);
            if (d != -1) {
                return vertex.v * d;
            }

        }
        return -1;
    }


}
