package com.vaani.leetcode.math;

import java.util.*;

/**
 * https://leetcode.com/problems/super-palindromes/
 * 906. Super Palindromes
 * Hard
 * <p>
 * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also the square of a palindrome.
 * <p>
 * Given two positive integers left and right represented as strings, return the number of super-palindromes integers in the inclusive range [left, right].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: left = "4", right = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 * Example 2:
 * <p>
 * Input: left = "1", right = "2"
 * Output: 1
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= left.length, right.length <= 18
 * left and right consist of only digits.
 * left and right cannot have leading zeros.
 * left and right represent integers in the range [1, 1018].
 * left is less than or equal to right.
 */
public class SuperPalindrome {
    public int superpalindromesInRange(String left, String right) {
        long l = Long.parseLong(left);
        long r = Long.parseLong(right);

        List<Long> palindromes = new ArrayList<>();

        for (long i = 1; i < 10; i++) {
            palindromes.add(i);
        }

        // Create palindromes
        //  Loop goes till 10^4 for 2 reasons
        //	1) when leftStr is of size 10^4 => palindrome String is 10^4=10^8 => square String will be of size 10^16
        //	2) when leftStr is of size 10^4, palindrome String is  leftStr + middle single digit + rightStr =10^9 =>
        //  	square string will be of size 10^18
        // 10^18 is max size for right.length
        for (long i = 1; i < 10000; i++) {
            String leftStr = Long.toString(i);
            StringBuilder rightStr = new StringBuilder(leftStr).reverse();

            palindromes.add(Long.parseLong(leftStr + rightStr));

            for (int j = 0; j < 10; j++) {
                palindromes.add(Long.parseLong(leftStr + j + rightStr));
            }

        }

        int ans = 0;
        for (long i : palindromes) {
            long square = i * i;

            if (square >= l && square <= r && isPalindrome(Long.toString(square))) {
                ans++;
            }

        }

        return ans;
    }

    public boolean isPalindrome(String num) {
        int l = 0, r = num.length() - 1;

        while (l < r) {
            if (num.charAt(l) != num.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
}



