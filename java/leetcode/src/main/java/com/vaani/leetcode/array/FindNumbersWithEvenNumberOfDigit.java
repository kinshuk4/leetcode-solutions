package com.vaani.leetcode.array;

import java.util.*;

public class FindNumbersWithEvenNumberOfDigit {
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int numDigits = 0;
            while (num > 0) {
                num = num / 10;
                numDigits++;
            }
            if (numDigits % 2 == 0) {
                result++;
            }
        }
        return result;
    }
}
