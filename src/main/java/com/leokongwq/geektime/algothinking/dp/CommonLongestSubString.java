package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:29
 * 最长公共子串(不连续)
 **/
public class CommonLongestSubString {

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
		if (a[i] == b[j]) {
			max_lcs(a, i + 1, b, j + 1, curLen + 1);
		} else {
			// 删除 a[i]，或者在 b[j]前面加上一个字符 a[i]，然后继续考察 a[i+1]和 b[j]；
			max_lcs(a, i + 1, b, j, curLen);
			// 删除 b[j]，或者在 a[i]前面加上一个字符 b[j]，然后继续考察 a[i]和 b[j+1]。
			max_lcs(a, i, b, j + 1, curLen);
		}
	}

	private int max(int x, int y, int z) {
		int maxv = Integer.MIN_VALUE;
		if (x > maxv) maxv = x;
		if (y > maxv) maxv = y;
		if (z > maxv) maxv = z;
		return maxv;
	}

	public static void main(String[] args) {
		CommonLongestSubString css = new CommonLongestSubString();
//		String a = "abcd";
//		String a = "aefd";
//		String a = "befd";
//		String a = "befdc";
		String a = "bbdc";
		String b = "bc";
		css.max_lcs(a, b);
		System.out.println(css.maxLen);
	}
}
