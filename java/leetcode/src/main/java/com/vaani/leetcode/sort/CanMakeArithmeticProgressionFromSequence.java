package com.vaani.leetcode.sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/can-make-arithmetic-progression-from-sequence/
 * <p>
 * 1502. Can Make Arithmetic Progression From Sequence
 * Easy
 * Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
 * <p>
 * Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,5,1]
 * Output: true
 * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
 * Example 2:
 * <p>
 * Input: arr = [1,2,4]
 * Output: false
 * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= arr.length <= 1000
 * -10^6 <= arr[i] <= 10^6
 */
public class CanMakeArithmeticProgressionFromSequence {
    static class UsingSort {
        public boolean canMakeArithmeticProgression(int[] arr) {
            Arrays.sort(arr);
            int d = arr[1] - arr[0];
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i + 1] - arr[i] != d) {
                    return false;
                }
            }
            return true;
        }
    }

    static class UsingAPProperties {
        public boolean canMakeArithmeticProgression(int[] arr) {
            int n = arr.length;
            int min = Integer.MAX_VALUE; // first term min of array
            int max = Integer.MIN_VALUE; // nth term max of array
            Set<Integer> set = new HashSet<>();

            for (int num : arr) {
                min = Math.min(num, min);
                max = Math.max(num, max);
                boolean isAdded = set.add(num);
//                if (!isAdded) { // duplicates are allowed to be part of AP as per leetcode
//                    return false; // if element already exists, it breaks AP
//                }
            }

            int a = min, last = max;

            int d = last - a; //(n - 1) times common difference of AP
            if (d % (n - 1) != 0) {// check Tn = a + (n - 1)d or not
                return false;
            }

            d /= n - 1; // common difference of AP
            int i = 0;
            while (i < n) {
                if (!set.contains(min)) {
                    return false;
                }
                min += d;
                i++;
            }

            return true;
        }
    }
}
