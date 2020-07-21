package com.vaani.leetcode.array;

import java.util.*;

/** https://leetcode.com/problems/number-of-good-pairs/
 * Given an array of integers nums.
 *
 * A pair (i,j) is called good if nums[i] == nums[j] and i < j.
 *
 * Return the number of good pairs.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1,1,3]
 * Output: 4
 * Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
 *
 * Example 2:
 *
 * Input: nums = [1,1,1,1]
 * Output: 6
 * Explanation: Each pair in the array are good.
 *
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 0
 */
public class NumberOfGoodPairs {
    public int numIdenticalPairs(int[] nums) {
        int result = 0;
        int[] count = new int[101];
        for (int a: nums) {
            result += count[a]++;
        }
        return result;
    }
}
