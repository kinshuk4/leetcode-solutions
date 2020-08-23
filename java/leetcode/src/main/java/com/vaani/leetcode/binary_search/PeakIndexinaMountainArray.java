package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 * 852. Peak Index in a Mountain Array
 * Easy
 * <p>
 * Let's call an array A a mountain if the following properties hold:
 * <p>
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * <p>
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 * <p>
 * Example 1:
 * <p>
 * Input: [0,1,0]
 * Output: 1
 * <p>
 * Example 2:
 * <p>
 * Input: [0,2,1,0]
 * Output: 1
 * <p>
 * Note:
 * <p>
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 */
public class PeakIndexinaMountainArray {
    static class OnSolution {
        public int peakIndexInMountainArray(int[] A) {
            int i = 0;
            while (A[i] < A[i + 1]) {
                i++;
            }
            return i;
        }
    }

    static class UsingBinarySearch {
        public int peakIndexInMountainArray(int[] A) {
            int lo = 0, hi = A.length - 1;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (A[mi] < A[mi + 1]) {
                    lo = mi + 1;
                } else {
                    hi = mi;
                }
            }
            return lo;
        }
    }

}
