package com.vaani.leetcode.string;

import com.vaani.dsa.ds.core.trie.basic.Trie;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * https://leetcode.com/problems/replace-words/
 * 648. Replace Words
 * Medium
 *In English, we have a concept called root, which can
 * be followed by some other words to form another longer word - let's call this word successor. For
 * example, the root an, followed by other, which can form another word another.
 *
 * <p>Now, given a dictionary consisting of many roots and a sentence. You need to replace all the
 * successor in the sentence with the root forming it. If a successor has many roots can form it,
 * replace it with the root with the shortest length.
 *
 * <p>You need to output the sentence after the replacement.
 *
 * <p>Example 1:
 *
 * <p>Input: dict = ["cat", "bat", "rat"] sentence = "the cattle was rattled by the battery" Output:
 * "the cat was rat by the bat"
 *
 * <p>Note:
 *
 * <p>The input will only have lower-case letters. 1 <= dict words number <= 1000 1 <= sentence
 * words number <= 1000 1 <= root length <= 100 1 <= sentence words length <= 1000
 *
 * <p>Solution: O(w + S) where w is the max length of each word in the dictionary and S is the
 * length of the string. Add all the words in the dictionary to a trie and evaluate each word in the
 * string to check if it matches any path in the trie. Terminate the search as soon as a leaf node
 * in the trie has been reached.
 */
public class ReplaceWords {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("a", "aa", "aaa");
        String sentence = "aa aa";

        ReplaceWords underTest = new ReplaceWords();
        System.out.println(underTest.replaceWords(words, sentence));

        String sentence2 = "the cattle was rattled by the battery";
        List<String> dict = List.of("cat", "bat", "rat");
        Assert.assertEquals("the cat was rat by the bat", underTest.replaceWords(dict, sentence2));

    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie root = new Trie();
        dict.forEach(root::insert);
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        Stream.of(words)
                .map(
                        w -> {
                            String s = root.shortestMatchingPrefix(w);
                            return s.isEmpty() ? w.concat(" ") : s.concat(" ");
                        })
                .forEach(result::append);
        return result.toString().trim();
    }


}
