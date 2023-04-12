package com.vaani.leetcode.contests.biweekly._99;

import static org.junit.Assert.assertEquals;

public class CountTotalNumberOfColoredCells {
    public static void main(String[] args) {
        CountTotalNumberOfColoredCells underTest = new CountTotalNumberOfColoredCells();
        assertEquals(13, underTest.coloredCells(3));
        assertEquals(25, underTest.coloredCells(4));
    }
    // 1 5 13 25
    public long coloredCells(int n) {
        long ans = 1;
        if (n == 1) {
            return ans;
        }
        long mult4 = 0;
        for (int i = 2; i <= n; i++) {
            mult4 += 4;
            ans += mult4;
        }
        return ans;
    }
    public long coloredCells1(int n) {
        long ans = 1;
        if (n == 1) {
            return ans;
        }
        long powOf2 = 2;
        for (int i = 2; i <= n; i++) {
            powOf2 = powOf2 * 2;
            ans += powOf2;
        }
        return ans;
    }
}
