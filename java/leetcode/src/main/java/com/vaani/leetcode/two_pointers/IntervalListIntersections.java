package com.vaani.leetcode.two_pointers;

import com.vaani.dsa.ds.core.visual.Interval;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/interval-list-intersections/
 * 986. Interval List Intersections
 * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
 * <p>
 * Return the intersection of these two interval lists.
 * <p>
 * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= A.length < 1000
 * 0 <= B.length < 1000
 * 0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
 * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
public class IntervalListIntersections {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0, j = 0;
        List<int[]> list = new LinkedList<>();
        while (i < A.length && j < B.length) {
            //   -----
            // -----
            while (i < A.length && j < B.length && A[i][1] >= B[j][1] && A[i][0] > B[j][0]) {
                //      -----
                // ----
                if (A[i][0] <= B[j][1]) {
                    list.add(new int[]{A[i][0], B[j][1]});
                }
                j++;
            }
            // ----------
            //   -----
            while (i < A.length && j < B.length && A[i][1] >= B[j][1] && A[i][0] <= B[j][0]) {
                list.add(new int[]{B[j][0], B[j][1]});
                j++;
            }
            // -----
            //   -----
            while (i < A.length && j < B.length && B[j][1] >= A[i][1] && B[j][0] > A[i][0]) {
                // ----
                //      -----
                if (B[j][0] <= A[i][1]) {
                    list.add(new int[]{B[j][0], A[i][1]});
                }
                i++;
            }
            //   -----
            // ----------
            while (i < A.length && j < B.length && B[j][1] >= A[i][1] && B[j][0] <= A[i][0]) {
                list.add(new int[]{A[i][0], A[i][1]});
                i++;
            }
        }
        int[][] res = new int[list.size()][2];
        return list.toArray(res);
    }

    // simpler
    public int[][] intervalIntersection2(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length) {
            // Let's check if A[i] intersects B[j].
            // lo - the startpoint of the intersection
            // hi - the endpoint of the intersection
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            // has interval
            if (lo <= hi) {
                result.add(new int[]{lo, hi});
            }

            // Remove the interval with the smallest endpoint
            if (A[i][1] < B[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    // interval object - simpler on eyes
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return new Interval[] {};
        }

        int m = A.length, n = B.length;
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();
        while (i < m && j < n) {
            Interval a = A[i];
            Interval b = B[j];

            // find the overlap... if there is any...
            int startMax = Math.max(a.start, b.start);
            int endMin = Math.min(a.end, b.end);

            if (endMin >= startMax) {
                res.add(new Interval(startMax, endMin));
            }

            //update the pointer with smaller end value...
            if (a.end == endMin) { i++; }
            if (b.end == endMin) { j++; }
        }

        //thanks EOAndersson for the concice expression of converting a list to an array
        //thanks Sithis for the explaination of using toArray (new T[0]) intead fo toArray newT[size])
        return res.toArray(new Interval[0]);
    }

}