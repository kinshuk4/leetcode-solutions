package com.vaani.leetcode.hashing;

import java.util.Set;
import java.util.stream.Collectors;

public class JewelsandStones {
    public int numJewelsInStones(String J, String S) {
        Set<Character> jewels = J.chars().mapToObj(e->(char)e).collect(Collectors.toSet());
        for (char c : J.toCharArray()) {
            jewels.add(c);
        }

        int cnt = 0;
        for (char c : S.toCharArray()) {
            if (jewels.contains(c))
                cnt++;
        }
        return cnt;
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
