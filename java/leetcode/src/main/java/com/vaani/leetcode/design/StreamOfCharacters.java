package com.vaani.leetcode.design;

import com.vaani.dsa.ds.core.trie.morebasic.without_prefix.TrieNode;
import com.vaani.dsa.ds.core.trie.morebasic.without_prefix.TrieNodeUsingArr;
import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/stream-of-characters/
 * 1032. Stream of Characters
 * Hard
 * <p>
 * Implement the StreamChecker class as follows:
 * <p>
 * StreamChecker(words): Constructor, init the data structure with the given words.
 * query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
 * streamChecker.query('a');          // return false
 * streamChecker.query('b');          // return false
 * streamChecker.query('c');          // return false
 * streamChecker.query('d');          // return true, because 'cd' is in the wordlist
 * streamChecker.query('e');          // return false
 * streamChecker.query('f');          // return true, because 'f' is in the wordlist
 * streamChecker.query('g');          // return false
 * streamChecker.query('h');          // return false
 * streamChecker.query('i');          // return false
 * streamChecker.query('j');          // return false
 * streamChecker.query('k');          // return false
 * streamChecker.query('l');          // return true, because 'kl' is in the wordlist
 * <p>
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 2000
 * Words will only consist of lowercase English letters.
 * Queries will only consist of lowercase English letters.
 * The number of queries is at most 40000.
 */
public class StreamOfCharacters {
    public static void main(String[] args) {
        String[] words = {"cd", "f", "kl"};
        UsingTrieAndQueue.StreamChecker underTest = new UsingTrieAndQueue.StreamChecker(words);
        Assert.assertFalse(underTest.query('a'));
        Assert.assertFalse(underTest.query('a'));          // return false
        Assert.assertFalse(underTest.query('b'));          // return false
        Assert.assertFalse(underTest.query('c'));          // return false
        Assert.assertTrue(underTest.query('d'));        // return true, because 'cd' is in the wordlist
        Assert.assertFalse(underTest.query('e'));          // return false
        Assert.assertTrue(underTest.query('f'));          // return true, because 'f' is in the wordlist
        Assert.assertFalse(underTest.query('g'));          // return false
        Assert.assertFalse(underTest.query('h'));          // return false
        Assert.assertFalse(underTest.query('i'));          // return false
        Assert.assertFalse(underTest.query('j'));          // return false
        Assert.assertFalse(underTest.query('k'));          // return false
        Assert.assertTrue(underTest.query('l'));          // return true, because 'kl' is in the wordlist
    }

    static class UsingTrieAndQueue {
        // TLE
        static class StreamChecker {
            private TrieNode root;
            private Queue<TrieNode> q;

            public StreamChecker(String[] words) {
                root = new TrieNode();
                for (String word : words) {
                    root.insert(word);
                }
                q = new LinkedList<>();
                q.add(root);
            }

            public boolean query(char letter) {
                boolean ans = false;
                int n = q.size();
                for (int i = 0; i < n; i++) {
                    TrieNode curr = q.poll();
                    if (curr.children.containsKey(letter)) {
                        TrieNode child = curr.children.get(letter);
                        q.add(child);
                        if (child.isWord) {
                            ans = true;
                        }
                    }
                    if (curr == root) {
                        q.add(root);
                    }
                }
                return ans;
            }
        }
    }

    // Pass
    static class UsingTrieAndQueue2 {
        static class StreamChecker {
            private TrieNodeUsingArr root;
            private Queue<TrieNodeUsingArr> q;

            public StreamChecker(String[] words) {
                root = new TrieNodeUsingArr();
                for (String word : words) {
                    root.insert(word);
                }
                q = new LinkedList<>();
                q.add(root);
            }

            public boolean query(char letter) {
                boolean ans = false;
                int n = q.size();
                for (int i = 0; i < n; i++) {
                    TrieNodeUsingArr curr = q.poll();
                    if (curr.children[letter-'a'] != null) {
                        TrieNodeUsingArr child = curr.children[letter - 'a'];
                        q.add(child);
                        if (child.isWord) {
                            ans = true;
                        }
                    }
                    if (curr == root) {
                        q.add(root);
                    }
                }
                return ans;
            }
        }
    }
}
