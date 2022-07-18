package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/longest-turbulent-subarray/
 * 978. Longest Turbulent Subarray
 * Medium
 * <p>
 * Given an integer array arr, return the length of a maximum size turbulent subarray of arr.
 * <p>
 * A subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.
 * <p>
 * More formally, a subarray [arr[i], arr[i + 1], ..., arr[j]] of arr is said to be turbulent if and only if:
 * <p>
 * For i <= k < j:
 * arr[k] > arr[k + 1] when k is odd, and
 * arr[k] < arr[k + 1] when k is even.
 * Or, for i <= k < j:
 * arr[k] > arr[k + 1] when k is even, and
 * arr[k] < arr[k + 1] when k is odd.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [9,4,2,10,7,8,8,1,9]
 * Output: 5
 * Explanation: arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * Example 2:
 * <p>
 * Input: arr = [4,8,12,16]
 * Output: 2
 * Example 3:
 * <p>
 * Input: arr = [100]
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 4 * 10^4
 * 0 <= arr[i] <= 10^9
 */
public class LongestTurbulentSubarray {
    public int maxTurbulenceSize(int[] arr) {
        if (arr.length == 1) {
            return 1;
        }

        // init sign after first comparison (-1 , 1 , 0 => smaller, larger, equal)
        int sign = 0;
        if (arr[0] > arr[1]) {
            sign = 1;
        } else if (arr[0] < arr[1]) {
            sign = -1;
        }

        // turbulent subarray considers only non-equal elements
        int ans = 1;
        int cnt;
        if (sign == 0) {
            cnt = 0;
        } else {
            cnt = 2;
            ans = cnt;
        }

        for (int i = 1; i < arr.length - 1; i++) {
            int tempSign = 0; // default value assumes arr[i] == arr[i+1]
            if (arr[i] > arr[i + 1]) {
                tempSign = 1;
            } else if (arr[i] < arr[i + 1]) {
                tempSign = -1;
            }

            if (tempSign == 0) {
                cnt = 1;
                continue;
            }

            if (sign == tempSign) {
                cnt = 2;
            } else {
                cnt++;
                sign = tempSign;
            }
            ans = Math.max(cnt, ans);
        }

        return ans;

    }
}
