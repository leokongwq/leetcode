package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-06-30 09:03
 **/
public class Double11Advance {

	/**
	 * @param items 商品价格
	 * @param n     商品个数
	 * @param w     表示满减条件
	 */
	public static void double11advance(int[] items, int n, int w) {
		//超过3倍就没有薅羊毛的价值了
		boolean[][] states = new boolean[n][3 * w + 1];
		// 第一行的数据要特殊处理
		states[0][0] = true;
		if (items[0] <= 3 * w) {
			states[0][items[0]] = true;
		}
		// 动态规划
		for (int i = 1; i < n; ++i) {
			// 不购买第i个商品
			for (int j = 0; j <= 3 * w; ++j) {
				if (states[i - 1][j]) {
					states[i][j] = states[i - 1][j];
				}
			}
			for (int j = 0; j <= 3 * w - items[i]; ++j) {
				//购买第i个商品
				if (states[i - 1][j]) {
					states[i][j + items[i]] = true;
				}
			}
		}

		int j;
		for (j = w; j < 3 * w + 1; ++j) {
			// 输出结果大于等于w的最小值
			if (states[n - 1][j]) {
				break;
			}
		}
		// 没有可行解
		if (j == 3 * w + 1) {
			return;
		}
		//到此 states[n - 1][j] 就是最小支付的价格，可以享受满减优惠
		// i表示二维数组中的行，j表示列
		// 状态 (i, j) 只有可能从 (i-1, j) 或者 (i-1, j-value[i]) 两个状态推导过来。
		// 所以，我们就检查这两个状态是否是可达的，也就是 states[i-1][j]或者 states[i-1][j-value[i]]是否是 true。
		for (int i = n - 1; i >= 1; --i) {
			if (j - items[i] >= 0 && states[i - 1][j - items[i]]) {
				// 购买这个商品
				System.out.print(items[i] + " ");
				j = j - items[i];
			} // else 没有购买这个商品，j不变。
		}
		if (j != 0) {
			System.out.print(items[0]);
		}
	}
}
