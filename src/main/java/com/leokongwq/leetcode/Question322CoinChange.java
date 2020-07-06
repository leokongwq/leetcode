package com.leokongwq.leetcode;

import java.util.Arrays;

/**
 * @author : jiexiu
 * @date : 2020-07-01 22:23
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 *  
 * <p>
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 **/
public class Question322CoinChange {

	public int coinChange(int[] coins, int amount) {
		if (coins == null || coins.length == 0) {
			return -1;
		}
		if (amount <= 0) {
			return 0;
		}
		Arrays.sort(coins);
		int minCoin = coins[0];
		if (amount < minCoin) {
			return -1;
		}
		int step = amount % minCoin == 0 ? amount / minCoin : amount / minCoin + 1;
		boolean[][] state = new boolean[step][amount + 1];
		for (int coin : coins) {
			if (coin <= amount) {
				state[0][coin] = true;
			}
		}
		//只需一步
		if (step == 1) {
			if (state[0][amount]) {
				return 1;
			}
		}
		for (int i = 1; i < step; i++) {
			for (int j = minCoin; j <= amount; j++) {
				if (state[i - 1][j]) {
					for (int coin : coins) {
						if (coin > amount) {
							continue;
						}
						//溢出
						if (j + coin < 0) {
							continue;
						}
						if (j + coin <= amount) {
							state[i][j + coin] = true;
						}
					}
				}
			}
		}
		for (int i = 0; i < state.length; i++) {
			if (state[i][amount]) {
				return i + 1;
			}
		}
		return -1;
	}

	int minNum = Integer.MAX_VALUE;

	/**
	 * 使用回溯法获取给定金额最小的硬币数量，调用时num为0
	 *
	 * @param coinVal 硬币值数组
	 * @param total   指定的金额
	 * @param num     每个解法所得到的硬币数量
	 */
	public void getLeastCoinNumByBackTracking(int[] coinVal, int total, int num) {
		if (total == 0) {
			if (num < minNum) {
				minNum = num;
			}
			return;
		}
		for (int i = 0; i < coinVal.length; i++) {
			if (total - coinVal[i] >= 0) {
				getLeastCoinNumByBackTracking(coinVal, total - coinVal[i], num + 1);
			}
		}
	}

	/**
	 * 使用动态规划法获取给定金额下最小的硬币数量
	 *
	 * @param coinVal 硬币值数组
	 * @param total   给定金额
	 * @return 给定金额下最小的硬币数量
	 */
	public int getLeastCoinNumByDP(int[] coinVal, int total) {
		// coinNum存放的是每个对应金额下最少硬币的最优解
		int coinNum[] = new int[total + 1];
		coinNum[0] = 0;
		//初始化coinNum数组，硬币值数组对应的值的硬币数量都为1
		for (int i = 0; i < coinVal.length; i++) {
			coinNum[coinVal[i]] = 1;
		}

		for (int i = 1; i <= total; i++) {
			if (coinNum[i] == 0) {
				// 获取每个i对应的最小硬币数值
				int minTemp = Integer.MAX_VALUE;
				for (int j = 0; j < coinVal.length; j++) {
					if (i - coinVal[j] > 0) {
						int v1 = coinNum[i - coinVal[j]] + 1;
						if (v1 < minTemp) {
							minTemp = v1;
						}
					}
				}
				coinNum[i] = minTemp;
			}
		}
		return coinNum[total];
	}

	/**
	 * 自上而下
	 */
	public int coinChangeV2(int[] coins, int amount) {
		if (amount < 1) {
			return 0;
		}
		return coinChangeV21(coins, amount, new int[amount]);
	}

	private int coinChangeV21(int[] coins, int rem, int[] count) {
		if (rem < 0) return -1;
		if (rem == 0) return 0;
		if (count[rem - 1] != 0) return count[rem - 1];
		int min = Integer.MAX_VALUE;
		for (int coin : coins) {
			int res = coinChangeV21(coins, rem - coin, count);
			if (res >= 0 && res < min)
				min = 1 + res;
		}
		count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
		return count[rem - 1];
	}

	/**
	 * 自下而上
	 */
	public int coinChangedpV3(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		//求每一个金额的最小硬币数
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}


	public static void main(String[] args) {
		Question322CoinChange coinChange = new Question322CoinChange();
//		System.out.println(coinChange.coinChange(new int[]{1, 2, 5}, 11));
//		System.out.println(coinChange.coinChange(new int[]{2}, 3));
//		System.out.println(coinChange.coinChange(new int[]{1}, 0));
//		System.out.println(coinChange.coinChange(new int[]{1}, 1));
		System.out.println(coinChange.coinChange(new int[]{1, 2}, 2));
//		System.out.println(coinChange.coinChange(new int[]{3}, 2));
//		System.out.println(coinChange.coinChange(new int[]{1, 2147483647}, 2));
	}
}
