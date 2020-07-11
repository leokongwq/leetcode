package com.leokongwq.algorithm.geektime.algothinking.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/8 - 09:15
 */
public class Fibonacci {

    /**
     * 普通递归解法 - 自顶向下
     */
    private static int fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return fib(n -1) + fib(n - 2);
    }

    private static Map<Integer, Integer> memo = new HashMap<>();

    /**
     * 带备忘录的递归解法 - 自顶向下
     */
    private static int fibWithMemo(int n) {
        if (n < 1) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        if (memo.get(n) != null) {
            return memo.get(n);
        }

        int res = fib(n - 1) + fib(n - 2);
        memo.put(n, res);
        return res;
    }

    /**
     * 通过迭代法来求解，效率更好（递归的函数调用很耗时）
     */
    private static int fibIte(int n) {
        if (n < 1) {
            return 0;
        }
        int[] memo = new int[n + 1];
        memo[1] = 1;
        memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    /**
     * fib（n）的值只和 n-1 和 n-2 的值相关，所以不需要记录之前的值的计算结果
     */
    private static int fibIteWithLowMemory(int n) {
        if (n < 1) {
            return 0;
        }

        int prev = 1;
        int cur = 1;

        for (int i = 3; i <= n; i++) {
            int sum = prev + cur;
            prev = cur;
            cur = sum;
        }
        return cur;
    }

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
//        System.out.println(fib(40));
//        System.out.println(fibWithMemo(40));
//        System.out.println(fibIte(40));
        System.out.println(fibIteWithLowMemory(40));
        System.out.println("time cost : " + (System.currentTimeMillis() - start));
    }
}
