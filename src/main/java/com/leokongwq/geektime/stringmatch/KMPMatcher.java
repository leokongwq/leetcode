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
 * 当遇到坏字符的时候，我们就要把模式串往后滑动，在滑动的过程中，只要模式串和好前缀有上下重合，前面几个字符的比较，
 * 就相当于拿好前缀的后缀子串，跟模式串的前缀子串在比较。这个比较的过程能否更高效了呢？可以不用一个字符一个字符地比较了吗？
 *
 * 只需要拿好前缀本身，在它的后缀子串中，查找最长的那个可以跟好前缀的前缀子串匹配的。假设最长的可匹配的那部分前缀子串是{v}，长度是 k。
 * 我们把模式串一次性往后滑动 j-k 位，相当于，每次遇到坏字符的时候，我们就把 j 更新为 k，i 不变，然后继续比较。
 *
 * KMP算法通过预处理模式串的所有后缀子串可以匹配的前缀子串，这样可以可以在匹配过程中直接使用预处理的结果来加速匹配的过程。
 * 这其实本质上是一种缓存技术，不过缓存的是结算结果，而不是单纯的数据
 *
 * 类似 BM 算法中的 bc、suffix、prefix 数组，KMP 算法也可以提前构建一个数组，用来存储模式串中每个前缀（这些前缀都有可能是好前缀）
 * 的最长可匹配前缀子串的结尾字符下标。
 * 我们把这个数组定义为 next 数组，很多书中还给这个数组起了一个名字，叫失效函数（failure function）。
 * 1. 数组的下标是每个前缀结尾字符下标
 * 2. 数组的值是这个前缀的最长可以匹配前缀子串的结尾字符下标。
 * 模式串：a b a b a c d
 * 模式串前缀-好前缀的候选     好前缀结尾字符下标                最长可匹配前缀结尾下标         next值
 * a                        0                               -1                          next[0] = -1
 * a b                      1                               -1                          next[1] = -1
 * a b a                    2                               0                           next[2] = -1
 * a b a b                  3                               1                           next[3] = 1
 * a b a b a                4                               2                           next[4] = 2
 * a b a b a c              5                               -1                          next[5] = -1
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
			// 找到匹配模式串的了
			if (j == m) {
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
	 *  终于看明白了，感觉设置了很多干扰项。其实用迭代思想解释就能理解了。
	 * 这个算法本质是找相等的最长匹配前缀和最长匹配后缀。
	 * 有两种情况，
	 * （1）如果b[i-1]的最长前缀下一个字符与b[i]相等，则next[i]=next[i-1]+1.
	 * （2）如果不相等，则我们假设找到b[i-1]的最长前缀里有b[0,y]与后缀的子串b[z,i-1]相等，然后只要b[y+1]与b[i]相等，那么b[0,y+1]就是最长匹配前缀。
	 *  这个y可以不断的通过迭代缩小就可以找到
	 *  自己理解：如果不相等，说明b[i]的最长匹配前缀子串一定比b[i-1]的最长匹配前缀子串短，所以需要在b[i-1]的最长匹配前缀子串里面找b[i]的最长匹配前缀子串
	 */
	private static int[] getNexts(char[] b, int m) {
		int[] next = new int[m];
		next[0] = -1;
		//k 可以理解为表示i-1的最长匹配前缀子串的结束下标
		int k = -1;
		for (int i = 1; i < m; ++i) {
			//处理 b[next[i-1] + 1] != b[i]的情况
			while (k != -1 && b[k + 1] != b[i]) {
				//next[k] 的值一定是小于k的，每次迭代最少缩小一半
				k = next[k];
			}
			if (b[k + 1] == b[i]) {
				++k;
			}
			next[i] = k;
		}
		return next;
	}

	private static int kmp(String s, String t) {
		return kmp(s.toCharArray(), s.length(), t.toCharArray(), t.length());
	}

	public static void main(String[] args) {
//		System.out.println(kmp("abc", "a"));
//		System.out.println(kmp("abc", "b"));
//		System.out.println(kmp("abc", "c"));
//		System.out.println(kmp("abc", "d"));
		System.out.println(kmp("ababaeabac", "ababacd"));
	}

}
