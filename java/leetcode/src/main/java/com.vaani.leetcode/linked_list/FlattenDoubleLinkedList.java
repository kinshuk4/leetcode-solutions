package com.vaani.leetcode.linked_list;

/**
 * https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
 * We use the multilevel linked list from Example 1 above:
 * <p>
*/
/*
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
 */
/**
 * The serialization of each level is as follows:
 * <p>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * Output:1,2,3,7,8,11,12,9,10,4,5,6
 * <p>
 * To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the previous level. The serialization becomes:
 * <p>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * Merging the serialization of each level and removing trailing nulls we obtain:
 * <p>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <p>
 * Example 2:
 * <p>
 * Input: head = [1,2,null,3]
 * Output: [1,3,2]
 * Explanation:
 * <p>
 * The input multilevel linked list is as follows:
 * <p>
 * 1---2---NULL
 * |
 * 3---NULL
 */

class DLLWithChildNode {
    public int val;
    public DLLWithChildNode prev;
    public DLLWithChildNode next;
    public DLLWithChildNode child;
}

public class FlattenDoubleLinkedList {
    public DLLWithChildNode flatten(DLLWithChildNode head) {
        DLLWithChildNode curr = head;

        while(curr != null && curr.child == null){
            curr = curr.next;
        }

        if(curr != null){
            DLLWithChildNode childTail = curr.child;
            while (childTail.next != null){
                childTail = childTail.next;
            }

            DLLWithChildNode restHead = flatten(curr.next);

            if(restHead != null){
                childTail.next = restHead;
                restHead.prev = childTail;
            }

            DLLWithChildNode childHead = flatten(curr.child);
            curr.next = childHead;
            childHead.prev = curr;
            curr.child = null;

        }

        return head;
    }
}
