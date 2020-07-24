package com.vaani.leetcode.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/partition-labels/
 * A string S of lowercase letters is given. We want
 * to partition this string into as many parts as possible so that each letter appears in at most
 * one part, and return a list of integers representing the size of these parts.
 *
 * <p>Example 1: Input: S = "ababcbacadefegdehijhklij" Output: [9,7,8] Explanation: The partition is
 * "ababcbaca", "defegde", "hijhklij". This is a partition so that each letter appears in at most
 * one part. A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into
 * less parts. Note:
 *
 * <p>S will have length in range [1, 500]. S will consist of lowercase letters ('a' to 'z') only.
 */
public class PartitionLabels {

    public static void main(String[] args) throws Exception {
        System.out.println(new PartitionLabels().partitionLabels("abc"));
        System.out.println(new PartitionLabels().partitionLabels3("ababcbacadefegdehijhklij"));
    }

    /*
     Solution O(n): Maintain a hashmap index of last occurrence of a character and do a linear
     check for max index, get the length and add it to the result set.
     */
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.trim().isEmpty()) return new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            map.putIfAbsent(c, i);
        }
        List<Integer> result = new ArrayList<>();
        int start = 0;
        int max = map.get(S.charAt(0));
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (map.get(c) > max) {
                max = map.get(c);
            } else if (i == max) {
                result.add(max - start + 1);
                if (i < S.length() - 1) {
                    start = i + 1;
                    max = map.get(S.charAt(i + 1));
                }
            }
        }
        return result;
    }

    public List<Integer> partitionLabels2(String S) {
        List<Integer> result = new ArrayList<>();
        if (S == null || S.trim().isEmpty()) {
            return result;
        }
        int[] lastIndices = new int[26];
        int n = S.length();
        for (int i = 0; i < n; i++) {
            char c = S.charAt(i);
            lastIndices[c - 'a'] = i;
        }
        int i = 0;

        while (i < n) {
            int end = lastIndices[S.charAt(i) - 'a'];
            int j = i;
            while (j != end) {
                end = Math.max(end, lastIndices[S.charAt(j++) - 'a']);
            }
            result.add(j - i + 1);
            i = j + 1;
        }

        return result;
    }

    public List<Integer> partitionLabels3(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            map.putIfAbsent(c, i);
            map.remove(c);
            map.put(c, i);
        }
        char start = S.charAt(0);
        int currMax = map.get(start);
        int startIndex = 0;
        List<Integer> list = new ArrayList<>();
        while (true) {
            int i = startIndex;
            for (; i <= currMax; i++) {
                char c = S.charAt(i);
                int pos = map.get(c);
                currMax = Math.max(currMax, pos);
            }
            if (i > currMax && i < S.length()) {
                list.add(i - startIndex);
                startIndex = i;
                currMax = map.get(S.charAt(i));
            } else {
                if (i == S.length()) {
                    list.add(i - startIndex);
                    break;
                }
            }
        }
        return list;
    }
}
