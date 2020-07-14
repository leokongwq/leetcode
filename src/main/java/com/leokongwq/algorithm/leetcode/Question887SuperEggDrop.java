package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/12 - 11:40
 *
 * hard
 *
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 *
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 *
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 *
 * 你的目标是确切地知道 F 的值是多少。
 *
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 *
 *  
 *
 * 示例 1：
 *
 * 输入：K = 1, N = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
 * 如果它没碎，那么我们肯定知道 F = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
 * 示例 2：
 *
 * 输入：K = 2, N = 6
 * 输出：3
 * 示例 3：
 *
 * 输入：K = 3, N = 14
 * 输出：4
 *
 */
public class Question887SuperEggDrop {

    public int superEggDrop(int K, int N) {
        return dpV1(K, N);
    }

    private Map<String, Integer> memo = new HashMap<>();

    /**
     * 动态规划
     *
     * 1. 状态，当前拥有的鸡蛋数k和需要测试的楼层数n
     * 2. 选择，其实就是去那层楼扔鸡蛋， 不同的选择，导致不同的状态转移
     *
     * 状态由2个因子确定，那么就是一个二维的状态表 或 带有2个参数的dp函数来表示状态转移（函数递归调用）
     * dp函数内部一个for循环来遍历所有选择，并选择最优的结果进行更新
     *
     * 动态规划的时间复杂度 = 子问题的个数 * dp函数本身的时间复杂度
     *
     * 该问题中，子问题的个数显然是不同状态的组合 = k * n; dp函数的时间复杂度为for循环的次数 n;
     * 总共就是 O（KN^2）; 空间复杂度是 O（KN）)原因是用了备忘录，有kn种状态
     *
     */
    public int dp(int k, int n) {
        if (n == 0 || n == 1 || k == 1) {
            return n;
        }

        if (memo.containsKey(k + "" + n)) {
            return memo.get(k + "" + n);
        }
        int minimun = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            //针对每一层扔鸡蛋的结果分别求出对应子问题的结果
            //dp(k - 1, i - 1) 表示在第i层扔，鸡蛋碎了，那么就需要将鸡蛋的个数减一，楼层减一，继续扔，直到没有鸡蛋或到地面
            //dp(k, n - i) 表示在第i层扔，鸡蛋没有碎，那么鸡蛋个数不变，问题转换为在拥有k个鸡蛋，n-i层楼，求解问题
            //把这2个子问题的最坏结果 + 第i层扔的一次 = i层楼扔鸡蛋，最坏情况下需要扔多少次
            int tMin = Math.max(dp(k - 1, i - 1), dp(k, n - i));
            minimun = Math.min(minimun, 1 + tMin);
        }
        memo.put(k + "" + n, minimun);
        return minimun;
    }

    /**
     * 通过使用二分搜索，将dp函数的时间复杂度降为log（n）, 整体时间复杂度为：O（KN * log（N））
     */
    public int dpV1(int k, int n) {
        if (n == 0 || n == 1 || k == 1) {
            return n;
        }

        if (memo.containsKey(k + "" + n)) {
            return memo.get(k + "" + n);
        }
        int res = Integer.MAX_VALUE;
        int low = 1;
        int high = n;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            int broken = dp(k - 1, mid - 1);
            int notBroken = dp(k, mid + 1);

            if (broken > notBroken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        memo.put(k + "" + n, res);
        return res;
    }

    public static void main(String[] args) {
        Question887SuperEggDrop superEggDrop = new Question887SuperEggDrop();

        System.out.println(superEggDrop.superEggDrop(1, 2));
        System.out.println(superEggDrop.superEggDrop(2, 6));
        System.out.println(superEggDrop.superEggDrop(3, 14));

        System.out.println(superEggDrop.superEggDrop(3, 26));
        System.out.println(superEggDrop.superEggDrop(4, 5000));
    }

}
