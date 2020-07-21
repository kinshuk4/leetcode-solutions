package com.vaani.leetcode.greedy;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
 * <p>
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * <p>
 * Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
 * <p>
 * If it cannot be done, return -1.
 * Exmaple1:
 * Input: A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
 * Output: 2
 * Explanation:
 * The first figure represents the dominoes as given by A and B: before we do any rotations.
 * If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
 * A=[2,2,2,2,2,2]
 * <p>
 * Example 2:
 * <p>
 * Input: A = [3,5,1,2,3], B = [3,6,3,3,4]
 * Output: -1
 * Explanation:
 * In this case, it is not possible to rotate the dominoes to make one row of values equal.
 */


public class MinimumDominoRotationsForEqualRow {
    /*
    Possibilities = all values in A match A[0]
    Possibilities = all values in A match B[0]
    Possibilities = all values in B match B[0]
    Possibilities = all values in B match A[0]
     */

    // https://www.youtube.com/watch?v=9Q73ScVu2GI
    public int minDominoRotations(int[] A, int[] B) {
        int minSwapsA = Math.min(numSwaps(A, B, A[0]), numSwaps(A, B, B[0]));
        int minSwapsB = Math.min(numSwaps(B, A, A[0]), numSwaps(B, A, B[0]));

        int minSwaps = Math.min(minSwapsA, minSwapsB);
        return minSwaps == Integer.MAX_VALUE ?-1:minSwaps;
    }

    private int numSwaps(int[] a, int[] b, int target) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            // if ith value in A or B don't match target
            // swapping will not help
            if(a[i] != target && b[i]!=target){
                return Integer.MAX_VALUE;
            }else if(a[i] != target){
                count++;
            }

        }
        return count;
    }
}
