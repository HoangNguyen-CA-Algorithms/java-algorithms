package com.company;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] PQ; // resizable array in production code
    private int size = 0;

    public MaxPQ(int size) {
        PQ = (Key[]) new Comparable[size + 1];
    }

    public void insert(Key key) {
        PQ[size++] = key; // converted to 0-based index
        swim(size);
    }

    public int size() {
        return this.size;
    }

    public Key delMax() {
        if (size < 1) throw new NoSuchElementException();
        Key item = PQ[0]; // converted to 0-based index
        swap(1, size);
        PQ[--size] = null;
        sink(1);

        return item;
    }

    private boolean less(int i, int j) {
        Key a = PQ[i-1];
        Key b = PQ[j-1];
        return a.compareTo(b) < 0;
    }

    // converted to 0-based index
    private void swap(int i, int j) {
        Key temp = PQ[i-1];
        PQ[i-1] = PQ[j-1];
        PQ[j-1] = temp;
    }

    // converted to 0-based index
    private void sink(int i) {

        while (left(i) <= size) {

            int chosen = left(i);
            if (right(i) <= size) {
                if (less(chosen, right(i))) {
                    chosen = right(i);
                }
            }

            if (less(chosen, i)) break;

            swap(i, chosen);
            i = chosen;
        }

    }

    private void swim(int i) {

        while (parent(i) >= 1) {

            if (less(parent(i), i)) {
                swap(i, parent(i));
                i = parent(i);
            } else {
                break;
            }
        }
    }

    private int left(int i) {
        return i * 2;
    }

    private int right(int i) {
        return i * 2 + 1;
    }

    private int parent(int i) {
        return i / 2;
    }

    @Override
    public String toString() {
        return Arrays.toString(PQ);
    }


    public static void main(String[] args) {
        MaxPQ<Integer> q = new MaxPQ<>(20);

        q.insert(1);
        q.insert(5);
        q.insert(6);
        q.insert(4);
        q.insert(3);
        q.insert(8);
        q.insert(84);
        q.insert(22);

        while (q.size() > 0) {
            System.out.println(q.delMax());
        }
    }
}
