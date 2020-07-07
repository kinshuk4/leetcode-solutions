package com.vaani.leetcode.array;

import java.util.*;

// https://leetcode.com/problems/contains-duplicate/
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
