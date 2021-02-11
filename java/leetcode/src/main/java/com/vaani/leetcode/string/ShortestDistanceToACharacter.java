package com.vaani.leetcode.string;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/shortest-distance-to-a-character/
 * 821. Shortest Distance to a Character
 * Easy
 * <p>
 * Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the shortest distance from s[i] to the character c in s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "loveleetcode", c = "e"
 * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * Example 2:
 * <p>
 * Input: s = "aaab", c = "b"
 * Output: [3,2,1,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s[i] and c are lowercase English letters.
 * c occurs at least once in s.
 */
public class ShortestDistanceToACharacter {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();

        int[] leftDistance = new int[n];
        int[] rightDistance = new int[n];

        Arrays.fill(leftDistance, Integer.MAX_VALUE);
        Arrays.fill(rightDistance, Integer.MAX_VALUE);

        int currDistance = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                currDistance = 0;
            } else {
                if (currDistance != Integer.MAX_VALUE) {
                    currDistance++;
                }
            }
            rightDistance[i] = currDistance;
        }
        currDistance = Integer.MAX_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                currDistance = 0;
            } else {
                if (currDistance != Integer.MAX_VALUE) {
                    currDistance++;
                }
            }
            leftDistance[i] = currDistance;
        }

        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            ans[i] = Math.min(rightDistance[i], leftDistance[i]);
        }
        return ans;

    }
}
