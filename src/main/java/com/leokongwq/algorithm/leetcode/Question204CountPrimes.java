package com.leokongwq.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2020/7/13 - 23:10
 *
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 素数的定义看起来很简单，如果一个数如果只能被 1 和它本身整除，那么这个数就是素数。
 *
 * 1 不是素数
 */
public class Question204CountPrimes {

    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public int countPrimesV1(int n) {
        boolean[] isPrim = new boolean[n];
        // 将数组都初始化为 true
        Arrays.fill(isPrim, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                // i 的倍数不可能是素数了
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 判断整数 n 是否是素数
     */
    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                // 有其他整除因子
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Question204CountPrimes solution = new Question204CountPrimes();
        System.out.println(solution.countPrimesV1(10));
        System.out.println(solution.countPrimesV1(25));
    }
}
