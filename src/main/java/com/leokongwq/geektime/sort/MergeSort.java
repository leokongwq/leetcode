package com.leokongwq.geektime.sort;

import com.leokongwq.geektime.Printer;

/**
 * @author jiexiu
 * created 2020/5/25 - 22:12
 */
public class MergeSort {

    static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        doMergeSort(arr, 0, arr.length - 1);
    }

    private static void doMergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        doMergeSort(arr, left, mid);
        doMergeSort(arr,mid + 1, right);

        merge(arr, left, mid, right);
    }

    /**
     * 将数组arr中的部分元素合并到 result 结果数组中
     * @param arr 原始数组
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] result = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                result[k++] = arr[i++];
            } else {
                result[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            result[k++] = arr[i++];
        }
        while (j <= right) {
            result[k++] = arr[j++];
        }

        for (int p = 0; p < result.length; p++) {
            arr[p+left] = result[p];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 2, 4};
        merge(arr, 0, 1, 3);
        Printer.printArray(arr);

        int[] arrb = new int[]{1, 3, 2, 4};
        mergeSort(arrb);
        Printer.printArray(arrb);

        int[] arrc = new int[]{4, 3, 2, 1};
        mergeSort(arrc);
        Printer.printArray(arrc);

        int[] arrd = new int[]{2, 1};
        mergeSort(arrd);
        Printer.printArray(arrd);
    }

}
