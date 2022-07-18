package com.vaani.leetcode.graph;

import java.util.*;

public class ShortestPathVisitingAllNodes {
    static class Pair<T, U> {
        T key;
        U value;
        Pair(T x, U y){
            this.key = x;
            this.value = y;
        }

        public T getKey() {
            return key;
        }

        public U getValue() {
            return value;
        }
    }
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int goal = (1 << n) - 1;

        boolean[][] visited = new boolean[n][(1<<n)];
        Queue<Pair<Integer, Integer>> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> node = new Pair<>(i, 1 << i);
            q.offer(node);
        }

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> node = q.poll();
                int curr = node.getKey();
                int status = node.getValue();

                if (status == goal) {
                    return steps;
                }
                if (visited[curr][status]) {
                    continue;
                }
                visited[curr][status] = true;
                for (int next : graph[curr]) {
                    Pair<Integer, Integer> nextNode = new Pair<>(next, status | (1 << next));
                    q.offer(nextNode);
                }
            }
            steps++;
        }
        return steps;
    }


}
