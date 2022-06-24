package com.vaani.leetcode.tree;

import java.util.*;

/**
 * 823. Binary Trees With Factors
 * Medium
 * <p>
 * Given an array of unique integers, arr, where each integer arr[i] is strictly greater than 1.
 * <p>
 * We make a binary tree using these integers, and each number may be used for any number of times. Each non-leaf node's value should be equal to the product of the values of its children.
 * <p>
 * Return the number of binary trees we can make. The answer may be too large so return the answer modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [2,4]
 * Output: 3
 * Explanation: We can make these trees: [2], [4], [4, 2, 2]
 * Example 2:
 * <p>
 * Input: arr = [2,4,5,10]
 * Output: 7
 * Explanation: We can make these trees: [2], [4], [5], [10], [4, 2, 2], [10, 2, 5], [10, 5, 2].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 1000
 * 2 <= arr[i] <= 10^9
 */
public class BinaryTreesWithFactors {
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);

        int MOD = 1_000_000_007;
        int N = arr.length;


        Map<Integer, Long> indexMap = new HashMap<>();
        for (int k : arr) {
            indexMap.put(k, 1L);
        }

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < i; ++j) {
                if (arr[i] % arr[j] == 0) {
                    int right = arr[i] / arr[j];
                    if (indexMap.containsKey(right)) {
                        indexMap.put(arr[i], indexMap.get(arr[i]) + indexMap.get(arr[j]) * indexMap.get(right));
                    }
                }
            }

        long l = indexMap.values().stream().reduce(0L, Long::sum) % MOD;
        return (int) l;
    }
}
