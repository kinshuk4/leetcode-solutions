package com.vaani.leetcode.binary_search;

import org.junit.Assert;

/**
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 * <p>
 * Return the number of negative numbers in grid.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 * Example 2:
 * <p>
 * Input: grid = [[3,2],[1,0]]
 * Output: 0
 * Example 3:
 * <p>
 * Input: grid = [[1,-1],[-1,-1]]
 * Output: 3
 * Example 4:
 * <p>
 * Input: grid = [[-1]]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 */
public class CountNegativeNumbersinaSortedMatrix {

    public static void main(String[] args) {
        int[][] grid = {
                {3, 2}, {1, 0}
        };

        Assert.assertEquals(0 , countNegatives(grid));
    }

    // brute
    public int countNegatives1(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] < 0) {
                    res += grid[i].length - j;
                    break;
                }
            }
        }

        return res;
    }

    public static int countNegatives(int[][] grid) {
        int result = 0;
        for (int[] ints : grid) {
            int start = 0;
            int end = ints.length - 1;
            int mid = start + (end - start) / 2;

            // note equality
            while (start <= end) {
                if (ints[mid] < 0) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                mid = start + (end - start) / 2;
            }
            result += grid[0].length - start;
        }
        return result;
    }
}
