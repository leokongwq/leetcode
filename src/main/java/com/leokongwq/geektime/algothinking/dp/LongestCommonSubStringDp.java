package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 18:12
 * 最长公共子串不连续
 **/
public class LongestCommonSubStringDp {

	public int lcs(char[] a, int n, char[] b, int m) {
		int[][] maxlcs = new int[n][m];
		//初始化第0行：a[0..0]与b[0..j]的maxlcs
		for (int j = 0; j < m; ++j) {
			if (a[0] == b[j]) {
				maxlcs[0][j] = 1;
			} else if (j != 0) {
				maxlcs[0][j] = maxlcs[0][j - 1];
			} else {
				maxlcs[0][j] = 0;
			}
		}
		//初始化第0列：a[0..i]与b[0..0]的maxlcs
		for (int i = 0; i < n; ++i) {
			if (a[i] == b[0]) {
				maxlcs[i][0] = 1;
			} else if (i != 0) {
				maxlcs[i][0] = maxlcs[i - 1][0];
			} else {
				maxlcs[i][0] = 0;
			}
		}

		// 填表
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < m; ++j) {
				if (a[i] == b[j]) {
					maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1] + 1);
				} else {
					maxlcs[i][j] = max(maxlcs[i - 1][j], maxlcs[i][j - 1], maxlcs[i - 1][j - 1]);
				}
			}
		}
		return maxlcs[n - 1][m - 1];
	}

	private int max(int x, int y, int z) {
		int maxv = Integer.MIN_VALUE;
		if (x > maxv) maxv = x;
		if (y > maxv) maxv = y;
		if (z > maxv) maxv = z;
		return maxv;
	}

	public static void main(String[] args) {
		LongestCommonSubStringDp lcsdp = new LongestCommonSubStringDp();
		String a = "befdc";
		String b = "bc";

		int result = lcsdp.lcs(a.toCharArray(), a.length(), b.toCharArray(), b.length());
		System.out.println(result);
	}
}
