package com.vaani.leetcode.bfs;

import org.junit.Assert;

import java.util.*;
import java.util.Queue;

public class WordLadder0 {
    public static void main(String[] args) {
        WordLadder0 wl0 = new WordLadder0();
        String[] dic = {"DOG", "COT", "COG", "FOG", "DOT"};
        Assert.assertTrue(wl0.isNavigable("FOG", "COT", new HashSet<>(List.of("DOG", "COT", "COG", "FOG", "DOT"))));
    }
    public boolean isNavigable(final String beginWord, final String endWord, final Set<String> dictionary) {
        if (beginWord.length() != endWord.length()) {
            return false;
        }
        if (beginWord.equals(endWord)) {
            return true;
        }

        dictionary.remove(beginWord);

        final Queue<String> queue = new LinkedList<String>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {
            final String top = queue.remove();

            for (int i = 0; i<top.length(); i++) {
                char[] temp = top.toCharArray();
                for (char j = 'A'; j<'Z'; j++) {
                    temp[i] = j;

                    final String candidate = new String(temp);

                    if (candidate.equals(endWord)) {
                        System.out.print("-->" + candidate);
                        return true;
                    } else if (dictionary.contains(candidate)) {
                        dictionary.remove(candidate);
                        queue.add(candidate);
                        System.out.print("-->" + candidate);
                    }
                }
            }
        }

        return false;
    }
}
