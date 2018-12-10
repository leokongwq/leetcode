package com.leokongwq.algorithm.sort;

/**
 * 插入排序
 * 最差时间复杂度为: O(n2)
 * 平均时间复杂度为: O(n2)
 * 空间复杂度: O(1)
 * 稳定性: 稳定
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午9:58
 * Email:leokongwq@gmail.com
 * 插入排序（英语：Insertion Sort）是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，
 * 在已排序序列中从后向前扫描，找到相应位置并插入。插入排序在实现上，通常采用in-place排序（即只需用到 O(1)的额外空间的排序），
 * 因而在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位，为最新元素提供插入空间。
 */
public class InsertSort {
    /**
     * 插入排序
     *
     * @param arr 待排序的数组
     */
    private static int[] insertSort(int[] arr) {
        int n = arr.length;
        int j = 0;
        for (int i = 1; i < n; i++) {
            int k = j;
            //查找插入位置
            while (k >= 0 && arr[k] > arr[i]) {
                k--;
            }
            if (k == j) {
                j++;
                continue;
            }
            int tmp = arr[i];
            for (int m = i; m > k + 1; m--) {
                arr[m] = arr[m - 1];
            }
            arr[k + 1] = tmp;
            // 4 3 2 1
            j++;
        }
        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr 待排序的数组
     */
    public static int[] insertSortV1(int[] arr) {
        int len = arr.length;
        int i, j;
        int temp;
        for (i = 1; i < len; i++) {
            //与已排序的数进行比较，大于temp，大于temp，有序元素进行后移
            temp = arr[i];
            // 如果将赋值放到下一行的for循环内, 会导致在第10行出现j未声明的错误
            j = i - 1;
            //j循环到-1时，由于[[短路求值]](http://zh.wikipedia.org/wiki/短路求值)，不会运算array[-1]
            for (; j >= 0 && arr[j] > temp; j--) {
                arr[j + 1] = arr[j];
            }
            //被排序数放到正确的位置 (由于循环结束时: j--, 所以 赋值时需要进行：+1操作)
            arr[j + 1] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{3,1,5,8,2,4};
        int[] arr = new int[]{4, 3, 2, 1};
        System.out.println(arr);
    }
}
