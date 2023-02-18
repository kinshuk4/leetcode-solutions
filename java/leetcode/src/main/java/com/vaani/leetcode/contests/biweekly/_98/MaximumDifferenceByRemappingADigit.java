package com.vaani.leetcode.contests.biweekly._98;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MaximumDifferenceByRemappingADigit {
    public static void main(String[] args) {
        MaximumDifferenceByRemappingADigit underTest = new MaximumDifferenceByRemappingADigit();
//        assertEquals(99009, underTest.minMaxDifference(11891));
//        assertEquals(99999, underTest.minMaxDifference(22222));
        assertEquals(99999, underTest.minMaxDifference(99999));
    }
    public int minMaxDifference(int num) {
        List<Integer> digs = new ArrayList<>();
        boolean isAll9 = true;
        int numBck = num;
        while (num > 0) {

            int d = num % 10;
            digs.add(d);
            if (isAll9 && d != 9) {
                isAll9 = false;
            }
            num = num / 10;
        }

        // max

        long max = 0L;
        if (isAll9) {
            max = numBck;
        }else {
            int dig = -1;
            for (int i = digs.size() - 1; i >= 0; i--) {
                dig = digs.get(i);
                if (dig != 9) {
                    break;
                }
            }

            max = getNumAfterReplacing(digs, dig, 9);

        }

        long min = 0L;
        if (numBck == 0) {
            min = numBck;
        }else {
            int dig = -1;
            for (int i = digs.size() - 1; i >= 0; i--) {
                dig = digs.get(i);
                if (dig != 0) {
                    break;
                }
            }
            min = getNumAfterReplacing(digs, dig, 0);
        }
        return (int) (max - min);

    }

    public long getNumAfterReplacing(List<Integer> digs, int digToReplace, int toReplaceWith) {
        long num = 0L;
        for (int i = digs.size() - 1; i >= 0; i--) {
           int dig = digs.get(i);
           int add = 0;
           if (dig == digToReplace) {
               add = toReplaceWith;
           }else {
               add = dig;
           }
           num = num * 10 + add;
        }
        return num;
    }
}
