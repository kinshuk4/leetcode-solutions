package com.vaani.leetcode.array;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/contains-duplicate-ii/
public class ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (map.containsKey(curr)) {
                int j = map.get(curr);
                if ((i - j) <= k) {
                    return true;
                }
            }

            map.put(curr, i);
        }
        return false;
    }
}
