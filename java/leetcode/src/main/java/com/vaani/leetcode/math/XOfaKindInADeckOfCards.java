package com.vaani.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/
 * 914. X of a Kind in a Deck of Cards
 * Easy
 * <p>
 * In a deck of cards, each card has an integer written on it.
 * <p>
 * Return true if and only if you can choose X >= 2 such that it is possible to split the entire deck into 1 or more groups of cards, where:
 * <p>
 * Each group has exactly X cards.
 * All the cards in each group have the same integer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: deck = [1,2,3,4,4,3,2,1]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[3,3],[4,4].
 * <p>
 * Example 2:
 * <p>
 * Input: deck = [1,1,1,2,2,2,3,3]
 * Output: false´
 * Explanation: No possible partition.
 * <p>
 * Example 3:
 * <p>
 * Input: deck = [1]
 * Output: false
 * Explanation: No possible partition.
 * <p>
 * Example 4:
 * <p>
 * Input: deck = [1,1]
 * Output: true
 * Explanation: Possible partition [1,1].
 * <p>
 * Example 5:
 * <p>
 * Input: deck = [1,1,2,2,2,2]
 * Output: true
 * Explanation: Possible partition [1,1],[2,2],[2,2].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= deck.length <= 10^4
 * 0 <= deck[i] < 10^4
 */
public class XOfaKindInADeckOfCards {
    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int c : deck) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int g = map.values().iterator().next();
        for (int value : map.values()) {
            g = gcd(g, value);
        }
        return g >= 2;
    }

    public int gcd(int x, int y) {
        return x == 0 ? y : gcd(y % x, x);
    }
}
