package com.vaani.leetcode.binary_search;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/find-k-closest-elements/
 * 658. Find K Closest Elements
 * Medium
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 * <p>
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 104
 * Absolute value of elements in the array and x will not exceed 104
 */
public class FindKClosestElements {
    // Onlgn
    static class UsingSorting {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> result = new LinkedList<>();
            Pair[] pairs = new Pair[arr.length];
            for (int i = 0; i < arr.length; i++) {
                pairs[i] = new Pair(Math.abs(arr[i] - x), i);
            }
            Arrays.sort(pairs);
            for (int i = 0; i < k; i++) {
                result.add(arr[pairs[i].idx]);
            }
            Collections.sort(result);
            return result;
        }

        static class Pair implements Comparable<Pair> {
            int diff;
            int idx;

            public Pair(int diff, int idx) {
                this.diff = diff;
                this.idx = idx;
            }

            @Override
            public int compareTo(Pair o) {
                int currDiff = this.diff - o.diff;
                return currDiff != 0 ? currDiff : Integer.compare(this.idx, o.idx);
            }
        }
    }

    // O(log n) solution
    static class UsingBinarySearch {
        public List<Integer> findClosestElements(int[] A, int k, int x) {
            int left = 0, right = A.length - k;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (x - A[mid] > A[mid + k] - x) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return Arrays.stream(A, left, left + k).boxed().collect(Collectors.toList());
        }
    }

}
