package com.vaani.leetcode.biweekly;

import java.util.*;

public class FindXorBeautyOfArray {
    public int xorBeauty(int[] nums) {
        Map<String, Integer> orMap = new HashMap<>();
        Map<String, Integer> andMap = new HashMap<>();
        int xorBeauty = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    int currBeauty = getXorBeauty(nums[i], nums[j], nums[k], orMap, andMap);
                    xorBeauty = xorBeauty ^ currBeauty;
                }
            }
        }

        return xorBeauty;
    }

    private int getXorBeauty(int a, int b, int c, Map<String, Integer> orMap, Map<String, Integer> andMap) {
        int orVal = 0;
        String orKey1 = a + "-" + b;

        if (orMap.containsKey(orKey1)) {
            orVal = orMap.get(orKey1) ;
        } else {
            orVal = a | b;
            String orKey2 = b + "-" + a;
            orMap.put(orKey1, orVal);
            orMap.put(orKey2, orVal);
        }

        int andVal = 0;
        String andKey1 = orVal + "-" + c;

        if (andMap.containsKey(andKey1)) {
            andVal = andMap.get(andKey1) ;
        } else {
            andVal = orVal & c;
            String andKey2 = c + "-" + orVal;
            andMap.put(andKey1, andVal);
            andMap.put(andKey2, andVal);
        }
        return andVal;
    }
}
