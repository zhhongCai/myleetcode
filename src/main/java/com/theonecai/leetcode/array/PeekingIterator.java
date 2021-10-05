package com.theonecai.leetcode.array;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *  leetcode 284
 */
public class PeekingIterator implements Iterator<Integer> {

    private List<Integer> nums;

    private int current;

    public PeekingIterator(Iterator<Integer> it) {
        // initialize any member here.
        nums = new ArrayList<>();
        current = 0;
        while (it.hasNext()) {
            nums.add(it.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (current < nums.size()) {
            return nums.get(current);
        }
        return null;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        return nums.get(current++);
    }

    @Override
    public boolean hasNext() {
        return current < nums.size();
    }

    public static void main(String[] args) {
        PeekingIterator peekingIterator = new PeekingIterator(Arrays.asList(1,2,3).iterator());
        Assert.assertEquals(1, (int) peekingIterator.next());
        Assert.assertEquals(2, (int) peekingIterator.peek());
        Assert.assertEquals(2, (int) peekingIterator.next());
        Assert.assertEquals(3, (int) peekingIterator.next());
        Assert.assertFalse(peekingIterator.hasNext());
    }
}
