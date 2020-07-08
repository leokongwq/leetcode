package com.leokongwq.algorithm.geektime.stringmatch;

/**
 * @author : jiexiu
 * @date : 2020-06-22 19:28
 * <p>
 * RK 算法的全称叫 Rabin-Karp 算法，是由它的两位发明者 Rabin 和 Karp 的名字来命名的
 * <p>
 * RK 算法的思路是这样的：
 * <p>
 * 我们通过哈希算法对主串中的 n-m+1 个子串分别求哈希值，然后逐个与模式串的哈希值比较大小。
 * 如果某个子串的哈希值与模式串相等，那就说明对应的子串和模式串匹配了（这里先不考虑哈希冲突的问题，后面我们会讲到）。
 * 因为哈希值是一个数字，数字之间比较是否相等是非常快速的，所以模式串和子串比较的效率就提高了。
 * <p>
 * 我们把 a～z 这 26 个字符映射到 0～25 这 26 个数字，a 就表示 0，b 就表示 1，以此类推，z 表示 25。
 * 我假设字符串中只包含 a～z 这 26 个小写字符，我们用二十六进制来表示一个字符串，对应的哈希值就是二十六进制数转化成十进制的结果。
 * cba = 2 * 26 * 26 + 1 * 26 + 0 * 1
 * <p>
 * 这种哈希算法有一个特点，在主串中，相邻两个子串的哈希值的计算公式有一定关系。
 * 相邻两个子串 s[i-1]和 s[i]（i 表示子串在主串中的起始位置，子串的长度都为 m），对应的哈希值计算公式有交集，
 * 也就是说，我们可以使用 s[i-1]的哈希值很快的计算出 s[i]的哈希值
 *
 * RK 算法整体的时间复杂度就是 O(n)， 如果冲突很多，极端情况下：时间复杂度：O(n*m)
 *
 **/
public class RKMatcher {

	/**
	 * 假设字符串 a 和 b 都是由 26个小写字母组成
	 */
	public static int rK(String a, String b) {
		int m = a.length();
		int n = b.length();
		//m - n + 1 个子串的hash值
		int[] hash = new int[m - n + 1];

		char[] a1 = a.toCharArray();
		char[] b1 = b.toCharArray();

		// 将26的次方存储在一个表里，取的时候直接用,虽然溢出，但没啥问题
		int s = 1;
		int[] table = new int[26];
		for (int j = 0; j < 26; j++) {
			table[j] = s;
			s *= 26;
		}
		//计算模式串的hash值
		int patternHash = 0;
		for (int j = 0; j < n; j++) {
			patternHash += (b1[j] - 'a') * table[n - 1 - j];
		}

		// 计算 m - n + 1个子串的hash值
		// a1[i + j] - 'a' 得到该字母对应的数字[0, 25]
		// n次方从高往低计算， 这里并没有泳道 h[i] 和 h[i-1]间的关系
		// h[i] = (h[i-1] - Math.pow(26, m-1) * (s[i-1] - 'a')) * 26 + Math.pow(26, 0) * (s[i + m - 1] - 'a')
		for (int i = 0; i <= m - n; i++) {
			s = 0;
			if (i == 0) {
				for (int j = 0; j < n; j++) {
					s += (a1[i + j] - 'a') * table[n - 1 - j];
				}
			} else {
				//快速计算h[i]子串的hash值
				s = (hash[i-1] - ((a1[i-1] - 'a') * table[n-1])) * 26 + table[0] * (a1[i + n -1] - 'a');
			}
			hash[i] = s;
			//找到第一个即可返回，不用计算后面的子串hash值，提高效率
			if (patternHash == hash[i]) {
				return i;
			}
		}
//
//		//模式串hash值和子串进行比较
//		for (int j = 0; j < m - n + 1; j++) {
//			if (hash[j] == s) {
//				return j;
//			}
//		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(rK("abc", "a"));
		System.out.println(rK("abc", "b"));
		System.out.println(rK("abc", "c"));
		System.out.println(rK("abc", "d"));
	}
}
