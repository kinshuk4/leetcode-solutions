package com.vaani.leetcode.array;

/**
 * https://leetcode.com/problems/largest-time-for-given-digits
 * 949. Largest Time for Given Digits
 * Easy
 * Given an array of 4 digits, return the largest 24 hour time that can be made.
 * <p>
 * The smallest 24 hour time is 00:00, and the largest is 23:59.  Starting from 00:00, a time is larger if more time has elapsed since midnight.
 * <p>
 * Return the answer as a string of length 5.  If no valid time can be made, return an empty string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,4]
 * Output: "23:41"
 * Example 2:
 * <p>
 * Input: [5,5,5,5]
 * Output: ""
 * <p>
 * <p>
 * Note:
 * <p>
 * A.length == 4
 * 0 <= A[i] <= 9
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        int[] A = {2, 0, 6, 6};
        System.out.println(new LargestTimeForGivenDigits().largestTimeFromDigits(A));
    }

    public String largestTimeFromDigits(int[] A) {
        int max = -1;
        String result = "";
        int n = A.length; // 4
        for (int i = 0; i < n; i++) {
            if (A[i] > 2) {
                continue;
            }
            for (int j = 0; j < n; j++) {
                if (j == i) {
                    continue;
                }
                if (A[i] == 2 && A[j] > 3) {
                    continue;
                }
                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (A[k] > 5) {
                        continue;
                    }
                    for (int l = 0; l < n; l++) {
                        if (l == i || l == j || l == k) {
                            continue;
                        }
                        int hours = 10 * A[i] + A[j];
                        int mins = 10 * A[k] + A[l];
                        int timeElapsed = hours * 60 + mins;
                        if (timeElapsed > max) {
                            max = timeElapsed;
                            result = String.format("%d%d:%d%d", A[i], A[j], A[k], A[l]);// faster
//                            result = A[i] + "" + A[j] + ":" + A[k] + "" + A[l]; // slower
                        }
                    }
                }
            }
        }
        return result;
    }

    // slightly easier on eye
    public String largestTimeFromDigits2(int[] A) {
        int ans = -1;

        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j == i) {
                    continue;
                }
                for (int k = 0; k < 4; ++k) {
                    if (k == i || k == j) {
                        continue;
                    }

                    int l = 6 - i - j - k;
                    // For each permutation of A[i], read out the time and
                    // record the largest legal time.
                    int hours = 10 * A[i] + A[j];
                    int mins = 10 * A[k] + A[l];
                    if (hours < 24 && mins < 60) {
                        ans = Math.max(ans, hours * 60 + mins);
                    }

                }
            }
        }

        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }
}
