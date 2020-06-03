package com.leokongwq.geektime.sort;

import com.leokongwq.geektime.Printer;

public class QuickSort {

    private static void quickSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        doQuickSort(arr, 0, arr.length - 1);
    }

    private static void doQuickSort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        //分区
        int q = SortUtil.partition(arr, p, r, false);

        doQuickSort(arr, p, q - 1);
        doQuickSort(arr, q + 1, r);
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        int[] arr = new int[]{2, 1, 1};

        quickSort(arr);

        Printer.printArray(arr);
        System.out.println();
    }
}