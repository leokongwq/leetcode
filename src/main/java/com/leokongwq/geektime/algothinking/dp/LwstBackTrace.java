package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-07-01 09:53
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

	// 调用方式 lwstBT(0, 0, 0);
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
		// 两个字符匹配
		if (a[i] == b[j]) {
			lwstBT(i + 1, j + 1, edist);
		} else { // 两个字符不匹配
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
