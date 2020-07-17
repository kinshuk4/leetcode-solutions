package com.vaani.leetcode.array;

import java.util.Arrays;

public class MaximumSumCircularSubarray {
    public int kadaneCircular(int[] A) {
        int totalSum = Arrays.stream(A).sum();

        int min = kadaneMin(A);
        int max = kardaneMax(A);

        //all elements in arr are negative:
        if (min == totalSum) {
            return max;
        }
        return Math.max(max, totalSum - min);
    }

    public int kadaneMin(int[] A) {
        int minSum = A[0];
        int currSum = A[0];

        for (int i = 1; i < A.length; i++) {
            currSum = Math.min(A[i], currSum + A[i]);
            minSum = Math.min(minSum, currSum);
        }
        return minSum;
    }

    public int kardaneMax(int[] A) {
        int maxSum = A[0];
        int currSum = A[0];

        for (int i = 1; i < A.length; i++) {
            currSum = Math.max(A[i], currSum + A[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }

    public int maxSubarraySumCircular(int[] A) {
        return kadaneCircular(A);
    }
}
