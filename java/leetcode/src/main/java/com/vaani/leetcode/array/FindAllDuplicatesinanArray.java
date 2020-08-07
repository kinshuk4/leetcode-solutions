package com.vaani.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * 442. Find All Duplicates in an Array
 * Medium
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * <p>
 * Find all the elements that appear twice in this array.
 * <p>
 * Could you do it without extra space and in O(n) runtime?
 * <p>
 * Example:
 * Input:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * Output:
 * [2,3]
 */

public class FindAllDuplicatesinanArray {

    //O(nlog(n))
    static class UsingSorting {
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> result = new ArrayList<>();

            Arrays.sort(nums);

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1])
                    result.add(nums[i]);
            }
            return result;
        }
    }

    //O(n)
    static class UsingInPlaceArrayModification {
        // We have a constraint - number can be between 1 and n inclusive
        public List<Integer> findDuplicates(int[] nums) {
            List<Integer> duplicates = new LinkedList<Integer>();
            // If a number has been seen we will set its corresponding index to negative
            // If it is already negative then we have seen it before
            for (int i = 0; i < nums.length; i++) {
                int val = Math.abs(nums[i]);
                if (nums[val - 1] < 0) {
                    duplicates.add(val);
                } else {
                    // We are setting the value at this index to be negative to imply we've seen the index itself in the array already
                    nums[val - 1] = -1 * nums[val - 1];
                }
            }
            return duplicates;
        }
    }

}
