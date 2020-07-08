package com.leokongwq.algorithm.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/21
 * Time: 下午12:08
 * Email:leokongwq@gmail.com
 */
public class PrintNxNMatrix {
    /**
     * 打印 N* N 矩阵
     *       0  1  2
     * * * * * * *
     * 0 * 1  2  3
     * 1 * 4  5  6
     * 2 * 7  8  9
     * @param arr
     */
    public static void printNxNMatrix(int[][] arr){
        int n = arr.length;
        //打印左上部分
        for (int i = 0; i < n; i++){
            int row = 0;
            int col = i;
            while (row <= i && col >= 0){
                System.out.print(arr[row][col] + ",");
                row++;
                col--;
            }
            System.out.println();
        }
        //打印右下
        for (int j = 1; j < n; j++){
            int row  = j;
            int col = n - 1;
            while (row < n && col > 0){
                System.out.print(arr[row][col] + ",");
                row++;
                col--;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int num = 1;
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                arr[i][j] = num++;
            }
        }

        printNxNMatrix(arr);
    }
}
