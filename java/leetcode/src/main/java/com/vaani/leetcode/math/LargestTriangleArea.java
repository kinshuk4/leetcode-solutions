package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/largest-triangle-area/
 * 812. Largest Triangle Area
 * Easy
 * <p>
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 * <p>
 * Example:
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2
 * Explanation:
 * The five points are show in the figure below. The red triangle is the largest.
 * <p>
 * Notes:
 * <p>
 * 3 <= points.length <= 50.
 * No points will be duplicated.
 * -50 <= points[i][j] <= 50.
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class LargestTriangleArea {
    public double largestTriangleArea(int[][] points) {
        int N = points.length;
        double ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
                }
            }
        }
        return ans;
    }

    /* Let area of triangle is S and O be origin, then
     S(ABC) = S(AOB) + S(BOC) + S(COA) given O lies within the triangle.
     More: https://www.mathopenref.com/coordtrianglearea.html
     */
    public double area(int[] P, int[] Q, int[] R) {
        int x1 = P[0];
        int x2 = Q[0];
        int x3 = R[0];
        int y1 = P[1];
        int y2 = Q[1];
        int y3 = R[1];
        return 0.5 * Math.abs(x1 * (y2 - y3) - x2 * (y1 - y3) + x3 * (y1 - y2));
    }
}
