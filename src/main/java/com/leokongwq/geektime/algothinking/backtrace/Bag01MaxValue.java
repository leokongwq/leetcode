package com.leokongwq.geektime.algothinking.backtrace;

/**
 * @author : jiexiu
 * @program : leetcode
 * @date : 2020-06-29 23:51
 **/
public class Bag01MaxValue {

	/**
	 * 结果放到maxV中
	 */
	private int maxV = Integer.MIN_VALUE;

	private int maxW = Integer.MIN_VALUE;

	/**
	 * 物品的重量
	 */
	private int[] weight = {2, 2, 4, 6, 3};
	/**
	 * 物品的价值
	 */
	private int[] value = {3, 4, 8, 9, 6};
	/**
	 * 物品个数
	 */
	private int n = 5;
	/**
	 * 背包承受的最大重量
	 */
	private int w = 9;

	public void f(int i, int cw, int cv) {
		// cw == w 表示装满了，i == n 表示物品都考察完了
		if (cw == w || i == n) {
			if (cv > maxV) {
				maxV = cv;
			}
			if (cw > maxW) {
				maxW = cw;
			}
			return;
		}
		// 选择不装第i个物品
		f(i + 1, cw, cv);

		if (cw + weight[i] <= w) {
			// 选择装第i个物品
			f(i + 1, cw + weight[i], cv + value[i]);
		}
	}

	public static void main(String[] args) {
		Bag01MaxValue bag01 = new Bag01MaxValue();
		bag01.f(0, 0, 0);
		System.out.println(bag01.maxV);
		System.out.println(bag01.maxW);
	}
}
