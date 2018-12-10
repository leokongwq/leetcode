package com.leokongwq.algorithm.search;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/1/20
 * Time: 上午10:56
 * Email:leokongwq@gmail.com
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        System.out.println(binarySearch(arr, 1));
        System.out.println(binarySearch(arr, 2));
        System.out.println(binarySearch(arr, 3));
        System.out.println(binarySearch(arr, 4));
    }

    /**
     * 二分法查找数组中特定元素, 查询到指定元素返回元素下标,否则返回-1
     * @param arr
     * @param target
     * @return
     */
    private static int binarySearch(int[] arr, int target){
        if (null == arr || arr.length == 0){
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (arr[mid] > target){
                high = mid - 1;
            }else if (arr[mid] < target){
                low = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }
}
