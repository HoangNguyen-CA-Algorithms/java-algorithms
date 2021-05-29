package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class SymbolTableBST<Key extends Comparable<Key>, Val> {

    private Node root;

    private class Node {
        private Key key;
        private Val val;
        private Node left;
        private Node right;
        private int count;

        public Node(Key key, Val val) {
            this.key = key;
            this.val = val;
            this.count = 1;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.count;
    }


    public void put(Key key, Val val) {
        root = put(root, key, val);
    }

    // recursive function to insert into BST
    private Node put(Node x, Key key, Val val) {
        if (x == null) return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Val get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public Val min() {
        if (root == null) return null;
        Node x = min(root);
        return x.val;
    }

    private Node min(Node x) {
        if (x == null) return null;
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Val max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.val;
    }

    public Val floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.val;
    }


    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;

        if (cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }


    public Val ceil(Key key) {
        Node x = ceil(root, key);
        if (x == null) return null;
        return x.val;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;

        if (cmp > 0) return ceil(x.right, key);
        Node t = ceil(x.left, key);
        if (t != null) return t;
        return x;
    }


    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        if (cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        return size(x.left);
    }


    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
        delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // for case both right & left are null, returns null anyways

            //case 1: 1 null child
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            //case 2: 2 null children
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new LinkedList<>();
        inOrder(root, q);
        return q;
    }

    private void inOrder(Node node, Queue<Key> q) {
        if (node == null) return;
        inOrder(node.left, q);
        q.add(node.key);
        inOrder(node.right, q);
    }


    //testing client
    public static void main(String[] args) {
        SymbolTableBST<String, Integer> BST = new SymbolTableBST<>();
        BST.put("A", 1);
        BST.put("B", 2);

        BST.put("C", 3);
        BST.put("D", 4);
        BST.put("E", 5);
        BST.put("F", 6);
        BST.put("G", 7);
        BST.put("X", 8);
        BST.put("Y", 9);
        BST.put("Z", 10);

        BST.delete("G");
        BST.delete("C");
        BST.delete("D");


        System.out.println(BST.min());
        System.out.println(BST.max());
        System.out.println(BST.floor("P"));
        System.out.println(BST.ceil("P"));

        for (String key : BST.keys()) {
            System.out.print(key + " ");
        }
        System.out.println();
        System.out.println("Size: " + BST.size());
    }

}
