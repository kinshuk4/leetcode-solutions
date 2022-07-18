package com.vaani.leetcode.string;

public class ShiftingLetters {



    public String shiftingLetters(String s, int[] shifts) {
        final int CHAR_SIZE = 26;
        shifts[shifts.length - 1] %= CHAR_SIZE;
        // start from the next char
        for (int i = shifts.length - 2; i >= 0; i--) {
            shifts[i] %= CHAR_SIZE;
            shifts[i] += shifts[i + 1];

        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int val = ((chars[i] - 'a') + shifts[i]) % CHAR_SIZE;
            chars[i] = (char) (val + 'a');
        }

        return new String(chars);

    }
}
