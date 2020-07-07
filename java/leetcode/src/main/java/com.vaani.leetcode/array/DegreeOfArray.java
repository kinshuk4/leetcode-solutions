package com.vaani.leetcode.array;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfArray {
    public static void main(String[] args) {

    }

    public int findShortestSubArray(int[] nums) {
        int maxFrequency = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        Map<Integer, Integer> startIndex = new HashMap<>();
        Map<Integer, Integer> endIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            if (frequencyMap.containsKey(value)) {
                frequencyMap.put(value, frequencyMap.get(value) + 1);
            } else {
                startIndex.put(value, i);
                frequencyMap.put(value, 1);
            }
            endIndex.put(value, i);
            maxFrequency = Integer.max(maxFrequency, frequencyMap.get(value));//Calculate the degree of the array
        }
        int result = nums.length;
        for (int i : frequencyMap.keySet()) {
            if (frequencyMap.get(i) == maxFrequency) {
                int len = endIndex.get(i) - startIndex.get(i) + 1;
                result = Integer.min(result, len);
            }
        }
        return result;
    }
}
