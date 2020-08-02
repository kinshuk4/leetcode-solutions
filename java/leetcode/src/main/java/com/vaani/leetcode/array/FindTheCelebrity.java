package com.vaani.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-the-celebrity/
 *
 * <p>Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may
 * exist one celebrity. The definition of a celebrity is that all the other n - 1 people know
 * him/her but he/she does not know any of them.
 *
 * <p>Now you want to find out who the celebrity is or verify that there is not one. The only thing
 * you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of
 * whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as
 * few questions as possible (in the asymptotic sense).
 *
 * <p>You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement
 * a function int findCelebrity(n), your function should minimize the number of calls to knows.
 *
 * <p>Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's
 * label if there is a celebrity in the party. If there is no celebrity, return -1.
 * <p>
 * https://www.youtube.com/watch?v=LZJBZEnoYLQ
 */
public class FindTheCelebrity {

    private static Map<Integer, Set<Integer>> map = new HashMap<>();

    public static void main(String[] args) {
        // initialize relationship
        map.put(0, new HashSet<>());
        map.put(1, new HashSet<>());
        map.put(2, new HashSet<>());
        map.put(3, new HashSet<>());
        map.put(4, new HashSet<>());
        map.put(5, new HashSet<>());
        map.put(6, new HashSet<>());
        map.get(0).add(3);
        map.get(0).add(1);
        map.get(0).add(2);
        map.get(0).add(4);
        map.get(0).add(5);
        map.get(0).add(6);

        map.get(1).add(0);
        map.get(1).add(3);
        map.get(1).add(2);

        map.get(2).add(1);
        map.get(2).add(3);
        map.get(2).add(4);

        map.get(4).add(2);
        map.get(4).add(3);
        map.get(4).add(5);

        map.get(5).add(4);
        map.get(5).add(3);
        map.get(5).add(6);

        map.get(6).add(5);
        map.get(6).add(3);
        map.get(6).add(0);

        System.out.println(new FindTheCelebrity().findCelebrity(0));
    }

    public int findCelebrity(int n) {
        int candidate = -1, i = 0, next = 1;
        while (i < n) {
            if (next >= n) break;
            if (knows(i, next) && !knows(next, i)) {
                i = next;
                next = i + 1;
            } else if ((!knows(i, next) && !knows(next, i)) || (knows(i, next) && knows(next, i))) {
                i = next + 1;
                next = i + 1;
            } else {
                next++;
            }
            candidate = i;
        }
        if (candidate == -1 || candidate >= n) return -1;
        for (int j = 0; j < candidate; j++) {
            if (!knows(j, candidate) || knows(candidate, j)) {
                candidate = -1;
                break;
            }
        }
        return candidate;
    }

    public int findCelebrity2(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i; // i can potentially be celebrity
            }
        }

        for (int i = 0; i < n; i++) {
            // ith value is not candidate and candidate know someone
            // or someone doesn't know the candidate
            if(i != candidate && knows(candidate, i) || !knows(i, candidate)){
                return -1;
            }
        }
        return candidate;
    }

    private boolean knows(int a, int b) {
        return map.get(a).contains(b);
    }
}
