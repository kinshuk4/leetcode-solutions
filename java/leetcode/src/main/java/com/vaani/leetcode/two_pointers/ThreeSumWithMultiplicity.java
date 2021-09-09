package com.vaani.leetcode.two_pointers;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/3sum-with-multiplicity/
 * 923. 3Sum With Multiplicity
 * Medium
 * <p>
 * Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
 * <p>
 * As the answer can be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * Output: 20
 * Explanation:
 * Enumerating by the values (arr[i], arr[j], arr[k]):
 * (1, 2, 5) occurs 8 times;
 * (1, 3, 4) occurs 8 times;
 * (2, 2, 4) occurs 2 times;
 * (2, 3, 3) occurs 2 times.
 * Example 2:
 * <p>
 * Input: arr = [1,1,2,2,2,2], target = 5
 * Output: 12
 * Explanation:
 * arr[i] = 1, arr[j] = arr[k] = 2 occurs 12 times:
 * We choose one 1 from [1,1] in 2 ways,
 * and two 2s from [2,2,2,2] in 6 ways.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 3 <= arr.length <= 3000
 * 0 <= arr[i] <= 100
 * 0 <= target <= 300
 */
public class ThreeSumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        Map<Integer, Long> freqMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(
                Function.identity(),
                HashMap::new, // can be skipped
                Collectors.counting()
                )
        );

        long ans = 0;
        final int MOD = 1000000007;
        for (Integer a : freqMap.keySet()) {
            for (Integer b : freqMap.keySet()) {
                int c = target - a - b;
                if (freqMap.containsKey(c)) {
                    long f1 = freqMap.get(a);
                    long f2 = freqMap.get(b);
                    long f3 = freqMap.get(c);

                    if (a.equals(b) && a == c) {
                        ans += ((f1) * (f1 - 1) * (f1 - 2)) / 6;
                    } else if (a.equals(b)) {
                        ans += ((f1) * (f1 - 1)) / 2 * f3;
                    } else if (a < b && b < c) {
                        ans += ((f1 * f2 * f3));
                    }

                }
                ans = ans % MOD;
            }
        }
        return (int) ans;
    }
}
