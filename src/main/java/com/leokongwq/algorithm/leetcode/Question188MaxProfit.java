package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/11 - 17:11
 *
 * hard
 *
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * <p>
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *      随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 */
public class Question188MaxProfit {

    /**
     * 动态规划
     *
     * dp[i][j][0]表示第i天交易了j次时不持有股票, dp[i][j][1]表示第i天交易了j次时持有股票
     */
    public int maxProfit(int max_k, int[] prices) {
        int n = prices.length;
        //买卖需要2天， 最多能交易 n / 2次
        if (max_k > n / 2) {
            return maxProfit_k_inf(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    // 处理 base case
                    dp[0][k][0] = dp[i][0][0] = 0;
                    dp[0][k][1] = dp[i][0][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        Question188MaxProfit maxProfit = new Question188MaxProfit();
        System.out.println(maxProfit.maxProfit(2, new int[]{2,4,1}));
        System.out.println(maxProfit.maxProfit(2, new int[]{3,2,6,5,0,3}));

        System.out.println(maxProfit.maxProfit(1, new int[]{1, 2}));
    }
}
