package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/champagne-tower/
 * 799. Champagne Tower
 * Medium
 * <p>
 * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup (250ml) of champagne.
 * <p>
 * Then, some champagne is poured in the first glass at the top.  When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)
 * <p>
 * For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.
 * <p>
 * <p>
 * <p>
 * Now after pouring some non-negative integer cups of champagne, return how full the jth glass in the ith row is (both i and j are 0-indexed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: poured = 1, query_row = 1, query_glass = 1
 * Output: 0.00000
 * Explanation: We poured 1 cup of champange to the top glass of the tower (which is indexed as (0, 0)). There will be no excess liquid so all the glasses under the top glass will remain empty.
 * Example 2:
 * <p>
 * Input: poured = 2, query_row = 1, query_glass = 1
 * Output: 0.50000
 * Explanation: We poured 2 cups of champange to the top glass of the tower (which is indexed as (0, 0)). There is one cup of excess liquid. The glass indexed as (1, 0) and the glass indexed as (1, 1) will share the excess liquid equally, and each will get half cup of champange.
 * Example 3:
 * <p>
 * Input: poured = 100000009, query_row = 33, query_glass = 17
 * Output: 1.00000
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= poured <= 10^9
 * 0 <= query_glass <= query_row < 100
 *
 * @see PascalsTriangle
 */
public class ChampagneTower {

    public static void main(String[] args) {
        System.out.println(new ChampagneTower.UsingPascalsTriangle().champagneTower(4, 2, 1));
    }

    /**
     * Solution: Calculate for every glass and for each row at a time. Use the value from the
     * * previous row to calculate the current value.
     */
    static class UsingPascalsTriangle {
        public double champagneTower(int poured, int query_row, int query_glass) {
            double[][] pascalTriangle = new double[query_row + 1][query_row + 1];
            pascalTriangle[0][0] = poured;
            for (int i = 1; i <= query_row; i++) {
                for (int j = 0; j <= query_row; j++) {
                    if (pascalTriangle[i - 1][j] > 1.0) {
                        pascalTriangle[i][j] += (pascalTriangle[i - 1][j] - 1.0) / 2;
                    }
                    if (j == 0) {
                        continue;
                    }
                    if (pascalTriangle[i - 1][j - 1] > 1.0) {
                        pascalTriangle[i][j] += (pascalTriangle[i - 1][j - 1] - 1.0) / 2;
                    }
                }
            }
            if (pascalTriangle[query_row][query_glass] > 1.0) {
                return 1;
            } else {
                return pascalTriangle[query_row][query_glass];
            }
        }

        public double champagneTowerSimpler(int poured, int query_row, int query_glass) {
            double[][] triangle = new double[query_row + 1][query_glass + 2];
            triangle[0][0] = poured;

            for (int i = 0; i < query_row; i++) {
                for (int j = 0; j <= query_glass; j++) {
                    double rest = Math.max(triangle[i][j] - 1.0, 0);
                    triangle[i+1][j] += rest / 2.0;
                    triangle[i+1][j+1] += rest / 2.0;
                }
            }

            return Math.min(triangle[query_row][query_glass], 1.0);
        }
    }

}
