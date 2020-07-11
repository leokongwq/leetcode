package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/11 - 16:29
 *
 * medium
 *
 * <p>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class Question309MaxProfit {

    /**
     * 动态规划
     * <p>
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        // 代表 dp[i - 2][0]
        int dp_pre_0 = 0;

        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;

            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);

            dp_pre_0 = temp;
        }
        return dp_i_0;
    }
}
