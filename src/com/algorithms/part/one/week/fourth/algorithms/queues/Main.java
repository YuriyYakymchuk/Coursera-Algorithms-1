package com.algorithms.part.one.week.fourth.algorithms.queues;

import edu.princeton.cs.algs4.StdRandom;

public class Main {

    public static void main(String[] args) {
        Integer[] array = buildArray(10);
        printArray(array);
        Heap.sort(array);
        System.out.println("");
        printArray(array);
    }

    private static void printArray(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    private static Integer[] buildArray(int k) {
        Integer[] array = new Integer[k];
        for (int i = 0; i < k; i++) {
            array[i] = StdRandom.uniform(10);
        }
        return array;
    }

}
