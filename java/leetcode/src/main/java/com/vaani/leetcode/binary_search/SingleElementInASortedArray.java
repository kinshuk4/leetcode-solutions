package com.vaani.leetcode.binary_search;

/**
 * https://leetcode.com/problems/single-element-in-a-sorted-array/
 * You are given a sorted array consisting of only
 * integers where every element appears exactly twice, except for one element which appears exactly
 * once. Find this single element that appears only once.
 *
 * <p>Example 1:
 *
 * <p>Input: [1,1,2,3,3,4,4,8,8] Output: 2
 * <p>
 * Example 2:
 *
 * <p>Input: [3,3,7,7,10,11,11] Output: 10
 *
 * <p>Note: Your solution should run in O(log n) time and O(1) space.
 */
public class SingleElementInASortedArray {
    public static void main(String[] args) {
        int[] A = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(new SingleElementInASortedArray().singleNonDuplicate(A));
    }

    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int l = 0, h = nums.length - 1;
        while (l <= h) {
            int m = l + ((h - l) / 2);
            int N = nums[m];
            if (m + 1 >= nums.length) {
                if (nums[m - 1] != N) {
                    return N;
                }
                h = m - 1;
            } else if (m - 1 < 0) {
                if (nums[m + 1] != N) {
                    return N;
                }
                l = m + 1;
            } else {
                if (m % 2 == 0) {
                    if (nums[m + 1] != N && nums[m - 1] != N) {
                        return N;
                    } else if (nums[m + 1] != N) {
                        h = m - 1;
                    } else l = m + 1;
                } else {
                    if (nums[m + 1] != N && nums[m - 1] != N) {
                        return N;
                    } else if (nums[m - 1] != N) {
                        h = m - 1;
                    } else l = m + 1;
                }
            }
        }
        return -1;
    }


    // simpler
    public static int singleNonDuplicate2(int[] nums) {
        int start = 0, end = nums.length - 1;

        while (start < end) {
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            // Example:
            // Index: 0 1 2 3 4 5 6
            // Array: 1 1 3 3 4 8 8
            //            ^
            int mid = start + (end - start) / 2;
            if (mid % 2 == 1) {
                mid--;
            }

            // We didn't find a pair. The single element must be on the left.
            // (pipes mean start & end)
            // Example: |0 1 1 3 3 6 6|
            //               ^ ^
            // Next:    |0 1 1|3 3 6 6
            if (nums[mid] != nums[mid + 1]) {
                end = mid;
            }

            // We found a pair. The single element must be on the right.
            // Example: |1 1 3 3 5 6 6|
            //               ^ ^
            // Next:     1 1 3 3|5 6 6|
            else {
                start = mid + 2;
            }
        }

        // 'start' should always be at the beginning of a pair.
        // When 'start > end', start must be the single element.
        return nums[start];
    }

    // more better - nums is odd element array
    //https://leetcode.com/problems/single-element-in-a-sorted-array/discuss/100754/Java-Binary-Search-short-(7l)-O(log(n))-w-explanations
    public int singleNonDuplicate3(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mid % 2 == 0) {
                // mid is even
                if (nums[mid] == nums[mid + 1]) {
                    start = mid + 2;
                } else {
                    end = mid;
                }
            } else {
                // mid is odd
                if (nums[mid] == nums[mid - 1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }

        }
        return nums[start];
    }
}
