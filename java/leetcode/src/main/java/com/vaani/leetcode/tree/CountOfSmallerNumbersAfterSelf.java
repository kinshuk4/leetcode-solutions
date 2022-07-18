package com.vaani.leetcode.tree;

import org.junit.Assert;

import java.util.*;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * <p>
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property
 * where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example:
 * <p>
 * Input: [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * <p>
 * Companies: Gogole, Amazon, Apple, Facebook, Adobe
 * <p>
 * Related Topics: Binary Search, Divide and Conquer, Sort, Binary Indexed Tree, Segment Tree
 * <p>
 * Similar Questions: (H) Count of Range Sum, (M) Queue Reconstruction by Height, (H) Reverse Pairs
 */
public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 1};
        CountOfSmallerNumbersAfterSelf.UsingBinaryTreeWithCount underTest = new CountOfSmallerNumbersAfterSelf.UsingBinaryTreeWithCount();
        Assert.assertEquals(List.of(2, 1, 1, 0), underTest.countSmaller(nums));
    }

    static class UsingBinaryTreeWithCount {
        // https://www.youtube.com/watch?v=buDoT9ESw1c
        public List<Integer> countSmaller(int[] nums) {
            Integer[] result = new Integer[nums.length];
            BinaryTreeNodeWithCount root = null;
            for (int i = nums.length - 1; i >= 0; i--) {
                root = insert(nums[i], root, result, i, 0);
            }
            return Arrays.asList(result);
        }

        static class BinaryTreeNodeWithCount {
            BinaryTreeNodeWithCount left, right;
            int val, sum, dup = 1;

            public BinaryTreeNodeWithCount(int v, int s) {
                val = v;
                sum = s;
            }
        }

        private BinaryTreeNodeWithCount insert(int num, BinaryTreeNodeWithCount node, Integer[] result, int i, int preSum) {
            if (node == null) {
                node = new BinaryTreeNodeWithCount(num, 0);
                result[i] = preSum;
            } else if (node.val == num) {
                node.dup++;
                result[i] = preSum + node.sum;
            } else if (node.val > num) {
                node.sum++;
                node.left = insert(num, node.left, result, i, preSum);
            } else {
                node.right = insert(num, node.right, result, i, preSum + node.dup + node.sum);
            }
            return node;
        }
    }

    static class UsingBinarySearch {
        private int search(List<Integer> sorted, int key, int left, int right) {
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (sorted.get(mid) == key && (mid - 1 < 0 || sorted.get(mid - 1) != key)) {
                    return mid;
                } else if (sorted.get(mid) >= key) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return Integer.MAX_VALUE;

        }

        public List<Integer> countSmaller(int[] nums) {
            List<Integer> ans = new ArrayList<>();
            List<Integer> sorted = new ArrayList<>();
            for (int num : nums) {
                sorted.add(num);
            }
            Collections.sort(sorted);

            for (int i : nums) {
                int idx = search(sorted, i, 0, sorted.size() - 1);
                sorted.remove(idx);
                ans.add(idx);
            }

            return ans;
        }

    }
}