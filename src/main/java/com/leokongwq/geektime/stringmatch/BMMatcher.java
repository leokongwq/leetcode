package com.leokongwq.geektime.stringmatch;

/**
 * @author : jiexiu
 * @date : 2020-06-24 17:53
 * <p>
 * BM（Boyer-Moore）算法。它是一种非常高效的字符串匹配算法，有实验统计，它的性能是著名的 KMP 算法的 3 到 4 倍
 * BM 和 KMP 算法的核心思想是：当遇到不匹配的字符时，一次移动n位。 难点是如何找到这个合适的N
 * <p>
 * BM 算法包含两部分，分别是坏字符规则（bad character rule）和 好后缀规则（good suffix shift）
 * <p>
 * 1. BM 算法的匹配顺序比较特别，它是按照模式串下标从大到小的顺序，倒着和主串匹配的
 * 2. 把遇到的第一个不匹配的字符称为：坏字符 e.g. c
 * 3. 拿坏字符 c 在模式串中查找, 如果模式串中没有坏字符，那么直接将子串滑动到坏字符后进行匹配
 * 4. 如果模式串中存在坏字符c，那么将主串和子串进行对齐，然后重新进行匹配
 * <p>
 * 当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作 si。如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作 xi。
 * 如果不存在，我们把 xi 记作 -1。那模式串往后移动的位数就等于 si-xi。（注意，我这里说的下标，都是字符在模式串的下标）。
 * <p>
 * <p>
 * 利用坏字符规则，BM 算法在最好情况下的时间复杂度非常低，是 O(n/m)
 * <p>
 * 不过，单纯使用坏字符规则还是不够的。因为根据 si-xi 计算出来的移动位数，有可能是负数，比如主串是 aaaaaaaaaaaaaaaa，模式串是 baaa。
 * 不但不会向后滑动模式串，还有可能倒退。所以，BM 算法还需要用到“好后缀规则”。
 * <p>
 * 好后缀规则
 * <p>
 * 好后缀规则指的是：当遇到不匹配的字符时，由于该字符后面的部分都是匹配的，此时如何计算滑动的位数。
 * 1. 把坏字符后面匹配的部分记为：好后缀 {u}
 * 2. 拿它在模式串中查找，如果找到了另一个跟{u}相匹配的子串{u*}，那我们就将模式串滑动到子串{u*}与主串中{u}对齐的位置
 * 3. 如果在模式串中找不到另一个等于{u}的子串(从模式串开头开始查找，不能包括好后缀)，我们就直接将模式串，滑动到主串中{u}的后面，
 * 因为之前的任何一次往后滑动，都没有匹配主串中{u}的情况。注意：可能滑动过多，导致匹配失败。
 * 4. 如果好后缀在模式串中不存在可匹配的子串，那在我们一步一步往后滑动模式串的过程中，只要主串中的{u}与模式串有重合，那肯定就无法完全匹配。
 * 但是当模式串滑动到前缀与主串中{u}的后缀有部分重合的时候，并且重合的部分相等的时候，就有可能会存在完全匹配的情况。
 * 所以，针对这种情况，我们不仅要看好后缀在模式串中，是否有另一个匹配的子串，我们还要考察好后缀的后缀子串，是否存在跟模式串的前缀子串匹配的。
 * <p>
 * <p>
 * 当模式串和主串中的某个字符不匹配的时候，如何选择用好后缀规则还是坏字符规则，来计算模式串往后滑动的位数？
 * <p>
 * 答案：分别计算好后缀和坏字符往后滑动的位数，然后取两个数中最大的，作为模式串往后滑动的位数。这种处理方法还可以避免我们前面提到的，
 * 根据坏字符规则，计算得到的往后滑动的位数，有可能是负数的情况。
 **/
public class BMMatcher {

	/**
	 *
	 */
	private static final int SIZE = 256;

	/**
	 * 将模式串中每个字符的下标记录到一个hash作用的数组中
	 *
	 * @param b  模式串的字符数组
	 * @param m  模式串的大小
	 * @param bc 模式串中每个字符在字符数组中的位置
	 */
	private static void generateBC(char[] b, int m, int[] bc) {
		for (int i = 0; i < SIZE; i++) {
			bc[i] = -1;
		}
		//如果有重复字符，则最后一个字符的下标覆盖第一次出现的字符的下标值，但是我们是从后往前查找，没关系。
		for (int i = 0; i < m; i++) {
			// 计算b[i]的ASCII值
			int ascii = (int) b[i];
			bc[ascii] = i;
		}
	}

