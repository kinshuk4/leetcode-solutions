package com.vaani.leetcode.string;

import com.vaani.dsa.ds.core.tree.suffix.naive_uncompressed.SuffixTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode.com/problems/longest-duplicate-substring/
 * 1044. Longest Duplicate Substring
 * Hard
 * <p>
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
 * <p>
 * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "banana"
 * Output: "ana"
 * <p>
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: ""
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 2 <= S.length <= 10^5
 * S consists of lowercase English letters.
 */
public class LongestDuplicateSubstring {

    @Test
    public void testSuffixTreeApproach() {
        UsingSuffixTree underTest = new UsingSuffixTree();
        Assert.assertEquals("ana", underTest.longestDupSubstring("banana"));
    }

    // Results in TLE
    static class UsingSuffixTree {
        public String longestDupSubstring(String S) {
            SuffixTree tree = new SuffixTree(S);
            int max = 0;
            String result = "";
            for (int i = 0; i < S.length(); i++) {
                for (int j = S.length(); j > i; j--) {
                    String curr = S.substring(i, j);
                    if (tree.getIndices(curr) != null && tree.getIndices(curr).size() > 1) {
                        return curr;
                    }
                }

            }
            return result;
        }
    }


    // Results in TLE
    static class UsingSuffixArray {
        // https://leetcode.com/problems/longest-duplicate-substring/discuss/700731/Java-Suffix-array-implementation-Memory-limit-exceeded
        public String longestDupSubstring(String S) {
            int N = S.length();
            String[] suffixes = new String[N];
            for (int i = 0; i < N; i++) {
                suffixes[i] = S.substring(i, N);
            }
            Arrays.sort(suffixes);
            String lrs = "";
            for (int i = 0; i < N - 1; i++) {
                String x = lcp(suffixes[i], suffixes[i + 1]);
                if (x.length() > lrs.length()) lrs = x;
            }
            return lrs;
        }

        private static String lcp(String s, String t) {
            int N = Math.min(s.length(), t.length());
            for (int i = 0; i < N; i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    return s.substring(0, i);
                }
            }
            return s.substring(0, N);
        }
    }


    // https://www.youtube.com/watch?v=FQ8hcOOzQMU
    static class UsingRabinKarpBinarySearch {
        public String longestDupSubstring(String S) {
            String result = "";

            int left = 1;
            int right = S.length() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                String dup = getDup(mid, S);

                if (dup != null) {
                    result = dup;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return result;
        }

        private String getDup(int window, String s) {
            long currHash = hash(s.substring(0, window));

            HashSet<Long> set = new HashSet<>();
            set.add(currHash);

            long pow = 1;
            for (int i = 1; i < window; i++) {
                pow = (pow * 31);
            }

            int n = s.length();
            for (int i = window; i < n; i++) {
                currHash = rollingHash(pow, currHash, s.charAt(i - window), s.charAt(i));
                if (!set.add(currHash)) {
                    return s.substring(i - window + 1, i + 1);
                }
            }

            return null;
        }

        private long hash(String s) {
            long h = 0;
            long a = 1;

            int n = s.length();
            for (int k = n; k >= 1; k--) {
                char ch = s.charAt(k - 1);
                h += (ch - 'a' + 1) * a;
                a = (a * 31);
            }

            return h;
        }

        private long rollingHash(long pow, long hash, char left, char right) {
            return (hash - (left - 'a' + 1) * pow) * 31 + (right - 'a' + 1);
        }

    }
}