package com.meiliinc.mls.leetcode;

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
 * Email:jiexiu@mogujie.com
 */
public class InsertSort {

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr){
        int n = arr.length;
        int j = 0;
        for (int i = 1; i < n; i++){
            int k = j;
            //查找插入位置
            while (k >= 0 && arr[k] > arr[i]){
                k--;
            }
            if (k == j){
                j++;
                continue;
            }
            int tmp = arr[i];
            for(int m = i; m > k + 1; m--){
                arr[m] = arr[m-1];
            }
            arr[k+1] = tmp;
            // 4 3 2 1
            j++;
        }
        return arr;
    }

    public static void main(String[] args) {
        //int[] arr = new int[]{3,1,5,8,2,4};
        int[] arr = new int[]{4, 3, 2, 1};
        arr = insertSort(arr);
        System.out.println(arr);
    }
}
