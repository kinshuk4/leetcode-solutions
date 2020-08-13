package com.vaani.leetcode.string;
/**
 * https://leetcode.com/problems/keyboard-row/
 * 500. Keyboard Row
 * Easy
 *
 * <p>Given a List of words, return the words that can be typed using letters of alphabet on only
 * one row's of American keyboard like the image below.
 *
 * <p>Example:
 *
 * <p>Input: ["Hello", "Alaska", "Dad", "Peace"] Output: ["Alaska", "Dad"]
 *
 * <p>Note:
 * <p>
 * You may use one character in the keyboard more than once.
 * You may assume the input string will only contain letters of alphabet.
 */

import java.util.*;
import java.util.stream.Stream;

public class KeyboardRow {
    public static void main(String[] args) {
    }

    public String[] findWords(String[] words) {
        String R1 = "qwertyuiop";
        String R2 = "asdfghjkl";
        String R3 = "zxcvbnm";
        List<String> answer = new ArrayList<>();
        for (String word : words) {
            Set<Character> set = new HashSet<>();
            for (char c : word.toCharArray()) {
                if (R1.indexOf(c) != -1) {
                    set.add('1');
                } else if (R2.indexOf(c) != -1) {
                    set.add('2');
                } else if (R3.indexOf(c) != -1) {
                    set.add('3');
                }
            }
            if (set.size() == 1) {
                answer.add(word);
            }
        }
        String[] ans = new String[answer.size()];
        int i = 0;
        for (String s : answer) {
            ans[i++] = s;
        }
        return ans;
    }

    public String[] findWords2(String[] words) {
        Map<Character, Integer> map = new HashMap<>();

        "qwertyuiop".chars().forEach(e -> map.put((char) e, 1)); // Row 1
        "asdfghjkl".chars().forEach(e -> map.put((char) e, 2));
        "zxcvbnm".chars().forEach(e -> map.put((char) e, 3));


        List<String> ans = new ArrayList<>();
        for (String s : words) {
            String word = s.toLowerCase();
            if (word.trim().length() == 0 || !map.containsKey(word.charAt(0))) {
                continue;
            }
            int currRow = map.get(word.charAt(0));
            boolean isValid = true;
            for (char c : word.toCharArray()) {
                if (!map.containsKey(c) || currRow != map.get(c)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                ans.add(s);
            }
        }

        return ans.toArray(new String[0]);
    }

    public String[] findWords3(String[] words) {
        return Stream.of(words).filter(s -> s.toLowerCase().matches("[qwertyuiop]*|[asdfghjkl]*|[zxcvbnm]*")).toArray(String[]::new);
    }
}
