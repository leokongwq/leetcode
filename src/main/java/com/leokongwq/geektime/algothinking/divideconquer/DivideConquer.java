package com.leokongwq.geektime.algothinking.divideconquer;

/**
 * @author : jiexiu
 * @date : 2020-06-28 11:16
 * <p>
 * 分治算法（divide and conquer）的核心思想其实就是四个字，分而治之 ，也就是将原问题划分成 n 个规模较小，并且结构与原问题相似的子问题，
 * 递归地解决这些子问题，然后再合并其结果，就得到原问题的解。
 * <p>
 * 分治算法是一种处理问题的思想，递归是一种编程技巧。实际上，分治算法一般都比较适合用递归来实现。
 * <p>
 * 每一层递归都会涉及这样三个操作：
 * 分解：将原问题分解成一系列子问题；
 * 解决：递归地求解各个子问题，若子问题足够小，则直接求解；
 * 合并：将子问题的结果合并成原问题。
 * <p>
 * 分治算法能解决的问题，一般需要满足下面这几个条件：
 * <p>
 * 1. 原问题与分解成的小问题具有相同的模式；
 * 2. 原问题分解成的子问题可以独立求解，子问题之间没有相关性，这一点是分治算法跟动态规划的明显区别，等我们讲到动态规划的时候，会详细对比这两种算法；
 * 3. 具有分解终止条件，也就是说，当问题足够小时，可以直接求解；
 * 4. 可以将子问题合并成原问题，而这个合并操作的复杂度不能太高，否则就起不到减小算法总体复杂度的效果了。
 * <p>
 * <p>
 * 用分治的思想来求数组 A 的逆序对个数。我们可以将数组分成前后两半 A1 和 A2，分别计算 A1 和 A2 的逆序对个数 K1 和 K2，
 * 然后再计算 A1 与 A2 之间的逆序对个数 K3。那数组 A 的逆序对个数就等于 K1+K2+K3。
 *
 *
 * 关于分治算法，我这还有两道比较经典的问题，你可以自己练习一下。
 * 1. 二维平面上有 n 个点，如何快速计算出两个距离最近的点对？
 * 2. 有两个 n*n 的矩阵 A，B，如何快速求解两个矩阵的乘积 C=A*B？
 *
 **/
public class DivideConquer {

	private int num = 0;

	public int count(int[] a, int n) {
		num = 0;
		mergeSortCounting(a, 0, n - 1);
		return num;
	}

	private void mergeSortCounting(int[] a, int p, int r) {
		//有限递归，子问题足够小，直接求解
		if (p >= r) {
			return;
		}
		//分解问题
		int q = (p + r) / 2;
		//分别求解子问题
		mergeSortCounting(a, p, q);
		mergeSortCounting(a, q + 1, r);
		//合并子问题的解
		merge(a, p, q, r);
	}

	private void merge(int[] a, int p, int q, int r) {
		int i = p, j = q + 1, k = 0;
		int[] tmp = new int[r - p + 1];
		while (i <= q && j <= r) {
			if (a[i] <= a[j]) {
				tmp[k++] = a[i++];
			} else {
				// 统计p-q之间，比a[j]大的元素个数
				num += (q - i + 1);
				tmp[k++] = a[j++];
			}
		}
		// 处理剩下的
		while (i <= q) {
			tmp[k++] = a[i++];
		}
		// 处理剩下的
		while (j <= r) {
			tmp[k++] = a[j++];
		}
		// 从tmp拷贝回a
		for (i = 0; i <= r - p; ++i) {
			a[p + i] = tmp[i];
		}
	}

}
