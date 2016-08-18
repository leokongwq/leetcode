package com.meiliinc.mls.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:18
 * Email:jiexiu@mogujie.com
 */
public class QuickSort {
    /**
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] quickSort(int[] arr, int left, int right){
        if (left > right){
            return arr;
        }
        int i = left;
        int j = right;
        //基数
        int tmp = arr[left];
        //2个哨兵没有相遇
        while (i != j){
            //从右边的哨兵开始
            while (arr[j] >= tmp && i < j){
                j--;
            }
            //左边开始探测
            while (arr[i] <= tmp && i < j){
                i++;
            }
            //没有相遇, 交换
            if (i < j){
                swap(arr, i, j);
            }
        }
        //最终将基准数归位
        arr[left] = arr[i];
        arr[i] = tmp;
        //处理2个子序列
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
        return arr;
    }

    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6,1,2,7,9,3,4,5,10,8};
        int[] result = quickSort(arr, 0, arr.length - 1);
        for (int i : result){
            System.out.printf("%d,", i);
        }
        System.out.println();
    }
}
