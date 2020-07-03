package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-06-29 22:45
 *
 * 对回溯算法求解0-1背包问题的优化
 **/
public class BagProblem {

	/**
	 * 结果放到maxW中
	 */
	private int maxW = Integer.MIN_VALUE;
	/**
	 * 物品的重量
	 */
	private int[] weight = new int[]{2, 2, 4, 6, 3};
	/**
	 * 物品个数
	 */
	private int n = 5;
	/**
	 * 背包承受的最大重量
	 */
	private int w = 9;
	/**
	 *  备忘录，默认值false
	 */
	private boolean[][] mem = new boolean[5][10];

	/**
	 * // 调用f(0, 0)
	 */
	public void f(int i, int cw) {
		// cw == w表示装满了，i == n 表示物品都考察完了
		if (cw == w || i == n) {
			if (cw > maxW) {
				maxW = cw;
			}
			return;
		}
		// 重复状态
		if (mem[i][cw]) {
			return;
		}
		// 记录(i, cw)这个状态
		mem[i][cw] = true;
		// 选择不装第i个物品
		f(i+1, cw);
		if (cw + weight[i] <= w) {
			// 选择装第i个物品
			f(i+1,cw + weight[i]);
		}
	}
}
