package com.vaani.leetcode.design;

import java.util.HashMap;
import java.util.Map;
/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 * 211. Add and Search Word - Data structure design
 * Medium
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or '.'. A '.' means
 * it can represent any one letter.
 * <p>
 * For example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * <p>
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree)
 * first.
 * <p>
 * Company Tags: Facebook
 * Tags: Backtracking, Trie, Design
 * Similar Problems: (M) Implement Trie (Prefix Tree)
 */
public class AddAndSearchWordDataStructureDesign {
    static class UsingTrieNodeAsChildrenArray {
        static class TrieNode {

            private final int R = 26;
            TrieNode[] links;
            boolean isEnd;

            public TrieNode() {
                links = new TrieNode[R];
            }
        }

        /**
         * Trie.
         * Create a trie in the word dictionary class.
         */
        public class WordDictionary {

            TrieNode root = new TrieNode();

            // Adds a word into the data structure.
            public void addWord(String word) {
                if (word == null || word.length() == 0) {
                    return;
                }
                TrieNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (node.links[c - 'a'] == null) {
                        node.links[c - 'a'] = new TrieNode();
                    }
                    node = node.links[c - 'a'];
                }
                node.isEnd = true;
            }

            // Returns if the word is in the data structure. A word could
            // contain the dot character '.' to represent any one letter.
            public boolean search(String word) {
                if (word == null) {
                    return false;
                }
                return searchPrefix(word, 0, root);
            }

            /**
             * Recursive.
             * A bit modification to the original search of Trie.
             * To handle '.'.
             * If the character is not a dot:
             * | Check if the next node is null, if yes, return false.
             * | If not, go on to the next node.
             * If the character is a dot, it can match any letter.
             * That means we must search every possible subtree/subtrie.
             * | So for each child of current node, search the rest characters of the word.
             * | If there is one match, return true.
             * | If there is no match at all, return false.
             * In the end, check the node's flag. If true, there is a word, return true.
             */
            private boolean search(TrieNode root, String word) {
                if (root == null) return false;
                TrieNode node = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (c != '.') {
                        if (node.links[c - 'a'] == null) return false;
                        node = node.links[c - 'a'];
                    } else {
                        for (TrieNode n : node.links) {
                            if (search(n, word.substring(i + 1))) return true;
                        }
                        return false;
                    }
                }
                return node.isEnd;
            }

            /**
             * Backtracking.
             * Statement: Given a word, a position, and a trie node, find whether the word is in the trie.
             * Recurrent Relation:
             * The word is in the trie if: Current character at pos match + Other characters from pos + 1 are in too.
             * Base case:
             * When subset is empty, return whether the node is end.
             * Complete action:
             * Current char can be '.' or a letter.
             * If it's not dot:
             * | Get the next node.
             * | Return next is not null && search(word, pos + 1, next).
             * <p>
             * If it's dot, how to deal with it? '.' can match any character.
             * | As long as current node has non-null link, search the rest of the prefix in trie.
             * | If one of them returns true, return true.
             * Return false.
             */
            private boolean searchPrefix(String word, int pos, TrieNode node) {
                if (pos == word.length()) {
                    return node.isEnd;
                }
                if (word.charAt(pos) == '.') {
                    for (int i = 0; i < node.links.length; i++) {
                        if (node.links[i] != null && searchPrefix(word, pos + 1, node.links[i])) {
                            return true;
                        }
                    }
                } else {
                    TrieNode next = node.links[word.charAt(pos) - 'a'];
                    return next != null && searchPrefix(word, pos + 1, next);
                }
                return false;
            }
        }
    }

    // submitted
    static class UsingTrieNodeWithChildrenAsMap {
        private static class TrieNode {
            public boolean isWord;
            public Map<Character, TrieNode> children;

            public TrieNode() {
                this.children = new HashMap<>();
                this.isWord = false;
            }
        }

        private TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            TrieNode current = root;
            int n = word.length();
            for (int i = 0; i < n; i++) {
                char c = word.charAt(i);
                if (current.children.containsKey(c)) {
                    current = current.children.get(c);
                } else {
                    TrieNode node = new TrieNode();
                    current.children.put(c, node);
                    current = node;
                }
            }
            current.isWord = true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return search(word, root);
        }

        private boolean search(String word, TrieNode node) {
            if (word == null || (word.isEmpty())) {
                return node.isWord;
            }
            char c = word.charAt(0);
            if (c != '.') {
                if (!node.children.containsKey(c)) {
                    return false;
                } else {
                    return search(word.substring(1), node.children.get(c));
                }
            } else {
                for (TrieNode child : node.children.values()) {
                    if (search(word.substring(1), child)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}