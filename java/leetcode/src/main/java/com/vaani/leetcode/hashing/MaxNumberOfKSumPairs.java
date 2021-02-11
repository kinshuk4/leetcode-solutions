package com.vaani.leetcode.hashing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 * 1679. Max Number of K-Sum Pairs
 * Medium
 * You are given an integer array nums and an integer k.
 * <p>
 * In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
 * <p>
 * Return the maximum number of operations you can perform on the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4], k = 5
 * Output: 2
 * Explanation: Starting with nums = [1,2,3,4]:
 * - Remove numbers 1 and 4, then nums = [2,3]
 * - Remove numbers 2 and 3, then nums = []
 * There are no more pairs that sum up to 5, hence a total of 2 operations.
 * Example 2:
 * <p>
 * Input: nums = [3,1,3,4,3], k = 6
 * Output: 1
 * Explanation: Starting with nums = [3,1,3,4,3]:
 * - Remove the first two 3's, then nums = [1,4,3]
 * There are no more pairs that sum up to 6, hence a total of 1 operation.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 * 1 <= k <= 109
 */
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Map<Integer, Long> freqMap = Arrays.stream(nums).boxed().collect(
                Collectors.groupingBy(
                        Function.identity(),
                        HashMap::new, // can be skipped
                        Collectors.counting()
                )
        );

        int ans = 0;

        for (int num : nums) {
            if (freqMap.get(num) >= 1 && freqMap.containsKey(k - num) && freqMap.get(k - num) >= 1) {
                freqMap.put(num, freqMap.get(num) - 1);
                if (freqMap.get(k - num) < 1) {
                    continue;
                }
                freqMap.put(k - num, freqMap.get(k - num) - 1);
                ans++;
            }
        }
        return ans;
    }
}
