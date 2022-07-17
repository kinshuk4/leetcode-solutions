package com.vaani.leetcode.array;

import java.util.*;

public class KSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0, len);
    }
    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index, int len) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(index >= len) {
            return res;
        }
        if(k == 2) {
            int i = index, j = len - 1;
            twoSum(nums, target, i, j, res);
        } else{
            for (int i = index; i < len - k + 1; i++) {
                //use current number to reduce ksum into k-1sum
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1, len);
                if(temp != null){
                    //add previous results
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len-1 && nums[i] == nums[i+1]) {
                    //skip duplicated numbers
                    i++;
                }
            }
        }
        return res;
    }

    private void twoSum(int[] nums, int target, int i, int j, ArrayList<List<Integer>> res) {
        while(i < j) {
            //find a pair
            if(target - nums[i] == nums[j]) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[i]);
                temp.add(target - nums[i]);
                res.add(temp);
                //skip duplication
                while(i < j && nums[i]== nums[i +1]) i++;
                while(i < j && nums[j -1]== nums[j]) j--;
                i++;
                j--;
                //move left bound
            } else if (target - nums[i] > nums[j]) {
                i++;
                //move right bound
            } else {
                j--;
            }
        }
    }


}
