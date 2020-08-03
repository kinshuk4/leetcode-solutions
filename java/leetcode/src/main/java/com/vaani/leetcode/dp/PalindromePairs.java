package com.vaani.leetcode.dp;

import java.util.*;

/**
 * 25/01/2018. Given a list of unique words, find all pairs of
 * distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e.
 * words[i] + words[j] is a palindrome.
 *
 * <p>Example 1: Given words = ["bat", "tab", "cat"] Return [[0, 1], [1, 0]] The palindromes are
 * ["battab", "tabbat"] Example 2: Given words = ["abcd", "dcba", "lls", "s", "sssll"] Return [[0,
 * 1], [1, 0], [3, 2], [2, 4]] The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 *
 * <p>Solution O(n x m ^ 2) where m is the average length of each string and n is the number of
 * strings.
 */
public class PalindromePairs {
    public static void main(String[] args) throws Exception {
        String[] words = {"", "aa"};
        List<List<Integer>> result = new PalindromePairs.UsingTrie2().palindromePairs(words);
        result.stream().map(x -> (x.get(0) + " " + x.get(1))).forEach(System.out::println);
    }

    //https://leetcode.com/problems/palindrome-pairs/discuss/559259/Java-AC-Brute-force-solution-with-optimisation-to-avoid-creating-a-new-string
    static class BruteForceWithOptimization {
        public List<List<Integer>> palindromePairs(String[] words) {
            int n = words.length;
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isPalindrome(words[i], words[j])) {
                        result.add(Arrays.asList(i, j));
                    }
                    if (isPalindrome(words[j], words[i])) {
                        result.add(Arrays.asList(j, i));
                    }
                }
            }
            return result;
        }

        private boolean isPalindrome(String word1, String word2) {
            int start = 0;
            int l1 = word1.length();
            int l2 = word2.length();
            int end = l1 + l2 - 1;

            while (start < end) {
                char ch1 = start < l1 ? word1.charAt(start) : word2.charAt(start - l1);
                char ch2 = end >= l1 ? word2.charAt(end - l1) : word1.charAt(end);
                if (ch1 != ch2) {
                    return false;
                }
                start++;
                end--;
            }
            return true;
        }
    }

