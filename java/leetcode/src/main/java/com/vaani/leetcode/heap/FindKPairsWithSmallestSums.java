package com.vaani.leetcode.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k <= 0) {
            return ans;
        }

        class Node {
            final int x;
            final int y;

            Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> nums1[a.x] + nums2[a.y]));

        int m = nums1.length, n = nums2.length;
        for (int j = 0; j <= n - 1; j++) {
            pq.offer(new Node(0, j));
        }

        k = (int) Math.min((long) k, (long) m * (long) n);
        for (int i = 0; i < k; i++) {
            Node curr = pq.poll();
            ans.add(List.of(nums1[curr.x], nums2[curr.y]));
            if (curr.x == m - 1) continue;
            pq.offer(new Node(curr.x + 1, curr.y));
        }
        return ans;
    }
}
