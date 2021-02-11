package com.vaani.leetcode.depth_first_search;

import java.util.*;

/**
 * https://leetcode.com/problems/beautiful-arrangement/
 * 526. Beautiful Arrangement
 * Medium
 * Suppose you have n integers from 1 to n. We define a beautiful arrangement as an array that is constructed by these n numbers successfully if one of the following is true for the ith position (1 <= i <= n) in this array:
 * <p>
 * The number at the ith position is divisible by i.
 * i is divisible by the number at the ith position.
 * Given an integer n, return the number of the beautiful arrangements that you can construct.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 2
 * Output: 2
 * Explanation:
 * The first beautiful arrangement is [1, 2]:
 * Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * The second beautiful arrangement is [2, 1]:
 * Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 15
 */
public class BeautifulArrangement {
    public int countArrangement(int n) {
        return dfs(n, new LinkedHashSet<>());
    }

    private int dfs(int n, Set<Integer> set) {

        if (set.size() == n) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (set.contains(i)) {
                continue;
            }
            // array is indexed and not 0 indexed. Hence adding 1
            int index = set.size() + 1;

            if (i % index == 0 || index % i == 0) {

                set.add(i);
                count += dfs(n, set);
                set.remove(i);

            }

        }

        return count;
    }
}