	/**
	 * bm 算法实现
	 *
	 * @param a 表示主串
	 * @param n 主串的长度
	 * @param b 模式串
	 * @param m 模式串的长度
	 * @return 模式串在主串中的下标
	 */
	public static int bm(char[] a, int n, char[] b, int m) {
		// 记录模式串中每个字符最后出现的位置 (bc = bad character)
		int[] bc = new int[SIZE];
		// 构建坏字符哈希表
		generateBC(b, m, bc);

		int[] suffix = new int[m];
		boolean[] prefix = new boolean[m];
		generateGS(b, m, suffix, prefix);

		// i表示主串与模式串对齐的第一个字符
		int i = 0;
		//从后往前进行模式串的匹配
		while (i <= n - m) {
			// j表示主串与模式串匹配的第一个字符
			int j = 0;
			for (j = m - 1; j >= 0; --j) {
				// 模式串从后往前匹配
				if (a[i + j] != b[j]) {
					break;
				}
			}
			//匹配了
			if (j < 0) {
				return i;
			}
			// x 表示坏字符规则下移动的量
			int x = j - bc[(int) a[i + j]];
			// y 表示好后缀规则下移动的量
			int y = 0;
			// 如果有好后缀的话 （j = m - 1 表示第一个字符就不匹配，此时没有好后缀）
			if (j < m - 1) {
				y = moveByGS(j, m, suffix, prefix);
			}
			// i = i + (j - bc[(int)a[i+j]]); 坏字符移动量计算， 取最大值来避免过度滑动丢失匹配
			i = i + Math.max(x, y);
		}

		return -1;
	}

	private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
		// 好后缀长度
		int k = m - 1 - j;
		// 模式串中存在好后缀子串
		if (suffix[k] != -1) {
			return j - suffix[k] + 1;
		}
		//好后缀的后缀子串是模式串的前缀子串
		// r表示好后缀的后缀子串长度， j+1表示好后缀的起始下标，j+2表示好后缀的最大的子串的起始下标
		// m-r表示从好后缀的长度最大的后缀子串开始匹配
		// 返回值 r 表示模式串首字符需要和主串的位置r进行对其
		for (int r = j + 2; r <= m - 1; ++r) {
			if (prefix[m - r]) {
				return r;
			}
		}
		return m;
	}

	/**
	 * 预处理生成好后缀 good suffix
	 * 计算结束下标为i的子串 和 模式串 的公共后缀子串. 这个实现还是很巧妙的。
	 *
	 * @param b      b表示模式串
	 * @param m      m表示长度
	 * @param suffix 表示模式串的后缀子串在模式串中匹配的起始下标
	 * @param prefix 表示模式串的后缀子串在模式串中是否有匹配的前缀串
	 */
	private static void generateGS(char[] b, int m, int[] suffix, boolean[] prefix) {
		// 初始化
		for (int i = 0; i < m; ++i) {
			suffix[i] = -1;
			prefix[i] = false;
		}
		// b[0, i]  表示开始下标固定为0，结束下标为i为的子串
		for (int i = 0; i <= m - 2; ++i) {
			int j = i;
			// k表示公共后缀子串长度
			int k = 0;
			// 与b[0, m-2]求公共后缀子串
			while (j >= 0 && b[j] == b[m - 1 - k]) {
				++k;
				//j+1表示公共后缀子串在b[0, i]中的起始下标
				suffix[k] = j;
				--j;
			}
			//如果公共后缀子串也是模式串的前缀子串
			if (j == -1) {
				prefix[k] = true;
			}
		}
	}

	private static int bm(String abc, String b) {
		return bm(abc.toCharArray(), abc.length(), b.toCharArray(), b.length());
	}

	public static void main(String[] args) {
		System.out.println(bm("abc", "a"));
		System.out.println(bm("abc", "b"));
		System.out.println(bm("abc", "c"));
		System.out.println(bm("abc", "d"));
	}

}
