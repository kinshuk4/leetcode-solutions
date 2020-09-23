package com.vaani.leetcode.array;

import com.vaani.dsa.ds.core.visual.Point;

import java.util.*;

/**
 * https://leetcode.com/problems/image-overlap/
 * 835. Image Overlap
 * Medium
 * <p>
 * Two images A and B are given, represented as binary, square matrices of the same size.  (A binary matrix has only 0s and 1s as values.)
 * <p>
 * We translate one image however we choose (sliding it left, right, up, or down any number of units), and place it on top of the other image.  After, the overlap of this translation is the number of positions that have a 1 in both images.
 * <p>
 * (Note also that a translation does not include any kind of rotation.)
 * <p>
 * What is the largest possible overlap?
 * <p>
 * Example 1:
 * <p>
 * Input: A = [[1,1,0],
 * [0,1,0],
 * [0,1,0]]
 * B = [[0,0,0],
 * [0,1,1],
 * [0,0,1]]
 * Output: 3
 * Explanation: We slide A to right by 1 unit and down by 1 unit.
 * Notes:
 * <p>
 * 1 <= A.length = A[0].length = B.length = B[0].length <= 30
 * 0 <= A[i][j], B[i][j] <= 1
 */

public class ImageOverlap {
    /**
     * Complexity Analysis
     * <p>
     * Time Complexity: O(N^4), where N is the length of A or B.
     * <p>
     * Space Complexity: O(N^2)
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int N = A.length;
        int[][] count = new int[2 * N + 1][2 * N + 1];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (A[i][j] == 1) {
                    for (int i2 = 0; i2 < N; ++i2) {
                        for (int j2 = 0; j2 < N; ++j2) {
                            if (B[i2][j2] == 1) {
                                count[i - i2 + N][j - j2 + N] += 1;
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int[] row : count)
            for (int v : row)
                ans = Math.max(ans, v);
        return ans;
    }

    public int largestOverlap2(int[][] A, int[][] B) {
        int N = A.length;
        List<Point> A2 = new ArrayList<>(), B2 = new ArrayList<>();
        for (int i = 0; i < N * N; ++i) {
            if (A[i / N][i % N] == 1) {
                A2.add(new Point(i / N, i % N));
            }
            if (B[i / N][i % N] == 1) {
                B2.add(new Point(i / N, i % N));
            }
        }

        Set<Point> Bset = new HashSet<>(B2);

        int ans = 0;
        Set<Point> seen = new HashSet<>();
        for (Point a : A2)
            for (Point b : B2) {
                Point delta = new Point(b.x - a.x, b.y - a.y);
                if (!seen.contains(delta)) {
                    seen.add(delta);
                    int cand = 0;
                    for (Point p : A2)
                        if (Bset.contains(new Point(p.x + delta.x, p.y + delta.y)))
                            cand++;
                    ans = Math.max(ans, cand);
                }
            }

        return ans;
    }

    public int largestOverlap3(int[][] A, int[][] B) {
        int rows = A.length, cols = A[0].length;
        List<Point> la = new ArrayList<>(), lb = new ArrayList<>(); // two lists to save pixel coordinates
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                if (A[r][c] == 1) { // save the pixel coordinates
                    la.add(new Point(r, c));
                }
                if (B[r][c] == 1) {
                    lb.add(new Point(r, c));
                }
            }
        Map<String, Integer> map = new HashMap<>(); // map to map the vector (from a pixel in A to a pixel in B) to its count
        int max = 0;
        for (Point pa : la) {
            for (Point pb : lb) {
                String s = (pa.x - pb.x) + " " + (pa.y - pb.y); // get the vector from a pixel in A to a pixel in B
                map.put(s, map.getOrDefault(s, 0) + 1); // count the number of same vectors
                max = Math.max(map.get(s), max);
            }
        }

        return max;
    }
}
