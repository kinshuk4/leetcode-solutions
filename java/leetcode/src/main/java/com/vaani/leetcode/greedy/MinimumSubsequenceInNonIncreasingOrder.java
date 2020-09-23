package com.vaani.leetcode.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/
 * 1403. Minimum Subsequence in Non-Increasing Order
 * Easy
 * <p>
 * Given the array nums, obtain a subsequence of the array whose sum of elements is strictly greater than the sum of the non included elements in such subsequence.
 * <p>
 * If there are multiple solutions, return the subsequence with minimum size and if there still exist multiple solutions, return the subsequence with the maximum total sum of all its elements. A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array.
 * <p>
 * Note that the solution with the given constraints is guaranteed to be unique. Also return the answer sorted in non-increasing order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,10,9,8]
 * Output: [10,9]
 * Explanation: The subsequences [10,9] and [10,8] are minimal such that the sum of their elements is strictly greater than the sum of elements not included, however, the subsequence [10,9] has the maximum total sum of its elements.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [4,4,7,6,7]
 * Output: [7,7,6]
 * Explanation: The subsequence [7,7] has the sum of its elements equal to 14 which is not strictly greater than the sum of elements not included (14 = 4 + 4 + 6). Therefore, the subsequence [7,6,7] is the minimal satisfying the conditions. Note the subsequence has to returned in non-decreasing order.
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [6]
 * Output: [6]
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 500
 * 1 <= nums[i] <= 100
 */
public class MinimumSubsequenceInNonIncreasingOrder {
    static class UsingMaxHeap {
        public List<Integer> minSubsequence(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            var pq = new PriorityQueue<Integer>(Collections.reverseOrder());
            int total = 0;
            for (var n : nums) {
                pq.offer(n);
                total += n;
            }

            int subSum = 0;
            while (subSum <= total / 2) {
                ans.add(pq.peek());
                subSum += pq.poll();
            }
            return ans;
        }
    }
}
