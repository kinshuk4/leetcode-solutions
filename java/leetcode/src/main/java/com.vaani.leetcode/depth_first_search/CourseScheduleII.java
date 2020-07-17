package com.vaani.leetcode.depth_first_search;

import com.vaani.dsa.ds.core.graph.simple.UnweightedAdjListGraph;

import java.util.*;

/**
 * https://leetcode.com/articles/course-schedule-ii/
 * There are a total of n courses you have to take,
 * labeled from 0 to n - 1.
 *
 * <p>Some courses may have prerequisites, for example to take course 0 you have to first take
 * course 1, which is expressed as a pair: [0,1]
 *
 * <p>Given the total number of courses and a list of prerequisite pairs, return the ordering of
 * courses you should take to finish all courses.
 *
 * <p>There may be multiple correct orders, you just need to return one of them. If it is impossible
 * to finish all courses, return an empty array.
 *
 * <p>For example:
 *
 * <p>2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished
 * course 0. So the correct course order is [0,1]
 *
 * <p>4, [[1,0],[2,0],[3,1],[3,2]] There are a total of 4 courses to take. To take course 3 you
 * should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you
 * finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering
 * is[0,2,1,3].
 *
 * <p>Note: The input prerequisites is a graph represented by a list of edges, not adjacency
 * matrices. Read more about how a graph is represented. You may assume that there are no duplicate
 * edges in the input prerequisites.
 */
public class CourseScheduleII {
    private Map<Integer, List<Integer>> graph;
    private BitSet visited;
    private Queue<Integer> toposorted;

    public static void main(String[] args) throws Exception {
        int[][] pre = {{1, 0}};
        CourseScheduleII underTest = new CourseScheduleII();
        int[] result = underTest.findOrder1DFS(2, pre);
        for (int i : result) System.out.print(i + " ");
        System.out.println();

        result = underTest.findOrder2Kahn(2, pre);
        Arrays.stream(result).forEach(x -> System.out.print(x + " "));
        System.out.println();


        result = underTest.findOrder2Kahn(2, new int[][]{});
        Arrays.stream(result).forEach(x -> System.out.print(x + " "));
        System.out.println();

    }

    // build graph
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] children : prerequisites) {
//            graph.putIfAbsent(children[0], new ArrayList<>());
            System.out.println(children[0]);
            graph.get(children[0]).add(children[1]);
        }
        return graph;
    }

    public int[] findOrder1DFS(int numCourses, int[][] prerequisites) {
        int j = 0;
        int[] courses = new int[numCourses];
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = j++;
        }
        graph = buildGraph(numCourses, prerequisites);
        visited = new BitSet();
        toposorted = new ArrayDeque<>();

        graph.keySet().stream().filter(v -> !visited.get(v)).forEach(this::dfs);

        visited.clear();
        int i = 0;
        while (!toposorted.isEmpty()) {
            int v = toposorted.poll();
            if (visited.get(v)) return new int[0];
            relax(v);
            result[i++] = v;
            courses[v] = -1;
        }
        // add the remaining courses
        for (int c : courses) {
            if (c != -1) result[i++] = c;
        }
        return result;
    }

    /**
     * Mark a vetex and its connected vertices as visited.
     */
    private void relax(int v) {
        visited.set(v);
        List<Integer> children = graph.get(v);
        if (children != null) {
            for (int c : children) visited.set(c);
        }
    }


    private void dfs(int v) {
        visited.set(v);
        List<Integer> children = graph.get(v);
        if (children != null) {
            for (int c : children) if (!visited.get(c)) dfs(c);
        }
        toposorted.offer(v);
    }

    private Map<Integer, List<Integer>> buildGraph2(int n, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] children : prerequisites) {
            graph.get(children[1]).add(children[0]);
        }
        return graph;
    }

    public int[] findOrder2Kahn(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph2(numCourses, prerequisites);

        int[] inDegrees = new int[numCourses];
        for (int node : graph.keySet()) {
            List<Integer> adjNodes = graph.get(node);
            for (int adjNode : adjNodes) {
                inDegrees[adjNode]++;
            }
        }

        // start the bfs
        Queue<Integer> queue = new LinkedList<>();

        // Add all the nodes with indegree 0
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int count = 0; //used for checking if graph is acyclic
        List<Integer> resultList = new LinkedList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++;
            resultList.add(node);
            List<Integer> adjNodes = graph.get(node);
            for (int adjNode : adjNodes) {
                inDegrees[adjNode]--;
                if (inDegrees[adjNode] == 0) {
                    queue.add(adjNode);
                }
            }

        }

        // graph has cycle
        if (count != numCourses) {
            return null;
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    // submitted
    public int[] findOrder3Topo(int numCourses, int[][] prerequisites) {
        UnweightedAdjListGraph graph = new UnweightedAdjListGraph(numCourses, true);
        for (int[] children : prerequisites) {
            graph.addEdge(children[1], children[0]);
        }

        if(graph.hasCycle()){
            return new int[]{};
        }

        BitSet visited = new BitSet();

        Stack<Integer> stack = new Stack<>();// for the topo sort

        for (int i = 0; i < numCourses; ++i) {
            if (!visited.get(i)) {
                dfs2(graph, i, visited, stack);
            }
        }


        List<Integer> resultList = new LinkedList<>();
        while (!stack.isEmpty()) {
            resultList.add(stack.pop());
        }
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs2(UnweightedAdjListGraph graph, int node, BitSet visited, Stack<Integer> stack) {
        visited.set(node);
        Optional<List<Integer>> adjListOptional = graph.getAdjList(node);
        if(adjListOptional.isPresent()){
            for (int adjNode : adjListOptional.get()) {
                if (!visited.get(adjNode)) {
                    dfs2(graph, adjNode, visited, stack);
                }
            }
        }

        stack.push(node);
    }


}
