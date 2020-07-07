package com.leokongwq.algorithm.leetcode;

/**
 * 采用深度优先搜索, 解决123456789的全排列问题
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/25
 * Time: 下午2:02
 * Email:leokongwq@gmail.com
 */
public class DeepthFirstSearch {

    //存放0 - 9 9个数组
    private static int[] boxs = new int[10];
    //如果那个数字已经被使用了,则books[num] = 1
    private static int[] books = new int[10];

    private static int total = 0;
    /**
     * 深度优先搜索简单模型实现
     * @param step 表示第几步
     */
    private static void dfs(int step) {
        //临界条件判断
        if (step == 10) {
            for (int j = 1; j <=9; j++) {
                System.out.print(boxs[j]);
            }
            System.out.println();
            total++;
            return;
        }
        //有序尝试每一种可能( i 代表每一个数字, 尝试每一个可以放入当前盒子的数子)
        for (int i = 1; i <= 9; i++) {
            //数字i没有被使用
            if (books[i] == 0) {
                books[i] = 1;
                //数字放入盒子
                boxs[step] = i;
                //尝试下一步, 走到下一个盒子前
                dfs(step + 1);
                //下一步的尝试结束后, 收回当前的可能
                books[i] = 0;
            }
        }
        return;
    }

    public static void main(String[] args) {
        dfs(1);
        System.out.printf("total=" + total);
    }
}
