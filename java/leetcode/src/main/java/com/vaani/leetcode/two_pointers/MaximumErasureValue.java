package com.vaani.leetcode.two_pointers;

import java.util.*;

/**
 * 1695. Maximum Erasure Value
 * Medium
 * <p>
 * <p>
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 * <p>
 * Return the maximum score you can get by erasing exactly one subarray.
 * <p>
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 * Example 2:
 * <p>
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 */
public class MaximumErasureValue {
    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0;
        int sum = 0;

        Set<Integer> unique = new HashSet<>();
        int left = 0;
        int right = 0;
        int length = nums.length;
        while (right < length) {
            int rightNum = nums[right];
            if (!unique.contains(rightNum)) {
                unique.add(rightNum);
                sum += rightNum;
                ans = Math.max(ans, sum);
                right++;
            } else {
                while (left < right && unique.contains(rightNum)) {
                    int leftNum = nums[left];
                    unique.remove(leftNum);
                    sum -= leftNum;
                    left++;
                }
            }
        }

        return ans;
    }
}
