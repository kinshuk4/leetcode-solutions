package com.vaani.leetcode.binary_search;

import org.junit.Assert;

/**
 * https://leetcode.com/problems/find-peak-element/
 * 162. Find Peak Element
 * Medium
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.¬
 * <p>
 * Follow up: Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {
    public static void main(String[] args) throws Exception {
        int[] nums = {3, 4, 3, 2, 1};
        System.out.println(new Iterative1().findPeakElement(nums));
        int[] nums3 = {4, 3, 2, 1};
        int[] nums2 = {1, 2, 3, 4};
        Iterative1 test = new Iterative1();
        Assert.assertEquals(0, test.findPeakElement(nums3)); // as left side is -∞
        Assert.assertEquals(3, test.findPeakElement(nums2)); // as right side -∞
    }


    static class Iterative1 {
        /**
         * <p>Solution: O(log N) check if the first or the last element is the peak element, if yes then
         * return this index. Else binary search for the answer - check mid element if this is a peak
         * element return this index, else if the left element is greater than current element search left
         * else search right.
         */
        public int findPeakElement(int[] nums) {
            if (nums.length == 1) return 0;
            if (nums[0] > nums[1]) return 0;
            else if (nums[nums.length - 1] > nums[nums.length - 2]) return nums.length - 1;

            int lo = 0, hi = nums.length - 1;
            int ans = 0;
            while (lo <= hi) {
                int m = lo + (hi - lo) / 2;
                if (m - 1 >= 0 && m + 1 < nums.length) {
                    if (nums[m] > nums[m - 1] && nums[m] > nums[m + 1]) {
                        return m;
                    }
                }
                if (m - 1 >= 0 && nums[m - 1] > nums[m]) { // search left
                    hi = m - 1;
                } else {
                    ans = lo; // mark this as the answer and search right
                    lo = m + 1;
                }
            }
            return ans;
        }

    }

    static class Iterative2 {
        // more compact
        public int findPeakElement(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            int left = 0;
            int right = nums.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < nums[mid + 1]) {
                    left = mid + 1; //we have peak on the right side as elements are increasing
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }


    static class Recursive1 {
        /**
         * Binary Search: O(logN)
         * <p>
         * http://www.geeksforgeeks.org/find-a-peak-in-a-given-array/
         */
        public int findPeakElement(int[] nums) {
            return findPeakElement(nums, 0, nums.length - 1);
        }

        public int findPeakElement(int[] nums, int start, int end) {

            int mid = start + (end - start) / 2;

            if ((mid == 0 || nums[mid - 1] < nums[mid])
                    && (mid == nums.length - 1 || nums[mid + 1] < nums[mid])) {
                return mid;
            }

            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return findPeakElement(nums, start, mid - 1);
            } else {
                return findPeakElement(nums, mid + 1, end);
            }
        }
    }

    // submitted
    static class Recursive2 {
        public int findPeakElement2(int[] nums) {
            return helper2(nums, 0, nums.length - 1);
        }

        public int helper2(int[] nums, int start, int end) {
            if (start == end) {
                return start;
            } else if (start + 1 == end) {
                if (nums[start] > nums[end]) return start;
                return end;
            } else {
                int mid = start + (end - start) / 2;

                if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                    return mid;
                } else if (nums[mid - 1] > nums[mid] && nums[mid] > nums[mid + 1]) { // increasing order to the right, search on left
                    return helper2(nums, start, mid - 1);
                } else {
                    return helper2(nums, mid + 1, end);
                }
            }
        }
    }
}
