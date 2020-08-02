package com.vaani.leetcode.two_pointers;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * 904. Fruit Into Baskets
 * Medium
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * <p>
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * <p>
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * <p>
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * <p>
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * <p>
 * What is the total amount of fruit you can collect with this procedure?
 */
public class FruitIntoBasket {
    // it is similar to longest substring with atmost 2 characters
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }

        int max = 1;
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;
        while (j < tree.length) {
            if (map.size() <= 2) {
                map.put(tree[j], j++);
            }

            if (map.size() > 2) {
                int min = tree.length - 1;
                for (int value : map.values()) {
                    min = Math.min(min, value);
                }

                i = min + 1;
                map.remove(tree[min]);
            }

            max = Math.max(max, j - i);
        }

        return max;
    }
}
