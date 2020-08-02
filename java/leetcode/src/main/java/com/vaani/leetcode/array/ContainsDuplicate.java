package com.vaani.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate/
 * 217. Contains Duplicate
 * Easy
 * <p>
 * Given an array of integers, find if the array contains any duplicates.
 * <p>
 * Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3,1]
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: [1,2,3,4]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        // Edge cases
        if (nums == null || nums.length <= 1) {
            return false;
        }

        // Use a set to store all the distinct elements
        Set<Integer> set = new HashSet<>();

        // This is really easy to understand
        for (int current : nums) {
            // If it contains it, return true right away
            if (set.contains(current)) {
                return true;
            }


            set.add(current);
        }
        return false;
    }
}
