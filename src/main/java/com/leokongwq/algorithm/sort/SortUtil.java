package com.leokongwq.algorithm.sort;

/**
 * @author jiexiu
 * created 2020/6/1 - 11:13
 */
public class SortUtil {

    /**
     * 有游标 i 将 数组分为两部分
     * 1. 小于 i 的是 小于 pivot 的部分
     * 2. 大于等于 i 的是待分区的部分
     */
    public static int partition(int[] arr, int p, int r, boolean desc) {
        int pivot = arr[r];
        int i = p;

        for (int j = p; j < r; j++) {
            if (desc && arr[j] >= pivot) {
                swap(arr, i, j);
                i++;
            }
            if (!desc && arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
//        System.out.println("i=" + i);
        return i;
    }

    private int partionDesc(int[] arr, int p, int r) {
        return partition(arr, p, r, true);
    }

    private int partionAsc(int[] arr, int p, int r) {
        return partition(arr, p, r, false);
    }

    static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
