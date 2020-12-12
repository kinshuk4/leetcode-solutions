package com.vaani.leetcode.dp;

/**
 * https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
 * 902. Numbers At Most N Given Digit Set
 * Hard
 * <p>
 * Given an array of digits, you can write numbers using each digits[i] as many times as we want.  For example, if digits = ['1','3','5'], we may write numbers such as '13', '551', and '1351315'.
 * <p>
 * Return the number of positive integers that can be generated that are less than or equal to a given integer n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = ["1","3","5","7"], n = 100
 * Output: 20
 * Explanation:
 * The 20 numbers that can be written are:
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 * Example 2:
 * <p>
 * Input: digits = ["1","4","9"], n = 1000000000
 * Output: 29523
 * Explanation:
 * We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
 * 81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
 * 2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
 * In total, this is 29523 integers that can be written using the digits array.
 * Example 3:
 * <p>
 * Input: digits = ["7"], n = 8
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i] is a digit from '1' to '9'.
 * All the values in digits are unique.
 * 1 <= n <= 10^9
 */
public class NumbersAtMostNGivenDigitSet {

    // https://leetcode.com/problems/numbers-at-most-n-given-digit-set/discuss/943922/Java-recursive-solution
    static class UsingRecursion {
        public int atMostNGivenDigitSet(String[] digits, int n) {
            int ans = 0;
            String nStr = Integer.toString(n);

            for (int i = 1; i < nStr.length(); i++) {
                ans += Math.pow(digits.length, i);
            }
            ans += getNDigitWithSameLength(0, digits, nStr);
            return ans;
        }

        private int getNDigitWithSameLength(int idx, String[] digits, String nStr) {
            int ans = 0;
            if (idx == nStr.length() - 1) {
                for (String digit : digits) {
                    if (digit.charAt(0) <= nStr.charAt(idx)) {
                        ans++;
                    }
                }
                return ans;
            }
            for (String digit : digits) {
                if (nStr.charAt(idx) > digit.charAt(0)) {
                    ans += Math.pow(digits.length, nStr.length() - idx - 1);
                } else if (nStr.charAt(idx) == digit.charAt(0)) {
                    ans += getNDigitWithSameLength(idx + 1, digits, nStr);
                }
            }
            return ans;
        }

    }

    // https://leetcode.com/problems/numbers-at-most-n-given-digit-set/discuss/875457/Java-or-beats-both-time-and-space-100
    static class UsingDP {
        public int atMostNGivenDigitSet(String[] digits, int n) {
            char[] nChars = String.valueOf(n).toCharArray();
            int ans = 1, prev = 1, count = -1;

            for (int i = 1, j = 1; i <= nChars.length; i++, j *= digits.length) {
                prev = ans;
                ans = 0;
                int x = nChars[nChars.length - i] - '0';
                for (String d : digits) {
                    if (Integer.parseInt(d) < x) {
                        ans += j;
                    } else if (Integer.parseInt(d) == x) {
                        ans += prev;
                    }
                }
                count += j;
            }

            return ans + count;

        }
    }

}
