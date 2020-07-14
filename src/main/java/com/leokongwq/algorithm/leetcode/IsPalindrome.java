package com.leokongwq.algorithm.leetcode;

/**
 * @author : jiexiu
 * @date : 2020-07-14 09:01
 *
 * 判断一个字符串是否是回文串
 **/
public class IsPalindrome {

	public boolean isPalindrome(String str) {
		if (str == null || str.length() <= 1) {
			return false;
		}
		int left = 0;
		int right = str.length() - 1;

		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	/**
	 * 寻找回文串
	 */
	public String palindrome(String s, int l, int r) {
		// 防止索引越界
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			// 向两边展开
			l--;
			r++;
		}
		// 返回以 s[l] 和 s[r] 为中心的最长回文串
		return s.substring(l + 1, r - l - 1);
	}
}
