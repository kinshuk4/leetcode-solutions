package com.vaani.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 * <p>
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 * <p>
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int N = nums.length;
        int n = N - 1;
        int expectedSum = n * (n + 1) / 2;

        int actualSum = Arrays.stream(nums).sum();

        return actualSum - expectedSum;
    }
}
