package com.vaani.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 * Medium
 * <p>
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * <p>
 * Return a list of all possible strings we could create. You can return the output in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: S = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4","3Z4"]
 * Example 3:
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 * Example 4:
 * <p>
 * Input: S = "0"
 * Output: ["0"]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * S will be a string with length between 1 and 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public static void main(String[] args) throws Exception {
        System.out.println(new LetterCasePermutation().letterCasePermutation("a1b2"));
    }

    /**
     * <p>Solution: O(N x 2 ^ N) Backtrack and generate all possible combinations.
     */
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        backtrack(S, result, 0, "");
        return result;
    }

    private void backtrack(String s, List<String> result, int i, String r) {
        if (i == s.length()) {
            result.add(r);
        } else {
            if (Character.isAlphabetic(s.charAt(i))) {
                backtrack(s, result, i + 1, r + s.charAt(i));
                if (Character.isLowerCase(s.charAt(i))) {
                    backtrack(s, result, i + 1, r + Character.toUpperCase(s.charAt(i)));
                } else {
                    backtrack(s, result, i + 1, r + Character.toLowerCase(s.charAt(i)));
                }
            } else {
                backtrack(s, result, i + 1, r + s.charAt(i));
            }
        }
    }
}
