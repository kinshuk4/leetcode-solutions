package com.vaani.leetcode.dp;

import java.util.*;

/**
 * 1345. Jump Game IV
 * Hard
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * <p>
 * In one step you can jump from index i to index:
 * <p>
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 * Example 2:
 * <p>
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You don't need to jump.
 * Example 3:
 * <p>
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 * Example 4:
 * <p>
 * Input: arr = [6,1,9]
 * Output: 2
 * Example 5:
 * <p>
 * Input: arr = [11,22,7,7,7,7,7,7,7,22,13]
 * Output: 3
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 */
public class JumpGame4 {
    // here we need to build a graph and see whether we can easily get on to the last element with the minimum steps possible.
    // here we have 3 options
    // 1. we move i+1 if i+1 < length
    // 2. we move i-1 if i-1 >= 0
    // 3. we move to j if arr[i] == arr[j] and i != j
    // now after building graph we need to do the bFS.
    // we can also make use of a visited boolean array to check we already visited a node or not
    // one of the key things is we need to clear the dictionary inorder to prevent the stepping back

    public int minJumps(int[] arr) {
        if (arr.length == 1) {
            return 0;
        }
        int count = 0;
        int length = arr.length;
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < length; i++) {
            graph.computeIfAbsent(arr[i], x -> new LinkedList<>()).add(i);
        }

        boolean[] visited = new boolean[length];
        visited[0] = true;

        Queue<Integer> queue = new LinkedList<>(); // for bfs
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = size; i > 0; i--) {
                int current = queue.poll();
                // if we reach the last index
                if (current == length - 1) {
                    return count;
                }
                // get the possible next values
                List<Integer> adjList = graph.get(arr[current]);
                // now lets add the previous, ie current-1, next ies, current + 1 into the list
                adjList.add(current - 1);
                adjList.add(current + 1);
                for (int adj : adjList) {
                    if (adj >= 0 && adj < length && !visited[adj]) {
                        visited[adj] = true;
                        queue.offer(adj);
                    }
                }
                adjList.clear();
            }
            count += 1;
        }
        return 0;
    }
}
