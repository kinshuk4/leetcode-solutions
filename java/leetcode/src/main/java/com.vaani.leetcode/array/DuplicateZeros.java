package com.vaani.leetcode.array;

/**
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 * <p>
 * Note that elements beyond the length of the original array are not written.
 * <p>
 * Do the above modifications to the input array in place, do not return anything from your function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= arr.length <= 10000
 * 0 <= arr[i] <= 9
 */
public class DuplicateZeros {
    public static void main(String[] args) {
        int[] arr = {1,0,2,3,0,4,5,0};
        duplicateZeros(arr);
    }
    public static void duplicateZeros(int[] arr) {
        // consider arr = [1,0,2,3,0,4,5,0]
        int countZeros = 0;
        int len = arr.length - 1;
        for (int left = 0; left <= len - countZeros; left++) {

            // Count the zeros
            if (arr[left] == 0) {

                // Edge case: This zero can't be duplicated. We have no more space,
                // as left is pointing to the last element which could be included
                if (left == len - countZeros) {
                    // For this zero we just copy it without duplication.
                    arr[len] = 0;
                    len -= 1;
                    break;
                }
                countZeros++;
            }
        }

        // arr = [1,0,2,3,0,4,5,0]

        // Start backwards from the last element which would be part of new array.
        int last = len - countZeros; // count Zeroes = 2, len = 5

        // Start from right to left now, Copy zero twice, and non zero once.
        for (int i = last; i >= 0; i--) {
            if (arr[i] == 0) {
                arr[i + countZeros] = 0;
                countZeros--;
                arr[i + countZeros] = 0;
            } else {
                arr[i + countZeros] = arr[i];
            }
        }


    }
}
