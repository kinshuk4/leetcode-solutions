package com.vaani.leetcode.hashing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/powerful-integers/
 * 970. Powerful Integers
 * Easy
 * <p>
 * Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.
 * <p>
 * Return a list of all powerful integers that have value less than or equal to bound.
 * <p>
 * You may return the answer in any order.  In your answer, each value should occur at most once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2, y = 3, bound = 10
 * Output: [2,3,4,5,7,9,10]
 * Explanation:
 * 2 = 2^0 + 3^0
 * 3 = 2^1 + 3^0
 * 4 = 2^0 + 3^1
 * 5 = 2^1 + 3^1
 * 7 = 2^2 + 3^1
 * 9 = 2^3 + 3^0
 * 10 = 2^0 + 3^2
 * <p>
 * Example 2:
 * <p>
 * Input: x = 3, y = 5, bound = 15
 * Output: [2,4,6,8,10,14]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= x <= 100
 * 1 <= y <= 100
 * 0 <= bound <= 10^6
 */
public class PowerfulIntegers {

    // try 1
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < 18 && Math.pow(x, i) <= bound; ++i)
            for (int j = 0; j < 18 && Math.pow(y, j) <= bound; ++j) {
                long v = (int) Math.pow(x, i) + (int) Math.pow(y, j);
                if (v <= bound) {
                    seen.add((int) v);
                }
            }

        return new ArrayList<>(seen);
    }

    // try 2
    public List<Integer> powerfulIntegers2(int x, int y, int bound) {
        Set<Integer> seen = new HashSet<>();

        int xMax = getMax(x, bound);
        int yMax = getMax(y, bound);

        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                double check = Math.pow(x, i) + Math.pow(y, j);
                if (check <= bound && !seen.contains((int) check))
                    seen.add((int) check);
            }
        }
        return new ArrayList<>(seen);
    }

    private int getMax(int base, int bound) {
        int max = 1;
        if (base == 1) {
            return 1;
        }
        for (int i = 0; i < 64; i++) {
            if (bound <= Math.pow(base, i)) {
                max = i;
                break;
            }
        }
        return max;
    }
}
