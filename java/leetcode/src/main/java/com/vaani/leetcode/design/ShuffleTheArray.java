package com.vaani.leetcode.design;

import org.apache.commons.lang3.NotImplementedException;

/**
 * https://leetcode.com/problems/shuffle-the-array/
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 * <p>
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,1,3,4,7], n = 3
 * Output: [2,3,5,4,1,7]
 * Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,3,4,4,3,2,1], n = 4
 * Output: [1,4,2,3,3,2,4,1]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1,1,2,2], n = 2
 * Output: [1,2,1,2]
 */
public class ShuffleTheArray {
    static class NotInPlace {
        public int[] shuffle(int[] nums, int n) {
            int num = nums.length;
            int[] a = new int[num];
            int index = 0;
            for (int i = 0; i < n; i++) {
                a[index++] = nums[i];
                a[index++] = nums[i + n];
            }
            return a;
        }

        public int[] shuffle2(int[] nums, int n) {
            int[] res = new int[2 * n];
            for (int i = 0; i < nums.length; i++)
                res[i] = i % 2 == 0 ? nums[i / 2] : nums[n + i / 2];
            return res;
        }
    }

    public int[] shuffle(int[] nums, int n) {

        throw new NotImplementedException("Not yet implemetned");
    }
}
