package com.vaani.leetcode.binary_search;

import java.util.Arrays;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 * Medium
 * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
 * <p>
 * Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 * <p>
 * It is guaranteed that there will be an answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,5,9], threshold = 6
 * Output: 5
 * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
 * If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
 * Example 2:
 * <p>
 * Input: nums = [2,3,5,7,11], threshold = 11
 * Output: 3
 * Example 3:
 * <p>
 * Input: nums = [19], threshold = 5
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 10^6
 * nums.length <= threshold <= 10^6
 */
public class FindTheSmallestDivisorGivenAThreshold {
    public int smallestDivisor(int[] nums, int threshold) {
        int l = 1;
        int r = Arrays.stream(nums).max().getAsInt();
        while (l < r) {
            int m = l + (r - l) / 2;
            if (isLessThan(nums, threshold, m)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public boolean isLessThan(int[] nums, int threshold, int middle) {
        int sum = 0;
        for (int num : nums) {
            sum += (num - 1) / middle + 1;
        }
        return sum <= threshold;
    }

}
