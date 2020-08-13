package com.vaani.leetcode.hashing;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-number-of-balloons/
 * 1189. Maximum Number of Balloons
 * Easy
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * <p>
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 * <p>
 * Example 1:
 * <p>
 * Input: text = "nlaebolko"
 * Output: 1
 * Example 2:
 * <p>
 * Input: text = "loonbalxballpoon"
 * Output: 2
 * Example 3:
 * <p>
 * Input: text = "leetcode"
 * Output: 0
 */
public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        Assert.assertEquals(0, new MaximumNumberOfBalloons().maxNumberOfBalloons("balon"));
    }

    public int maxNumberOfBalloons(String text) {
        String balloon = "balloon";
        Map<Character, Integer> map = new HashMap<>();
        balloon.chars().forEach(c -> map.put((char) c, map.getOrDefault((char) c, 0) + 1));
        int[] A = new int[26];
        text.chars().forEach(c -> ++A[c - 'a']);
        int ans = Integer.MAX_VALUE;
        for (char c : map.keySet()) {
            ans = Math.min(ans, A[c - 'a'] / map.get(c));
        }
        return ans;
    }
}
