package com.vaani.leetcode.array;

import java.util.*;

/**
 * https://leetcode.com/problems/reduce-array-size-to-the-half/
 * 1338. Reduce Array Size to The Half
 * Medium
 * <p>
 * Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
 * <p>
 * Return the minimum size of the set so that at least half of the integers of the array are removed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [3,3,3,3,5,5,5,2,2,7]
 * Output: 2
 * Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
 * Possible sets of size 2 are {3,5},{3,2},{5,2}.
 * Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
 * Example 2:
 * <p>
 * Input: arr = [7,7,7,7,7,7]
 * Output: 1
 * Explanation: The only possible set you can choose is {7}. This will make the new array empty.
 * Example 3:
 * <p>
 * Input: arr = [1,9]
 * Output: 1
 * Example 4:
 * <p>
 * Input: arr = [1000,1000,3,7]
 * Output: 1
 * Example 5:
 * <p>
 * Input: arr = [1,2,3,4,5,6,7,8,9,10]
 * Output: 5
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 10^5
 * arr.length is even.
 * 1 <= arr[i] <= 10^5
 */
public class ReduceArraySizeToTheHalf {
    public int minSetSize(int[] arr) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int[] frequencies = new int[freqMap.values().size()];
        int i = 0;
        for (int freq : freqMap.values()) {
            frequencies[i++] = freq;
        }
        Arrays.sort(frequencies);

        final int HALF = arr.length / 2;
        int ans = 0, removed = 0;
        i = frequencies.length - 1;
        while (removed < HALF) {
            ans += 1;
            removed += frequencies[i--];
        }
        return ans;
    }
}
