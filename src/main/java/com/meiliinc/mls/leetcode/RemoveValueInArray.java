package com.meiliinc.mls.leetcode;

/**
 * 从数组中删除指定的值, 并返回新数组的长度; 要求如下:
 * 1: 不能创建新数组
 * 2: 原有数组的元素顺序可以改变
 *  如: 原数组[1,2,2,3,2,4] ==> [1,3,4,3,2,4] 新数组的长度为3
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/25
 * Time: 下午7:22
 * Email:jiexiu@mogujie.com
 */
public class RemoveValueInArray {

    private static int removeElem(int[] arr, int num) {
        int i  = 0, j = 0;
        for (i = 0; i < arr.length; i++) {
            if (arr[i] == num){
                continue;
            }
            arr[j++] = arr[i];
        }
        return j;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2,3,2,4};
        removeElem(arr, 2);
    }
}
