package com.company;

import java.util.Arrays;

public class QuickSort {


    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    private static void swap(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void sort(Comparable[] arr, int l, int h) {
        if (h <= l) return;
        System.out.println(l +" " + h);


        Comparable chosen = arr[l];

        int i = l + 1;
        int j = h;


        while (true) {
            while (less(arr[i], chosen)) {
                i++;
                if (i == h) break;
            }

            while (!less(arr[j], chosen)) {
                j--;
                if (j == l) break;
            }

            if (i >= j) break;
            swap(arr, i, j);
        }
        swap(arr, l, j);
        sort(arr, l, j - 1);
        sort(arr, j + 1, h);

    }

    public static void sort(Comparable[] arr) {
        //shuffle array here
        sort(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {

        Integer[] arr = {5, 554654, 2, 234, 341, 43, 5454,22};

        sort(arr);
        System.out.println(Arrays.toString(arr));


    }
}
