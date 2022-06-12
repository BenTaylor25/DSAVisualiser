package com.mygdx.dsav.DataStructs;

import java.util.LinkedList;

/**
 * A Stack is a collection of values where the only value you can access is the 'head' or the top.
 * <p>
 * Think of a stack of papers on a desk; you can add 'push()' more paper to the top of the stack,
 * take 'pop()' a piece of paper, or view 'peek()' the paper at the top.
 * <p>
 * Stacks are First In, Last Out (FILO) as opposed to Queues that are FIFO.
 */
public class Stack {
    private LinkedList<String> container;

    /** Creates a new Stack. */
    public Stack() {
        container = new LinkedList<String>();
    }

    /**
     * Adds a new item to the top of the Stack.
     * 
     * @param newItem String item to add.
     */
    public void push(String newItem) {
        container.add(newItem);
    }

    /**
     * Removes the top of the stack and return it.
     * 
     * @return The top of the Stack.
     */
    public String pop() {
        String popVal = container.getLast();
        container.removeLast();
        return popVal;
    }

    /**
     * Returns the head of the Stack without removing it. 
     * 
     * @return The head of the Stack.
     */
    public String peek() {
        return container.getLast();
    }

    /**
     * Peek at the whole Stack.
     * 
     * @return The internal representation of the Stack.
     */
    public LinkedList<String> viewStack() {
        return new LinkedList<>(container);
    }

    /**
     * Replace the internal representation of the Stack.
     * 
     * @param newContainer Replacement Stack contents.
     */
    public void overrideStack(LinkedList<String> newContainer) {
        container = new LinkedList<>(newContainer);
    }

    @Override
    public String toString() {
        String merge = "";
        for (String x : container) {
            merge += x;
            if (x != container.getLast()) {
                merge += " ";
            }
        }
        return merge;
    }

    /** Returns the number of elements in the Stack. */
    public int size() {
        return container.size();
    }
}
