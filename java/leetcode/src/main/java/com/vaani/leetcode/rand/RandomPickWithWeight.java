package com.vaani.leetcode.rand;

import java.util.*;

public class RandomPickWithWeight {
    private TreeMap<Integer, Integer> map;
    private Random rand;
    private int total;

    public RandomPickWithWeight(int[] w) {
        map = new TreeMap<>();
        total = 0;
        rand = new Random();
        for (int i = 0; i < w.length; i++) {
            total += w[i];
            map.put(total, i);
        }

    }

    public int pickIndex() {
        int k = map.higherKey(rand.nextInt(total));
        return map.get(k);
    }
}
