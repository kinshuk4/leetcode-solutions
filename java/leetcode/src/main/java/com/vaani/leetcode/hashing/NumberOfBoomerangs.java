package com.vaani.leetcode.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/number-of-boomerangs/
 * 447. Number of Boomerangs
 * Easy
 * <p>
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * <p>
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
 * <p>
 * Example:
 * Input:
 * [[0,0],[1,0],[2,0]]
 * <p>
 * Output:
 * 2
 * <p>
 * Explanation:
 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
public class NumberOfBoomerangs {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        int[][] dis = new int[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                dis[i][j] = distance(points[i], points[j]);
            }
        }

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    ans += atLeastTwoEqual(dis[i][j], dis[i][k], dis[j][k]);
                }
            }
        }
        return ans;
    }

    private int atLeastTwoEqual(int dis, int dis1, int dis2) {
        if (dis == dis1 && dis1 == dis2)
            return 6;
        else if (dis != dis1 && dis1 != dis2 && dis != dis2)
            return 0;
        else
            return 2;
    }

    private int distance(int[] point, int[] point1) {
        return (int) Math.pow(point[0] - point1[0], 2) + (int) Math.pow(point[1] - point1[1], 2);
    }

    // submitted
    public int numberOfBoomerangs2(int[][] points) {
        int count = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Long, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (j != i) {
                    long d = distanceSquare(points[i], points[j]);
                    map.put(d, map.getOrDefault(d, 0) + 1);
                }
            }
            for (int v : map.values()) {
                if (v > 1) {
                    count += v * (v - 1);
                }
            }
        }
        return count;
    }

    private long distanceSquare(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];

        return dx * dx + dy * dy;
    }
}
