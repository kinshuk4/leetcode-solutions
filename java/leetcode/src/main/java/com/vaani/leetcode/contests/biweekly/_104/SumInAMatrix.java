package com.vaani.leetcode.contests.biweekly._104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

public class SumInAMatrix {
    public static void main(String[] args) {
        SumInAMatrix underTest = new SumInAMatrix();
        int[][] nums = {{7,2,1},{6,4,2},{6,5,3},{3,2,1}};
        assertEquals(15, underTest.matrixSum(nums));
    }

    public int matrixSum(int[][] nums) {
        int m = nums.length;
        int n = nums[0].length;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<PriorityQueue<Integer>> heapArr = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            heapArr.add(new PriorityQueue<>(Collections.reverseOrder()));
            for (int j = 0; j < n; j++) {
                heapArr.get(i).add(nums[i][j]);
            }
        }
        int score = 0;

        for (int j = 0; j < n; j++) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                int curr = heapArr.get(i).poll();
                max = Math.max(max, curr);
            }
            score += max;
        }
        return score;
    }
}
