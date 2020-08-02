package com.vaani.leetcode.two_pointers;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 209. Minimum Size Subarray Sum
 * Medium
 *
 * <p>Given an array of n positive integers and a positive integer s, find the minimal length of a
 * contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 * <p>
 * Example:
 * <p>
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) throws Exception {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(7, nums));
    }

    // O(n) solution
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0, count = 0, min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; j < nums.length; ) {
            if (nums[j] >= s) {
                return 1;
            } else {
                sum += nums[j];
                count++;
                if (sum >= s) {
                    min = Math.min(min, count);
                    //increment i and shrink the window between 2 pointers
                    while (j > i) {
                        sum -= nums[i];
                        count--;
                        i++;
                        if (sum < s) {
                            break;
                        }
                        min = Math.min(min, count);
                    }
                }
            }
            j++;
        }
        if (min == Integer.MAX_VALUE) {
            return 0;
        }
        return min;
    }
}
