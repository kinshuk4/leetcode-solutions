package com.vaani.leetcode.stack;

import java.util.*;

/**
 * 1673. Find the Most Competitive Subsequence
 * Medium
 * <p>
 * Given an integer array nums and a positive integer k, return the most competitive subsequence of nums of size k.
 * <p>
 * An array's subsequence is a resulting sequence obtained by erasing some (possibly zero) elements from the array.
 * <p>
 * We define that a subsequence a is more competitive than a subsequence b (of the same length) if in the first position where a and b differ, subsequence a has a number less than the corresponding number in b. For example, [1,3,4] is more competitive than [1,3,5] because the first position they differ is at the final number, and 4 is less than 5.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,5,2,6], k = 2
 * Output: [2,6]
 * Explanation: Among the set of every possible subsequence: {[3,5], [3,2], [3,6], [5,2], [5,6], [2,6]}, [2,6] is the most competitive.
 * Example 2:
 * <p>
 * Input: nums = [2,4,3,3,5,4,9,6], k = 4
 * Output: [2,3,3,4]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 */
public class FindTheMostCompetitiveSubsequence {
    public int[] mostCompetitive(int[] nums, final int k) {
        int noOfNumNotInSeq = nums.length - k;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : nums) {
            while (!deque.isEmpty() && noOfNumNotInSeq > 0 && deque.peek() > num) {
                deque.pop();
                noOfNumNotInSeq--;
            }

            deque.push(num);
        }

        while (deque.size() > k) {
            deque.pop();
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = deque.pop();
        }
        return ans;

    }
}
