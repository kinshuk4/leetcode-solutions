package com.vaani.leetcode.array;

import com.vaani.dsa.ds.utils.ArrayUtils;
import org.junit.Assert;

/**
 * https://leetcode.com/problems/third-maximum-number/
 * Given a non-empty array of integers, return the
 * third maximum number in this array. If it does not exist, return the maximum number. The time
 * complexity must be in O(n).
 *
 * <p>Example 1: Input: [3, 2, 1]
 *
 * <p>Output: 1
 *
 * <p>Explanation: The third maximum is 1. Example 2: Input: [1, 2]
 *
 * <p>Output: 2
 *
 * <p>Explanation: The third maximum does not exist, so the maximum (2) is returned instead. Example
 * 3: Input: [2, 2, 3, 1]
 *
 * <p>Output: 1
 *
 * <p>Explanation: Note that the third maximum here means the third maximum distinct number. Both
 * numbers with value 2 are both considered as second maximum.
 */
public class ThirdMaximumNumber {
    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        int[] a = {1, 2};
        ThirdMaximumNumber underTest = new ThirdMaximumNumber();
        Assert.assertEquals(2, underTest.thirdMax(a));

        a = new int[]{3, 2, 1};
        Assert.assertEquals(1, underTest.thirdMax(a));
        a = new int[]{2, 2, 3, 1};
        Assert.assertEquals(1, underTest.thirdMax(a));

        a = new int[]{1, 2, 2, 5, 3, 5};
        Assert.assertEquals(2, underTest.thirdMax(a));

        a = new int[]{1, Integer.MIN_VALUE, 2};
        Assert.assertEquals(Integer.MIN_VALUE, underTest.thirdMax(a));
    }

    public int thirdMax(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        // max[0] is first max
        // have to choose long as leetcode's arrays contains Integer.MIN_VALUE as array elements
        // that confuses the code
        long[] maxArr = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > maxArr[0]) {
                maxArr[2] = maxArr[1];
                maxArr[1] = maxArr[0];
                maxArr[0] = num;
                count++;
            } else if (num == maxArr[0]) {
            } else if (num > maxArr[1]) {
                maxArr[2] = maxArr[1];
                maxArr[1] = num;
                count++;
            } else if (num == maxArr[1]) {
            } else if (num >= maxArr[2]) {
                maxArr[2] = num;
                count++;
            }
        }

        if (count < 3) {
            return (int)maxArr[0];
        }

        return (int)maxArr[2];
    }

    public int thirdMax2(int[] nums) {
        long[] max = {Long.MIN_VALUE, Long.MIN_VALUE, Long.MIN_VALUE};
        int count = 0;
        for (int num : nums) {
            for (int j = 0; j < 3; j++) {
                if (max[j] > num) continue;
                else if (max[j] == num) break;
                int k = j;
                long temp1, temp2;
                temp1 = num;
                count++;
                while (k < 3) {
                    temp2 = max[k];
                    max[k] = temp1;
                    temp1 = temp2;
                    k++;
                }
                break;
            }
        }
        System.out.println(Integer.MIN_VALUE);
        return (count >= 3) ? (int) max[2] : (int) max[0];
    }
}
