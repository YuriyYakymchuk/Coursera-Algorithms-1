package com.algorithms.part.one.week.second.sort;

import edu.princeton.cs.algs4.StdRandom;

import static com.algorithms.part.one.SortUtils.less;
import static com.algorithms.part.one.SortUtils.swap;

public class Main {

    public static void main(String[] args) {
        Point[] firstArray = composePoints(5);
        Point[] secondArray = composePoints(5);
        int k = numberOfCommonPoints(firstArray, secondArray);
        System.out.println("Number of common points: " + k);
    }

    private static int numberOfCommonPoints(Point[] firstArray, Point[] secondArray) {
        shellSort(firstArray);
        shellSort(secondArray);
        System.out.println("First array: ");
        printArray(firstArray);
        System.out.println("Second array: ");
        printArray(secondArray);

        int commonElements = 0;
        for (int i = 0, j = 0; i < firstArray.length && j < firstArray.length;) {
            if (firstArray[i].equals(secondArray[j])) {
                commonElements++;
                i++;
                j++;
            } else if (less(firstArray[i], firstArray[j])) {
                i++;
            } else {
                j++;
            }
        }
        return commonElements;
    }

    private static Point[] composePoints(int k) {
        Point[] points = new Point[k];
        for (int i = 0; i < k; i++) {
            points[i] = new Point(StdRandom.uniform(2), StdRandom.uniform(2));
        }
        return points;
    }

    private static void sortBuckets(Color[] buckets) {
        int redIndex = 0;
        int whiteIndex = buckets.length - 1;
        for (int i = 0; i <= whiteIndex;) {
            switch (buckets[i]) {
                case RED: {
                    if (i > redIndex) {
                        swapBuckets(buckets, i, redIndex);
                    }
                    redIndex++;
                    i++;
                    break;
                }
                case WHITE: {
                    swapBuckets(buckets, whiteIndex, i);
                    whiteIndex --;
                    break;
                } case BLUE: {
                    i++;
                    break;
                }
                default: {
                    throw new IllegalArgumentException();
                }
            }
        }
    }

    private static void swapBuckets(Color[] buckets, int i, int j) {
        Color swap = buckets[i];
        buckets[i] = buckets[j];
        buckets[j] = swap;
    }

    private static void printBuckets(Color[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            System.out.println(buckets[i]);
        }
    }

    private static Color[] composeBuckets(int k) {
        Color[] buckets = new Color[k];
        for (int i = 0; i < k; i++) {
            buckets[i] = Color.values()[StdRandom.uniform(3)];
        }
        return buckets;
    }

    private static void printArray(Comparable[] array) {
        for (Comparable item : array) {
            System.out.println(item);
        }
    }

    private static Person[] composeData() {
        Person[] people = new Person[7];
        people[0] = new Person("Cd", 12);
        people[1] = new Person("Aa", 12);
        people[2] = new Person("Sw", 12);
        people[3] = new Person("Ws", 12);
        people[4] = new Person("Cf", 12);
        people[5] = new Person("Of", 12);
        people[6] = new Person("Df", 12);
        return people;
    }

    private static void selectionSort(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i+1; j < array.length; j++) {
                if (less(array[j], array[min])) {
                    min = j;
                }
            }
            swap(array, i, min);
        }
    }

    private static void insertionSort(Comparable[] array) {
        int N = array.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (less(array[j], array[j-1])) {
                    swap(array, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    private static void shellSort(Comparable[] array) {
        int h = 1;
        int N = array.length;
        while (N/3 > h) {
            h = h * 3 + 1;
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(array[j], array[j - h]); j -= h) {
                    swap(array, j, j - h);
                }
            }
            h = h/3;
        }
    }
}
