package com.leokongwq.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/9/21
 * Time: 上午11:41
 * Email:leokongwq@gmail.com
 */
public class FindMinNumInRotatedArray {

    /**
     * 给定一个整数数组,该数组在某个点分割后, 前后2部分都是有序的,
     * 查找该数组中的最小值。
     * eg: [4,5,6,7, 1, 2, 3]
     * @param arr
     * @return
     */
    public static int findMinNumInRotatedArray(int[] arr, int start, int end){
        if (arr == null){
            return -1;
        }
        if (arr.length == 1){
            return arr[0];
        }
        int mid = start + (end - start) / 2;
        while (start != end){
            // 在大区间
            if (arr[mid] > arr[start] && arr[mid] > arr[end]){
                return findMinNumInRotatedArray(arr, mid + 1, end);
            }else {
                //在小区间
                return findMinNumInRotatedArray(arr, start, mid - 1);
            }
        }
        return arr[start];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4,5,6,7, 1, 2, 3};
        int minNum = findMinNumInRotatedArray(arr, 0, arr.length - 1);
        System.out.println("mininum num is :" + minNum);
    }
}
