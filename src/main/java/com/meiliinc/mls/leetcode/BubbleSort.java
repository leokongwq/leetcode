package com.meiliinc.mls.leetcode;

/**
 *
 * 冒泡排序(时间换空间)
 * 最差时间复杂度为: O(n2)
 * 平均时间复杂度为: O(n2)
 * 空间复杂度: O(1)
 * 稳定性: 稳定
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午10:45
 * Email:jiexiu@mogujie.com
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                if (arr[j] < arr[i]){
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1};
        arr = bubbleSort(arr);
        System.out.println(arr);
    }
}
