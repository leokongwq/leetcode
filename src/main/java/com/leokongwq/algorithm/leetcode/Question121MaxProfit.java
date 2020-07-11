package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/11 - 10:47
 *
 * easy
 *
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Question121MaxProfit {

    /**
     * 暴力求解
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int maxProfit = 0;

        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }

    /**
     * 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格 - 前i-1天中的最小价格}
     */
    public int maxProfitV1(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int min = prices[0];
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return profit;
    }

    /**
     * dp方式求解
     * dp[i][j] 表示第i天，持有股票或不持有股票是的最大利润。j = 0 表示不持有，j = 1表示持有
     *
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     *
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max（dp[i-1][0], dp[i-1][1] + prices[i])）
     * dp[i][1] = max(（dp[i-1][1], -prices[i])）
     *
     */
    public int maxProfitV2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                //第一天买入股票，利润为负数
                dp[i][1] = -prices[i];
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * dp 方式求解，压缩状态数组，节省空间
     */
    public int maxProfitV3(int[] prices) {
        int n = prices.length;
        // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            // dp[i][1] = max(dp[i-1][1], -prices[i])
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        Question121MaxProfit solution = new Question121MaxProfit();

//        System.out.println(solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
//
//        System.out.println(solution.maxProfitV1(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(solution.maxProfitV1(new int[]{7, 6, 4, 3, 1}));
//
//        System.out.println(solution.maxProfitV2(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(solution.maxProfitV2(new int[]{7, 6, 4, 3, 1}));

        System.out.println(solution.maxProfit(new int[]{1, 3}));
        System.out.println(solution.maxProfitV2(new int[]{1, 3}));
    }
}
