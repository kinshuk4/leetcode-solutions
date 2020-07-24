package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 * Given a directed, acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.
 */
/*
Example:
Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */


public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        System.out.println(new UsingDFSMemo().allPathsSourceTarget(graph));
    }


    static class UsingDFSMemo {
        /*
         * <p>Solution: Do a dfs to reach every path. Since its a DAG there can be no cycles and safe to
         * proceed without checking if the node has already been visited. Maintain a stack to keep track of
         * the path and when a leaf node has been reached add the elements in the stack to the result array
         */
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            Set<Integer> visited = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            List<List<Integer>> result = new ArrayList<>();
            dfs(graph, result, visited, 0, stack);
            return result;
        }

        private void dfs(int[][] graph, List<List<Integer>> result, Set<Integer> visited, int i, Stack<Integer> stack) {
            visited.add(i);
            stack.push(i);
            int[] children = graph[i];
            if (children.length == 0) {
                List<Integer> childList = new ArrayList<>(stack);
                result.add(childList);
            } else {
                for (int c : children) {
                    dfs(graph, result, visited, c, stack);
                }
            }
            stack.pop();
            visited.remove(i);
        }
    }

    static class UsingBFS {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> result = new ArrayList<>();
            Queue<List<Integer>> queue = new ArrayDeque<>();
            queue.add(Collections.singletonList(0)); // list with 1st node

            while (!queue.isEmpty()) {
                List<Integer> e = queue.remove();
                if (e.get(e.size() - 1) == graph.length - 1) {
                    result.add(e);
                    continue;
                }

                for (int n : graph[e.get(e.size() - 1)]) {
                    List<Integer> next = new ArrayList<>(e);
                    next.add(n);
                    queue.add(next);
                }
            }

            return result;
        }
    }

    static class UsingDFS {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();

            path.add(0);
            dfsSearch(graph, 0, result, path);

            return result;
        }

        private void dfsSearch(int[][] graph, int node, List<List<Integer>> result, List<Integer> currPath) {
            if (node == graph.length - 1) {
                result.add(new ArrayList<>(currPath));
                return;
            }

            for (int nextNode : graph[node]) {
                currPath.add(nextNode);
                dfsSearch(graph, nextNode, result, currPath);
                currPath.remove(currPath.size() - 1);
            }
        }
    }

    static class UsingDFSMemo2 {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            Map<Integer, List<List<Integer>>> map = new HashMap<>();

            return dfsSearch(graph, 0, map);
        }

        private List<List<Integer>> dfsSearch(int[][] graph, int node, Map<Integer, List<List<Integer>>> map) {
            if (map.containsKey(node)) {
                return map.get(node);
            }

            List<List<Integer>> result = new ArrayList<>();
            if (node == graph.length - 1) {
                List<Integer> path = new LinkedList<>();
                path.add(node);
                result.add(path);
            } else {
                for (int nextNode : graph[node]) {
                    List<List<Integer>> subPaths = dfsSearch(graph, nextNode, map);
                    for (List<Integer> path : subPaths) {
                        LinkedList<Integer> newPath = new LinkedList<>(path);
                        newPath.addFirst(node);
                        result.add(newPath);
                    }
                }
            }

            map.put(node, result);
            return result;
        }
    }

}
