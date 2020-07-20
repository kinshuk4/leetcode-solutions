package com.vaani.leetcode.array;

import com.vaani.dsa.ds.utils.generic.ArrayUtils;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
 * <p>
 * You may return any answer array that satisfies this condition.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,1,2,4]
 * Output: [2,4,3,1]
 * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
class Solution {
    // two pointers
    public int[] sortArrayByParity(int[] A) {
        int n = A.length;
        int left = 0;
        int right = n - 1;

        while (left < n && right >= 0 && left < right) {
            while (left < n && A[left] % 2 == 0) {
                left++;
            }
            while (right >= 0 && A[right] % 2 != 0) {
                right--;
            }

            if (left < n && right >= 0 && left < right) {
                swap(A, left, right);
                left++;
                right--;
            }
        }
        return A;
    }
}