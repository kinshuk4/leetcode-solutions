package com.vaani.leetcode.dp;

import java.util.*;

/**
 * 943. Find the Shortest Superstring
 * Hard
 * <p>
 * Given an array of strings words, return the smallest string that contains each string in words as a substring. If there are multiple valid strings of the smallest length, return any of them.
 * <p>
 * You may assume that no string in words is a substring of another string in words.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["alex","loves","leetcode"]
 * Output: "alexlovesleetcode"
 * Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
 * Example 2:
 * <p>
 * Input: words = ["catg","ctaagt","gcta","ttca","atgcatc"]
 * Output: "gctaagttcatgcatc"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 12
 * 1 <= words[i].length <= 20
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 */
public class FindTheShortestSuperstring {

    public String shortestSuperstring(String[] words) {
        int n = words.length;
        int m = 1 << n;
        int[][] path = new int[m][n];
        int[][] graph = buildGraph(words);
        int min = Integer.MAX_VALUE;
        int last = 0;

        Integer[][] dp = new Integer[m][n];


        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int l = 1 << j;
                if ((i & l) == 0) {
                    continue;
                }
                int prev = i - l;
                if (prev == 0) {
                    dp[i][j] = words[j].length();
                    continue;
                }
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (dp[prev][k] != null && dp[i][j] > dp[prev][k] + graph[k][j]) {
                        dp[i][j] = dp[prev][k] + graph[k][j];
                        path[prev][j] = k;  // could also be [prev][i], but not saving space;
                    }
                }
                if (i == m - 1 && dp[i][j] != null && dp[i][j] < min) {
                    min = dp[i][j];
                    last = j;
                }
            }
        }

        List<Integer> route = new ArrayList<>();
        int curr = m - 1;
        while (curr > 0) {
            route.add(last);
            curr -= (1 << last);
            last = path[curr][last];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(words[route.get(route.size() - 1)]);
        for (int i = route.size() - 2; i >= 0; i--) {
            sb.append(words[route.get(i)].substring(words[route.get(i)].length() - graph[route.get(i + 1)][route.get(i)]));
        }
        return sb.toString();

    }

    private int[][] buildGraph(String[] words) {
        int n = words.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = cost(words[i], words[j]);
                graph[j][i] = cost(words[j], words[i]);
            }
        }
        return graph;
    }

    private int cost(String a, String b) {
        int m = a.length();
        int n = b.length();
        for (int i = Math.min(m, n); i > 0; i--) {
            boolean isSame = true;
            for (int j = 0; j < i; j++) {
                if (a.charAt(m - i + j) != b.charAt(j)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                return n - i;
            }
        }
        return n;
    }
}
