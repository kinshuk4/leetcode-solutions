package com.vaani.leetcode.binary_search;

import com.vaani.dsa.algo.array.sorted.SearchInsertPosition;

/**
 * 22/05/2017. Given a sorted array and a target value, return the
 * index if the target is found. If not, return the index where it would be if it were inserted in
 * order.
 *
 * <p>You may assume no duplicates in the array.
 *
 * <p>Here are few examples. [1,3,5,6], 5 → 2 [1,3,5,6], 2 → 1 [1,3,5,6], 7 → 4 [1,3,5,6], 0 → 0
 */
public class SearchInsertPositionSolution {
    public static void main(String[] args) throws Exception {
        int[] A = {1, 3, 5, 6};
        new SearchInsertPosition().searchInsert(A, 5);
    }
}
