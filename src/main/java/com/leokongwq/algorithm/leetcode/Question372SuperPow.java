package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author jiexiu
 * created 2020/7/16 - 06:09
 *
 * 你的任务是计算 ab 对 1337 取模，a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
 *
 * 示例 1:
 *
 * 输入: a = 2, b = [3]
 * 输出: 8
 * 示例 2:
 *
 * 输入: a = 2, b = [1,0]
 * 输出: 1024
 *
 *
 * 那么，说一个关于模运算的技巧吧，毕竟模运算在算法中比较常见：
 * (a * b) % k = (a % k)(b % k) % k
 * 证明很简单，假设：
 * a = Ak +B；b = Ck + D
 * 其中 A,B,C,D 是任意常数，那么：
 * ab = ACk^2 + ADk + BCk +BD
 * ab % k = BD % k
 * 又因为：
 * a % k = B；b % k = D
 * 所以：
 * (a % k)(b % k) % k = BD % k
 *
 */
public class Question372SuperPow {

    private static int base = 1337;

    public int superPow(int a, int[] b) {
        LinkedList<Integer> list = new LinkedList<>();
        if (b != null) {
            Arrays.stream(b).forEach(list::add);
        }
        return superPow(a, list);
    }

    private int superPow(int a, LinkedList<Integer> b) {
        // 递归的 base case
        if (b.isEmpty()) {
            return 1;
        }
        // 取出最后一个数
        int last = b.removeLast();
        // 将原问题化简，缩小规模递归求解
        int part1 = mypow(a, last);
        int part2 = mypow(superPow(a, b), 10);
        // 每次乘法都要求模
        return (part1 * part2) % base;
    }

    /**
     * 计算 a 的 k 次方的结果
     */
    private int mypow(int a, int k) {
        // 对因子求模
        a %= base;
        int res = 1;
        for (int i = 0; i < k; i++) {
            // 这里有乘法，是潜在的溢出点
            res *= a;
            // 对乘法结果求模
            res %= base;
        }
        return res;
    }
}
