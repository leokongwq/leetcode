package com.leokongwq.algorithm.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:32
 * <p>
 * 如何量化两个字符串的相似度？计算机只认识数字，所以要解答开篇的问题，我们就要先来看，如何量化两个字符串之间的相似程度呢？
 * 有一个非常著名的量化方法，那就是编辑距离（Edit Distance）。顾名思义，编辑距离指的就是，将一个字符串转化成另一个字符串，
 * 需要的最少编辑操作次数（比如增加一个字符、删除一个字符、替换一个字符）。编辑距离越大，说明两个字符串的相似程度越小；相反，编辑距离就越小，说明两个字符串的相似程度越大。
 * 对于两个完全相同的字符串来说，编辑距离就是 0。根据所包含的编辑操作种类的不同，编辑距离有多种不同的计算方式，
 * 比较著名的有莱文斯坦距离（Levenshtein distance）和最长公共子串长度（Longest common substring length）。
 * 其中，莱文斯坦距离允许增加、删除、替换字符这三个编辑操作，最长公共子串长度只允许增加、删除字符这两个编辑操作。
 * 而且，莱文斯坦距离和最长公共子串长度，从两个截然相反的角度，分析字符串的相似程度。莱文斯坦距离的大小，表示两个字符串差异的大小；
 * 而最长公共子串的大小，表示两个字符串相似程度的大小。
 *
 * 假设 a[0, i - 1] 和 b[0, j - 1] 的莱文斯坦距离是 m, 那么如何求 a[0, i] 和 b[0, j]呢？
 * 分为2中情况:
 * a[i] == b[j] : 此时a[0, i] 和 b[0, j]的莱文斯坦距离计算公式如下：
 * minDist[i, j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]))
 *
 * a[i] != b[j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1])) + 1(表示i和j要相等，需要额外编辑一次)
 *
 **/
public class LwstDP {

	/**
	 * 使用动态规划求解 莱文斯坦距离
	 */
	public int lwstdp(char[] a, int n, char[] b, int m) {
		//定义状态表，minDist[i][j] 表示 以 i 和 j 结尾的子串的 莱文斯坦距离
		int[][] minDist = new int[n][m];

		// 初始化第0行:a[0..0] 与 b[0..j] 的编辑距离
		for (int j = 0; j < m; ++j) {
			if (a[0] == b[j]) {
				//可以理解为a[0]签名插入n个字符 或 b[j] 删除 j个字符
				minDist[0][j] = j;
			} else if (j != 0) {
				minDist[0][j] = minDist[0][j - 1] + 1;
			} else {
				// 第一个字符不相等，且 j == 0, 只需要替换其中一个即可，因此操作次数为1
				minDist[0][j] = 1;
			}
		}
		// 初始化第0列:a[0..i]与b[0..0]的编辑距离
		for (int i = 0; i < n; ++i) {
			if (a[i] == b[0]) {
				minDist[i][0] = i;
			} else if (i != 0) {
				minDist[i][0] = minDist[i - 1][0] + 1;
			} else {
				minDist[i][0] = 1;
			}
		}

		// 按行填表
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < m; ++j) {
				if (a[i] == b[j]) {
					minDist[i][j] = min(
							minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
				} else {
					minDist[i][j] = min(
							minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
				}
			}
		}
		return minDist[n - 1][m - 1];
	}

	private int min(int x, int y, int z) {
		int minv = Integer.MAX_VALUE;
		if (x < minv) {
			minv = x;
		}
		if (y < minv) {
			minv = y;
		}
		if (z < minv) {
			minv = z;
		}
		return minv;
	}

	public static void main(String[] args) {
		LwstDP lwstdp = new LwstDP();
		System.out.println(lwstdp.lwstdp("mitcmu".toCharArray(), "mitcmu".length(), "mtacnu".toCharArray(), "mtacnu".length()));
	}
}
