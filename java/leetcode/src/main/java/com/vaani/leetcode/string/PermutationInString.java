package com.vaani.leetcode.string;

/**
 * 18/09/2017. Given two strings s1 and s2, write a function to
 * return true if s2 contains the permutation of s1. In other words, one of the first com.vaani.leetcode.string's
 * permutations is the substring of the second string.
 *
 * <p>Example 1: Input:s1 = "ab" s2 = "eidbaooo" Output:True Explanation: s2 contains one
 * permutation of s1 ("ba").
 *
 * <p>Example 2: Input:s1= "ab" s2 = "eidboaoo" Output: False Note: The input strings only contain
 * lower case letters. The length of both given strings is in range [1, 10,000].
 */
public class PermutationInString {


    public static void main(String[] args) throws Exception {
        System.out.println(new PermutationInString.SlidingWindow1().checkInclusion("ab", "eidboaoo"));
        System.out.println(new PermutationInString.SlidingWindow1().checkInclusion("adc", "dcda"));
    }


    // sliding window 1
    static class SlidingWindow1 {
        public boolean checkInclusion(String s1, String s2) {
            if (s2.length() < s1.length()) {
                return false;
            }

            int[] map1 = new int[256];
            int[] map2 = new int[256];

            int w = s1.length();
            int n = s2.length();
            // first window
            for (int i = 0; i < w; i++) {
                map1[s1.charAt(i)]++;
                map2[s2.charAt(i)]++;
            }
            if (isEqual(map1, map2)) {
                return true;
            }

            // remaining windows
            for (int i = 0; i < n - w; i++) {
                map2[s2.charAt(i)]--;
                map2[s2.charAt(i + w)]++;

                if (isEqual(map1, map2)) {
                    return true;
                }
            }

            // another way
//            for (int i = w; i < n; i++) {
//                map2[s2.charAt(i - w)]--;
//                map2[s2.charAt(i)]++;
//
//                if (isEqual(map1, map2)) {
//                    return true;
//                }
//            }

            return false;
        }

        //            for (int i = 1, j = s1.length(), l = s2.length(); j < l; i++, j++) {
//                map2[s2.charAt(i - 1)]--;
//                map2[s2.charAt(j)]++;
//
//                if (isEqual(map1, map2)) {
//                    return true;
//                }
//            }

        private boolean isEqual(int[] map1, int[] map2) {
            boolean equal = true;
            for (int i = 0; i < 256; i++) {
                if (map1[i] != map2[i]) {
                    equal = false;
                    break;
                }
            }
            return equal;
        }
    }

}
