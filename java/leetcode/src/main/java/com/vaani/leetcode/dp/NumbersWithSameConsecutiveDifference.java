package com.vaani.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/numbers-with-same-consecutive-differences/
 * 967. Numbers With Same Consecutive Differences
 * Medium
 * <p>
 * Return all non-negative integers of length N such that the absolute difference between every two consecutive digits is K.
 * <p>
 * Note that every number in the answer must not have leading zeros except for the number 0 itself. For example, 01 has one leading zero and is invalid, but 0 is valid.
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: N = 3, K = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * <p>
 * Example 2:
 * <p>
 * Input: N = 2, K = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 9
 * 0 <= K <= 9
 */
public class NumbersWithSameConsecutiveDifference {
    static class UsingDFS {
        public int[] numsSameConsecDiff(int N, int K) {
            List<Integer> ans = new ArrayList<>();
            if (N == 0) {
                return new int[0];
            }
            if (N == 1) {
                ans.add(0);      // edge case

            }
            dfs(N, K, ans, 0);
            return ans.stream().mapToInt(i -> i).toArray();
        }

        public void dfs(int N, int K, List<Integer> list, int number) {
            if (N == 0) {
                // base case, if you have added enough number of integers then append it to list;
                // Here N is used as the total digits in temporary number
                list.add(number);
                return;
            }
            for (int i = 0; i < 10; ++i) {
                if (i == 0 && number == 0) {   // Do not add 0 at beginning of a number
                    continue;
                } else if (i != 0 && number == 0) {     // base case, we add all the digits when we do not have any previous digit to check if difference = K
                    dfs(N - 1, K, list, i);
                } else {
                    if (Math.abs((number % 10) - i) == K) {
                        // General dfs to add the digit at the units place and
                        // reducing the number of digits by 1.
                        dfs(N - 1, K, list, number * 10 + i);
                    }
                }
            }
        }
    }
}
