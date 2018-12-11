package com.leokongwq.algorithm.sort;

/**
 * 快速排序
 * 最差时间复杂度为: O(n2)
 * 平均时间复杂度为: O(n*lgN)
 * 空间复杂度: O(lgN)~O(n)
 * 稳定性: 不稳定
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:18
 * Email:leokongwq@gmail.com
 */
public class QuickSort {
    /**
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static void quickSort(int[] arr, int left, int right) {
        //递归退出条件
        if (arr == null || arr.length == 1 || left > right) {
            return;
        }
        int i = left;
        int j = right;
        //选择基准数
        int pivot = arr[i];
        //基数
        //2个哨兵没有相遇
        while (i != j) {
            //从右边的哨兵开始
            while (arr[j] >= pivot && i < j) {
                j--;
            }
            //左边开始探测
            while (arr[i] <= pivot && i < j) {
                i++;
            }
            //没有相遇, 交换
            if (i < j) {
                swap(arr, i, j);
            }
        }
        //最终将基准数归位
        arr[left] = arr[i];
        arr[i] = pivot;
        //处理2个子序列
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * wikipedia
     * 下面的算法实现不是稳定的排序算法
     */
    private static void qSort(int[] arr, int head, int tail) {
        if (head >= tail || arr == null || arr.length <= 1) {
            return;
        }
        int i = head, j = tail, pivot = arr[(head + tail) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i < j) {
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                ++i;
                --j;
            } else if (i == j) {
                ++i;
            }
        }
        qSort(arr, head, j);
        qSort(arr, i, tail);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
//        quickSort(arr, 0, arr.length - 1);
        qSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.printf("%d,", i);
        }
        System.out.println();
    }
}
