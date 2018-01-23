package com.algorithms.week.second.queue.impl;

import com.algorithm.stack.queue.Stack;

import java.util.Iterator;

public class StackList<T> implements Stack<T>, Iterable<T> {

    private int size;
    private Node<T> first;

    public StackList() {
        size = 0;
        first = null;
    }

    @Override
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node<T> node = new Node<>();
        node.setItem(item);
        node.setNext(first);
        first = node;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        T item = first.getItem();
        first = first.getNext();
        size--;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackListIterator();
    }

    private class StackListIterator implements Iterator<T> {

        private Node<T> node;

        public StackListIterator() {
            node = first;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T item = node.getItem();
            node = node.getNext();
            return item;
        }
    }
}
