package com.vaani.leetcode.linked_list;

import com.vaani.dsa.ds.core.list.sll.simple.ListNode;

import static com.vaani.dsa.ds.algos.list.linked.IntersectionOfTwoLinkedList.getIntersectionNode2;
import static com.vaani.dsa.ds.algos.list.linked.IntersectionOfTwoLinkedLists.getIntersectionNode2;

/**
 * Write a program to find the node at which the
 * intersection of two singly linked lists begins.
 *
 * <p>
 *
 * <p>For example, the following two linked lists:
 *
 * <p>A: a1 → a2 ↘ c1 → c2 → c3 ↗ B: b1 → b2 → b3 begin to intersect at node c1.
 *
 * <p>
 *
 * <p>Notes:
 *
 * <p>If the two linked lists have no intersection at all, return null. The linked lists must retain
 * their original structure after the function returns. You may assume there are no cycles anywhere
 * in the entire linked structure. Your code should preferably run in O(n) time and use only O(1)
 * memory.
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) throws Exception {
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        System.out.println(getIntersectionNode2(node1, node2));
    }

}
