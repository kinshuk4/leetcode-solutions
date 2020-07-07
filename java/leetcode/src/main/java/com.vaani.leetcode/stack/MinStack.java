package com.vaani.leetcode.stack;

import java.util.Stack;

/**
 * 08/03/2017. Design a stack that supports push, pop, top, and
 * retrieving the minimum element in constant time.
 *
 * <p>push(x) -- Push element x onto com.vaani.leetcode.stack. pop() -- Removes the element on top of the com.vaani.leetcode.stack. top()
 * -- Get the top element. getMin() -- Retrieve the minimum element in the com.vaani.leetcode.stack. Example: MinStack
 * minStack = new MinStack(); minStack.push(-2); minStack.push(0); minStack.push(-3);
 * minStack.getMin(); --> Returns -3. minStack.pop(); minStack.top(); --> Returns 0.
 * minStack.getMin(); --> Returns -2.
 */
public class MinStack {

    public static void main(String[] args) throws Exception {
        minstackTwoStacks();
        minstackOneStack();
    }

    private static void minstackTwoStacks() {
        com.vaani.dsa.ds.algos.stack.MinStack minStack = new com.vaani.dsa.ds.algos.stack.MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

    private static void minstackOneStack() {
        com.vaani.dsa.ds.algos.stack.MinStackUsingOneStack minStack = new com.vaani.dsa.ds.algos.stack.MinStackUsingOneStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }

}
