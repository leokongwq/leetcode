package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/17 - 14:54
 * <p>
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 * <p>
 * Example 1:
 * <p>
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 * <p>
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class Question121StockBuySell {

    /**
     * 1. 下标 i 表示 股票在第 i 天的价格
     * 2. 设计算法 获取最大利润
     * <p>
     * <p>
     * 例如： Input: [7,1,5,3,6,4]
     * 答案是：5，
     * 第一天买入， 价格是： 1
     * 第五天卖出： 价格是 6 ，利润 = 6 - 1 = 5
     * <p>
     * 注意：
     * 1. 没有买股票前不能卖(废话)
     * 2. 只能进行一次交易
     * 3. 卖出价格 一定大于 买入价格 7 - 1 = 6 ， 但是 7 是第一天， 只能是买入的时间
     * <p>
     * <p>
     * 思路：
     * 1. 暴力搜索， 时间复杂度是 N平方
     * 2. 动态规划
     */
    private static int maxProfitV1(int[] prices) {
        int maxProfit = 0;
        //外层循环表示买入的天数
        for (int i = 0; i < prices.length - 1; i++) {
            //内存循环表示卖出的天数
            for (int j = i + 1; j < prices.length; j++) {
                //有利润，则卖出，计算利润
                if (prices[j] > prices[i]) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
        }
        return maxProfit;
    }

    private static int maxProfitV2(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = maxCur + prices[i] - prices[i - 1];
            maxCur = Math.max(0, maxCur);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    /**
     * 记录买入价格最低点，用每天的价格减去最低价， 选取最大的结果值
     */
    private static int maxProfitV3(int[] prices) {
        int maxProfit = 0;
        //记录最低的价格
        int buy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            buy = Math.min(buy, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - buy);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfitV1(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitV1(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitV2(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitV2(new int[]{7, 6, 4, 3, 1}));

        System.out.println(maxProfitV3(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfitV3(new int[]{7, 6, 4, 3, 1}));
    }
}
