package com.leokongwq.algorithm.sort;

/**
 *
 * 冒泡排序(时间换空间)
 * 最差时间复杂度为: O(n2)
 * 平均时间复杂度为: O(n2)
 * 空间复杂度: O(1)
 * 稳定性: 稳定
 *
 * 冒泡排序的实现通常会对已经排序好的数列拙劣地运行 O(n2)
 * 而插入排序在这个例子只需要O(n)个运算. 因此工程中通常使用插入排序替代冒泡排序
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午10:45
 * Email:leokongwq@gmail.com
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

    /**
     * 冒泡排序总共需要比较n-1轮(最后一轮只有一个元素,没有比较的必要),每一轮只能将一个元素归位
     * 每一轮都需要将该轮(idx - 1)位置的元素和所有未归位的元素进行一一比较。
     * @param arr
     * @return
     */
    public static int[] bubbleSortV1(int[] arr){
        for (int i = 0; i < arr.length - 1; i++){
            for (int j = i; j < arr.length - 1; j++){
                if (arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1};
        arr = bubbleSort(arr);
        System.out.println(arr);

        arr = bubbleSortV1(arr);
        System.out.println(arr);
    }
}
