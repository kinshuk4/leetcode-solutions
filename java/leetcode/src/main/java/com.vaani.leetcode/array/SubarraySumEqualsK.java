package com.vaani.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**https://leetcode.com/problems/subarray-sum-equals-k/
 * Given an array of integers and an integer k, you
 * need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 *
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * <p>Example 1: Input:nums = [1,1,1], k = 2 Output: 2 Note: The length of the array is in range [1,
 * 20,000]. The range of numbers in the array is [-1000, 1000] and the range of the integer k is
 * [-1e7, 1e7].
 *
 * <p>Solution: O(n) Maintain a hash-map of prefix sum and its count and check for range sum for
 * each element.
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) throws Exception {
        int[] A = {1, 2, 1, -2, 3, -1, -1};
        System.out.println(new SubarraySumEqualsK().subarraySum2(A, 2));
    }

    // 1 iteration
    public int subarraySum1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int result = 0;
        for (int i : nums) {
            sum += i;
            if (sum == k){
                result += 1;
            }
            result += map.getOrDefault(sum-k, 0); // return 0 if key is not here

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return result;
    }

    // 2 iterations
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int i : nums) {
            sum += i;
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        sum = 0;
        int result = 0;
        for (int i : nums) {
            int key = sum + k;
            if (map.containsKey(key)) {
                int count = map.get(key);
                result += count;
            }
            sum += i;

            if (map.containsKey(sum)) {
                int count = map.get(sum);
                if (count - 1 > 0) {
                    map.put(sum, count - 1);
                } else {
                    map.remove(sum);
                }
            }
        }
        return result;
    }
}
