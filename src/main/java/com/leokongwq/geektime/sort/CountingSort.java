package com.leokongwq.geektime.sort;

import com.leokongwq.geektime.Printer;

/**
 * @author jiexiu
 * created 2020/6/1 - 21:53
 */
public class CountingSort {

    private static void countingSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        //求最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //计数
        int[] countingArr = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            countingArr[arr[i]]++;
        }

        //顺序求和
        for (int j = 1; j < countingArr.length; j++) {
            countingArr[j] += countingArr[j - 1];
        }

        //关键
        int[] result = new int[arr.length];
        for (int p = arr.length - 1; p >= 0; p--) {
            int index = countingArr[arr[p]] - 1;
            result[index] = arr[p];
            countingArr[arr[p]]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 3, 0, 2, 3, 0, 3};
        countingSort(arr);

        Printer.printArray(arr);
    }
}
