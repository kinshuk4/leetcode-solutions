package com.vaani.leetcode.array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
 * <p>
 * Return the answer in an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [8,1,2,2,3]
 * Output: [4,0,1,1,3]
 * Explanation:
 * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
 * For nums[1]=1 does not exist any smaller number than it.
 * For nums[2]=2 there exist one smaller number than it (1).
 * For nums[3]=2 there exist one smaller number than it (1).
 * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
 * Example 2:
 * <p>
 * Input: nums = [6,5,4,8]
 * Output: [2,1,0,3]
 * Example 3:
 * <p>
 * Input: nums = [7,7,7,7]
 * Output: [0,0,0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 */
public class HowManyNumbersAreSmallerThantheCurrentNumber {
    // O(n^2)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (j != i && nums[j] < nums[i]) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    static class SortingBased {
        static class Pair implements Comparable<Pair> {
            int val;
            int idx;

            public Pair(int num, int idx) {
                this.val = num;
                this.idx = idx;
            }

            @Override
            public int compareTo(Pair o) {
                return this.val - o.val;
            }
        }

        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] res = new int[nums.length];
            Pair[] pairs = new Pair[nums.length];

            for (int i = 0; i < nums.length; i++) {
                pairs[i] = new Pair(nums[i], i);
            }
            Arrays.sort(pairs);
            int count = 0;
            res[pairs[0].idx] = count;
            for (int i = 1; i < nums.length; i++) {
                // same value, not increase count
                if (pairs[i].val == pairs[i - 1].val) {
                    res[pairs[i].idx] = count;
                } else {
                    count = i;
                    res[pairs[i].idx] = count;
                }
            }
            return res;
        }
    }

    // smart
    static class MapBasedSolution {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] count = new int[101];
            int[] res = new int[nums.length];

            for (int num : nums) {
                count[num]++;
            }

            for (int i = 1; i <= 100; i++) {
                count[i] += count[i - 1];
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    res[i] = 0;
                } else {
                    res[i] = count[nums[i] - 1];
                }
            }

            return res;
        }
    }
}
