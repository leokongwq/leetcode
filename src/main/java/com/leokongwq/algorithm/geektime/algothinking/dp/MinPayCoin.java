package com.leokongwq.algorithm.geektime.algothinking.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiexiu
 * @date : 2020-06-30 22:43
 *
 * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。如果我们要支付 w 元，求最少需要多少个硬币。
 * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
 *
 * f(9)=1+min(f(8),f(6),f(4))
 **/
public class MinPayCoin {

	private static Map<Integer, Integer> memory = new HashMap<>();

	/**
	 * 状态转移方程 + 递归
	 */
	private static int minCoin(int money) {
		if (money == 1 || money == 3 || money == 5) {
			return 1;
		}
		if (money <= 0) {
			return Integer.MAX_VALUE;
		}
		if (memory.containsKey(money)) {
			return memory.get(money);
		}
		int minCoin = 1 + Math.min(Math.min(minCoin(money - 1), minCoin(money - 3)), minCoin(money - 5));
		memory.put(money, minCoin);
		return minCoin;
	}

	/**
	 * 状态表
	 */
	private static int minCoins(int money) {
		if (money == 1 || money == 3 || money == 5) {
			return 1;
		}
		//最小币值是1，那么找零money，最多可以划分为money个阶段
		boolean [][] state = new boolean[money][money + 1];
		state[0][1] = true;
		state[0][3] = true;
		state[0][5] = true;

		for (int i = 1; i < money; i++) {
			for (int j = 1; j <= money; j++) {
				if (state[i - 1][j]) {
					if (j + 1 <= money) {
						state[i][j + 1] = true;
					}
					if (j + 3 <= money) {
						state[i][j + 3] = true;
					}
					if (j + 5 <= money) {
						state[i][j + 5] = true;
					}
					//如果第i阶段已经满足了，说明用到币个数越少
					if (state[i][money]) {
						return i + 1;
					}
				}
			}
		}
		return money;
	}

	private static int minCoinsDpV2(int money) {
		//状态数组， 数组下标表示找零的金额，值表示需要数量最少的硬币
		int[] states = new int[money + 1];
		states[1] = 1;
		states[2] = 2;
		states[3] = 1;
		states[4] = 2;
		states[5] = 1;

		//填充状态表
		for (int i = 6; i <= money; i++) {
			states[i] = 1 + Math.min(Math.min(states[i - 1], states[i - 3]), states[i - 5]);
		}

		return states[money];
	}

	public static void main(String[] args) {
		System.out.println(minCoin(9));
		System.out.println(minCoin(5));
		System.out.println(minCoin(3));
		System.out.println(minCoin(1));
		System.out.println(minCoin(10));

		System.out.println("**********************");
		System.out.println(minCoins(9));
		System.out.println(minCoins(5));
		System.out.println(minCoins(3));
		System.out.println(minCoins(1));
		System.out.println(minCoins(10));
	}
}