//    static class UsingTrie {
//        // Solution O(n x m ^ 2) where m is the average length of each string and n is the number of strings.
//        static class TrieNode {
//            Map<Character, TrieNode> children;
//            int index;
//            boolean isWord;
//
//            public TrieNode() {
//                children = new HashMap<>();
//            }
//        }
//
//        static class Trie {
//            TrieNode root = new TrieNode();
//
//            public void insert(String word, int pos) {
//                TrieNode curr = root;
//                for (int i = 0; i < word.length(); i++) {
//                    char c = word.charAt(i);
//                    TrieNode child = curr.children.get(c);
//                    boolean isWord = i == word.length() - 1;
//                    if (child == null) {
//                        child = new TrieNode();
//                        curr.children.put(c, child);
//                    }
//
//                    if (isWord) {
//                        child.isWord = true;
//                        child.index = pos;
//                    }
//                    curr = child;
//
//                }
//            }
//        }
//
//
//        public List<List<Integer>> palindromePairs(String[] words) {
//            Trie forwardTrie = new Trie(); // maintain a forward and backward trie
//            Trie backwardTrie = new Trie();
//            Map<Integer, Set<Integer>> links = new HashMap<>(); // maintain links to avoid duplicates
//            for (int i = 0; i < words.length; i++) {
//                forwardTrie.insert(words[i], i);
//                backwardTrie.insert(new StringBuilder(words[i]).reverse().toString(), i);
//            }
//            List<List<Integer>> result = new ArrayList<>();
//            for (int i = 0; i < words.length; i++) {
//                String word = words[i];
//                boolean[][] T = new boolean[word.length()][word.length()];
//                for (int j = 0, l = word.length(); j < l; j++) {
//                    T[j][j] = true;
//                    int k = j + 1;
//                    if (k < l) {
//                        if (word.charAt(j) == word.charAt(k)) {
//                            T[j][k] = true;
//                        }
//                    }
//                }
//                for (int m = 2, l = word.length(); m < l; m++) {
//                    for (int j = 0, k = m; j < l && k < l; j++, k++) {
//                        if (word.charAt(j) == word.charAt(k) && T[j + 1][k - 1]) {
//                            T[j][k] = true;
//                        }
//                    }
//                }
//                TrieNode subTrie = backwardTrie;
//                if (subTrie.getSubTrie(null) != null && !word.isEmpty() && T[0][word.length() - 1]) {
//                    TrieNode emptySubTrie = subTrie.getSubTrie(null);
//                    result.add(Arrays.asList(i, emptySubTrie.getIndex()));
//                    result.add(Arrays.asList(emptySubTrie.getIndex(), i));
//                }
//                for (int j = 0, l = word.length(); j < l; j++) {
//                    char c = word.charAt(j);
//                    subTrie = subTrie.getSubTrie(c);
//                    if (subTrie == null) break;
//                    TrieNode indexNode = subTrie.getSubTrie(null);
//                    if (indexNode != null) {
//                        if ((j == l - 1) || (T[j + 1][l - 1])) {
//                            if (indexNode.getIndex() != i) { // ignore same index
//                                Set<Integer> linkedTo = links.get(i);
//                                if (linkedTo == null) {
//                                    linkedTo = new HashSet<>();
//                                    links.put(i, linkedTo);
//                                }
//                                if (!linkedTo.contains(indexNode.getIndex())) {
//                                    linkedTo.add(indexNode.getIndex());
//                                    result.add(Arrays.asList(i, indexNode.getIndex()));
//                                }
//                            }
//                        }
//                    }
//                }
//                subTrie = forwardTrie;
//                for (int j = word.length() - 1; j >= 0; j--) {
//                    char c = word.charAt(j);
//                    subTrie = subTrie.getSubTrie(c);
//                    if (subTrie == null) break;
//                    TrieNode indexNode = subTrie.getSubTrie(null);
//                    if (indexNode != null) {
//                        if ((j == 0) || (T[0][j - 1])) {
//                            if (indexNode.getIndex() != i) { // ignore same index
//                                Set<Integer> linkedTo = links.get(indexNode.getIndex());
//                                if (linkedTo == null) {
//                                    linkedTo = new HashSet<>();
//                                    links.put(indexNode.getIndex(), linkedTo);
//                                }
//                                if (!linkedTo.contains(i)) {
//                                    linkedTo.add(i);
//                                    result.add(Arrays.asList(indexNode.getIndex(), i));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            return result;
//        }
//    }

    static class UsingTrie2 {

        public class Trie {

            private Map<Character, Trie> map;
            private int index;

            /**
             * Initialize your data structure here.
             */
            public Trie() {
                map = new HashMap<>();
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String word, int pos) {
                if (word != null) {
                    add(0, word, word.length(), pos);
                }
            }

            private void add(int i, String word, int length, int pos) {
                if (i < length) {
                    char c = word.charAt(i);
                    Trie subTrie = map.get(c);
                    if (subTrie == null) {
                        subTrie = new Trie();
                        map.put(c, subTrie);
                    }
                    subTrie.add(i + 1, word, length, pos);
                } else {
                    Trie t = new Trie();
                    t.index = pos;
                    map.put(null, t); // use null to indicate end of com.vaani.leetcode.string
                }
            }

            public Trie getSubTrie(Character c) {
                return this.map.get(c);
            }

            public int getIndex() {
                return index;
            }
        }


        public List<List<Integer>> palindromePairs(String[] words) {
            Trie forwardTrie = new Trie(); // maintain a forward and backward trie
            Trie backwardTrie = new Trie();
            Map<Integer, Set<Integer>> links = new HashMap<>(); // maintain links to avoid duplicates
            for (int i = 0; i < words.length; i++) {
                forwardTrie.insert(words[i], i);
                backwardTrie.insert(new StringBuilder(words[i]).reverse().toString(), i);
            }
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                boolean[][] T = new boolean[word.length()][word.length()];
                for (int j = 0, l = word.length(); j < l; j++) {
                    T[j][j] = true;
                    int k = j + 1;
                    if (k < l) {
                        if (word.charAt(j) == word.charAt(k)) {
                            T[j][k] = true;
                        }
                    }
                }
                for (int m = 2, l = word.length(); m < l; m++) {
                    for (int j = 0, k = m; j < l && k < l; j++, k++) {
                        if (word.charAt(j) == word.charAt(k) && T[j + 1][k - 1]) {
                            T[j][k] = true;
                        }
                    }
                }
                Trie subTrie = backwardTrie;
                if (subTrie.getSubTrie(null) != null && !word.isEmpty() && T[0][word.length() - 1]) {
                    Trie emptySubTrie = subTrie.getSubTrie(null);
                    result.add(Arrays.asList(i, emptySubTrie.getIndex()));
                    result.add(Arrays.asList(emptySubTrie.getIndex(), i));
                }
                for (int j = 0, l = word.length(); j < l; j++) {
                    char c = word.charAt(j);
                    subTrie = subTrie.getSubTrie(c);
                    if (subTrie == null) break;
                    Trie indexNode = subTrie.getSubTrie(null);
                    if (indexNode != null) {
                        if ((j == l - 1) || (T[j + 1][l - 1])) {
                            if (indexNode.getIndex() != i) { // ignore same index
                                Set<Integer> linkedTo = links.get(i);
                                if (linkedTo == null) {
                                    linkedTo = new HashSet<>();
                                    links.put(i, linkedTo);
                                }
                                if (!linkedTo.contains(indexNode.getIndex())) {
                                    linkedTo.add(indexNode.getIndex());
                                    result.add(Arrays.asList(i, indexNode.getIndex()));
                                }
                            }
                        }
                    }
                }
                subTrie = forwardTrie;
                for (int j = word.length() - 1; j >= 0; j--) {
                    char c = word.charAt(j);
                    subTrie = subTrie.getSubTrie(c);
                    if (subTrie == null) break;
                    Trie indexNode = subTrie.getSubTrie(null);
                    if (indexNode != null) {
                        if ((j == 0) || (T[0][j - 1])) {
                            if (indexNode.getIndex() != i) { // ignore same index
                                Set<Integer> linkedTo = links.get(indexNode.getIndex());
                                if (linkedTo == null) {
                                    linkedTo = new HashSet<>();
                                    links.put(indexNode.getIndex(), linkedTo);
                                }
                                if (!linkedTo.contains(i)) {
                                    linkedTo.add(i);
                                    result.add(Arrays.asList(indexNode.getIndex(), i));
                                }
                            }
                        }
                    }
                }
            }
            return result;
        }
    }
}
