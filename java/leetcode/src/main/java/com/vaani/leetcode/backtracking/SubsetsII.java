package com.vaani.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets-ii/
 * 90. Subsets II
 * Medium
 * <p>
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 * <p>
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 * <p>
 * Input: nums = [0]
 * Output: [[],[0]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class SubsetsII {

    public static void main(String[] args) throws Exception {
        int[] n = {1, 2, 3};
        List<List<Integer>> result = new SubsetsII().subsetsWithDup(n);
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>()); // empty subset
        int start = 0, newStart = 0;
        Arrays.sort(nums);
        for (int i = 0, l = nums.length; i < l; i++) {
            newStart = result.size();
            if (i == 0 || nums[i] != nums[i - 1]) {
                start = 0;
            }
            for (int j = start, resLen = result.size(); j < resLen; j++) {
                List<Integer> newList = new ArrayList<>(result.get(j));
                newList.add(nums[i]);
                result.add(newList);
            }
            start = newStart;
        }
        return result;
    }
}
