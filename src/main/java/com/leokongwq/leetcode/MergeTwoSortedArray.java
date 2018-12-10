package com.leokongwq.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午3:07
 * Email:leokongwq@gmail.com
 */
public class MergeTwoSortedArray {

    /**
     * 合并2个排序好的数组
     * @param a
     * @param b
     * @return
     */
    public static int[] mergeTwoSortedArray(int[] a, int[] b){
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        //合并前半部分
        while (i < a.length && j < b.length){
            if (a[i] < b[j]){
                result[k++] = a[i++];
            }else {
                result[k++] = b[j++];
            }
        }
       while (i < a.length){
           result[k++] = a[i++];
       }
       while (j < b.length){
           result[k++] = b[j++];
       }
        return result;
    }

    /**
     * 合并2个排序数组, 数组 a 足够大, 可以容纳b的元素
     * @param a
     * @param b
     * @return
     */
    public static int[] mergeTwoSortedArrayV2(int[] a, int[] b){
        int k = a.length - 1;
        int i = 2;
        int j = b.length - 1;
        while (k > 0){
            if (a[i] < b[j]){
                a[k--] = b[j--];
            }else {
                a[k--] = a[i--];
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5};
        int[] arr2 = new int[]{2, 4, 6};
        int [] ret = mergeTwoSortedArray(arr1, arr2);
        System.out.println(ret);
        int[] arrA = new int[]{1,3,5, 0 , 0 ,0};
        int[] arrB = new int[]{2,4,6};
        ret = mergeTwoSortedArrayV2(arrA, arrB);
        System.out.println(ret);
    }
}
