package com.algorithms.week.second.queue.impl;

import com.algorithms.week.second.queue.Queue;

import java.util.Iterator;

public class ArrayQueue<T> implements Queue<T> {
    private static final int INITIAL_CAPACITY = 1;

    private int startIndex;
    private int endIndex;
    private int capacity;
    private T[] items;

    public ArrayQueue() {
        capacity = INITIAL_CAPACITY;
        startIndex = 0;
        endIndex = 0;
        items = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return startIndex >= endIndex;
    }

    @Override
    public int size() {
        return endIndex - startIndex;
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(capacity * 2);
        }
        items[endIndex++] = item;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = items[startIndex];
        items[startIndex++] = null;
        return item;
    }

    private boolean isFull() {
        return endIndex == capacity;
    }

    private void resize(int capacity) {
        this.capacity = capacity;
        T[] newArray = (T[]) new Object[this.capacity];
        for (int i = 0; i < endIndex; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<T> {

        private int index;

        public ArrayQueueIterator() {
            index = startIndex;
        }

        @Override
        public boolean hasNext() {
            return index < endIndex;
        }

        @Override
        public T next() {
            return items[index++];
        }
    }
}
