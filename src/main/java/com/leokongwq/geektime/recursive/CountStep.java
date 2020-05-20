package com.leokongwq.geektime.recursive;

/**
 * @author jiexiu
 * created 2020/5/20 - 23:48
 *
 * 用递归求解 N 层台阶的走法
 *
 * 问题：有一个N层的台阶，开始 或 在台阶上迈出下一步时可以选走一个台阶或两个台阶，共有多少中走法
 *
 * 第一步：问题分解
 *  所有的走法可以分为2类，第一类是：第一步走一个台阶， 第二类是：第一步走2个台阶，可以看做是2课独立的树
 *  把
 */
public class CountStep {

    /**
     *
     * @param n 表示台阶数
     * @return 返回一个数字，表示共有多少中走法
     */
    static int countStep(int n) {
        if (n <= 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        //这个公式非常关键
        return countStep(n - 1) + countStep(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(countStep(0));
        System.out.println(countStep(1));
        System.out.println(countStep(2));
        System.out.println(countStep(3));
    }
}
