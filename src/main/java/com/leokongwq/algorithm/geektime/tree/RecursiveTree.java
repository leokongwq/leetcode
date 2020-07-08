package com.leokongwq.algorithm.geektime.tree;

/**
 * @author : jiexiu
 * @date : 2020-06-18 17:25
 *
 * 如何编程打印一组数据的所有排列呢？这里就可以用递归来实现。如果我们确定了最后一位数据，那就变成了求解剩下 n−1 个数据的排列问题。
 * 而最后一位数据可以是 n 个数据中的任意一个，因此它的取值就有 n 种情况。
 * 所以，“n 个数据的排列”问题，就可以分解成 n 个“n−1 个数据的排列”的子问题。
 *
 * 全排列的递归算法的时间复杂度大于 O(n!)，小于 O(n∗n!)，
 **/
public class RecursiveTree {

	// 调用方式：
	// int[] a = a={1, 2, 3, 4}; printPermutations(a, 4, 4);
	// k表示要处理的子数组的数据个数
	public void printPermutations(int[] data, int n, int k) {
		if (k == 1) {
			for (int i = 0; i < n; ++i) {
				System.out.print(data[i] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < k; ++i) {
			//最后一位从 data[i] 到 data[k-1]
			int tmp = data[i];
			data[i] = data[k - 1];
			data[k - 1] = tmp;

			printPermutations(data, n, k - 1);
			//复原
			tmp = data[i];
			data[i] = data[k - 1];
			data[k - 1] = tmp;
		}
	}

	//1 个细胞的生命周期是 3 小时，1 小时分裂一次。求 n 小时后，容器内有多少细胞？请你用已经学过的递归时间复杂度的分析方法，分析一下这个递归问题的时间复杂度
	private static int cellSplit(int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return 2;
		}
		if (n == 2) {
			return 4;
		}
		//先分裂，后死亡   如果是先死亡后分裂：f(n) = [f(n-1) - f(n-3)] * 2
		return cellSplit(n - 1) * 2 - cellSplit(n - 3);
	}
}
