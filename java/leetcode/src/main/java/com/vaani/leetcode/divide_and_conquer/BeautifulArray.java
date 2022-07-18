package com.vaani.leetcode.divide_and_conquer;

/**
 * 932. Beautiful Array
 * Medium
 * <p>
 * For some fixed n, an array nums is beautiful if it is a permutation of the integers 1, 2, ..., n, such that:
 * <p>
 * For every i < j, there is no k with i < k < j such that nums[k] * 2 = nums[i] + nums[j].
 * <p>
 * Given n, return any beautiful array nums.  (It is guaranteed that one exists.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: n = 5
 * Output: [3,1,2,5,4]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= n <= 1000
 */
public class BeautifulArray {
    public int[] beautifulArray(int n) {
        int[] ans = new int[n];
        if (n == 1) {
            ans[0] = 1;
            return ans;
        }
        int[] right = beautifulArray(n / 2);
        int[] left = beautifulArray((n + 1) / 2);

        for (int i = left.length; i < n; i++) {
            ans[i] = right[i - left.length] * 2;
        }
        for (int i = 0; i < left.length; i++) {
            ans[i] = left[i] * 2 - 1;
        }
        return ans;

    }
}
