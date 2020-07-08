package com.leokongwq.algorithm.geektime.sort;

import com.leokongwq.algorithm.geektime.Printer;

/**
 * @author jiexiu
 * created 2020/5/24 - 09:26
 */
public class BubbleSort {

    /**
     * 冒泡排序
     *
     * 算法特性：
     * 基于比较和交换，稳定排序，原地排序，最好时间复杂度O(n), 最坏和平均时间复杂度O(n2)
     *
     * 1. 总共需要N-1次冒泡就可以排序号整个数组，原因在于每次冒泡可以归为一个元素，最后一个元素不需要处理。
     * 2. 外层循环有2个作用，
     *      1.控制冒泡次数
     *      2.通过i的值来计算已经排好序和未排好序的分隔点
     * 3. 每次冒泡都会将未排序区间的最大元素归位
     *
     *
     */
    static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                // 表示有数据交换
                flag = true;
            }
            // 没有数据交换，提前退出
            if (!flag) {
                break;
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,2,1};
        bubbleSort(arr);
        Printer.printArray(arr);

        arr = new int[]{5, 1, 2, 3, 4};
        bubbleSort(arr);
        Printer.printArray(arr);
    }
}
