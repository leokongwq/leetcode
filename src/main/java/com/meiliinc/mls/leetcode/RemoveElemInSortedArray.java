package com.meiliinc.mls.leetcode;

/**
 * 删除一个排序数组中的重复元素,
 * 1: 不能开辟新的内存空间
 * 2: 每个元素出现一次
 * 3: 原有顺序保存一致
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/25
 * Time: 下午7:43
 * Email:jiexiu@mogujie.com
 */
public class RemoveElemInSortedArray {

    public static int removeRepeatElemInSortedArray(int[] arr){
            if (null == arr || arr.length == 0){
                return 0;
            }
            if (arr.length == 1){
                return 1;
            }
            int  j = 1;
            for (int i = 0; i < arr.length; i++){
                if (j == arr.length){
                    return i;
                }
                if (arr[i] != arr[j]){
                    j++;
                    continue;
                }
                if (j == arr.length - 1){
                    return j;
                }
                arr[j] = arr[j+1];
                j++;
            }
            return arr.length;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,4,5};
        int len = removeRepeatElemInSortedArray(arr);
        System.out.println(len);
        arr = new int[]{1,2,3,3,4,5,5,6};
        System.out.println(removeRepeatElemInSortedArray(arr));
    }
}
