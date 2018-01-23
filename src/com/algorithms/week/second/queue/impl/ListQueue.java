package com.algorithms.week.second.queue.impl;

import com.algorithms.week.second.queue.Queue;

import java.util.Iterator;

public class ListQueue<T> implements Queue<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    public ListQueue() {
        size = 0;
        first = last = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<T> oldLast = last;
        last = new Node<>();
        last.setItem(item);
        last.setNext(null);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.setNext(last);
        }
        size ++;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T item = first.getItem();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        }
        size--;
        return item;
    }
}
