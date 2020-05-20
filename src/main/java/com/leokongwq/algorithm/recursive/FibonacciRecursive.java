package com.leokongwq.algorithm.recursive;

/**
 * @author jiexiu
 * created 2018/12/21 - 11:55
 * https://www.jianshu.com/p/6bdc8e3637f2
 */
public class FibonacciRecursive {

    /**
     * 最简单，但是效率低下，
     * 1. 重复计算，n -1 和 n - 2 的求解过程中有重复计算
     * 2. 每次函数调用需要保存现场，返回后恢复现场， 不利于CPU流水线执行。
     */
    private static int fibonacciRecursive(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * 尾递归
     * 1. 第一次调用的传参满足 第一次计算的条件
     * 2. 每次调用就进行一次有效计算， 对应普通递归的 回归流程。
     * 3. 尾递归方法的参数是每次计算所需要的所有数据集合，本例中是 ret1, ret2
     */
    private static int fibonacciTailRecursive(int n, int ret1, int ret2) {
        if (n == 0) {
            return ret1;
        }
        return fibonacciTailRecursive(n - 1, ret2, ret1 + ret2);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int n = fibonacciRecursive(100);
//        System.out.println("result:" + n);
//        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        fibonacciTailRecursive(10000, 0 , 1);
        System.out.println("time cost: " + (System.currentTimeMillis() - start));
    }
}
