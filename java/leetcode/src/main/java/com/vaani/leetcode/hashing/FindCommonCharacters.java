package com.vaani.leetcode.hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/find-common-characters/
 * 1002. Find Common Characters
 * Easy
 * <p>
 * Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 * <p>
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] is a lowercase letter
 */
public class FindCommonCharacters {
    public List<String> commonChars(String[] A) {
        List<String> ans = new ArrayList<>();
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (String str : A) {
            int[] currCnt = new int[26];
            // count each char's frequency in string str.
            str.chars().forEach(c -> ++currCnt[c - 'a']);
            for (int i = 0; i < 26; ++i) {
                // update minimum frequency.
                count[i] = Math.min(currCnt[i], count[i]);
            }
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            String curr = Character.toString(c);
            while (count[c - 'a']-- > 0) {
                ans.add(curr);
            }
        }
        return ans;
    }
}
