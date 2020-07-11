package com.leokongwq.algorithm.geektime.algothinking.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : jiexiu
 * @date : 2020-06-30 22:43
 * <p>
 * 假设我们有几种不同币值的硬币 v1，v2，……，vn（单位是元）。如果我们要支付 w 元，求最少需要多少个硬币。
 * 比如，我们有 3 种不同的硬币，1 元、3 元、5 元，我们要支付 9 元，最少需要 3 个硬币（3 个 3 元的硬币）。
 * <p>
 * f(9)=1+min(f(8),f(6),f(4))
 **/
public class MinPayCoin {

    private static Map<Integer, Integer> memory = new HashMap<>();

    private static int mincoinCount = Integer.MAX_VALUE;

    /**
     * 暴力递归求解 - 回溯
     *
     * @param coins  中是可选硬币面值
     * @param amount 是目标金额
     */
    private static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        coinChangeRecur(coins, amount, 0, 0);
        return mincoinCount;
    }

    private static void coinChangeRecur(int[] coins, int amount, int currentAmount, int coinCount) {
        //终止条件 - 凑够了
        if (currentAmount == amount) {
            mincoinCount = Math.min(coinCount, mincoinCount);
            return;
        }

        for (int coin : coins) {
            if (coin + currentAmount <= amount) {
                coinChangeRecur(coins, amount, coin + currentAmount, coinCount + 1);
            }
        }
    }

    private static Map<Integer, Integer> memo = new HashMap<>();

    /**
     * 自顶向下-递归-暴力求解 （可以通过备忘录来解决重复子问题）
     * 暴力递归时间复杂度： O(（k * n^k)）
     * 带备忘录的递归时间复杂度：O(（kn）)
     */
    private static int coinChangeDpUpToDown(int[] coins, int amount) {
        if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subRes = coinChangeDpUpToDown(coins, amount - coin);
            if (subRes == -1) {
                continue;
            }
            res = Math.min(res, subRes + 1);
        }

        int result = (res == Integer.MAX_VALUE ? -1 : res);
        memo.put(amount, res);
        return result;
    }

    /**
     * 通过动态规划求解 - 自底向上
     * 1. 多阶段求最优解，该问题的阶段可以理解为每选择一个硬币后，手里钱的总数
     * 2. 假设f（n-m）为凑齐n-m快钱的最少硬币数，那么f（n）= f（n-m）+ 1, 这里的1表示选择一个面值为m的硬币
     * 3. 子问题的最优解得到后，可以通过子问题的最优解获取原问题的最优解
     */
    private static int coinChangeDp(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        //一维状态表， 下标表示金额， 值表示凑齐该金额的最少硬币数
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        //逐个推导每种金额的最少硬币数
        for (int i = 1; i < dp.length; i++) {
            // 内层循环求所有选择的最小值
            for (int coin : coins) {
                if (i - coin >= 0) {
                    //合并解,找最优
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] != amount + 1 ? dp[amount] : -1;
    }

    public static void main(String[] args) {
//        System.out.println(coinChange(new int[]{1, 3, 5}, 9));
//        System.out.println(coinChange(new int[]{1, 3, 5}, 10));
//        System.out.println(coinChange(new int[]{1, 3, 5}, 8));
//        System.out.println(coinChange(new int[]{1, 3, 5}, 7));

//        System.out.println(coinChange(new int[]{1, 3, 5}, 1));
//        System.out.println(coinChange(new int[]{1, 3, 5}, 5));
//        System.out.println(coinChange(new int[]{1, 3, 5}, 9));

//        System.out.println(coinChangeDp(new int[]{1, 3, 5}, 9));
        System.out.println(coinChangeDp(new int[]{2}, 3));

    }
}
