package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/11 - 13:55
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Question123MaxProfit {

    /**
     * 动态规划
     * 最多完成2笔交易， k = 2
     * <p>
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])卖出
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])买入
     * <p>
     * 如果 buy，就要从利润中减去 prices[i]，如果 sell，就要给利润增加 prices[i]。
     * <p>
     * 今天的最大利润就是这两种可能选择中较大的那个。而且注意 k 的限制，我们在选择 buy 的时候，把 k 减小了 1，很好理解吧，
     * 当然你也可以在 sell 的时候减 1，一样的。
     *
     *  # dp[i][j][0]表示第i天交易了j次时不持有股票, dp[i][j][1]表示第i天交易了j次时持有股票
     *  # 定义卖出股票时交易次数加1
     *
     * 买卖一次 为 一次交易
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int maxK = 2;
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = maxK; k >= 1; k--) {
                if (i - 1 == -1) {
                    // 把第一天所有的情况都处理完，第二天通过第一天的结果进行推导
                    dp[0][0][0] = 0;
                    dp[0][0][1] = Integer.MIN_VALUE;

                    //第一天结束，不持有，则利润为0；持有，则利润肯定是负数
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[0];

                    continue;
                }
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][maxK][0];
    }

    /**
     * 因为 k = 2 所以可以直接枚举出 k = 1 和 k = 2 的初始值 （隐式的 i = 0）
     *
     */
    int maxProfit_k_2(int[] prices) {
        //隐式的 i = 0, 不持有股票，利润为0， 持有股票，利润设置为无穷小，表示特殊值
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;

        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    public static void main(String[] args) {
        Question123MaxProfit maxProfit = new Question123MaxProfit();

        System.out.println(maxProfit.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(maxProfit.maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maxProfit.maxProfit(new int[]{7, 6, 4, 3, 1}));

//        System.out.println(maxProfit.maxProfit_k_2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
//        System.out.println(maxProfit.maxProfit_k_2(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(maxProfit.maxProfit_k_2(new int[]{7, 6, 4, 3, 1}));
    }
}
