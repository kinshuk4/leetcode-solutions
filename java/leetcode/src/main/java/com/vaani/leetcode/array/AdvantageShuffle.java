package com.vaani.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/advantage-shuffle/
 * 870. Advantage Shuffle
 * Medium
 * <p>
 * Given two arrays A and B of equal size, the advantage of A with respect to B is the number of indices i for which A[i] > B[i].
 * <p>
 * Return any permutation of A that maximizes its advantage with respect to B.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [2,7,11,15], B = [1,10,4,11]
 * Output: [2,11,7,15]
 * Example 2:
 * <p>
 * Input: A = [12,24,8,32], B = [13,25,32,11]
 * Output: [24,32,8,12]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length = B.length <= 10000
 * 0 <= A[i] <= 10^9
 * 0 <= B[i] <= 10^9
 */
public class AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        Arrays.sort(A);

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        for (int i = 0; i < len; i++) {
            maxHeap.offer(new int[]{i, B[i]});
        }

        int slow = 0;
        int fast = len - 1;

        int[] ans = new int[len];

        while (!maxHeap.isEmpty()) {
            int[] bPoint = maxHeap.poll();
            int bIdx = bPoint[0];
            int bVal = bPoint[1];

            if (A[fast] > bVal) {
                ans[bIdx] = A[fast];
                fast--;
            } else {
                ans[bIdx] = A[slow];
                slow++;
            }

        }

        return ans;

    }
}
