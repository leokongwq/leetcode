package com.leokongwq.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:16
 * Email:leokongwq@gmail.com
 */
public class TestArrayMerge {
    /**
     * 合并2个有序的整形数组
     * @param a
     * @param b
     */
    public static final int[] arrayMerge(int[] a, int[] b){
        if (a == null && b == null){
            return null;
        }
        if (a == null || a.length == 0){
            return b;
        }
        if (b == null || a.length == 0){
            return a;
        }
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        //
        while (i < a.length && j < b.length){
            if (a[i] <= b[j]){
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        //a有剩余元素
        while (i < a.length){
            result[k++] = a[i++];
        }
        //b有剩余元素
        while (j < b.length){
            result[k++] = b[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] merged = arrayMerge(new int[]{1, 3, 5}, new int[]{2, 3, 4, 4});
        System.out.println(merged);
    }
}
