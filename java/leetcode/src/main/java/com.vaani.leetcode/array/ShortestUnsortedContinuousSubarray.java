package com.vaani.leetcode.array;

import java.util.*;

public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        if (nums[left] > nums[right]){
            return nums.length;
        }
        return -1;
    }
}
