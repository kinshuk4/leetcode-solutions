package com.vaani.leetcode.math;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
 * Output: false
 */
public class CheckIfItIsaStraightLine {
    // naive - check the slopes
    public boolean checkStraightLine(int[][] coordinates) {
        double dx = coordinates[1][0] - coordinates[0][0];
        double dy = coordinates[1][1] - coordinates[0][1];

        double slope = dy / dx; // not handling case when dx = 0

        for (int i = 2; i < coordinates.length; i++) {
            double dxLocal = coordinates[i][0] - coordinates[i - 1][0];
            double dyLocal = coordinates[i][1] - coordinates[i - 1][1];
            if (dyLocal / dxLocal != slope) {
                return false;
            }
        }

        return true;
    }

    // Lesser division heavy
    public boolean checkStraightLine2(int[][] coordinates) {
        int dx = coordinates[0][0] - coordinates[1][0];
        int dy = coordinates[0][1] - coordinates[1][1];

        for (int i = 2; i < coordinates.length; ++i) {
            double dxWith0 = coordinates[i][0] - coordinates[0][0];
            double dyWith0 = coordinates[i][1] - coordinates[0][1];
            if (dxWith0 * dy != dyWith0 * dx) {
                return false;
            }
        }

        return true;
    }
}
