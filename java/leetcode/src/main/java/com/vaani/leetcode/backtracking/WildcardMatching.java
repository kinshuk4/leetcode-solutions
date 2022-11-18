package com.vaani.leetcode.backtracking;

/**
 * 21/01/2018. Implement wildcard pattern matching with support
 * for '.' and '*'.
 *
 * <p>'.' Matches any single character. '*' Matches any sequence of characters (including the empty
 * sequence).
 *
 * <p>The matching should cover the entire input string (not partial).
 *
 * <p>The function prototype should be: bool isMatch(const char *s, const char *p)
 *
 * <p>Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true isMatch("aa", "a*") → true isMatch("ab", "?*") → true isMatch("aab",
 * "c*a*b") → false
 *
 * <p>Solution: Maintain two indexes one for string and other one for the pattern. 1. If the
 * characters match in both the indexes or if the char at pattern is '.' then increment both the
 * indexes. 2. If a star(*) is encountered save the position of star in the given string as
 * 'startPosAtStr' and position of star in the pattern as 'starIdx' and this time increment only
 * index for pattern. 3. If the characters do not match and if the start is not encountered
 * previously return false else increment 'startPosAtStr' and assign this as the new index for
 * string and start the new pattern index from starIdx + 1
 */
public class WildcardMatching {

    /**
     * Main method
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println(new WildcardMatching().isMatch("abebd", "?be******e"));
    }

    static class UsingBacktracking {
        public boolean isMatch(String s, String p) {
            return dfs(s, p, 0, 0);
        }

        private boolean dfs(String s, String p, int i, int j) {
            int sn = s.length(), pn = p.length();
            if (j == pn) { // since * in p can match 0 of previous char, so empty string(i==sn) may match p
                return i == sn;
            }
            char pj = p.charAt(j);
            if (j + 1 < pn && p.charAt(j + 1) == '*') { //match *, needs to look at the next char to repeate current char
                if (dfs(s, p, i, j + 2)) {
                    return true;
                }
                while (i < sn && (pj == '.' || pj == s.charAt(i))) {
                    if (dfs(s, p, ++i, j + 2)) {
                        return true;
                    }
                }
            } else if (i < sn && (s.charAt(i) == pj ||    //match char
                    pj == '.')) {              //match dot
                return dfs(s, p, i + 1, j + 1);
            }
            return false;
        }
    }

    public boolean isMatch(String s, String p) {
        int starIdx = -1;
        int starPosAtStr = -1;
        int j = 0;
        for (int i = 0, l = s.length(); i < l; ) {
            if (j < p.length()) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
                    i++;
                    j++;
                } else if (p.charAt(j) == '*') {
                    starIdx = j;
                    starPosAtStr = i;
                    j++; // increment only pattern index. This is because '*' can match also empty string.
                } else if (starIdx != -1) {
                    i = ++starPosAtStr;
                    j = starIdx + 1;
                } else return false;
            } else if (starIdx != -1) {
                i = ++starPosAtStr;
                j = starIdx + 1;
            } else return false;
        }
        // check if the remaining characters in pattern contains only '*'
        while (j < p.length()) {
            if (p.charAt(j) == '*') {
                j++;
            } else break;
        }
        return j == p.length();
    }

    static class UsingCharArrays {

        static class UsingRecursion {

            public boolean isMatch(String s, String p) {
                return isMatch(s.toCharArray(), p.toCharArray(), s.length() - 1, p.length() - 1);
            }

            private boolean isMatch(char[] chars, char[] pattern, int n, int m) {
                // If both the input string and pattern reach their end,
                // return true
                if (m < 0 && n < 0) {
                    return true;
                }

                // If only the pattern reaches its end, return false
                else if (m < 0) {
                    return false;
                }

                // If only the input string reaches its end, return true
                // if the remaining characters in the pattern are all '*'
                else if (n < 0) {
                    for (int i = 0; i <= m; i++) {
                        if (pattern[i] != '*') {
                            return false;
                        }
                    }

                    return true;
                }

                boolean matches = false;

                if (pattern[m] == '*') {
                    // 1. '*' matches with current characters in the input string.
                    // Here, we will move to the next character in the string.

                    // 2. Ignore '*' and move to the next character in the pattern
                    matches = isMatch(chars, pattern, n - 1, m) ||
                            isMatch(chars, pattern, n, m - 1);
                } else {
                    // If the current character is not a wildcard character, it
                    // should match the current character in the input string
                    if (pattern[m] != '.' && pattern[m] != chars[n]) {
                        matches = false;
                    }
                    // check if pattern[0…m-1] matches word[0…n-1]
                    else {
                        matches = isMatch(chars, pattern, n - 1, m - 1);
                    }
                }


                return matches;
            }
        }

        static class UsingTdDp {

            public boolean isMatch(String s, String p) {
                int n = s.length();
                int m = p.length();
                return isMatch(s.toCharArray(), p.toCharArray(), n - 1, m - 1, new boolean[n + 1][m + 1]);
            }

            private boolean isMatch(char[] chars, char[] pattern, int n, int m, boolean[][] cache) {
                // If both the input string and pattern reach their end,
                // return true
                if (m < 0 && n < 0) {
                    return true;
                }

                // If only the pattern reaches its end, return false
                else if (m < 0) {
                    return false;
                }

                // If only the input string reaches its end, return true
                // if the remaining characters in the pattern are all '*'
                else if (n < 0) {
                    for (int i = 0; i <= m; i++) {
                        if (pattern[i] != '*') {
                            return false;
                        }
                    }

                    return true;
                }

                // If the subproblem is encountered for the first time
                if (!cache[n][m]) {
                    if (pattern[m] == '*') {
                        // 1. '*' matches with current characters in the input string.
                        // Here, we will move to the next character in the string.

                        // 2. Ignore '*' and move to the next character in the pattern
                        cache[n][m] = isMatch(chars, pattern, n - 1, m, cache) ||
                                isMatch(chars, pattern, n, m - 1, cache);
                    } else {
                        // If the current character is not a wildcard character, it
                        // should match the current character in the input string
                        if (pattern[m] != '.' && pattern[m] != chars[n]) {
                            cache[n][m] = false;
                        }
                        // check if pattern[0…m-1] matches word[0…n-1]
                        else {
                            cache[n][m] = isMatch(chars, pattern, n - 1, m - 1, cache);
                        }
                    }
                }

                return cache[n][m];
            }
        }
    }
}
