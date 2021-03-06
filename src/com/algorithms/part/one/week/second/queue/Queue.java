package com.algorithms.part.one.week.second.queue;

public interface Queue<T> extends Iterable<T> {

    boolean isEmpty();
    int size();
    void enqueue(T item);
    T dequeue();
}
