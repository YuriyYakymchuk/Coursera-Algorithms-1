package com.algorithms.part.one.week.second;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;

    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item can't be null.");
        }
        if (size == items.length) {
            resize(size * 2);
        }
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        int index = StdRandom.uniform(size);
        Item item = items[index];
        items[index] = items[--size];
        items[size] = null;
        if (size > 0 && size <= items.length/4) {
            resize(items.length/2);
        }
        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }
        return items[StdRandom.uniform(size)];
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {
        private int index;
        private final int[] order;

        public RandomizedIterator() {
            index = 0;
            order = new int[size];
            for (int i = 0; i < size; i++) {
                order[i] = i;
            }
            StdRandom.shuffle(order);
        }

        @Override
        public boolean hasNext() {
            return index < order.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Queue is empty.");
            }
            return items[order[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("This method is not implemented.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(438);
        rq.enqueue(587);
        rq.enqueue(211);
        rq.dequeue();
        rq.dequeue();

    }
}
