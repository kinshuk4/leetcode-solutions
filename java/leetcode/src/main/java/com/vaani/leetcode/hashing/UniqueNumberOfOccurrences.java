package com.vaani.leetcode.hashing;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/unique-number-of-occurrences/
 * 1207. Unique Number of Occurrences
 * Easy
 * <p>
 * Given an array of integers arr, write a function that returns true if and only if the number of occurrences of each value in the array is unique.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,2,2,1,1,3]
 * Output: true
 * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
 * <p>
 * Example 2:
 * <p>
 * Input: arr = [1,2]
 * Output: false
 * <p>
 * Example 3:
 * <p>
 * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * Output: true
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= arr.length <= 1000
 * -1000 <= arr[i] <= 1000
 */
public class UniqueNumberOfOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Long> freqMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(),
                HashMap::new, // can be skipped
                Collectors.counting()));

        Set<Long> set = new HashSet<>();
        for (int key : freqMap.keySet()) {
            if (set.contains(freqMap.get(key))) {
                return false;
            }
            set.add(freqMap.get(key));
        }
        return true;
    }
}
