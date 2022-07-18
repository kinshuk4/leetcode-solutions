package com.vaani.leetcode.dp;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 * 1074. Number of Submatrices That Sum to Target
 * Hard
 * <p>
 * Given a matrix and a target, return the number of non-empty submatrices that sum to target.
 * <p>
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 * <p>
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * Example 2:
 * <p>
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 * Example 3:
 * <p>
 * Input: matrix = [[904]], target = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 */
public class NumberOfSubmatricesThatSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        Map<Integer, Integer> count = new HashMap<>();
        int[] dp = new int[matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                count.put(0, 1);
                ans += numSubmatrixBacktrack(i, j, matrix, target, count, dp);
                count.clear();
            }
        }
        return ans;
    }

    private int numSubmatrixBacktrack(int start, int end, int[][] matrix, int target, Map<Integer, Integer> count, int[] dp) {
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < matrix[start].length; i++) {
            sum += matrix[end][i];
            dp[i] = sum + (start == end ? 0 : dp[i]);
            ans += count.getOrDefault(dp[i] - target, 0);
            count.put(dp[i], count.getOrDefault(dp[i], 0) + 1);
        }
        return ans;
    }

}
