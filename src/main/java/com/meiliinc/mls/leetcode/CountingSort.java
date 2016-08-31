package com.meiliinc.mls.leetcode;

/**
 * 基数排序
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/31
 * Time: 下午6:38
 * Email:jiexiu@mogujie.com
 */
public class CountingSort {

    /**
     * 计数排序
     * @param arr 待排序数组
     * @param n 最大的数
     * @return 排序后的数组
     */
    public static int[] countSort(int[] arr, int n){
        int[] buckets = new int[n + 1];
        for (int num : arr){
            buckets[num]++;
        }
        for (int i = 1; i < buckets.length; i++){
            buckets[i] += buckets[i - 1];
        }
        int[] sorted = new int[arr.length];
        //倒序遍历arr
        for (int i = arr.length - 1; i >= 0; i--){
            //buckets[arr[i]] 表示该元素签名还有多少个元素(包括该元素)
            sorted[buckets[arr[i]] - 1] = arr[i];
            buckets[arr[i]]--;
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,1,4,6,7,2};
        arr = countSort(arr, 7);
        System.out.println(arr);
    }
}
