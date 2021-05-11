package com.company;

import java.util.Arrays;

public class WeightedQuickUnion {
    int[] UF;
    int[] sizes;
    WeightedQuickUnion(int n){
        UF = new int[n];
        sizes = new int[n];
        for (int i = 0; i < n; i++){
            UF[i] = i;
            sizes[i] = 1;
        }


    }

    private int root(int a){
        while (a != UF[a]){
            UF[a] = UF[UF[a]];// Path compression (halves path length)
            a = UF[a];
        }
        return a;
    }

    public void union(int a, int b){ // weighted union

        int rootA = root(a);
        int rootB = root(b);

        if (rootA == rootB) return;
        if (sizes[rootA] < sizes[rootB]){
            UF[rootA] = rootB;
            sizes[rootB] += sizes[rootA];
        }else{
            UF[rootB] = rootA;
            sizes[rootA] += sizes[rootB];

        }
    }

    public boolean connected(int a, int b){
        return root(a) == root(b);
    }



    @Override
    public String toString() {
        return "WeightedQuickUnion{" +
                "UF=" + Arrays.toString(UF) +
                ", sizes=" + Arrays.toString(sizes) +
                '}';
    }

    //test client
    public static void main(String[] args) {
        WeightedQuickUnion UF = new WeightedQuickUnion(10);

        UF.union(0,9);
        UF.union(1,9);
        UF.union(2,9);
        UF.union(3,9);

        UF.union(4,5);
        UF.union(5,6);
        UF.union(6,7);
        UF.union(7,9);


        System.out.println(UF.connected(0,9));
        System.out.println(UF.connected(1,9));
        System.out.println(UF.connected(2,9));
        System.out.println(UF.connected(3,9));
        System.out.println(UF.connected(4,9));


        System.out.println(UF);
    }
}
