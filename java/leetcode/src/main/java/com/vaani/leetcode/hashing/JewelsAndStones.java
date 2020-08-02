package com.vaani.leetcode.hashing;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/jewels-and-stones/
 * 771. Jewels and Stones
 * Easy
 * <p>
 * You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.
 * <p>
 * The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".
 * <p>
 * Example 1:
 * <p>
 * Input: J = "aA", S = "aAAbbbb"
 * Output: 3
 * <p>
 * Example 2:
 * <p>
 * Input: J = "z", S = "ZZ"
 * Output: 0
 */
public class JewelsAndStones {
    // submitted
    public int numJewelsInStones(String J, String S) {
        Set<Character> jewels = J.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        for (char c : J.toCharArray()) {
            jewels.add(c);
        }

        int count = 0;
        for (char c : S.toCharArray()) {
            if (jewels.contains(c))
                count++;
        }
        return count;
    }

    public int numJewelsInStones2(String J, String S) {
        int[] hash = new int[256];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i)]++;
        }
        int count = 0;
        for (int i = 0; i < J.length(); i++) {
            count += hash[J.charAt(i)];
        }
        return count;

    }
}
