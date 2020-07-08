package com.leokongwq.algorithm.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:29
 * 最长公共子串(不连续) 表示编辑距离的一种，只允许增加、删除字符
 *
 * 回溯
 **/
public class LongestCommonSubString {

	/**
	 * 暴力解法 - 倒序遍历
	 * 可以通过备忘录来避免重复计算
	 */
	int max_lcsV1(char[] a, int i, char[] b, int j) {
		if (i == -1 || j == -1) {
			return 0;
		}
		if (a[i] == b[j]) {
			return max_lcsV1(a, i - 1, b, j - 1) + 1;
		} else {
			return Math.max(max_lcsV1(a, i - 1, b, j), max_lcsV1(a, i, b, j - 1));
		}
	}

    /**
     * 暴力解法 - 正序比较
     */
	int max_lcsV2(String a, String b) {
		if (a.length() < b.length()) {
			return max_lcs(a.toCharArray(), 0, b.toCharArray(), 0);
		} else {
			return max_lcs(b.toCharArray(), 0, a.toCharArray(), 0);
		}
	}

	private int max_lcs(char[] a, int i, char[] b, int j) {
		if (i == a.length || j == b.length) {
			return 0;
		}
		//
		if (a[i] == b[j]) {
			return max_lcs(a, i + 1, b, j + 1) + 1;
		} else {
			// 删除 a[i]，或者在 b[j]前面加上一个字符 a[i]，然后继续考察 a[i+1]和 b[j]；
			int max1 = max_lcs(a, i + 1, b, j);
			// 删除 b[j]，或者在 a[i]前面加上一个字符 b[j]，然后继续考察 a[i]和 b[j+1]。
			int max2 = max_lcs(a, i, b, j + 1);
			return Math.max(max1, max2);
		}
	}

	public static void main(String[] args) {
		LongestCommonSubString css = new LongestCommonSubString();
//		String a = "abcd";
//		String a = "aefd";
//		String a = "befd";
//		String a = "befdc";
		String a = "bbdc";
		String b = "bc";

		int lcs = css.max_lcsV1(a.toCharArray(), a.length() - 1, b.toCharArray(), b.length() - 1);
        System.out.println(lcs);

		System.out.println(css.max_lcsV2(a, b));
	}
}
