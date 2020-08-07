package com.vaani.leetcode.array;

import static java.lang.Math.abs;

/**
 * https://leetcode.com/problems/count-good-triplets/
 * 1534. Count Good Triplets
 * Easy
 * <p>
 * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
 * <p>
 * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
 * <p>
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * <p>
 * Where |x| denotes the absolute value of x.
 * <p>
 * Return the number of good triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * Output: 4
 * Explanation: There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * Output: 0
 * Explanation: No triplet satisfies all conditions.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 */
public class CountGoodTriplets {

    static class UsingBruteForce {
        public int countGoodTriplets(int[] arr, int a, int b, int c) {
            int n = arr.length;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (isGoodTriplet(arr[i], arr[j], arr[k], a, b, c)) {
                            ans++;
                        }
                    }
                }
            }
            return ans;
        }

        boolean isGoodTriplet(int first, int second, int third, int a, int b, int c) {
            return abs(first - second) <= a && abs(second - third) <= b && abs(first - third) <= c;
        }
    }

    // TODO write optimized code

}
