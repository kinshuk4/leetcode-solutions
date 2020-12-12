package com.vaani.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 228. Summary Ranges
 * Easy
 * <p>
 * You are given a sorted unique integer array nums.
 * <p>
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 * <p>
 * Each range [a,b] in the list should be output as:
 * <p>
 * "a->b" if a != b
 * "a" if a == b
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * Example 2:
 * <p>
 * Input: nums = [0,2,3,4,6,8,9]
 * Output: ["0","2->4","6","8->9"]
 * Explanation: The ranges are:
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * Example 3:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 4:
 * <p>
 * Input: nums = [-1]
 * Output: ["-1"]
 * Example 5:
 * <p>
 * Input: nums = [0]
 * Output: ["0"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 20
 * -2^31 <= nums[i] <= 2^31 - 1
 * All the values of nums are unique.
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> rangeList = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return rangeList;
        }

        int start = nums[0];
        int end = nums[0];

        for (int i = 0; i < nums.length - 1; i++) {
            int curNum = nums[i];
            int nextNum = nums[i + 1];

            if (nextNum - curNum == 1) {
                end++;
            } else {
                rangeList.add(rangeToString(start, end));
                start = nextNum;
                end = nextNum;
            }
        }

        rangeList.add(rangeToString(start, end));
        return rangeList;
    }

    public String rangeToString(int start, int end) {
        return Integer.toString(start) + (start != end ? "->" + end : "");
    }
}
