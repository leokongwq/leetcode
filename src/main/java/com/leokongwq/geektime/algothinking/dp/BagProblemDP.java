package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-06-29 22:58
 * <p>
 * 我们把问题分解为多个阶段，每个阶段对应一个决策。
 * 我们记录每一个阶段可达的状态集合（去掉重复的），然后通过当前阶段的状态集合，来推导下一个阶段的状态集合，动态地往前推进。
 * 这也是动态规划这个名字的由来，你可以自己体会一下，是不是还挺形象的？
 * <p>
 * 时间复杂度 O(n * w) n 表示物品个数，w 表示背包可以承载的总重量。
 * <p>
 * 尽管动态规划的执行效率比较高，但是就刚刚的代码实现来说，我们需要额外申请一个 n 乘以 w+1 的二维数组，对空间的消耗比较多。
 * 所以，有时候，我们会说，动态规划是一种空间换时间的解决思路。
 **/
public class BagProblemDP {

	/**
	 * @param weight 物品重量
	 * @param n      物品个数
	 * @param w      背包可承载重量
	 */
	public int knapsack(int[] weight, int n, int w) {
		// 默认值false
		boolean[][] states = new boolean[n][w + 1];
		// 第一行的数据要特殊处理，可以利用哨兵优化
		states[0][0] = true;
		if (weight[0] <= w) {
			states[0][weight[0]] = true;
		}
		// 动态规划状态转移
		for (int i = 1; i < n; ++i) {
			// 不把第i个物品放入背包
			for (int j = 0; j <= w; ++j) {
				if (states[i - 1][j]) {
					//将state[i - 1][j] 赋值给state[i][j] 说明第i个物品或第i个阶段和i-1阶段的状态一直，也就是第i个物品不放进背包，如果放进去那么状态肯定变了。
					states[i][j] = states[i - 1][j];
				}
			}
			//把第i个物品放入背包；w - weight[i] 表示 上一个阶段物品放入背包后，剩余空间的最大值. 也可以理解为状态取值的上限
			//循环处理上个阶段所有可能的状态取值
			for (int j = 0; j <= w - weight[i]; ++j) {
				if (states[i - 1][j]) {
					states[i][j + weight[i]] = true;
				}
			}
		}
		// 输出结果（倒数，第一个满足的状态就是最优解）
		for (int i = w; i >= 0; --i) {
			//所有可能的解都在最后的状态记录中
			if (states[n - 1][i]) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * 通过一维数组解决空间复杂度问题
	 *
	 * @param items 物品重量
	 * @param n     物品个数
	 * @param w     背包可承载重量
	 */
	public int knapsack2(int[] items, int n, int w) {
		// 默认值false
		boolean[] states = new boolean[w + 1];
		// 第一行的数据要特殊处理，可以利用哨兵优化
		states[0] = true;
		if (items[0] <= w) {
			states[items[0]] = true;
		}
		// 动态规划
		for (int i = 1; i < n; ++i) {
			//把第i个物品放入背包（一定要从大到小来处理，否则会出现for循环重复计算问题）
			for (int j = w - items[i]; j >= 0; --j) {
				if (states[j]) {
					states[j + items[i]] = true;
				}
			}
		}
		// 输出结果
		for (int i = w; i >= 0; --i) {
			if (states[i]) {
				return i;
			}
		}
		return 0;
	}


	public static int knapsack3(int[] weight, int[] value, int n, int w) {
		int[][] states = new int[n][w + 1];
		// 初始化states
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < w + 1; ++j) {
				states[i][j] = -1;
			}
		}
		states[0][0] = 0;
		if (weight[0] <= w) {
			states[0][weight[0]] = value[0];
		}
		//动态规划，状态转移
		for (int i = 1; i < n; ++i) {
			// 不选择第i个物品
			for (int j = 0; j <= w; ++j) {
				if (states[i - 1][j] >= 0) {
					states[i][j] = states[i - 1][j];
				}
			}
			// 选择第i个物品
			for (int j = 0; j <= w - weight[i]; ++j) {
				if (states[i - 1][j] >= 0) {
					int v = states[i - 1][j] + value[i];
					//因为 可能存在多个重量相同，但是价值不同的状态，所以只保留价值最大的状态
					if (v > states[i][j + weight[i]]) {
						states[i][j + weight[i]] = v;
					}
				}
			}
		}
		// 找出最大值
		int maxvalue = -1;
		for (int j = 0; j <= w; ++j) {
			if (states[n - 1][j] > maxvalue) {
				maxvalue = states[n - 1][j];
			}
		}
		return maxvalue;
	}

	public static void main(String[] args) {
		BagProblemDP bagProblemDP = new BagProblemDP();
		System.out.println(bagProblemDP.knapsack(new int[]{2, 2, 4, 6, 3}, 5, 9));
		System.out.println(bagProblemDP.knapsack2(new int[]{2, 2, 4, 6, 3}, 5, 9));
	}
}
