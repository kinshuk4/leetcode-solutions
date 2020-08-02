package com.vaani.leetcode.heap;

import java.util.PriorityQueue;

/** https://leetcode.com/problems/k-closest-points-to-origin/
 * 973. K Closest Points to Origin
 * Medium
 * We have a list of points on the plane. Find the K
 * closest points to the origin (0, 0).
 *
 * <p>(Here, the distance between two points on a plane is the Euclidean distance.)
 *
 * <p>You may return the answer in any order. The answer is guaranteed to be unique (except for the
 * order that it is in.)
 *
 * <p>Example 1:
 *
 * <p>Input: points = [[1,3],[-2,2]], K = 1 Output: [[-2,2]] Explanation: The distance between (1,
 * 3) and the origin is sqrt(10). The distance between (-2, 2) and the origin is sqrt(8). Since
 * sqrt(8) < sqrt(10), (-2, 2) is closer to the origin. We only want the closest K = 1 points from
 * the origin, so the answer is just [[-2,2]]. Example 2:
 *
 * <p>Input: points = [[3,3],[5,-1],[-2,4]], K = 2 Output: [[3,3],[-2,4]] (The answer [[-2,4],[3,3]]
 * would also be accepted.)
 *
 * <p>Note:
 *
 * <p>1 <= K <= points.length <= 10000 -10000 < points[i][0] < 10000 -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] A = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] ans = new KClosestPointsToOrigin.MaxHeapWithDataModel().kClosest(A, 2);
        System.out.println();
    }

    // 50 ms
    static class MaxHeapWithDataModel {
        static class Point {
            int x, y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public long distance() {
                return (long) (x * x) + (long) (y * y);
            }
        }

        public int[][] kClosest(int[][] points, int K) {
            PriorityQueue<Point> maxHeap = new PriorityQueue<>((o1, o2) -> Long.compare(o2.distance(), o1.distance()));
            for (int[] p : points) {
                maxHeap.offer(new Point(p[0], p[1]));
                if (maxHeap.size() > K) {
                    maxHeap.poll();
                }
            }
            int[][] result = new int[K][2];
            int i = 0;
            while (!maxHeap.isEmpty()) {
                Point point = maxHeap.poll();
                result[i][0] = point.x;
                result[i++][1] = point.y;
            }
            return result;
        }
    }


    // No big difference in time - 49 ms
    public int[][] kClosest(int[][] points, int K) {
//        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Long.compare(getDistance(b), getDistance(a)));
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] * b[0] + b[1] * b[1] - (a[0] * a[0] + a[1] * a[1]));
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        int[][] result = new int[K][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            int[] point = maxHeap.poll();
            result[i++] = point;
        }
        return result;
    }

    long getDistance(int[] point) {
        return (long) (point[0] * point[0]) + (long) (point[1] * point[1]);
    }



}
