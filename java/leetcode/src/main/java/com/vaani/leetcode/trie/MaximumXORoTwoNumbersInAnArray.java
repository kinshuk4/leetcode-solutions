package com.vaani.leetcode.trie;

import java.util.*;
/**
 * https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/
 * 421. Maximum XOR of Two Numbers in an Array
 * Medium
 * <p>
 * Given a non-empty array of numbers, a0, a1, a2, … , an-1, where 0 ≤ ai < 2^31.
 * <p>
 * Find the maximum result of ai XOR aj, where 0 ≤ i, j < n.
 * <p>
 * Could you do this in O(n) runtime?
 * <p>
 * Example:
 * <p>
 * Input: [3, 10, 5, 25, 2, 8]
 * <p>
 * Output: 28
 * <p>
 * Explanation: The maximum result is 5 ^ 25 = 28.
 */

public class MaximumXORoTwoNumbersInAnArray {
    // submitted
    static class UsingBruteForce {
        public int findMaximumXOR(int[] nums) {
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (i != j) {
                        result = Math.max(result, (nums[i] ^ nums[j]));
                    }
                }
            }
            return result;
        }
    }

    static class UsingHashSetAndBitManipulation {
        public int findMaximumXOR(int[] nums) {
            int max = 0, mask = 0;
            for(int i = 31; i >= 0; i--){
                mask = mask | (1 << i);
                Set<Integer> set = new HashSet<>();
                for(int num : nums){
                    set.add(num & mask);
                }
                int tmp = max | (1 << i);
                for(int prefix : set){
                    if(set.contains(tmp ^ prefix)) {
                        max = tmp;
                        break;
                    }
                }
            }
            return max;
        }
    }

    static class UsingTrie {
        static class Trie {
            Trie[] children;
            public Trie() {
                children = new Trie[2];
            }
        }

        public int findMaximumXOR(int[] nums) {
            if(nums == null || nums.length == 0) {
                return 0;
            }
            // Init Trie.
            Trie root = new Trie();
            for(int num: nums) {
                Trie curNode = root;
                for(int i = 31; i >= 0; i --) {
                    int curBit = (num >>> i) & 1;
                    if(curNode.children[curBit] == null) {
                        curNode.children[curBit] = new Trie();
                    }
                    curNode = curNode.children[curBit];
                }
            }
            int max = Integer.MIN_VALUE;
            for(int num: nums) {
                Trie curNode = root;
                int curSum = 0;
                for(int i = 31; i >= 0; i --) {
                    int curBit = (num >>> i) & 1;
                    if(curNode.children[curBit ^ 1] != null) {
                        curSum += (1 << i);
                        curNode = curNode.children[curBit ^ 1];
                    }else {
                        curNode = curNode.children[curBit];
                    }
                }
                max = Math.max(curSum, max);
            }
            return max;
        }
    }


    public static void main(String[] args) {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println(new UsingBruteForce().findMaximumXOR(nums));
    }
}
