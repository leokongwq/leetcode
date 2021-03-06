package com.leokongwq.algorithm.geektime.heap;

import com.leokongwq.algorithm.base.Printer;

/**
 * @author : jiexiu
 * @date : 2020-06-18 18:18
 * <p>
 * 堆是一种特殊的树。只要满足这两点，它就是一个堆
 * 1. 堆是一个完全二叉树；
 * 2. 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值。
 *
 * 堆可以实现：优先级队列、求 Top K 和求中位数。
 * 很对数据结构和算法都依赖堆：赫夫曼编码、图的最短路径、最小生成树算法
 * Java： PriorityQueue
 * C++： priority_queue
 *
 * 常见面试题：
 * 1. 合并有序小文件
 * 2. 高性能定时器
 **/
public class ArrayHeap {

	private int[] arr;
	private int n;
	private int count;

	public ArrayHeap(int capacity) {
		this.n = capacity;
		this.arr = new int[capacity + 1];
	}

	public void insert(int data) {
		if (count >= n) {
			return;
		}
		++count;
		arr[count] = data;

		int i = count;
		// 自下往上堆化, 是一个大顶推。 父节点的下标 是 i / 2
		// 根节点下标是1，也就是堆定元素的下标是 1
		while (i / 2 > 0 && arr[i] > arr[i / 2]) {
			swap(arr, i, i / 2);
			i = i / 2;
		}
	}

	public void removeMax() {
		// 堆中没有数据
		if (count == 0) {
			return;
		}
		arr[1] = arr[count];
		//等价于删除元素，因为腾出来位置了
		--count;
		heapify(arr, count, 1);
	}

	/**
	 * 自上往下堆化
	 * 构造的是一个大顶堆
	 */
	private static void heapify(int[] a, int n, int i) {
		while (true) {
			//找到 i 的左右子节点中的最大值，并进行交换
			int maxPos = i;
			if (i * 2 <= n && a[i] < a[i * 2]) {
				maxPos = i * 2;
			}
			if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) {
				maxPos = i * 2 + 1;
			}
			//说明 i 就是合适位置， arr[i] 大于等于 arr[2 * i] 和 arr[2 * i + 1]
			if (maxPos == i) {
				break;
			}
			swap(a, i, maxPos);
			i = maxPos;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * 按照插入元素的方式建立堆，都是从下往上进行调整
	 */
	private void buildHeapV1(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			// 自下往上堆化, 是一个大顶推。 父节点的下标 是 i / 2
			// 根节点下标是1，也就是堆定元素的下标是 1
			while (i / 2 > 0 && arr[i] > arr[i / 2]) {
				swap(arr, i, i / 2);
				i = i / 2;
			}
		}
	}

	/**
	 * 从后往前， 从上往下进行调整
	 * 对于完全二叉树来说，下标从 n/2 ​+ 1 到 n 的节点都是叶子节点。
	 * 注意： 这里 i = 0 是空的，不适用，方便编程。
	 * 堆排序的建堆过程的时间复杂度是 O(n)
	 * 这个构造的是一个大顶堆
	 */
	private static void buildHeapV2(int[] arr, int n) {
		// n / 2 是第最后一个非叶子节点
		for (int i = n / 2; i >= 1; --i) {
			heapify(arr, n, i);
		}
	}


	/**
	 * n表示数据的个数，数组a中的数据从下标1到n的位置。
	 * 堆排序步骤：
	 * 1. 将数组堆化，构造一个大顶堆 （原因是结果升序排列，否则构造小顶堆）
	 * 2. 将第一步得到的堆化数组中第一个元素和最后一个元素进行交换，那么相当于最大的元素已经归位
	 * 3. 调整目前堆定元素到合适位置，调整后，堆定元素就是第二大的元素了。
	 * 4. 重复执行 2->3 步骤，直到遇到数组第一个元素时停止
	 */
	private static void sort(int[] a, int n) {
		buildHeapV2(a, n);
		int k = n;
		while (k > 1) {
			swap(a, 1, k);
			--k;
			heapify(a, k, 1);
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[]{0, 3, 1, 2, 5, 4, 6};
		sort(arr, 6);
		Printer.printArray(arr);
	}
}
