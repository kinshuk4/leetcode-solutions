package com.vaani.leetcode.design;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import java.util.Random;

/**
 * https://leetcode.com/problems/linked-list-random-node/
 * 382. Linked List Random Node
 * Medium
 * <p>
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * <p>
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?
 * <p>
 * Example:
 * <p>
 * // Init a singly linked list [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 * <p>
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * solution.getRandom();
 */
public class LinklistRandomNode {
    private final ListNode head;
    private int size;

    /**
     * @param head The linked list's head.
     *             Note that the head is guaranteed to be not null, so it contains at least one node.
     */
    public LinklistRandomNode(ListNode head) {
        this.head = head;
        size = 0;
        ListNode current = head;
        while (current != null) {
            current = current.next;
            size++;
        }
        this.size = size;

    }

    /**
     * Returns a random node's value.
     */
    public int getRandom() {
        Random rand = new Random();
        int randInt = rand.nextInt(size);
        //Traverse through list again to reach node at value randInt.
        ListNode temp = head;
        while (randInt > 0 && temp.next != null) {
            temp = temp.next;
            randInt--;
        }
        return temp.val;
    }
}
