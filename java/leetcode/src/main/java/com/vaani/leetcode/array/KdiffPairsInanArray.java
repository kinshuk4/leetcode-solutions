package com.vaani.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 * 28/03/2017. Given an array of integers and an integer k, you
 * need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an
 * integer pair (i, j), where i and j are both numbers in the array and their absolute difference is
 * k.
 *
 * <p>Example 1: Input: [3, 1, 4, 1, 5], k = 2 Output: 2 Explanation: There are two 2-diff pairs in
 * the array, (1, 3) and (3, 5). Although we have two 1s in the input, we should only return the
 * number of unique pairs. Example 2: Input:[1, 2, 3, 4, 5], k = 1 Output: 4 Explanation: There are
 * four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5). Example 3: Input: [1, 3, 1, 5,
 * 4], k = 0 Output: 1 Explanation: There is one 0-diff pair in the array, (1, 1). Note: The pairs
 * (i, j) and (j, i) count as the same pair. The length of the array won't exceed 10,000. All the
 * integers in the given input belong to the range: [-1e7, 1e7].
 */
public class KdiffPairsInanArray {


    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(new KdiffPairsInanArray().findPairs(nums, -1));
    }

    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int count = 0;
        if (nums.length == 0 || k < 0) return 0;
        for (int i : nums) {
            frequencyMap.put(i, frequencyMap.getOrDefault(i, 0) + 1);
        }

        if (k == 0) {
            for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() > 1) {
                    count++;//as we need unique diffs
                }
            }
            return count;
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {

            if (frequencyMap.containsKey(entry.getKey() + k)) {
                count++;
            }

        }
        return count;
    }
}
