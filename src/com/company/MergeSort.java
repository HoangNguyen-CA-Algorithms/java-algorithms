package com.company;

import java.util.Arrays;

public class MergeSort {

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }



    private static void merge(Comparable[] arr, Comparable[] aux, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;

        // copy arr into aux
        for (int a = low; a <= high; a++) {
            aux[a] = arr[a];
        }

        for (int k = low; k <= high; k++) {
            if (i > mid) arr[k] = aux[k++];
            else if (j > high) arr[k] = aux[i++];
            else if (less(aux[i], aux[j])) {
                arr[k] = aux[i++];
            } else {
                arr[k] = aux[j++];
            }
        }


    }


    private static void sort(Comparable[] arr, Comparable[] aux, int low, int high) {
        if (high <= low) return;

        int mid = low + (high - low) / 2;

        sort(arr, aux, low, mid);
        sort(arr, aux, mid + 1, high);
        merge(arr, aux, low, mid, high);

    }

    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }


    //test client
    public static void main(String[] args) {
         Integer[] arr = {1,5,73,34,2, 33333, 111111, 2, 2, 2};
         MergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
