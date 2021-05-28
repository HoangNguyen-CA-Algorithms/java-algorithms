package com.company;

public class SymbolTableBST<Key extends Comparable<Key>, Val>{

    private Node root;

    private class Node{
        private Key key;
        private Val val;
        private Node left;
        private Node right;

        public Node(Key key, Val val){
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Val val){

    }

    public Val get(Key key){

    }

    public void delete(Key key) {

    }

}
