package com.leokongwq.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午2:35
 * Email:leokongwq@gmail.com
 */
public class PascalTriangle {

    private static void generatePascalTriangle(int n){
        int[][] arr = new int[n][];
        arr[0] = new int[]{1};
        arr[1] = new int[]{1, 1};
        int ceilNum = 3;
        while (ceilNum <= n){
            //该层的数组
            int[] ceil = new int[ceilNum];
            for (int i = 0; i < ceilNum; i++){
                if (i == 0 || i == ceilNum - 1 ){
                    ceil[i] = 1;
                }else{
                    ceil[i] = arr[ceilNum - 2][i - 1] + arr[ceilNum - 2][i];
                }
            }
            //
            arr[ceilNum-1] = ceil;
            ceilNum++;
        }

        System.out.println(arr);
    }

    public static void main(String[] args) {
        generatePascalTriangle(5);
    }
}
