package com.leokongwq.algorithm.thingking;

/**
 * @author jiexiu
 * created 2019/1/17 - 15:19
 * <p>
 * 动态规划算法的核心是记住已经求过的解，记住求解的方式有两种：
 * ①自顶向下的备忘录法
 * ②自底向上
 *
 * https://blog.csdn.net/u013309870/article/details/75193592
 *
 * https://www.zhihu.com/question/23995189
 * 动态规划是通过拆分问题，定义问题状态和状态之间的关系，使得问题能够以递推（或者说分治）的方式去解决。
 * 本题下的其他答案，大多都是在说递推的求解方法，但如何拆分问题，才是动态规划的核心。
 * 而拆分问题，靠的就是状态的定义和状态转移方程的定义。
 */
public class DynamicPrograming {

    /**
     * 递归求解斐波那数列
     */
    private static int fib(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 自顶向下的备忘录法
     */
    private static int fibonacci(int n) {
        if (n <= 0) {
            return n;
        }
        //备忘录
        int[] memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fib(n, memo);
    }

    private static int fib(int n, int[] memo) {
        //从备忘录获取已经计算过的值
        if (memo[n] != -1) {
            return memo[n];
        }
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中。
        if (n <= 2) {
            memo[n] = 1;
        } else {
            memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        }
        return memo[n];
    }

    //自底向上的动态规划

    //备忘录法还是利用了递归，上面算法不管怎样，计算fib（6）的时候最后还是要计算出fib（1），fib（2），fib（3）……,
    // 那么何不先计算出fib（1），fib（2），fib（3）……,呢？
    // 这也就是动态规划的核心，先计算子问题，再由子问题计算父问题。

    public static int fibV1(int n) {
        if (n <= 0) {
            return n;
        }
        int[] memo = new int[n + 1];
        memo[0] = 0;
        memo[1] = 1;
        for (int i = 2; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static int fibV2(int n) {
        if (n <= 0) {
            return n;
        }

        int Memo_i_2 = 0;
        int Memo_i_1 = 1;
        int Memo_i = 1;
        for (int i = 2; i <= n; i++) {
            Memo_i = Memo_i_2 + Memo_i_1;
            Memo_i_2 = Memo_i_1;
            Memo_i_1 = Memo_i;
        }
        return Memo_i;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //fib(50);
        fibonacci(500);
        System.out.println(System.currentTimeMillis() - start);
    }
}
