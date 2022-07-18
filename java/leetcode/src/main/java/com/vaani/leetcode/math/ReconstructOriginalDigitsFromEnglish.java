package com.vaani.leetcode.math;

/**
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/
 * 423. Reconstruct Original Digits from English
 * Medium
 * <p>
 * 284
 * <p>
 * 870
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.
 * <p>
 * Note:
 * Input contains only lowercase English letters.
 * Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
 * Input length is less than 50,000.
 * Example 1:
 * Input: "owoztneoer"
 * <p>
 * Output: "012"
 * Example 2:
 * Input: "fviefuro"
 * <p>
 * Output: "45"
 */
public class ReconstructOriginalDigitsFromEnglish {
    public String originalDigits(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Zero, one, tWo, three, foUr, five, siX, seven, eiGht, Nine
        int[] digit = new int[10];

        digit[0] = freq['z' - 'a'];
        digit[2] = freq['w' - 'a'];
        digit[4] = freq['u' - 'a'];
        digit[6] = freq['x' - 'a'];
        digit[8] = freq['g' - 'a'];

        digit[5] = freq['f' - 'a'] - digit[4];
        digit[7] = freq['s' - 'a'] - digit[6];
        digit[3] = freq['t' - 'a'] - digit[8] - digit[2];
        digit[1] = freq['o' - 'a'] - digit[2] - digit[4] - digit[0];
        digit[9] = freq['i' - 'a'] - digit[5] - digit[6] - digit[8];

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 10; i++) {
            int count = digit[i];
            while (count-- > 0) {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
