package com.algorithms.week.second.queue.main;

import com.algorithms.week.second.queue.Queue;
import com.algorithms.week.second.queue.Stack;
import com.algorithms.week.second.queue.impl.ArrayQueue;
import com.algorithms.week.second.queue.impl.ListQueue;
import com.algorithms.week.second.queue.impl.StackArray;
import com.algorithms.week.second.queue.impl.StackList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        arrayQueue();
        AtomicInteger rowCount = new AtomicInteger(0);
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.forEach(integer -> rowCount.addAndGet(integer));
        System.out.println(rowCount.get());
        System.out.println(String.format("%d" ,1));
    }

    private static void arrayQueue() {
        Queue<String> queue = new ArrayQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("Queue size: " + queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    private static void listQueue() {
        Queue<String> queue = new ListQueue<>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        System.out.println("Queue size: " + queue.size());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    private static void listStack() {
        StackList<String> stack = new StackList<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack size: " + stack.size());
        Iterator<String> iterator = stack.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            System.out.println(item);
        }
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

    private static void arrayStack() {
        Stack<String> stack = new StackArray<>();
        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack size: " + stack.size());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
