package com.vaani.leetcode.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/sequential-digits/
 * 1291. Sequential Digits
 * Medium
 * <p>
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * <p>
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 * <p>
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 10 <= low <= high <= 10^9
 */
public class SequentialDigits {
    static class UsingExistingNumbers {
        public List<Integer> sequentialDigits(int low, int high) {
            int[] nums = {12, 23, 34, 45, 56, 67, 78, 89,
                    123, 234, 345, 456, 567, 678, 789,
                    1234, 2345, 3456, 4567, 5678, 6789,
                    12345, 23456, 34567, 45678, 56789,
                    123456, 234567, 345678, 456789,
                    1234567, 2345678, 3456789,
                    12345678, 23456789,
                    123456789};
            List<Integer> ans = new ArrayList<>();

            for (int num : nums) {
                if (num < low) {
                    continue;
                }
                if (num > high) {
                    break;
                }
                ans.add(num);
            }
            return ans;
        }
    }


    static class UsingBFS {
        public List<Integer> sequentialDigits(int low, int high) {
            List<Integer> ans = new ArrayList<>();
            Queue<Integer> q = new LinkedList<>();
            if (low <= 0 && high >= 0) {
                ans.add(0);
            }
            for (int i = 1; i < 10; i++) {
                q.add(i);
            }
            while (q.size() > 0) {
                int curr = q.remove();
                if (curr >= low && curr <= high) {
                    ans.add(curr);
                }
                int onesDigit = curr % 10;
                if (onesDigit < 9 && curr * 10 + onesDigit + 1 <= high) {
                    q.add(curr * 10 + onesDigit + 1);
                }
            }
            return ans;
        }
    }
}
