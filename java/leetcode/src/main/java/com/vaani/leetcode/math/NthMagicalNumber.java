package com.vaani.leetcode.math;

import java.math.BigInteger;

/**
 * https://leetcode.com/problems/nth-magical-number/
 878. Nth Magical Number
 Hard

 A positive integer is magical if it is divisible by either a or b.

 Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large, return it modulo 109 + 7.



 Example 1:

 Input: n = 1, a = 2, b = 3
 Output: 2
 Example 2:

 Input: n = 4, a = 2, b = 3
 Output: 6
 Example 3:

 Input: n = 5, a = 2, b = 4
 Output: 10
 Example 4:

 Input: n = 3, a = 6, b = 4
 Output: 8


 Constraints:

 1 <= n <= 109
 2 <= a, b <= 4 * 104
 *

 */
public class NthMagicalNumber {
    public static void main(String[] args) {
        System.out.println(new NthMagicalNumber().nthMagicalNumber(3, 2, 4));
    }

    /**
     * <p>Solution: O(log((2 ^ 64) - 1)) Lets take example of N = 5, A = 4 and B = 6 The multiple of A
     * are 4, 8, 12, 16, 20, 24 . . . The multiple of B are 6, 12, 18, 24 . . .
     *
     * <p>Lets take a arbitrary number E = 21 and see if this fits the correct answer E / A = 5 E / B =
     * 3 This means there are 5 + 3 = 8 numbers which are divisible by either A or B such as 4, 6, 8,
     * 12, 12, 16, 18, 20 but we have double counted number 12 so we have to reduce 8 by 1 therefore
     * there are 7 numbers. But, 7 is greater than required number N = 5 that means we have to search
     * between 0 and E - 1. Thus we can binary search to arrive at the answer.
     *
     * <p>The number of common multiples such as 12 in the above example can be found by E / LCM(4, 6)
     */
    public int nthMagicalNumber(int n, int a, int b) {
        final int MOD = 1000000007;
        BigInteger bigInteger = new BigInteger(String.valueOf(a));
        long aL = (long) a * b;
        long lcm = aL / bigInteger.gcd(new BigInteger(String.valueOf(b))).longValue();
        long l = 0, h = Long.MAX_VALUE;
        while (l <= h) {
            long m = l + (h - l) / 2;
            int status = check(n, m, a, b, lcm);
            if (status == 0) {
                long modA = m % a;
                long modB = m % b;
                if (modA == 0 || modB == 0) return (int) (m % MOD);
                else if (modA < modB) return (int) ((m - modA) % MOD);
                else return (int) ((m - modB) % MOD);
            } else if (status == -1) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return 0;
    }

    private int check(int N, long num, int A, int B, long lcm) {
        long sum = (num / A) + (num / B);
        long common = num / lcm;
        sum -= common;
        if (sum == N) return 0;
        else if (sum > N) return 1;
        else return -1;
    }
}
