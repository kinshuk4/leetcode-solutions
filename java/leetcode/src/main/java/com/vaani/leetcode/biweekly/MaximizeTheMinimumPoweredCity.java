package com.vaani.leetcode.biweekly;

import java.util.PriorityQueue;
import java.util.*;

public class MaximizeTheMinimumPoweredCity {
    public long maxPower(int[] stations, int r, int k) {
        int n = stations.length;
        Map<Integer, Long> power = new HashMap<>();
        Queue<Map.Entry<Integer, Long>> minHeap = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (int i = 0; i < n; i++) {
            for (int j = -r ; j < 0; j++) {
                int idx = i + j;
                if (idx >= 0) {
                    power.put(i, power.getOrDefault(i, 0L) + stations[i]);
                }
            }
            for (int j = r; j > 0; j--) {
                int idx = i + j;
                if (idx < n) {
                    power.put(i, power.getOrDefault(i, 0L) + stations[i]);
                }
            }

        }
        for (Map.Entry<Integer, Long> entry: power.entrySet()) {
            minHeap.add(entry);
        }

        
    }
}
