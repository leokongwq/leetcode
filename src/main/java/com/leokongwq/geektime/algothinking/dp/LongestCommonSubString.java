package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:29
 * 最长公共子串(不连续) 表示编辑距离的一种，只允许增加、删除字符
 *
 * 回溯
 **/
public class LongestCommonSubString {

	private int maxLen = 0;

	public void max_lcs(String a, String b) {
		if (a.length() < b.length()) {
			max_lcs(a.toCharArray(), 0, b.toCharArray(), 0, 0);
		} else {
			max_lcs(b.toCharArray(), 0, a.toCharArray(), 0, 0);
		}
	}

	private void max_lcs(char[] a, int i, char[] b, int j, int curLen) {
		if (i == a.length || j == b.length) {
			if (curLen > maxLen) {
				maxLen = curLen;
			}
			return;
		}
		//
		if (a[i] == b[j]) {
			max_lcs(a, i + 1, b, j + 1, curLen + 1);
		} else {
			// 删除 a[i]，或者在 b[j]前面加上一个字符 a[i]，然后继续考察 a[i+1]和 b[j]；
			max_lcs(a, i + 1, b, j, curLen);
			// 删除 b[j]，或者在 a[i]前面加上一个字符 b[j]，然后继续考察 a[i]和 b[j+1]。
			max_lcs(a, i, b, j + 1, curLen);
		}
	}

	/**
	 * 暴力解法
	 * 可以通过备忘录来避免重复计算
	 */
	private static int max_lcs(char[] a, int i, char[] b, int j) {
		if (i == -1 || j == -1) {
			return 0;
		}
		if (a[i] == b[j]) {
			return max_lcs(a, i - 1, b, j - 1) + 1;
		} else {
			return Math.max(max_lcs(a, i - 1, b, j), max_lcs(a, i, b, j - 1));
		}
	}

	private int max(int x, int y, int z) {
		int maxv = Integer.MIN_VALUE;
		if (x > maxv) {
			maxv = x;
		}
		if (y > maxv) {
			maxv = y;
		}
		if (z > maxv) {
			maxv = z;
		}
		return maxv;
	}

	public static void main(String[] args) {
		LongestCommonSubString css = new LongestCommonSubString();
//		String a = "abcd";
//		String a = "aefd";
//		String a = "befd";
//		String a = "befdc";
		String a = "bbdc";
		String b = "bc";
		css.max_lcs(a, b);
		System.out.println(css.maxLen);
		System.out.println(max_lcs(a.toCharArray(), a.length() - 1, b.toCharArray(), b.length() - 1));
	}
}
