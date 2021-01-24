package com.vaani.leetcode.array;

public class CheckIfAll1sAreAtLeastLengthKPlacesAway {
    public boolean kLengthApart(int[] nums, int k) {
        if(k == 0){
            return true;
        }
        int prevIdx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (prevIdx != -1 && i - prevIdx - 1 < k) {
                    return false;
                }
                prevIdx = i;
            }
        }
        return true;
    }
}
