package com.vaani.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.reverseBetweenRange;

/**
 * https://leetcode.com/problems/pancake-sorting/
 * <p>
 * 969. Pancake Sorting
 * Medium
 * <p>
 * 535
 * <p>
 * 659
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array of integers A, We need to sort the array performing a series of pancake flips.
 * <p>
 * In one pancake flip we do the following steps:
 * <p>
 * Choose an integer k where 0 <= k < A.length.
 * Reverse the sub-array A[0...k].
 * For example, if A = [3,2,1,4] and we performed a pancake flip choosing k = 2, we reverse the sub-array [3,2,1], so A = [1,2,3,4] after the pancake flip at k = 2.
 * <p>
 * Return an array of the k-values of the pancake flips that should be performed in order to sort A. Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = [3,2,4,1]
 * Output: [4,2,4,3]
 * Explanation:
 * We perform 4 pancake flips, with k values 4, 2, 4, and 3.
 * Starting state: A = [3, 2, 4, 1]
 * After 1st flip (k = 4): A = [1, 4, 2, 3]
 * After 2nd flip (k = 2): A = [4, 1, 2, 3]
 * After 3rd flip (k = 4): A = [3, 2, 1, 4]
 * After 4th flip (k = 3): A = [1, 2, 3, 4], which is sorted.
 * Notice that we return an array of the chosen k values of the pancake flips.
 * Example 2:
 * <p>
 * Input: A = [1,2,3]
 * Output: []
 * Explanation: The input is already sorted, so there is no need to flip anything.
 * Note that other answers, such as [3, 3], would also be accepted.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i] <= A.length
 * All integers in A are unique (i.e. A is a permutation of the integers from 1 to A.length).
 * <p>
 * Simple:
 * Given an an unsorted array, sort the given array.
 * You are allowed to do only following operation on array
 * flip(arr, i): Reverse array from 0 to i
 * <p>
 * Tags: Sort
 */
class PancakeSorting {
    public static void main(String[] args) {
        int[] A = {23, 10, 20, 11, 12, 6, 7};

        PancakeSorting.UsingFlipAndMax p = new PancakeSorting.UsingFlipAndMax();
        p.pancakeSort(A);
        for (int n : A) System.out.print(n + ", ");
    }

    static class UsingFlipAndMax {
        /**
         * Find max from from start to end
         * If max is not at the end, filp it to first and flip it to end
         * Reduce array size by one
         * Stop till size reduced to 1
         */
        public void pancakeSort(int[] A) {
            if (A == null || A.length <= 1) {
                return;
            }

            for (int i = A.length; i > 1; i--) { // i is current size
                int mi = findMax(A, i);
                if (mi != i) {
                    flip(A, mi);
                    flip(A, i - 1);
                }
            }
        }

        private void flip(int[] A, int i) {
            int temp, start = 0;
            while (start < i) {
                temp = A[start];
                A[start] = A[i];
                A[i] = temp;
                start++;
                i--;
            }
        }

        private int findMax(int[] A, int size) {
            int maxIdx = 0;
            for (int i = 1; i < size; i++) {
                if (A[i] > A[maxIdx]) {
                    maxIdx = i;
                }
            }
            return maxIdx;
        }
    }

    static class UsingFlipAndMax2 {
        public List<Integer> pancakeSort(int[] A) {
            List<Integer> ans = new ArrayList<>();
            int n = A.length;
            while (n > 0) {
                int index = find(A, n);
                reverseBetweenRange(A, 0, index);
                reverseBetweenRange(A, 0, n - 1);
                ans.add(index + 1);
                ans.add(n--);
            }
            return ans;
        }

        private int find(int[] A, int target) {
            for (int i = 0; i < A.length; i++) {
                if (A[i] == target) {
                    return i;
                }
            }
            return -1;
        }

    }
}
