package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/buddy-strings/
 * 859. Buddy Strings
 * Easy
 * <p>
 * Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.
 * <p>
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j]. For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = "ab", B = "ba"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
 * Example 2:
 * <p>
 * Input: A = "ab", B = "ab"
 * Output: false
 * Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
 * Example 3:
 * <p>
 * Input: A = "aa", B = "aa"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
 * Example 4:
 * <p>
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 * <p>
 * Input: A = "", B = "aa"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A and B consist of lowercase letters.
 */
public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        // If both strings equal, then check for duplicated char in string A. If exists, we return true
        if (A.equals(B)) {
            int[] count = new int[26];

            for (int i = 0; i < A.length(); i++) {
                if (count[A.charAt(i) - 'a']++ > 0) {
                    return true;
                }
            }
            return false;
        }

        // Find first different character in the strings from end
        int first = A.length() - 1;
        while (first >= 0 && A.charAt(first) == B.charAt(first)) {
            first--;
        }

        // Find second different character in the strings, if any.
        int second = first - 1;
        while (second >= 0 && A.charAt(second) == B.charAt(second)) {
            second--;
        }

        // If second character exists, then they should match for swapping.
        if (second < 0 || A.charAt(first) != B.charAt(second) || A.charAt(second) != B.charAt(first)) {
            return false;
        }

        // Now remaining string should be same
        // Make sure rest of string is the same.
        int rest = second - 1;
        while (rest >= 0 && A.charAt(rest) == B.charAt(rest)) {
            rest--;
        }
        return rest < 0;

    }

}
