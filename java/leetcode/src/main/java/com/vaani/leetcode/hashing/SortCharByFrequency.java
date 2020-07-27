package com.vaani.leetcode.hashing;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/sort-characters-by-frequency/
 * Given a string, sort it in decreasing order based on the frequency of characters.
 *
 * <p>Example 1:
 *
 * <p>Input: "tree"
 *
 * <p>Output: "eert"
 *
 * <p>Explanation: 'e' appears twice while 'r' and 't' both appear once. So 'e' must appear before
 * both 'r' and 't'. Therefore "eetr" is also a valid answer. Example 2:
 *
 * <p>Input: "cccaaa"
 *
 * <p>Output: "cccaaa"
 *
 * <p>Explanation: Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer. Note
 * that "cacaca" is incorrect, as the same characters must be together.
 *
 * <p>Example 3:
 *
 * <p>Input: "Aabb"
 *
 * <p>Output: "bbAa"
 *
 * <p>Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect. Note that 'A' and 'a' are
 * treated as two different characters.
 */
public class SortCharByFrequency {


    public static void main(String[] args) throws Exception {
        System.out.println(new SortCharByFrequency().frequencySort("askdfkasdkfasdkljfklasdjfkl"));
    }

    public String frequencySort(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        int[] buff = new int[256];

        for (int i = 0, l = s.length(); i < l; i++) {
            buff[s.charAt(i)]++;
        }

        List<CharFrequency> frequencyList = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            if (buff[i] > 0) {
                CharFrequency f = new CharFrequency();
                f.i = i;
                f.c = buff[i];
                frequencyList.add(f);
            }
        }

        frequencyList.sort((o1, o2) -> Integer.compare(o2.c, o1.c));

        StringBuilder resultSB = new StringBuilder();
        for (CharFrequency f : frequencyList) {
            char c = (char) f.i;
            int freq = f.c;
            while (freq-- > 0) {
                resultSB.append(c);
            }
        }
        return resultSB.toString();
    }

    static class CharFrequency {
        int i;
        int c;
    }
}
