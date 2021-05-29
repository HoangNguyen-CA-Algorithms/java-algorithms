package com.company;

public class SymbolTableBST<Key extends Comparable<Key>, Val>{

    private Node root;

    private class Node{
        private Key key;
        private Val val;
        private Node left;
        private Node right;

        public Node(Key key, Val val) {
            this.key = key;
            this.val = val;
        }
    }


    public void put(Key key, Val val) {
        root = put(root, key, val);
    }

    // recursive function to insert into BST
    private Node put(Node node, Key key, Val val) {
        if (node == null) return new Node(key, val);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val;
        return node;
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
        Node x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.val;
    }

    public Val max() {
        if (root == null) return null;
        Node x = root;
        while (x.right != null) {
            x = x.right;
        }
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

    public Val floor(Key key) {
        Node x = floor(root, key);
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

    public Val ceil(Key key) {
        Node x = ceil(root, key);
        if (x == null) return null;
        return x.val;
    }

    public void delete(Key key) {

    }

    private void inOrder(Node node, StringBuilder builder) {
        if (node == null) return;
        inOrder(node.left, builder);
        builder.append("(" + node.key + ", " + node.val + ") ");
        inOrder(node.right, builder);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        inOrder(root, builder);
        return builder.toString();
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

        System.out.println(BST.min());
        System.out.println(BST.max());
        System.out.println(BST.floor("P"));
        System.out.println(BST.ceil("P"));

        System.out.println(BST);
    }

}
