package com.vaani.leetcode.hashing;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/occurrences-after-bigram/
 * 1078. Occurrences After Bigram
 * Easy
 * <p>
 * Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.
 * <p>
 * For each such occurrence, add "third" to the answer, and return the answer.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
 * Output: ["girl","student"]
 * <p>
 * Example 2:
 * <p>
 * Input: text = "we will we will rock you", first = "we", second = "will"
 * Output: ["we","rock"]
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= text.length <= 1000
 * text consists of space separated words, where each word consists of lowercase English letters.
 * 1 <= first.length, second.length <= 10
 * first and second consist of lowercase English letters.
 */
public class OccurrencesAfterBigram {

    public String[] findOcurrences(String text, String first, String second) {
        List<String> ans = new LinkedList<>();
        boolean one = false;
        boolean two = false;
        for (String word : text.split(" ")) {

            if (two) {
                ans.add(word);
                one = false;
                two = false;
            }
            if (word.equals(first)) {
                one = true;
            } else if (one && word.equals(second)) {
                two = true;
            } else {
                one = false;
                two = false;
            }
        }

        return ans.toArray(new String[0]);
    }
}
