package com.leokongwq.algorithm.sort;

/**
 * 冒泡排序(时间换空间)
 * 最差时间复杂度为: O(n2)
 * 平均时间复杂度为: O(n2)
 * 空间复杂度: O(1)
 * 稳定性: 稳定
 * <p>
 * 冒泡排序的实现通常会对已经排序好的数列拙劣地运行 O(n2)
 * 而插入排序在这个例子只需要O(n)个运算. 因此工程中通常使用插入排序替代冒泡排序
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午10:45
 * Email:leokongwq@gmail.com
 */
public class BubbleSort {

	/**
	 * 这种其实是错误的冒泡排序写法， 和教科书描述的算法过程不同。
	 * 可以认为是冒泡排序的相反过程，可以提前查找到 arr 中前 K 个最小的元素
	 */
	public static int[] bubbleSort(int[] arr) {
		int n = arr.length;
		//外层循环一次，可以找到数组中的最小值，并保持到arr[i]位置
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}
		return arr;
	}

	/**
	 * 冒泡排序总共需要比较n-1轮(最后一轮只有一个元素,没有比较的必要),每一轮只能将一个元素归位
	 * 每一轮都需要将该轮(idx - 1)位置的元素和所有未归位的元素进行一一比较。
	 */
	public static int[] bubbleSortV1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					swap(arr, j, j + 1);
				}
			}
		}
		return arr;
	}

	static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	static void printArray(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
	}
	public static void main(String[] args) {
		int[] arr = new int[]{2, 3, 5, 1};
		arr = bubbleSort(arr);
		printArray(arr);
		System.out.println();

		arr = new int[]{2, 3, 5, 1};
		arr = bubbleSortV1(arr);
		printArray(arr);
	}
}
