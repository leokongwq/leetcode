package com.leokongwq.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:15
 * Email:leokongwq@gmail.com
 */
public class TestBinarySearch {
    public static int binarySearchV1(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return -1;
        }
        if(arr[0] == num) {
            return 0;
        }
        if (arr[arr.length - 1] == num){
            return arr.length - 1;
        }
        int left = 0, right = arr.length - 1;

        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arr[mid] > num){
                right = mid - 1;
                continue;
            }
            if (arr[mid] < num){
                left = mid + 1;
                continue;
            }
            return mid;
        }
        return - 1;
    }

    public static int binarySearchV2(int[] arr, int left, int right, int num){
        if (arr == null || arr.length == 0 || left > right){
            return - 1;
        }
        int mid = left + (right - left) / 2;
        if (arr[mid] > num){
            right = mid - 1;
            return binarySearchV2(arr, left, right, num);
        }
        if (arr[mid] < num){
            left = mid + 1;
            return binarySearchV2(arr, left, right, num);
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        System.out.println(binarySearchV1(arr, 0));
        System.out.println(binarySearchV1(arr, 1));
        System.out.println(binarySearchV1(arr, 4));
        System.out.println(binarySearchV1(arr, 7));
        System.out.println(binarySearchV1(arr, 8));
    }
}
