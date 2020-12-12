package com.vaani.leetcode.dp;

/**
 * 1306. Jump Game III
 * Medium
 * Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
 * <p>
 * Notice that you can not jump outside of the array at any time.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 5
 * Output: true
 * Explanation:
 * All possible ways to reach at index 3 with value 0 are:
 * index 5 -> index 4 -> index 1 -> index 3
 * index 5 -> index 6 -> index 4 -> index 1 -> index 3
 * Example 2:
 * <p>
 * Input: arr = [4,2,3,0,3,1,2], start = 0
 * Output: true
 * Explanation:
 * One possible way to reach at index 3 with value 0 is:
 * index 0 -> index 4 -> index 1 -> index 3
 * Example 3:
 * <p>
 * Input: arr = [3,0,2,1,2], start = 2
 * Output: false
 * Explanation: There is no way to reach at index 1 with value 0.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] < arr.length
 * 0 <= start < arr.length
 */
public class JumpGame3 {
    static class UsingDFS {
        public boolean canReach(int[] arr, int start) {
            return canReach(arr, start, new int[arr.length]);
        }

        public boolean canReach(int[] arr, int start, int[] visited) {
            // it shouldnt go out of bound
            if (start < 0 || start >= arr.length) {
                return false;
            }
            if (visited[start] != 0) {
                return false;
            }
            if (arr[start] == 0) {
                return true;
            }
            visited[start] = 1;
            if (canReach(arr, start + arr[start], visited) || canReach(arr, start - arr[start], visited)) {
                return true;
            }

            return false;
        }

    }

}
