package com.vaani.leetcode.contests.biweekly._99;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SplitWithMinimumSum {
    public static void main(String[] args) {
        SplitWithMinimumSum underTest = new SplitWithMinimumSum();
        assertEquals(59, underTest.splitNum(4325));
    }
    public int splitNum(int num) {
        List<Integer> digList = new ArrayList<>();
        int rem = 0;
        while (num != 0) {
            rem = num % 10;
            digList.add(rem);
            num /= 10;
        }
        Collections.sort(digList);

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (int i = 0; i < digList.size(); i++) {
            if (i % 2 == 0) {
                sb1.append(digList.get(i));
            }

            if (i % 2 == 1) {
                sb2.append(digList.get(i));
            }
        }
        String s1 = sb1.toString();
        String s2 = sb2.toString();

        return Integer.parseInt(s1) + Integer.parseInt(s2);
    }
}
