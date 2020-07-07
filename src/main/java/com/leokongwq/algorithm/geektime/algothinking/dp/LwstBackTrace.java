package com.leokongwq.algorithm.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:53
 *
 * 莱文斯坦距离，又称Levenshtein距离，是编辑距离的一种。指两个字串之间，由一个转成另一个所需的最少编辑操作次数。
 * 允许的编辑操作包括：
 * 将一个字符替换成另一个字符
 * 插入一个字符
 * 删除一个字符
 * 俄罗斯科学家弗拉基米尔·莱文斯坦在1965年提出这个概念。
 *
 * 回溯是一个递归处理的过程。如果 a[i]与 b[j]匹配，我们递归考察 a[i+1]和 b[j+1]。如果 a[i]与 b[j]不匹配，那我们有多种处理方式可选：
 * 1. 可以删除 a[i]，然后递归考察 a[i+1]和 b[j]；
 * 2. 可以删除 b[j]，然后递归考察 a[i]和 b[j+1]；
 * 3. 可以在 a[i]前面添加一个跟 b[j]相同的字符，然后递归考察 a[i]和 b[j+1];
 * 4. 可以在 b[j]前面添加一个跟 a[i]相同的字符，然后递归考察 a[i+1]和 b[j]；
 * 5. 可以将 a[i]替换成 b[j]，或者将 b[j]替换成 a[i]，然后递归考察 a[i+1]和 b[j+1]。
 **/
public class LwstBackTrace {

	private char[] a = "mitcmu".toCharArray();
	private char[] b = "mtacnu".toCharArray();
	private int n = 6;
	private int m = 6;

	// 存储结果
	private int minDist = Integer.MAX_VALUE;

	/**
	 * 求解莱文斯坦距离
	 * @param i 字符串a的比较下标
	 * @param j 字符串b的比较下标
	 * @param edist 当前编辑距离
	 */
	public void lwstBT(int i, int j, int edist) {
		if (i == n || j == m) {
			//说明m对应的串比较完了，补齐需要 n - i 次操作
			if (i < n) {
				edist += (n - i);
			}
			//说明n对应的串比较完了，补齐需要 m - j 次操作
			if (j < m) {
				edist += (m - j);
			}
			//更新结果
			if (edist < minDist) {
				minDist = edist;
			}
			return;
		}
		// 两个字符匹配，遍历距离不变，继续考察下一个字符
		if (a[i] == b[j]) {
			lwstBT(i + 1, j + 1, edist);
		} else {
			// 两个字符不匹配，选择所有可以执行操作（编辑距离加一），这里如果有返回值(假设是a, b, c)，则取 min(a, b, c)

			// 删除a[i]或者b[j]前添加一个字符
			lwstBT(i + 1, j, edist + 1);
			// 删除b[j]或者a[i]前添加一个字符
			lwstBT(i, j + 1, edist + 1);
			// 将a[i]和b[j]替换为相同字符
			lwstBT(i + 1, j + 1, edist + 1);
		}
	}

	public static void main(String[] args) {
		LwstBackTrace lwstBackTrace = new LwstBackTrace();
		lwstBackTrace.lwstBT(0, 0, 0);
		System.out.println(lwstBackTrace.minDist);
	}
}
