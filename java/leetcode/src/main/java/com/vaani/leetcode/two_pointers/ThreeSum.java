package com.vaani.leetcode.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.vaani.dsa.algo.ds.array.nsum.ThreeSum.threeSum;

/**
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] nums = {
                -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1,
                2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4, -1, 0, 1, 2, -1, -4
        };
        System.out.println(threeSum(nums));
    }
}
