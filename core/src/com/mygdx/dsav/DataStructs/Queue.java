package com.mygdx.dsav.DataStructs;

import java.util.LinkedList;

/**
 * A Queue is a collection of values where the only value you can access is the 'head' or the start.
 * <p>
 * Think of a queue of people; new people can join the queue 'enqueue()', the person at the front
 * can leave the queue 'dequeue()', and you can see 'peek()' at the person at the front.
 * <p>
 * Queues are First In, First Out (FIFO) as opposed to Stacks that are FILO.
 */
public class Queue {
    private LinkedList<String> container;

    /** Creates a new Queue. */
    public Queue() {
        container = new LinkedList<String>();
    }

    /**
     * Adds new item to the end of the Queue.
     * 
     * @param newItem String item to add.
     */
    public void enqueue(String newItem) {
        container.add(newItem);
    }

    /**
     * Removes the start of the Queue and returns it.
     * <p>
     * Trying to Dequeue from an empty Queue will return the empty string.
     * <p>
     * (this is different for different implementations of Queues)
     * 
     * @return The front of the Queue.
     */
    public String dequeue() {
        if (size() == 0) {
            return "";
        }

        return container.poll();
    }

    /**
     * Returns the front of the Queue without removing it.
     * <p>
     * Trying to Peek at an empty Queue will return the empty string.
     * <p>
     * (this is different for different implementations of Queues)
     * 
     * @return The front of the Queue.
     */
    public String peek() {
        if (size() == 0) {
            return "";
        } 

        return container.get(0);
    }

    /**
     * Peek at the whole Queue.
     * 
     * @return The internal representation of the Queue.
     */
    public LinkedList<String> viewQueue() {
        return new LinkedList<>(container);
    }

    /**
     * Replace the internal representation of the Queue.
     * 
     * @param newContainer Replacement Queue contents.
     */
    public void overrideQueue(LinkedList<String> newContainer) {
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

    /** Returns the number of elements in the Queue. */
    public int size() {
        return container.size();
    }
}
