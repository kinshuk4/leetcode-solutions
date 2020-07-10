package com.vaani.leetcode.heap;

import org.junit.Assert;

import java.util.*;

public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        TopKFrequentElements underTest = new TopKFrequentElements();
        Assert.assertArrayEquals(new int[]{1, 2}, underTest.topKFrequent(nums, 2));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // O(1) time
        if (k == nums.length) {
            return nums;
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        Queue<Integer> minHeap = new PriorityQueue<>((n1, n2) -> frequencyMap.get(n1) - frequencyMap.get(n2));

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n : frequencyMap.keySet()) {
            minHeap.add(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // 3. build an output array
        // O(k log k) time
        int[] top = new int[k];
        for (int i = k - 1; i >= 0; --i) {
            top[i] = minHeap.poll();
        }
        return top;
    }
}
