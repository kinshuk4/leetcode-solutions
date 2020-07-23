package com.vaani.leetcode.string;

import java.util.Stack;

import static com.vaani.dsa.ds.utils.generic.ArrayUtils.swap;

/**
 * https://leetcode.com/problems/reverse-only-letters/
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 * <p>
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 * <p>
 * Input: "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 * <p>
 * <p>
 * Note:
 * <p>
 * S.length <= 100
 * 33 <= S[i].ASCIIcode <= 122
 * S doesn't contain \ or "
 */
public class ReverseOnlyLetters {

    static class UsingStack {
        public String reverseOnlyLetters(String S) {
            Stack<Character> letters = new Stack();
            for (char c : S.toCharArray())
                if (Character.isLetter(c))
                    letters.push(c);

            StringBuilder ans = new StringBuilder();
            for (char c : S.toCharArray()) {
                if (Character.isLetter(c))
                    ans.append(letters.pop());
                else
                    ans.append(c);
            }

            return ans.toString();
        }
    }

    // two pointers
    public String reverseOnlyLetters(String S) {
        char[] chars = S.toCharArray();
        int i = 0;
        int j = S.length() - 1;

        while (i < j){
            while (i < j && !Character.isLetter(S.charAt(i))){
                i++;
            }

            while (i < j && !Character.isLetter(S.charAt(j))){
                j--;
            }

            swap(chars, i++, j--);
        }

        return new String(chars);
    }
}
