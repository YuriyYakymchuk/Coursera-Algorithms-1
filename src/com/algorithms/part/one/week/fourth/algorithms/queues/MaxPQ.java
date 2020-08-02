package com.algorithms.part.one.week.fourth.algorithms.queues;

import java.util.Arrays;

import static com.algorithms.part.one.SortUtils.less;
import static com.algorithms.part.one.SortUtils.swap;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] elements;
    private int size;

    public MaxPQ() {
        elements = (Key[]) new Comparable[1];
        size = 0;
    }

    public MaxPQ(Key[] elements) {
        this.elements = Arrays.copyOf(elements, elements.length + 1);
        this.size = elements.length;
    }

    public MaxPQ(int size) {
        this.elements = (Key[]) new Comparable[size + 1];
    }

    public void insert(Key element) {
        elements[++size] = element;
        swim(size);
    }

    public Key deleteMax() {
        Key max = elements[1];
        swap(elements, 1, size);
        elements[size--] = null;
        sink(1);
        return max;
    }

    public boolean isEmpty() {
        return size < 1;
    }

    public int size() {
        return size;
    }

    public void print() {
        for (int i = 1; i <= size; i++) {
            System.out.println(elements[i]);
        }
    }

    private void swim(int k) {
        while (k > 1 && less(elements[k/2], elements[k])) {
            swap(elements, k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= size) {
            int max = 2*k;
            if (max < size && less(elements[max], elements[max + 1])) {
                max++;
            }
            if (!less(elements[k], elements[max])) {
                break;
            }
            swap(elements, max, k);
            k = max;
        }
    }
}
