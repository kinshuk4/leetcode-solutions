package com.vaani.leetcode.math;

import com.vaani.dsa.ds.core.visual.Point;

public class kClosestPointsToOrigin {
    static class UsingKthOrderStatistics {
        public Point[] kClosest(final Point[] points, final int k) {
            final int n = points.length;
            final double[] dist = new double[n];
            for (int i = 0; i < n; i++) {
                dist[i] = Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y);
            }
            final double kthMin = kthSmallest(dist, 0, n - 1, k);

            final Point[] ans = new Point[k];
            for (int i = 0, j = 0; i < n && j < k; i++) {
                final double d = Math.sqrt(points[i].x * points[i].x + points[i].y * points[i].y);
                if (d <= kthMin) {
                    ans[j++] = points[i];
                }
            }

            return ans;
        }

        public double kthSmallest(final double[] A, final int l, final int r, final int k) {
            if (l < r) {
                final int q = RandomizedPartition(A, l, r);

                final int n = q - l + 1;
                if (k == n) {
                    return A[q];
                } else if (k < n) {
                    return kthSmallest(A, l, q - 1, k);
                } else {
                    return kthSmallest(A, q + 1, r, k - n);
                }
            } else {
                return Double.MIN_VALUE;
            }
        }

        private int RandomizedPartition(final double[] A, final int l, final int r) {
            final int i = (int) Math.round(l + Math.random() * (r - l));
            swap(A, i, r);
            return partition(A, l, r);
        }

        private void swap(final double[] input, final int i, final int j) {
            final double temp = input[i];
            input[i] = input[j];
            input[j] = temp;
        }

        private int partition(final double[] A, final int l, final int r) {
            final double pivot = A[r];
            int i = l - 1;
            int j = l;

            for (j = l; j < r; j++) {
                if (A[j] <= pivot) {
                    swap(A, ++i, j);
                }
            }

            swap(A, i + 1, r);
            return i + 1;
        }
    }
}
