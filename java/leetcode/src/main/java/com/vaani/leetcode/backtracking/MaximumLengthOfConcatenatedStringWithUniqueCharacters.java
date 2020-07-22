package com.vaani.leetcode.backtracking;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 * <p>
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 * <p>
 * Return the maximum possible length of s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 * <p>
 * Example 2:
 * <p>
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 */
public class MaximumLengthOfConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        List<String> arr = List.of("un", "iq", "ue");
        UsingDFS dfs = new UsingDFS();
        Assert.assertEquals(4, dfs.maxLength(arr));

    }

    /*
    Initial the result res to include the case of empty string "".
    res include all possible combinations we find during we iterate the input.

    Itearte the the input strings,
    but skip the word that have duplicate characters.
    The examples is kind of misleading,
    but the input string can have duplicate characters,
    no need to considerate these strings.

    For each string,
    we check if it's conflit with the combination that we found.
    If they have intersection of characters, we skip it.
    If not, we append this new combination to result.

    return the maximum length from all combinations.
     */
    static class UsingSet {
        public int maxLength(List<String> arr) {
            List<Integer> dp = new ArrayList<>();
            dp.add(0);

            int res = 0;
            for (String s : arr) {
                int a = 0, dup = 0;
                for (char c : s.toCharArray()) {
                    dup |= a & (1 << (c - 'a')); // sets the position to 1 if there exiss some character in the current word
                    // (if the character is 'a', then set position 0 as 1)

                    a |= 1 << (c - 'a'); // check whether there are some duplicate characters If there is, some position in dup will be set as 1, which will make dup larger than 0. Then we do not need to consider this word definitely.
                }
                if (dup > 0) {
                    continue;
                }
                for (int i = dp.size() - 1; i >= 0; --i) {
                    if ((dp.get(i) & a) > 0) continue;
                    dp.add(dp.get(i) | a);
                    res = Math.max(res, Integer.bitCount(dp.get(i) | a));
                }
            }
            return res;
        }
    }

    static class UsingDFS {

        public int maxLength(List<String> arr) {
            int[] result = new int[1]; // because java is pass by value

            // Remove all the strings with non unique characters
            arr = arr.stream().filter(x -> uniqueCharCount(x) != -1).collect(Collectors.toList());
            maxUnique(arr, 0, "", result);
            return result[0];
        }

        private void maxUnique(List<String> arr, int i, String current, int[] result) {
            if (i == arr.size() && uniqueCharCount(current) > result[0]) {
                result[0] = current.length();
                return;
            }
            if (i == arr.size()) {
                return;
            }
            maxUnique(arr, i + 1, current, result); // we don't include the i+1 string
            maxUnique(arr, i + 1, current + arr.get(i), result); // we include the i+1 string
        }

        // returns -1 when same string has non unique char otherwise length of string
        private int uniqueCharCount(String s) {
            int[] counts = new int[26];
            for (char ch : s.toCharArray()) {
                if (counts[ch - 'a']++ > 0) {
                    return -1;
                }
            }
            return s.length();
        }


    }
}
