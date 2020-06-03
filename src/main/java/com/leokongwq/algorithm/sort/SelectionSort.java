package com.leokongwq.algorithm.sort;

/**
 * @author jiexiu
 * created 2018/12/10 - 19:34
 * 选择排序
 * 选择排序（Selection sort）是一种简单直观的排序算法。它的工作原理如下。首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置
 * 上，因此对 n 个元素的表进行排序总共进行至多 n - 1 次交换。在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 */
public class SelectionSort {

    /**
     *
     * @param arr 待排序的数组
     * @param n 数组大小
     */
    private static void selectSort(int[] arr, int n) {

        int minIdx = 0;
        /*
         下标 i 将待排序元素分隔为两部分：
         1. 小于 i 的是已经排序的部分
         2. 大于等于 i 的是待排序部分
         */
        for (int i = 0; i < n - 1; i++) {
            //查找待排序序列的最小值
            minIdx = i;
            for (int j = i; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                int temp = arr[minIdx];
                arr[minIdx] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{3, 2, 1, 6, 5, 4};

        selectSort(arr, arr.length);

        for (int n : arr) {
            System.out.print(n + ",");
        }
    }
}
