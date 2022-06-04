package com.vaani.leetcode.heap;

import java.util.*;

/**
 * https://leetcode.com/problems/reachable-nodes-in-subdivided-graph/
 * 882. Reachable Nodes In Subdivided Graph
 * Hard
 * <p>
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1. You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 * <p>
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph, and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 * <p>
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti, and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti+1, xcnti], [xcnti, vi].
 * <p>
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 * <p>
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * Output: 13
 * Explanation: The edge subdivisions are shown in the image above.
 * The nodes that are reachable are highlighted in yellow.
 * Example 2:
 * <p>
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
 * Output: 23
 * Example 3:
 * <p>
 * Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
 * Output: 1
 * Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= edges.length <= min(n * (n - 1) / 2, 10^4)
 * edges[i].length == 3
 * 0 <= u_i < v_i < n
 * There are no multiple edges in the graph.
 * 0 <= cnt_i <= 10^4
 * 0 <= maxMoves <= 10^9
 * 1 <= n <= 3000
 */
public class ReachableNodesInSubdividedGraph {
    public static void main(String[] args) {
        int[][] edges = {{0, 1, 1000}, {0, 2, 1}, {1, 2, 1}};
        System.out.println(new ReachableNodesInSubdividedGraph().reachableNodes(edges, 200, 3));
    }

    /**
     * <p>Solution: O(E log N) E is the length of edges and N is the number of nodes. The n nodes on a
     * vertex form a weight and thus the graph becomes a weighted graph. Keep track of number of moves
     * available and run a Dijkstra's algorithm.
     */
    static class Node {
        int n, w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] e : edges) {
            graph.putIfAbsent(e[0], new ArrayList<>());
            graph.get(e[0]).add(new Node(e[1], e[2] + 1));

            graph.putIfAbsent(e[1], new ArrayList<>());
            graph.get(e[1]).add(new Node(e[0], e[2] + 1));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.w));
        Map<Integer, Integer> distance = new HashMap<>();
        int count = 0;
        pq.offer(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (!distance.containsKey(curr.n)) {
                count += 1;
                distance.put(curr.n, curr.w);
                List<Node> children = graph.get(curr.n);
                if (children != null) {
                    for (Node c : children) {
                        if (!distance.containsKey(c.n)) {
                            int availableMoves = maxMoves - curr.w;
                            int nodesInBetween = c.w - 1;
                            if (nodesInBetween >= availableMoves) {
                                count += availableMoves;
                            } else {
                                count += nodesInBetween;
                                if (availableMoves >= c.w) {
                                    Node child = new Node(c.n, distance.get(curr.n) + c.w);
                                    pq.offer(child);
                                }
                            }
                        } else {
                            int childAvailableMoves = maxMoves - distance.get(c.n);
                            int nodesInBetween = c.w - 1;
                            int unvisitedNodes = nodesInBetween - childAvailableMoves;
                            if (unvisitedNodes > 0) {
                                int availableMovesForCurr = maxMoves - distance.get(curr.n);
                                count += Math.min(unvisitedNodes, availableMovesForCurr);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
