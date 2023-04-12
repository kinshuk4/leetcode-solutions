package com.vaani.leetcode.array;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class MinimizeMaximumOfArray {
    public static void main(String[] args) {
        MinimizeMaximumOfArray underTest = new MinimizeMaximumOfArray();
//        assertEquals(5, underTest.minimizeArrayValue(new int[]{3, 7 , 1, 6}));
        assertEquals(8, underTest.minimizeArrayValue(new int[]{6,9,3,8,14}));
    }
    public int minimizeArrayValue1(int[] nums) {
        TreeMap<Integer, TreeSet<Integer>> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new TreeSet<>());
            map.get(nums[i]).add(i);
        }
//        Integer prev = null;
        while (true) {
            int curr = map.lastKey();
            int idx = map.get(curr).last();
            // no operation can be performed
            if (idx == 0) {
                return curr;
            }
            int adj = idx - 1;
            int adjKey = nums[adj];

            while (adjKey == curr && adj >= 1) {
                idx = adj;
                adj = idx - 1;
                curr = adjKey;
                adjKey = nums[adj];

            }

            int adjKeyIncr = adjKey + 1;

            if (adjKeyIncr > curr) {
                return curr;
            }

            map.get(curr).remove(idx);
            if(map.get(curr).size() == 0) {
                map.remove(curr);
            }
            int decCurr = curr - 1;
            map.putIfAbsent(decCurr, new TreeSet<>());
            map.get(decCurr).add(idx);
            nums[idx] = decCurr;

            map.get(adjKey).remove(adj);
            if(map.get(adjKey).size() == 0) {
                map.remove(adjKey);
            }
            map.putIfAbsent(adjKeyIncr, new TreeSet<>());
            map.get(adjKeyIncr).add(adj);
            nums[adj] = adjKeyIncr;

        }

//        return map.lastKey();


    }

    public int minimizeArrayValue(int[] nums) {
        int lo = 0, hi = max(nums), ans = hi;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(check(nums, mid)) {
                ans = mid;
                hi = mid - 1;
            }
            else lo = mid + 1;
        }
        return ans;
    }


    public boolean check(int[] nums, int mid) {
        if(nums[0] > mid) return false;
        long pre = nums[0];
        for(int i = 1; i < nums.length; i ++) {
            long scope = mid - pre;
            if(nums[i] - scope > mid) {
                return false;
            }
            pre = nums[i] - scope;
        }
        return true;
    }

    public int max(int[] arr) {
        int ans = 0;
        for(int i : arr) {
            ans = Math.max(i, ans);
        }
        return ans;
    }
}
