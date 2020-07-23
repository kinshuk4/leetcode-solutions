package com.vaani.leetcode.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/largest-values-from-labels/
 * We have a set of items: the i-th item has value values[i] and label labels[i].
 * <p>
 * Then, we choose a subset S of these items, such that:
 * <p>
 * |S| <= num_wanted
 * For every label L, the number of items in S with label L is <= use_limit.
 * <p>
 * Return the largest possible sum of the subset S.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,1,2,2,3], num_wanted = 3, use_limit = 1
 * Output: 9
 * Explanation: The subset chosen is the first, third, and fifth item.
 * <p>
 * Example 2:
 * <p>
 * Input: values = [5,4,3,2,1], labels = [1,3,3,3,2], num_wanted = 3, use_limit = 2
 * Output: 12
 * Explanation: The subset chosen is the first, second, and third item.
 * <p>
 * Example 3:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 1
 * Output: 16
 * Explanation: The subset chosen is the first and fourth item.
 * <p>
 * Example 4:
 * <p>
 * Input: values = [9,8,8,7,6], labels = [0,0,0,1,1], num_wanted = 3, use_limit = 2
 * Output: 24
 * Explanation: The subset chosen is the first, second, and fourth item.
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= values.length == labels.length <= 20000
 * 0 <= values[i], labels[i] <= 20000
 * 1 <= num_wanted, use_limit <= values.length
 */
public class LargestValuesFromLabels {
    public static void main(String[] args) {

    }

    // from each subset - we just pick at most use_limit
    // num wanted is numbers we can add up to get largest sum
    static class Item {
        int value;
        int label;

        public Item(int val, int label) {
            this.value = val;
            this.label = label;
        }
    }

    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            items.add(new Item(values[i], labels[i]));
        }

        PriorityQueue<Item> maxHeap = new PriorityQueue<>((a, b) -> b.value - a.value);
        maxHeap.addAll(items);

        // label Id to frequency of use
        Map<Integer, Integer> counts = new HashMap<>();
        int value = 0;
        while (!maxHeap.isEmpty() && num_wanted > 0) {
            Item current = maxHeap.poll();
            counts.put(current.label, counts.getOrDefault(current.label, 0) + 1);
            if (counts.get(current.label) <= use_limit) {
                value += current.value;
                num_wanted--;
            }
        }
        return value;
    }
}
