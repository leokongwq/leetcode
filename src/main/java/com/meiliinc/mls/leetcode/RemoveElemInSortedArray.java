package com.meiliinc.mls.leetcode;

/**
 * 删除一个排序数组中的重复元素,
 * 1: 不能开辟新的内存空间
 * 2: 每个元素出现一次
 * 3: 原有顺序保存一致
 * 如 : [1,2,2] => [1,2] 返回2; [1,2,3,3,4,5,5,6] => [1,2,3,4,5,6] 返回6
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/25
 * Time: 下午7:43
 * Email:jiexiu@mogujie.com
 */
public class RemoveElemInSortedArray {

    /**
     * 实现原理:
     * 1. 设置i,j 两个游标, j 从1开始;
     * 2. 比较arr[i] 和 arr[j]; 如果不相等, 判断 i 和 j 的距离, 如果距离为1, 则 i , j 同时前进; 大于1, 则将arr[++1] 赋值为 arr[j]
     * 3. 如果arr[i] 和 arr[j] 相等, 则 i 不动,  j 前进;
     * 4. 当 j = arr.length 循环结束; 此时 i 指向新数组的最后一个元素。 新数组的长度为: i+1
     * @param arr 待删除重复元素的排序数组
     * @return
     */
    public static int removeRepeatElemInSortedArray(int[] arr){
            if (null == arr || arr.length == 0){
                return 0;
            }
            if (arr.length == 1){
                return 1;
            }
            int i = 0, j = 1;
            while (j < arr.length){
                if (arr[i] != arr[j]){ //相差一个位置
                    if((j - i) == 1){
                        i++;
                        j++;
                        continue;
                    }else {
                        arr[++i] = arr[j];
                        continue;
                    }
                }else if (arr[i] == arr[j]){ //相等, j往后走, i不动
                    j++;
                }
            }
            return i+1;
    }

    /**
     * 删除排序数组中的重复元素, 重复元素只能出现一次
     * @param arr
     * @param n
     * @return
     */
    public static int removeRepeatElemInSortedArrayV1(int[] arr, int n){
            if (n == 0){
                return 0;
            }
            int j = 0;
            for (int i = 1; i < arr.length; i++){
                if (arr[j] != arr[i]){
                    arr[++j] = arr[i];
                }
            }
            return j + 1;
    }


    public static int removeRepeatElemInSortedArrayV2(int[] arr){
        if (null == arr || arr.length == 0){
            return 0;
        }
        if (arr.length == 1){
            return 1;
        }
        int i = 0, j = 1, cnt = 0;
        while (j < arr.length){
            if (arr[i] != arr[j]){ //相差一个位置
                if((j - i) == 1){
                    i++;
                    j++;
                    continue;
                }else {
                    arr[++i] = arr[j];
                    cnt = 0;
                    continue;
                }
            }else if (arr[i] == arr[j]){ //相等, j往后走, i不动
                if(cnt == 0){ //
                    i++;
                    j++;
                    cnt++;
                    continue;
                }
                j++;
            }
        }
        return i;
    }

    /**
     * 产出重复数组中出现的元素, 重复元素只能出现2次
     * @param arr
     * @param n
     * @return
     */
    public static int removeRepeatElemInSortedArrayV3(int[] arr, int n){
        if (n == 0){
            return 0;
        }

        int j = 0, num = 0;
        for (int i = 1; i < n; i++){
            if (arr[j] == arr[i]){
                num++;
                if (num < 2){
                    arr[++j] = arr[i];
                }
            }else {
                arr[++j] = arr[i];
                num = 0;
            }
        }
        return j + 1;
    }

    private static void printArray(int[] arr, int len){
        for (int i = 0; i < len; i++){
            System.out.print(arr[i] + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,3,4,5};
        int len = removeRepeatElemInSortedArray(arr);
        System.out.println(len);
        printArray(arr, len);

        arr = new int[]{1,2,3,3,4,5,5,6};
        len = removeRepeatElemInSortedArray(arr);
        System.out.println(len);
        printArray(arr, len);

        arr = new int[]{1,1,2};
        len = removeRepeatElemInSortedArray(arr);
        System.out.println(len);
        printArray(arr, len);

        //连续元素至多2个
        arr = new int[]{1,2,3,3,3,5,5,6};
        len = removeRepeatElemInSortedArrayV2(arr);
        System.out.println(len);
        printArray(arr, len);

        arr = new int[]{1,1,1,2,2,3};
        len = removeRepeatElemInSortedArrayV2(arr);
        System.out.println(len);
        printArray(arr, len);
    }
}
