package com.algorithms.part.one.week.second.queue.impl;

public class Node<T> {
    private T item;
    private Node next;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
