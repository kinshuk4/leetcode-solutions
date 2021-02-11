package com.vaani.leetcode.design;

import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/
 * 284. Peeking Iterator
 * Medium
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
 * <p>
 * Example:
 * <p>
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 * <p>
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
public class PeekingIterator implements Iterator<Integer> {
    Integer nextItem = null;
    private Iterator<Integer> iterator = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        nextItem = this.iterator.next();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return nextItem;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer valueToReturn = nextItem;
        if (iterator.hasNext()) {
            this.nextItem = iterator.next();
        } else {
            this.nextItem = null;
        }
        return valueToReturn;

    }

    @Override
    public boolean hasNext() {
        return nextItem != null;
    }
}
