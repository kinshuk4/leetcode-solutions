package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * s = "aaabb", k = 3
 * <p>
 * Output:
 * 3
 * <p>
 * The longest substring is "aaa", as 'a' is repeated 3 times.
 * <p>
 * Example 2:
 * <p>
 * Input:
 * s = "ababbc", k = 2
 * <p>
 * Output:
 * 5
 * <p>
 * The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring1(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        final int[] frequencyArr = new int[26];
        // record the frequency of each character
        for (int i = 0; i < s.length(); i += 1) {
            frequencyArr[s.charAt(i) - 'a'] += 1;
        }

        boolean allKFlag = true;
        for (int freq : frequencyArr) {
            if (freq < k && freq > 0) {
                allKFlag = false;
            }
        }
        // return the length of string if this string is a valid string
        if (allKFlag) {
            return s.length();
        }


        int result = 0;
        int start = 0, cur = 0;
        // otherwise we use all the infrequent elements as splits
        while (cur < s.length()) {
            if (frequencyArr[s.charAt(cur) - 'a'] < k) {
                result = Math.max(result, longestSubstring1(s.substring(start, cur), k));
                start = cur + 1;
            }
            cur++;
        }
        result = Math.max(result, longestSubstring1(s.substring(start), k));
        return result;
    }

    // https://www.youtube.com/watch?v=bHZkCAcj3dc
    public int longestSubstring2(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (k < 2) {
            return s.length();
        }
        return helper2(s, 0, s.length(), k);
    }

    public int helper2(String s, int l, int r, int k) {
        if (l >= r) {
            return 0;
        }
        if (k > (r - l)) {
            return 0;
        }

        // build freq map
        int[] freq = new int[26];
        for (int i = l; i < r; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        // check if valid
        boolean allKFlag = true;
        for (int i : freq) {
            if (i > 0 && i < k) {
                allKFlag = false;
                break;
            }
        }
        if (allKFlag) {
            return r - l;
        }

        // if not for each invalid character start a new split search
        int best = 0, start = l;
        for (int i = l; i < r; i++) {
            if (freq[s.charAt(i) - 'a'] < k) {
                best = Math.max(best, helper2(s, start, i, k));
                start = i + 1;
            }
        }
        best = Math.max(best, helper2(s, start, r, k));
        return best;
    }
}
