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


        public class WordDictionary {
            private class TrieNode {
                TrieNode[] children;
                boolean isLeaf;

                public TrieNode() {
                    children = new TrieNode[26];
                }
            }

            private final TrieNode root;

            public WordDictionary() {
                root = new TrieNode();
            }

            // Adds a word into the data structure.
            public void addWord(String word) {
                TrieNode p = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    int index = c - 'a';
                    if (p.children[index] == null) {
                        TrieNode temp = new TrieNode();
                        p.children[index] = temp;
                    }
                    p = p.children[index];
                }

                p.isLeaf = true;
            }

            // Returns if the word is in the data structure. A word could
            // contain the dot character '.' to represent any one letter.
            public boolean search(String word) {
                return dfsSearch(word, 0, root);
            }

            public boolean dfsSearch(String word, int pos, TrieNode p) {
                if (p.isLeaf && pos == word.length())
                    return true;

                if (pos >= word.length())
                    return false;

                char c = word.charAt(pos);

                if (c == '.') {
                    for (TrieNode child : p.children) {
                        if (child != null) {
                            if (dfsSearch(word, pos + 1, child)) {
                                return true;
                            }
                        }
                    }
                    return false;

                }
                int index = c - 'a';

                if (p.children[index] == null) {
                    return false;
                }

                return dfsSearch(word, pos + 1, p.children[index]);
            }
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
//        private boolean searchPrefix(String word, int pos, TrieNode node) {
//            if (pos == word.length()) {
//                return node.isEnd;
//            }
//            if (word.charAt(pos) == '.') {
//                for (int i = 0; i < node.links.length; i++) {
//                    if (node.links[i] != null && searchPrefix(word, pos + 1, node.links[i])) {
//                        return true;
//                    }
//                }
//            } else {
//                TrieNode next = node.links[word.charAt(pos) - 'a'];
//                return next != null && searchPrefix(word, pos + 1, next);
//            }
//            return false;
//        }
//    }
    }

    // submitted
    static class UsingTrieNodeWithChildrenAsMap {
        private static class TrieNode {
            public boolean isLeaf;
            public Map<Character, TrieNode> children;

            public TrieNode() {
                this.children = new HashMap<>();
                this.isLeaf = false;
            }
        }

        private final TrieNode root = new TrieNode();

        // Adds a word into the data structure.
        public void addWord(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
            }
            curr.isLeaf = true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return dfsSearch(word, root);
        }

        private boolean dfsSearch(String word, TrieNode node) {
            if (word == null || (word.isEmpty())) {
                return node.isLeaf;
            }
            char c = word.charAt(0);
            if (c == '.') {
                for (TrieNode child : node.children.values()) {
                    if (dfsSearch(word.substring(1), child)) {
                        return true;
                    }
                }
            }
            //if character is neither equal to the node nor '.', return false
            if (!node.children.containsKey(c)) {
                return false;
            }
            return dfsSearch(word.substring(1), node.children.get(c));
        }
    }

    static class UsingTrieNodeWithChildrenAsMapAndLighterSearch {
        public class WordDictionary {
            private class TrieNode {
                public boolean isLeaf;
                public Map<Character, TrieNode> children;

                public TrieNode() {
                    this.children = new HashMap<>();
                    this.isLeaf = false;
                }
            }

            private final TrieNode root = new TrieNode();

            // Adds a word into the data structure.
            public void addWord(String word) {
                TrieNode curr = root;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!curr.children.containsKey(c)) {
                        curr.children.put(c, new TrieNode());
                    }
                    curr = curr.children.get(c);
                }
                curr.isLeaf = true;
            }

            // Returns if the word is in the data structure. A word could
            // contain the dot character '.' to represent any one letter.
            public boolean search(String word) {
                return dfsSearch(word, 0, root);
            }

            private boolean dfsSearch(String word, int pos, TrieNode node) {
                if (pos == word.length()) {
                    return node.isLeaf;
                }

                char c = word.charAt(pos);
                if (c == '.') {
                    for (TrieNode child : node.children.values()) {
                        if (dfsSearch(word, pos + 1, child)) {
                            return true;
                        }
                    }
                }

                //if character at position 'pos' is neither equal to the node nor '.', return false
                if (!node.children.containsKey(word.charAt(pos))) {
                    return false;
                }

                return dfsSearch(word, pos + 1, node.children.get(c));
            }
        }
    }


// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}