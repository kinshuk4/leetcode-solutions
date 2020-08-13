package com.vaani.leetcode.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/set-mismatch/
 * 645. Set Mismatch
 * Easy
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * <p>
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
 * <p>
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Note:
 * The given array size will in the range [2, 10000].
 * The given array's numbers won't have any order.
 */
public class SetMismatch {
    static class UsingSorting {
        public int[] findErrorNums(int[] nums) {
            Arrays.sort(nums);
            int[] res = new int[2];

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    res[0] = nums[i];
                }
                if (nums[i] - nums[i - 1] == 2) {
                    res[1] = nums[i - 1] + 1;
                }
            }
            if (res[1] == 0) {
                if (nums[nums.length - 1] == nums.length)
                    res[1] = 1;
                else
                    res[1] = nums.length;
            }
            return res;
        }
    }

    // submitted
    static class UsingSetAndAP {
        public int[] findErrorNums(int[] nums) {
            Set<Integer> set = new HashSet<>();
            int duplicate = 0, n = nums.length;
            long expectedSum = (n * (n + 1)) / 2;
            for (int i : nums) {
                if (set.contains(i)) {
                    duplicate = i;
                }
                expectedSum -= i;
                set.add(i);
            }
            return new int[]{duplicate, (int) expectedSum + duplicate};
        }
    }

    static class O1SpaceUsingNumberRange {
        /**
         * Because numbers are from 1 to n, after we put number i to index i - 1 there's only 1 mis-matching which is the answer. Time complexity O(n). Space complexity O(1).
         */
        public int[] findErrorNums(int[] nums) {
            int[] result = new int[2];

            for (int i = 0; i < nums.length; i++) {
                while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] - 1 != i) {
                    result[0] = nums[i];
                    result[1] = i + 1;
                    break;
                }
            }

            return result;
        }
    }


}
