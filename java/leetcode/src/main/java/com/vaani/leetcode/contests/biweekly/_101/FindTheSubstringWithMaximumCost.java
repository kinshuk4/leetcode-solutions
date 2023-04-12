package com.vaani.leetcode.contests.biweekly._101;

import java.util.*;

public class FindTheSubstringWithMaximumCost {
    public static void main(String[] args) {
        FindTheSubstringWithMaximumCost underTest = new FindTheSubstringWithMaximumCost();
//        assertEquals(59, underTest.splitNum(4325));
    }

    public int maxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        for (int i = 0; i < arr.length; i++) {
            maxEndingHere = maxEndingHere + arr[i];
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
            maxEndingHere = Math.max(maxEndingHere, 0);
        }

        return maxSoFar;
    }

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> valMap = new HashMap<>();
        for (int i = 0; i < alpha.length(); i++) {
            valMap.put(alpha.charAt(i), i + 1);
        }
        for (int i = 0; i < vals.length; i++) {
            valMap.put(chars.charAt(i), vals[i]);
        }

        int[] valArr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            valArr[i] = valMap.get(s.charAt(i));
        }
        int ans = maxSubarray(valArr);
        return Math.max(ans, 0);
    }

    public int maximumCostSubstring3(String s, String chars, int[] vals) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> valMap = new HashMap<>();
        for (int i = 0; i < alpha.length(); i++) {
            valMap.put(alpha.charAt(i), i + 1);
        }
        for (int i = 0; i < vals.length; i++) {
            valMap.put(chars.charAt(i), vals[i]);
        }
        int max = 0;
        int maxFull = 0;
        int curr = 0;
        for (int i = 0; i < s.length(); i++) {
            maxFull += valMap.get(s.charAt(i));
            curr = valMap.get(s.charAt(i));
            max = Math.max(curr, max);
            for (int j = i + 1; j < s.length(); j++) {
                curr += valMap.get(s.charAt(j));
                max = Math.max(curr, max);
            }
            curr = 0;
        }

        return Math.max(max, maxFull);
    }


    public int maximumCostSubstring4(String s, String chars, int[] vals) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> valMap = new HashMap<>();
        for (int i = 0; i < alpha.length(); i++) {
            valMap.put(alpha.charAt(i), i + 1);
        }
        for (int i = 0; i < vals.length; i++) {
            valMap.put(chars.charAt(i), vals[i]);
        }
        int max = 0;
        // int maxFull = 0;
        int curr = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cv = valMap.get(c);
            // maxFull += cv;
            curr = cv;
            if (curr < 0) {
                curr = 0;
                continue;
            }
            max = Math.max(curr, max);
            for (int j = i + 1; j < s.length(); j++) {
                curr += valMap.get(s.charAt(j));
                max = Math.max(curr, max);
            }
            curr = 0;
        }

        return max;
    }

    // TLE
    public int maximumCostSubstring2(String s, String chars, int[] vals) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        Map<Character, Integer> valMap = new HashMap<>();
        for (int i = 0; i < alpha.length(); i++) {
            valMap.put(alpha.charAt(i), i + 1);
        }
        for (int i = 0; i < vals.length; i++) {
            valMap.put(chars.charAt(i), vals[i]);
        }
        int max = 0;
        int maxFull = 0;
        int curr = 0;
        for (int i = 0; i < s.length(); i++) {
            maxFull += valMap.get(s.charAt(i));
//            curr += s.charAt(i);
            for (int w = 1; w < s.length(); w++) {
                if (i + w > s.length()) break;
                String sub = s.substring(i, i + w);
                curr = 0;
                for (int k = 0; k < sub.length(); k++) {
                    curr += valMap.get(sub.charAt(k));
                }
                max = Math.max(curr, max);
            }
//            curr -= s.charAt(i);
        }

        return Math.max(max, maxFull);
    }
}
