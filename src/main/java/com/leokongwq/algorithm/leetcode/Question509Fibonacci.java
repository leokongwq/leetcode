package com.leokongwq.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2019/1/22 - 18:06
 *
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
 * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0,
 * F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).
 */
public class Question509Fibonacci {

   private static Map<Integer, Integer> book = new HashMap<>();

    /**
     * 递归实现，效率低
     */
    private static int fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n is negative");
        }
        if (n <= 1) {
            book.put(n, n);
            return n;
        }
        if (book.containsKey(n)) {
            return book.get(n);
        }
        int result = fib(n - 1) + fib(n - 2);
        book.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(3));
        System.out.println(fib(4));
        System.out.println(fib(30));
    }
}
