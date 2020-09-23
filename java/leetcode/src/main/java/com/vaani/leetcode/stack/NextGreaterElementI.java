package com.vaani.leetcode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-i/
 * <p>
 * 496. Next Greater Element I
 * Easy
 * <p>
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 * <p>
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * Output: [-1,3,-1]
 * Explanation:
 * For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 * For number 1 in the first array, the next greater number for it in the second array is 3.
 * For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 * <p>
 * Example 2:
 * <p>
 * Input: nums1 = [2,4], nums2 = [1,2,3,4].
 * Output: [3,-1]
 * Explanation:
 * For number 2 in the first array, the next greater number for it in the second array is 3.
 * For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 * <p>
 * Note:
 * <p>
 * All elements in nums1 and nums2 are unique.
 * The length of both nums1 and nums2 would not exceed 1000.
 */
public class NextGreaterElementI {
    public static void main(String[] args) throws Exception {
        int[] A = {4, 1, 2};
        int[] B = {1, 3, 4, 2};
        int[] result = new NextGreaterElementI.UsingBruteForce().nextGreaterElement(A, B);
        System.out.println(Arrays.toString(result));
    }

    static class UsingBruteForce {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                int n = nums1[i];
                boolean found = false;
                int nF = 0;
                for (int k : nums2) {
                    if (k == n) {
                        found = true;
                    }
                    if (found) {
                        if (k > n) {
                            nF = k;
                            break;
                        }
                    }
                }
                if (found) {
                    result[i] = nF;
                } else {
                    result[i] = -1;
                }
            }
            return result;
        }
    }

    static class UsingMapAndStack {
        // find nums1 next greatest as per nums2
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
            Stack<Integer> stack = new Stack<>();
            for (int num : nums2) {
                while (!stack.isEmpty() && stack.peek() < num) {
                    map.put(stack.pop(), num);
                }

                stack.push(num);
            }

            int[] result = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                result[i] = map.getOrDefault(nums1[i], -1);
            }
            return result;
        }
    }


}
