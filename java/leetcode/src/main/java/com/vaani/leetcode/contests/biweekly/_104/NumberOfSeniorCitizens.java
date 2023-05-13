package com.vaani.leetcode.contests.biweekly._104;

import com.vaani.leetcode.contests.biweekly._102.FindTheWidthOfColumnsOfAGrid;

import static org.junit.Assert.assertEquals;

public class NumberOfSeniorCitizens {
    public static void main(String[] args) {
        NumberOfSeniorCitizens underTest = new NumberOfSeniorCitizens();
        String[] details = {"7868190130M7522","5303914400F9211","9273338290F4010"};
        assertEquals(2, underTest.countSeniors(details));
    }
    public int countSeniors(String[] details) {
        int count = 0;
        for(String detail: details) {
            int age = Integer.valueOf(detail.substring(11, 13));
            if (age > 60) {
                count++;
            }
        }
        return count;
    }
}
