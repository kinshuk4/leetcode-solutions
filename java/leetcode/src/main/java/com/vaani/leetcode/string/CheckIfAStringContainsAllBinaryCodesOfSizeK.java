package com.vaani.leetcode.string;

import java.util.*;

/**
 * 1461. Check If a String Contains All Binary Codes of Size K
 * Medium
 * Given a binary string s and an integer k.
 * <p>
 * Return True if every binary code of length k is a substring of s. Otherwise, return False.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "00110110", k = 2
 * Output: true
 * Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indicies 0, 1, 3 and 2 respectively.
 * Example 2:
 * <p>
 * Input: s = "00110", k = 2
 * Output: true
 * Example 3:
 * <p>
 * Input: s = "0110", k = 1
 * Output: true
 * Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
 * Example 4:
 * <p>
 * Input: s = "0110", k = 2
 * Output: false
 * Explanation: The binary code "00" is of length 2 and doesn't exist in the array.
 * Example 5:
 * <p>
 * Input: s = "0000000001011100", k = 4
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 5 * 10^5
 * s consists of 0's and 1's only.
 * 1 <= k <= 20
 */
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    public boolean hasAllCodes(String s, int k) {
        if (s.length() < k) {
            return false;
        }

        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - k; i++) {
            set.add(s.substring(i, i + k));
        }

        return set.size() == Math.pow(2, k);
    }
}
