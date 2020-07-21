package com.vaani.leetcode.string;

import java.util.*;

/**
 * https://leetcode.com/problems/reorganize-string/
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result.  If not possible, return the empty string.
 *
 * Example 1:
 *
 * Input: S = "aab"
 * Output: "aba"
 *
 * Example 2:
 *
 * Input: S = "aaab"
 * Output: ""
 *
 * Note:
 *
 *     S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    public String reorganizeString(String S) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : S.toCharArray()) {
            int count = frequencyMap.getOrDefault(c, 0) + 1;
            // Impossible to form a solution
            if (count > (S.length() + 1) / 2) return "";
            frequencyMap.put(c, count);
        }
        // Greedy: fetch char of max count as next char in the result.
        // Use PriorityQueue to store pairs of (char, count) and sort by count DESC.
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));
        maxHeap.addAll(frequencyMap.keySet());

        // Build the result.
        StringBuilder sb = new StringBuilder();
        while (maxHeap.size() > 1) {
            char curr = maxHeap.poll();
            char next = maxHeap.poll();
            sb.append(curr);
            sb.append(next);

            frequencyMap.put(curr, frequencyMap.get(curr) - 1);
            frequencyMap.put(next, frequencyMap.get(next) - 1);

            if(frequencyMap.get(curr) > 0){
                maxHeap.offer(curr);
            }
            if(frequencyMap.get(next) > 0){
                maxHeap.offer(next);
            }
        }

        if(!maxHeap.isEmpty()){
            char last = maxHeap.poll();

            if(frequencyMap.get(last) > 1){
                return "";
            }
            sb.append(last);
        }

        return sb.toString();
    }
}
