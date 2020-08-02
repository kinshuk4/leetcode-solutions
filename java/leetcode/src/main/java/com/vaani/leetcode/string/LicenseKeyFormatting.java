package com.vaani.leetcode.string;

/**
 * https://leetcode.com/problems/license-key-formatting/
 * 482. License Key Formatting
 * Easy
 * <p>
 * You are given a license key represented as a string S which consists only alphanumeric character and dashes. The string is separated into N+1 groups by N dashes.
 * <p>
 * Given a number K, we would want to reformat the strings such that each group contains exactly K characters, except for the first group which could be shorter than K, but still must contain at least one character. Furthermore, there must be a dash inserted between two groups and all lowercase letters should be converted to uppercase.
 * <p>
 * Given a non-empty string S and a number K, format the string according to the rules described above.
 * <p>
 * Example 1:
 * <p>
 * Input: S = "5F3Z-2e-9-w", K = 4
 * <p>
 * Output: "5F3Z-2E9W"
 * <p>
 * Explanation: The string S has been split into two parts, each part has 4 characters.
 * Note that the two extra dashes are not needed and can be removed.
 * <p>
 * Example 2:
 * <p>
 * Input: S = "2-5g-3-J", K = 2
 * <p>
 * Output: "2-5G-3J"
 * <p>
 * Explanation: The string S has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.
 * <p>
 * Note:
 * <p>
 * The length of string S will not exceed 12,000, and K is a positive integer.
 * String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 * String S is non-empty.
 */
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        System.out.println(new LicenseKeyFormatting().licenseKeyFormatting("5F3Z-2e-9-w", 4));
    }


    // will not work for case - "--a-a-a-a--", 2
    public String licenseKeyFormatting2(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        for (int i = S.length() - 1; i >= 0; i--) {
            char c = Character.toUpperCase(S.charAt(i));
            if (c == '-') {

                continue;
            }
            sb.insert(0, c); // we are insert at start so that we don't have to reverse string
            count++;

            if (i != 0 && count % K == 0) { // we are not at the start of string and count is already K, then just append '-'
                sb.insert(0, '-');
            }
        }
        return sb.toString();
    }

    public String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        int i = S.length() - 1;
        while (i >= 0) {
            char c = Character.toUpperCase(S.charAt(i));
            if (c == '-') {
                i--; // move to next char
            } else if (count != 0 && count % K == 0) {
                sb.insert(0, '-');
                count = 0;
            } else {
                sb.insert(0, c);
                count++;
                i--; // move to next char
            }
        }

        return sb.toString();
    }
}
