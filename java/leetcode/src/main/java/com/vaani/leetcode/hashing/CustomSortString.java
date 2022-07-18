package com.vaani.leetcode.hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/custom-sort-string/
 * 791. Custom Sort String
 * Medium
 * <p>
 * order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
 * <p>
 * order was sorted in some custom order previously. We want to permute the characters of str so that they match the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the returned string.
 * <p>
 * Return any permutation of str (as a string) that satisfies this property.
 * <p>
 * Example:
 * Input:
 * order = "cba"
 * str = "abcd"
 * Output: "cbad"
 * Explanation:
 * "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
 * Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.
 * <p>
 * <p>
 * Note:
 * <p>
 * order has length at most 26, and no character is repeated in order.
 * str has length at most 200.
 * order and str consist of lowercase letters only.
 */
public class CustomSortString {
    public static void main(String[] args) throws Exception {
        System.out.println(new CustomSortString().customSortString("cba", "abcd"));
    }

    /**
     * <p>Solution: O(N) count occurrence of each character and write to the output com.vaani.leetcode.string
     */
    public String customSortString(String order, String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (!map.containsKey(str.charAt(i))) {
                map.put(str.charAt(i), 1);
            } else {
                map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
            }
        }
        StringBuilder result = new StringBuilder();
        for (char c : order.toCharArray()) {
            if (map.containsKey(c)) {
                int count = map.remove(c);
                result.append(String.valueOf(c).repeat(Math.max(0, count)));
            }
        }
        for (char c : map.keySet()) {
            int count = map.get(c);
            result.append(String.valueOf(c).repeat(Math.max(0, count)));
        }
        return result.toString();
    }
}
