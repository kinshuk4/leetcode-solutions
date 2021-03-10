package com.vaani.leetcode.hashing;

import java.util.*;

/**
 * 820. Short Encoding of Words
 * Medium
 * A valid encoding of an array of words is any reference string s and array of indices indices such that:
 * <p>
 * - words.length == indices.length
 * - The reference string s ends with the '#' character.
 * - For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
 * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["time", "me", "bell"]
 * Output: 10
 * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
 * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
 * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
 * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
 * Example 2:
 * <p>
 * Input: words = ["t"]
 * Output: 2
 * Explanation: A valid encoding would be s = "t#" and indices = [0].
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 7
 * words[i] consists of only lowercase letters.
 */
public class ShortEncodingOfWords {
    public static void main(String[] args) {
        String[] A = {"memo", "me", "mo"};
        System.out.println(new ShortEncodingOfWords().minimumLengthEncoding(A));
    }

    /**
     * <p>Solution: Sort the words by length and then use a hashmap to map each substring of a com.vaani.leetcode.string
     * with its position.
     */

    static class Node {
        String s;
        int len;

        Node(String s, int len) {
            this.s = s;
            this.len = len;
        }
    }

    public int minimumLengthEncoding(String[] words) {
        List<Node> list = new ArrayList<>();
        for (String w : words) {
            list.add(new Node(w, w.length()));
        }
        list.sort((o1, o2) -> Integer.compare(o2.len, o1.len));

        Map<String, Integer> map = new HashMap<>();

        int ans = 0;
        for (Node node : list) {
            String str = node.s;
            if (!map.containsKey(str)) {
                for (int i = 0, l = str.length(); i < l; i++) {
                    map.put(str.substring(i, l), ans + i);
                }
                ans += (str.length() + 1);
            }
        }
        return ans;
    }
}
