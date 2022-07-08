package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/decode-ways-ii/
 * 639. Decode Ways II
 * Hard
 * <p>
 * 753
 * <p>
 * 666
 * <p>
 * Add to List
 * <p>
 * Share
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * In addition to the mapping above, an encoded message may contain the '*' character, which can represent any digit from '1' to '9' ('0' is excluded). For example, the encoded message "1*" may represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19". Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.
 * <p>
 * Given a string s containing digits and the '*' character, return the number of ways to decode it.
 * <p>
 * Since the answer may be very large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "*"
 * Output: 9
 * Explanation: The encoded message can represent any of the encoded messages "1", "2", "3", "4", "5", "6", "7", "8", or "9".
 * Each of these can be decoded to the strings "A", "B", "C", "D", "E", "F", "G", "H", and "I" respectively.
 * Hence, there are a total of 9 ways to decode "*".
 * Example 2:
 * <p>
 * Input: s = "1*"
 * Output: 18
 * Explanation: The encoded message can represent any of the encoded messages "11", "12", "13", "14", "15", "16", "17", "18", or "19".
 * Each of these encoded messages have 2 ways to be decoded (e.g. "11" can be decoded to "AA" or "K").
 * Hence, there are a total of 9 * 2 = 18 ways to decode "1*".
 * Example 3:
 * <p>
 * Input: s = "2*"
 * Output: 15
 * Explanation: The encoded message can represent any of the encoded messages "21", "22", "23", "24", "25", "26", "27", "28", or "29".
 * "21", "22", "23", "24", "25", and "26" have 2 ways of being decoded, but "27", "28", and "29" only have 1 way.
 * Hence, there are a total of (6 * 2) + (3 * 1) = 12 + 3 = 15 ways to decode "2*".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 105
 * s[i] is a digit or '*'.
 */
public class DecodeWaysII {

    public static void main(String[] args) throws Exception {
        System.out.println(new DecodeWaysII().numDecodings("10"));
    }

    /**
     * <p>Solution: O(n) consider each digit and a pair of digits and perform a cartesian product to
     * calculate the total number of ways. A pair of digits are to be considered only if their combined
     * value does not exceed 26. Corner cases with combination of * and 0s can be tricky
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        final int CONST = 1000000007;
        if (s.charAt(n - 1) == '*') {
            dp[n - 1] = 9;
        } else if (s.charAt(n - 1) == '0') {
            dp[n - 1] = 0;
        } else dp[n - 1] = 1;
        dp[n] = 1;

        for (int i = n - 2; i >= 0; i--) {
            char curr = s.charAt(i);
            char next = s.charAt(i + 1);

            switch (curr) {
                case '0' -> dp[i] = 0;

                // number begins with a '*'
                case '*' -> {
                    dp[i] = (int) ((9 * (long) dp[i + 1]) % CONST);
                    switch (next) {
                        // The next char is a '*'
                        case '*':
                            dp[i] =
                                    (int)
                                            ((dp[i] + ((15 * (long) dp[i + 2]) % CONST))
                                                    % CONST); // multiplication can be
                            // very large hence type casting to long is necessary
                            break;

                        case '0':
                            dp[i] = (int) ((dp[i] + ((2 * (long) dp[i + 2]) % CONST)) % CONST);
                            break;

                        default:
                            if ((next - '0') > 6) {
                                dp[i] = ((dp[i] + (dp[i + 2])) % CONST);
                            } else {
                                dp[i] = (int) ((dp[i] + ((2 * (long) dp[i + 2]) % CONST)) % CONST);
                            }
                            break;
                    }
                }
                default -> {
                    dp[i] = dp[i + 1];
                    if (next == '*') {
                        if ((curr - '0') == 1) {
                            dp[i] = (int) ((dp[i] + ((9 * (long) dp[i + 2]) % CONST)) % CONST);
                        } else if ((curr - '0') == 2) {
                            dp[i] = (int) ((dp[i] + ((6 * (long) dp[i + 2]) % CONST)) % CONST);
                        }
                    } else {
                        if ((curr - '0') == 1 || ((curr - '0') == 2 && (next - '0' <= 6))) {
                            dp[i] = (dp[i] + dp[i + 2]) % CONST;
                            ;
                        }
                    }
                }
            }
        }
        return dp[0];
    }
}
