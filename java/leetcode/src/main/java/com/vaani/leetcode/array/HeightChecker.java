package com.vaani.leetcode.array;

import java.util.*;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

public class HeightChecker {
    // insertion sort doesnt work
    public int heightCheckerInsertionSort(int[] heights) {
        int count = 0;
        for (int i = 1; i < heights.length; i++) {
            for (int j = i; j > 0; j--) {
                if (heights[j] < heights[j - 1]) {
                    swap(heights, j, j - 1);
                    count++;
                }
            }
        }
        return count;
    }

    public int heightChecker(int[] heights) {
        int count = 0;
        int[] b = Arrays.copyOf(heights, heights.length);
        Arrays.sort(b);
        for (int i = 0; i < heights.length; i++) {
           if(b[i] != heights[i]){
               count++;
           }
        }
        return count;
    }
}
