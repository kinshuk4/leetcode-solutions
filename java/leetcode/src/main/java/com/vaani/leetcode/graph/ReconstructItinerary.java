package com.vaani.leetcode.graph;

import java.util.*;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 * <p>
 * Note:
 * <p>
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 * All airports are represented by three capital letters (IATA code).
 * You may assume all tickets form at least one valid itinerary.
 * Example 1:
 * <p>
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * Example 2:
 * <p>
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 * But it is larger in lexical order.
 */
public class ReconstructItinerary {
    public static void main(String[] args) {

    }

    static class UsingDFSRecursive {
        // key: depart, value: list of arrive
        Map<String, Queue<String>> orderedmap = new HashMap<>();
        List<String> res = new LinkedList<>();

        public List<String> findItinerary(List<List<String>> tickets) {
            for (int i = 0; i < tickets.size(); i++) {
                List<String> item = tickets.get(i);
                String depart = item.get(0);
                String arrive = item.get(1);
                if (orderedmap.get(depart) == null) {
                    orderedmap.put(depart, new PriorityQueue<>(Comparator.naturalOrder()));
                }
                orderedmap.get(depart).add(arrive);
            }
            dfs("JFK", tickets.size() + 1);
            return res;

        }

        public boolean dfs(String start, int total) {

            //look for a complete traversal
            if (res.size() == total)
                return true;
            if (orderedmap.get(start) == null)
                return false;

            //remember options
            PriorityQueue<String> branches = new PriorityQueue<>(orderedmap.get(start));

            while (branches.size() > 0) {
                String branch = branches.poll();
                // branches already diverge from orderedmap.get(start)
                orderedmap.get(start).remove(branch);
                res.add(start);
                if (dfs(branch, total))
                    return true;
                res.remove(start);
                //back fill this dead branch to selection
                orderedmap.get(start).add(branch);
                res.remove(res.size() - 1);
            }
            return false;
        }
    }

    static class UsingDFSIterative {
        public List<String> findItinerary2(List<List<String>> tickets) {
            Map<String, Queue<String>> graph = new HashMap<>();

            for (List<String> ticket : tickets) {
                String depart = ticket.get(0);
                String arrive = ticket.get(1);
                graph.putIfAbsent(depart, new PriorityQueue<>(Comparator.naturalOrder())); // to keep stations sorted. We can also use treemap
                graph.get(depart).add(arrive);
            }

            Stack<String> stack = new Stack<>();
            stack.push("JFK");
            List<String> result = new ArrayList<>();
            while (!stack.isEmpty()) {
                String src = stack.peek();

                if (graph.get(src).size() == 0)  //No further travel possible
                {
                    result.add(src);
                    stack.pop();
                } else {
                    String dst = graph.get(src).poll();
                    stack.push(dst);
                }

            }
            return result;

        }

        // compressed
        public List<String> findItinerary(List<List<String>> tickets) {
            Map<String, PriorityQueue<String>> graph = new HashMap<>();
            for (List<String> ticket : tickets) {
                graph.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
            }
            Stack<String> stack = new Stack<>();
            LinkedList<String> result = new LinkedList<>(); // using LinkedList instead of List as we have addFirst(value) function faster than add(0, value)
            stack.push("JFK");
            while (!stack.isEmpty()) {
                String src = stack.peek();
                // key may not be in graph in case it is like final destination, for eg. see SJC in test case
                if (!graph.containsKey(src) || graph.get(src).isEmpty()) { //No further travel possible
                    result.addFirst(src); // adding at start otherwise we have to rverse
                    stack.pop();
                } else {
                    String dst = graph.get(src).poll();
                    stack.push(dst);
                }
            }
            return result;
        }


        // even more compressed
        public List<String> findItinerary3(String[][] tickets) {
            Map<String, PriorityQueue<String>> g = new HashMap<>();
            for (String[] t : tickets) {
                g.computeIfAbsent(t[0], k -> new PriorityQueue<>()).add(t[1]);
            }
            Deque<String> stack = new ArrayDeque<>();
            LinkedList<String> route = new LinkedList<>();
            stack.push("JFK");
            while (!stack.isEmpty()) {
                while (g.containsKey(stack.peek()) && !g.get(stack.peek()).isEmpty()) {
                    stack.push(g.get(stack.peek()).poll());
                }
                route.addFirst(stack.pop());
            }
            return route;
        }

    }

}
