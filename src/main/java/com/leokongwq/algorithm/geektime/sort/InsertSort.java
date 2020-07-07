package com.leokongwq.algorithm.geektime.sort;

import com.leokongwq.algorithm.geektime.Printer;

/**
 * @author jiexiu
 * created 2020/5/24 - 09:40
 */
public class InsertSort {

    /**
     * 插入排序
     *
     * 算法特性：
     * 基于比较和交换，稳定排序，原地排序，最好时间复杂度O(n), 最坏和平均时间复杂度O(n2)
     *
     * 算法思想如下：
     *
     * 1. 将待排序的数组分为2部分，一部分是已经排好序的， 另一部分是未排好序的。
     * 2. 每次从未排好序的数组中取一个元素，插入已经排好序的序列中。
     * 3. 循环执行第二步，直到所有未排序的序列都插入已经排序的序列中。
     */
    static void insertSort(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        //从第二个元素开始，因为第一个元素组成的序列肯定是有序的
        for (int i = 1; i < n; i++) {
            int value = arr[i];
            //从后往前查找，查找插入点并进行插入， 可以保持稳定排序
            int j = i - 1;
            while (j >= 0 && arr[j] > value) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = value;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};
        insertSort(arr, arr.length);
        Printer.printArray(arr);

        arr = new int[]{5, 1, 2, 3, 4};
        insertSort(arr, arr.length);
        Printer.printArray(arr);
    }

}
