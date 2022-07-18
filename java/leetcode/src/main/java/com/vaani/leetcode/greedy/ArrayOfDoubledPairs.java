package com.vaani.leetcode.greedy;

import java.util.*;

/**
 * https://leetcode.com/problems/array-of-doubled-pairs/
 * 954. Array of Doubled Pairs
 * Medium
 * <p>
 * <p>
 * Given an array of integers arr of even length, return true if and only if it is possible to reorder it such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,1,3,6]
 * Output: false
 * Example 2:
 * <p>
 * Input: arr = [2,1,2,6]
 * Output: false
 * Example 3:
 * <p>
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 * Example 4:
 * <p>
 * Input: arr = [1,2,4,16,8,4]
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= arr.length <= 3 * 10^4
 * arr.length is even.
 * -10^5 <= arr[i] <= 10^5
 */
public class ArrayOfDoubledPairs {
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        if (map.containsKey(0) && map.get(0) % 2 != 0) {
            return false;
        }

        int countNums = 0;

        for (int num : arr) {
            int twice = 2 * num;
            if (map.containsKey(num) && map.containsKey(twice)) {
                if (map.get(num) == 0 || map.get(twice) == 0) {
                    continue;
                }
                countNums += 2;
                map.put(num, map.get(num) - 1);
                map.put(twice, map.get(twice) - 1);
            }
        }
        return countNums == arr.length;
    }
}
