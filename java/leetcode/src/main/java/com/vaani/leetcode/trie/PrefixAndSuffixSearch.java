package com.vaani.leetcode.trie;


/**
 * https://leetcode.com/problems/prefix-and-suffix-search/
 * 745. Prefix and Suffix Search
 * Hard
 * <p>
 * Design a special dictionary which has some words and allows you to search the words in it by a prefix and a suffix.
 * <p>
 * Implement the WordFilter class:
 * <p>
 * WordFilter(string[] words) Initializes the object with the words in the dictionary.
 * f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * Output
 * [null, 0]
 * <p>
 * Explanation
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 15000
 * 1 <= words[i].length <= 10
 * 1 <= prefix.length, suffix.length <= 10
 * words[i], prefix and suffix consist of lower-case English letters only.
 * At most 15000 calls will be made to the function f.
 */
public class PrefixAndSuffixSearch {
     class WordFilter {
         class TrieNode {
            TrieNode[] children;
            int index;

            public TrieNode() {
                children = new TrieNode[27];
            }

            public void insert(String s, int index) {
                TrieNode cur = root;
                for (char c : s.toCharArray()) {
                    int n = c - 'a';
                    if (cur.children[n] == null) {
                        cur.children[n] = new TrieNode();
                    }
                    cur = cur.children[n];
                    cur.index = index;
                }
            }

            public int startsWith(String prefix) {
                TrieNode cur = root;
                for (char c : prefix.toCharArray()) {
                    TrieNode next = cur.children[c - 'a'];
                    if (next == null) {
                        return -1;
                    }
                    cur = next;
                }
                return cur.index;
            }
        }

        private final TrieNode root;
        private final static String DELIM = "{";

        public WordFilter(String[] words) {
            root = new TrieNode();

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                for (int j = 0; j <= word.length(); j++) {
                    root.insert(word.substring(j) + DELIM + word, i);
                }
            }
        }

        public int f(String prefix, String suffix) {
            return root.startsWith(suffix + DELIM + prefix);
        }
    }
}
