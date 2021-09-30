package com.vaani.leetcode.tree;

import java.util.*;

/**
 * 834. Sum of Distances in Tree
 * Hard
 *
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 *
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 * Example 2:
 *
 *
 * Input: n = 1, edges = []
 * Output: [0]
 * Example 3:
 *
 *
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * The given input represents a valid tree.
 */
public class SumOfDistancesInTree {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Integer>[] tree = buildGraph(n, edges);

        int[] cnt = new int[n];
        int[] ans = new int[n];

        dfs(tree, 0, -1, cnt, ans);
        dfs2(tree, 0, -1, cnt, ans);

        return ans;
    }

    private List<Integer>[] buildGraph(int n, int[][] edges) {
        List<Integer>[] tree = new List[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }
        return tree;
    }

    private void dfs(List<Integer>[] tree, int cur, int pre, int[] cnt, int[] ans) {
        for (int child : tree[cur]) {
            if (child != pre) {
                dfs(tree, child, cur, cnt, ans);
                ans[cur] += ans[child] + cnt[child];
                cnt[cur] += cnt[child];
            }
        }
        cnt[cur]++;
    }

    private void dfs2(List<Integer>[] tree, int cur, int pre, int[] cnt, int[] ans) {
        for (int child : tree[cur]) {
            if (child != pre) {
                ans[child] = ans[cur] - cnt[child] + cnt.length - cnt[child];
                dfs2(tree, child, cur, cnt, ans);
            }
        }
    }

}
