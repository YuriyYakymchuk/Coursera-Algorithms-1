package com.algorithms.part.one.week.second.queue.impl;


import com.algorithms.part.one.week.second.queue.Stack;

public class StackArray<T> implements Stack<T> {

    private static final int INITIAL_CAPACITY = 1;

    private int capacity;
    private int size;
    private T[] items;

    public StackArray() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        items = (T[]) new Object[capacity];
    }

    @Override
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize(capacity * 2);
        }
        items[size++] = item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = items[--size];
        items[size] = null;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }

    private void resize(int capacity) {
        this.capacity = capacity;
        T[] newArray = (T[]) new Object[this.capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[i];
        }
        items = newArray;
    }
}
