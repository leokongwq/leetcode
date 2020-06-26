package com.leokongwq.geektime.stringmatch;

/**
 * @author : jiexiu
 * @date : 2020-06-24 17:54
 *
 * https://www.zhihu.com/question/21923021
 *
 * KMP 算法是根据三位作者（D.E.Knuth，J.H.Morris 和 V.R.Pratt）的名字来命名的，算法的全称是 Knuth Morris Pratt 算法，简称为 KMP 算法。
 *
 * KMP 算法的时间复杂度就是 O(m+n)
 * KMP 算法的空间复杂度就是 O(m)
 *
 * 在模式串和主串匹配的过程中，把不能匹配的那个字符仍然叫作坏字符，把已经匹配的那段字符串叫作好前缀。
 *
 * 当遇到坏字符的时候，我们就要把模式串往后滑动，在滑动的过程中，只要模式串和好前缀有上下重合，前面几个字符的比较，就相当于拿好前缀的后缀子串，
 * 跟模式串的前缀子串在比较。这个比较的过程能否更高效了呢？可以不用一个字符一个字符地比较了吗？
 **/
public class KMPMatcher {

	// a, b分别是主串和模式串；n, m分别是主串和模式串的长度。
	public static int kmp(char[] a, int n, char[] b, int m) {
		//next数组中，每个下标表示以该下标结尾的好前缀，下标位置保持的值是该好前缀的所有后缀在从模式串位置0开始的最长可匹配前缀的结束下标。
		int[] next = getNexts(b, m);

		int j = 0;
		for (int i = 0; i < n; ++i) {
			// 一直找到a[i]和b[j] (从最长的前缀串开始尝试)
			while (j > 0 && a[i] != b[j]) {
				j = next[j - 1] + 1;
			}
			if (a[i] == b[j]) {
				++j;
			}
			if (j == m) { // 找到匹配模式串的了
				return i - m + 1;
			}
		}
		return -1;
	}

	/**
	 * @param b b表示模式串
	 * @param m m表示模式串的长度
	 * 数组的下标表示模式串的前缀子串的结束位置
	 * 数组元素的值表示模式串的后缀子串在模式串中匹配的结束位置
	 *
	 */
	private static int[] getNexts(char[] b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		int k = -1;
		for (int i = 1; i < m; ++i) {
			while (k != -1 && b[k + 1] != b[i]) {
				k = next[k];
			}
			if (b[k + 1] == b[i]) {
				++k;
			}
			next[i] = k;
		}
		return next;
	}

	private static int kmp(String abc, String a) {
		return kmp(abc.toCharArray(), abc.length(), a.toCharArray(), a.length());
	}

	public static void main(String[] args) {
//		System.out.println(kmp("abc", "a"));
//		System.out.println(kmp("abc", "b"));
//		System.out.println(kmp("abc", "c"));
//		System.out.println(kmp("abc", "d"));
		System.out.println(kmp("ababaeabac", "ababacd"));
	}

}
