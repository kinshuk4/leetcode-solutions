package com.vaani.leetcode.contests.biweekly._101;

import com.vaani.leetcode.contests.biweekly._99.SplitWithMinimumSum;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class FormSmallestNumberFromTwoDigitArrays {
    public static void main(String[] args) {
        FormSmallestNumberFromTwoDigitArrays underTest = new FormSmallestNumberFromTwoDigitArrays();
//        assertEquals(59, underTest.splitNum(4325));
    }


    public int minNumber(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        int min1 = Integer.MAX_VALUE;
        for (int num : nums1) {
            set1.add(num);
            min1 = Math.min(min1, num);
        }
        int min2 = Integer.MAX_VALUE;
        Set<Integer> common = new HashSet<>();
        for (int num : nums2) {

            if (set1.contains(num)) {
                common.add(num);
            }
            min2 = Math.min(min2, num);
        }

        int min3 = Integer.MAX_VALUE;
        for(int num: common) {
            min3 = Math.min(min3, num);
        }

        if (!common.isEmpty()) {
            return min3;
        }

        String ans = "";
        if (min1 > min2) {
            ans += min2 + "" + min1;
        } else if (min1 == min2) {
            ans += min1;
        } else {
            ans += min1 + "" + min2;
        }

        return Integer.parseInt(ans);
    }

    public int min(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }
}
