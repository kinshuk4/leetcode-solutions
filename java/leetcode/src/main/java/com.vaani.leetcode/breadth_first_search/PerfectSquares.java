package com.vaani.leetcode.breadth_first_search;

import static java.lang.Math.*;

/**
 * https://leetcode.com/problems/perfect-squares/
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * <p>
 * Example 1:
 * <p>
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 * <p>
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        return numSquaresLegendre(n);
    }

    int numSquaresRecursive(int n) {
        return numSquaresRecursiveHelper(n);
    }

    int numSquaresRecursiveHelper(int n) {
        if (n <= 3) {
            return n;
        }

        int result = n; // all sum of 1^2
        for (int i = 1; i * i <= n; ++i) {
            result = min(result, 1 + numSquaresRecursiveHelper(n - i * i));
        }

        return result;
    }

    int numSquaresRecursiveMemo(int n) {
        int[] dp = new int[n + 1];
        int ans = numSquaresRecursiveMemoHelper(n, dp);
        return ans;
    }

    int numSquaresRecursiveMemoHelper(int n, int[] dp) {
        if (n <= 3)
            return n;
        if (dp[n] != -1)
            return dp[n];

        int ans = n;
        for (int i = 1; i * i <= n; ++i) {
            ans = min(ans, 1 + numSquaresRecursiveMemoHelper(n - i * i, dp));
        }

        dp[n] = ans;
        return ans;
    }


    int numSquaresDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; ++i) {
            dp[i] = i;
            for (int j = 1; j * j <= i; ++j) {
                int sq = j * j;
                dp[i] = min(dp[i], 1 + dp[i - sq]);
            }
        }
        return dp[n];
    }


    //Legendre's 3-square theorem
    int numSquaresLegendre(int n) {
        if (ceil(sqrt(n)) == floor(sqrt(n)))
            return 1;
        while (n % 4 == 0)   //Remove 4^a term
            n /= 4;

        if (n % 8 == 7)      //check if the no now is in the form of (8b + 7)
            return 4;

        for (int i = 1; i * i <= n; ++i) {
            int base = (int) sqrt(n - i * i);
            if (base * base == (n - i * i)) {
                return 2;
            }
        }
        return 3;
    }


}
