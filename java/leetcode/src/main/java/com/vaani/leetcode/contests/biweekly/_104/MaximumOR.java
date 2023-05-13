package com.vaani.leetcode.contests.biweekly._104;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MaximumOR {
    public static void main(String[] args) {
        MaximumOR underTest = new MaximumOR();
        String[] details = {"7868190130M7522", "5303914400F9211", "9273338290F4010"};
        assertEquals(30, underTest.maximumOr(new int[]{12, 9}, 1));
    }

    public long maximumOr(int[] nums, int k) {
        int maximum = Arrays.stream(nums).max().getAsInt();
        int msb = getMostSignificantBit(maximum);
        int mask = ~((1 << msb) - 1);
        int or = Arrays.stream(nums).reduce(0, (a, b) -> a | b);
        for (int num : nums) {
            if ((num & mask) < maximum && k > 0) {
                num <<= 1;
                or |= num;
                k--;
            }
        }
        while (k > 0) {
            boolean found = false;
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                if ((num & mask) < maximum && k > 0) {
                    nums[i] <<= 1;
                    or |= nums[i];
                    found = true;
                    k--;
                }
            }
            if (!found) {
                break;
            }
            maximum = Arrays.stream(nums).max().getAsInt();
            msb = getMostSignificantBit(maximum);
            mask = ~((1 << msb) - 1);
        }
        return or + 1;

    }

    private static int getMostSignificantBit(int num) {
        return 31 - Integer.numberOfLeadingZeros(num);
    }


}
