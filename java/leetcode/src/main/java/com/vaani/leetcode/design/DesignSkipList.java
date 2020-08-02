package com.vaani.leetcode.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * https://leetcode.com/problems/design-skiplist/
 * 1206. Design Skiplist
 * Hard
 * <p>
 * Design a Skiplist without using any built-in libraries.
 * <p>
 * A Skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and red-black tree which has the same function and performance, the code length of Skiplist can be comparatively short and the idea behind Skiplists are just simple linked lists.
 * <p>
 * For example: we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist works this way:
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top layers, add , erase and search can be faster than O(n). It can be proven that the average time complexity for each operation is O(log(n)) and space complexity is O(n).
 * <p>
 * To be specific, your design should include these functions:
 * <p>
 * bool search(int target) : Return whether the target exists in the Skiplist or not.
 * void add(int num): Insert a value into the SkipList.
 * bool erase(int num): Remove a value in the Skiplist. If num does not exist in the Skiplist, do nothing and return false. If there exists multiple num values, removing any one of them is fine.
 * <p>
 * See more about Skiplist : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Skiplist skiplist = new Skiplist();
 * <p>
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // return false.
 * skiplist.add(4);
 * skiplist.search(1);   // return true.
 * skiplist.erase(0);    // return false, 0 is not in skiplist.
 * skiplist.erase(1);    // return true.
 * skiplist.search(1);   // return false, 1 has already been erased.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= num, target <= 20000
 * At most 50000 calls will be made to search, add, and erase.
 */

// Sentinel
//          \
// level 3: -Inf ----------------------------> 4
// level 2: -Inf ------------> 2 ------------> 4
// level 1: -Inf ----> 1 ----> 2 ------------> 4
// level 0: -Inf ----> 1 ----> 2 ----> 3 ----> 4 : this level is the most concrete level
public class DesignSkipList {
    // https://www.youtube.com/watch?v=7pWkspmYUVo
    static class Skiplist {
        private static final double DEFAULT_PROB = 0.5;
        private final Random rand = new Random();

        private static final class Node {
            private final int val;
            private Node left, right, up, down;

            private Node(int val) {
                this.val = val;
            }
        }

        private final List<Node> sentinels = new ArrayList<>();

        {
            sentinels.add(new Node(Integer.MIN_VALUE));
        }

        public Skiplist() {

        }

        public boolean search(int target) {
            Node smallerOrEquals = getSmallerOrEquals(target);
            return smallerOrEquals.val == target;
        }

        public void add(int num) {
            Node curr = getSmallerOrEquals(num);
            // The lowest level of curr, which is smaller than it or equal to num
            final Node toInsert = new Node(num);
            append(curr, toInsert);
            // populate the level
            populateLevelUp(toInsert);
        }

        public boolean erase(int num) {
            final Node toRemove = getSmallerOrEquals(num);
            if (toRemove.val != num) {
                return false;
            }
            Node curr = toRemove;
            while (curr != null) {
                final Node prev = curr.left, next = curr.right;
                prev.right = next;
                if (next != null) {
                    next.left = prev;
                }
                curr = curr.up;
            }
            return true;
        }

        private Node getSmallerOrEquals(final int target) {
            Node curr = getSentinel();
            while (curr != null) {
                if (curr.right == null || curr.right.val > target) {
                    if (curr.down == null) {
                        break;
                    }
                    curr = curr.down;
                } else {
                    curr = curr.right;
                }
            }
            return curr;
        }

        private Node getSentinel() {
            return sentinels.get(sentinels.size() - 1);
        }

        private void populateLevelUp(final Node toInsert) {
            Node curPrev = toInsert.left, curr = toInsert;

            while (flipCoin()) {
                while (curPrev.left != null && curPrev.up == null) {
                    curPrev = curPrev.left;
                }
                if (curPrev.up == null) {
                    final Node newSentinel = new Node(Integer.MIN_VALUE);
                    curPrev.up = newSentinel;
                    newSentinel.down = curPrev;
                    sentinels.add(curPrev.up);
                }
                curPrev = curPrev.up;
                final Node newNode = new Node(toInsert.val);
                curr.up = newNode;
                newNode.down = curr;
                curr = curr.up;
                curPrev.right = curr;
                curr.left = curPrev;
            }
        }

        private void append(Node prev, Node cur) {
            final Node next = prev.right;
            prev.right = cur;
            cur.left = prev;
            if (next != null) {
                next.left = cur;
                cur.right = next;
            }
        }


        private boolean flipCoin() {
            return rand.nextDouble() < DEFAULT_PROB;
        }


    }
}
