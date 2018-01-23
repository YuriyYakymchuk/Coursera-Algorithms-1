package com.algorithms.week.fourth.algorithms.binary.tree;

public class Main {

    public static void main(String[] args) {

        BinarySearchTree<String, Integer> binarySearchTree = new BinarySearchTree<>();
        binarySearchTree.put("Yura", 28);
        binarySearchTree.put("Halya", 29);
        binarySearchTree.put("AA", 29);
        binarySearchTree.put("ZZ", 29);
        binarySearchTree.delete("AA");
        System.out.println(binarySearchTree.get("Yura"));
        System.out.println(binarySearchTree.get("AA"));
    }
}
