package com.vaani.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 19/05/2017. The com.vaani.leetcode.string "PAYPALISHIRING" is written in a zigzag
 * pattern on a given number of rows like this: (you may want to display this pattern in a fixed
 * font for better legibility)
 *
 * <p>P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR" Write the code that
 * will take a com.vaani.leetcode.string and make this conversion given a number of rows:
 *
 * <p>com.vaani.leetcode.string convert(com.vaani.leetcode.string text, int nRows); convert("PAYPALISHIRING", 3) should return
 * "PAHNAPLSIIGYIR".
 */
public class ZigZagConversion {
    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new ZigZagConversion().convert("PAYPALISHIRING", 5));
    }

    /**
     * Convert and return the result
     *
     * @param s       input com.vaani.leetcode.string
     * @param numRows max rows
     * @return Result com.vaani.leetcode.string
     */
    public String convert(String s, int numRows) {
        if (s == null || s.length() <= numRows || numRows == 1) return s;
        List<String> list = new ArrayList<>();
        char[] A = new char[numRows];
        int direction = 1; // 1 indicates forward, 0 indicates backwards
        int n = 1;
        A[0] = s.charAt(0);
        for (int j = 1; j < numRows; ) {
            if (n >= s.length()) break;
            A[j] = s.charAt(n++);
            if (j == numRows - 1) {
                list.add(String.valueOf(A));
                A = new char[numRows];
                Arrays.fill(A, ' ');
                direction = 0;
            } else if (j == 0) {
                list.add(String.valueOf(A));
                A = new char[numRows];
                Arrays.fill(A, ' ');
                direction = 1;
            }
            j = direction == 1 ? j + 1 : j - 1;
        }
        if (!String.valueOf(A).trim().isEmpty()) list.add(String.valueOf(A));
        char[] arr = new char[s.length()];
        int k = 0;
        for (int j = 0; j < numRows; j++) {
            for (String aList : list) {
                if (aList.charAt(j) != ' ') arr[k++] = aList.charAt(j);
            }
        }
        return new String(arr).trim();
    }
}
