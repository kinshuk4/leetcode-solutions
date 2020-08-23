package com.vaani.leetcode.math;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-perimeter-triangle/
 * 976. Largest Perimeter Triangle
 * Easy
 * <p>
 * Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.
 * <p>
 * If it is impossible to form any triangle of non-zero area, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [2,1,2]
 * Output: 5
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,1]
 * Output: 0
 * <p>
 * Example 3:
 * <p>
 * Input: [3,2,3,4]
 * Output: 10
 * <p>
 * Example 4:
 * <p>
 * Input: [3,6,2,3]
 * Output: 8
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 3 <= A.length <= 10000
 * 1 <= A[i] <= 10^6
 */
public class LargestPerimeterTriangle {
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        for (int i = A.length - 3; i >= 0; i--) {
            if (A[i] + A[i + 1] > A[i + 2]) {
                return A[i] + A[i + 1] + A[i + 2];
            }
        }


        return 0;
    }
}
