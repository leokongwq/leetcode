package com.leokongwq.algorithm.geektime.stringmatch;

/**
 * @author : jiexiu
 * @date : 2020-06-22 18:45
 *
 * BF 算法中的 BF 是 Brute Force 的缩写，中文叫作暴力匹配算法，也叫朴素匹配算法。
 * 从名字可以看出，这种算法的字符串匹配方式很“暴力”，当然也就会比较简单、好懂，但相应的性能也不高。
 *
 * BF 算法的思想可以用一句话来概括，那就是，我们在主串中，检查起始位置分别是 0、1、2…n-m 且长度为 m 的 n-m+1 个子串，看有没有跟模式串匹配的。
 *
 * BF 算法的时间复杂度很高，是 O(n*m)，但在实际的开发中，它却是一个比较常用的字符串匹配算法。为什么这么说呢？原因有两点。
 * 第一，实际的软件开发中，大部分情况下，模式串和主串的长度都不会太长。而且每次模式串与主串中的子串匹配的时候，当中途遇到不能匹配的字符的时候，就可以就停止了，
 * 不需要把 m 个字符都比对一下。所以，尽管理论上的最坏情况时间复杂度是 O(n*m)，但是，统计意义上，大部分情况下，算法执行效率要比这个高很多。
 *
 * 第二，朴素字符串匹配算法思想简单，代码实现也非常简单。简单意味着不容易出错，如果有 bug 也容易暴露和修复。在工程中，在满足性能要求的前提下，简单是首选。
 * 这也是我们常说的KISS（Keep it Simple and Stupid）设计原则。
 **/
public class BFMatcher {

	/**
	 *  判断 pattern 是否在 target 里面
	 */
	private static int match(String target, String pattern) {
		int n = target.length();
		int m = pattern.length();
		if (m > n) {
			return -1;
		}

		for (int i = 0; i <= n - m; i++) {
			int k = 0;
			for (int j = 0; j < m; j++) {
				if (target.charAt(i + j) == pattern.charAt(j)) {
					k++;
				} else {
					break;
				}
			}
			if (k == m) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(match("abc", "a"));
		System.out.println(match("abc", "b"));
		System.out.println(match("abc", "c"));
		System.out.println(match("abc", "d"));
	}
}
