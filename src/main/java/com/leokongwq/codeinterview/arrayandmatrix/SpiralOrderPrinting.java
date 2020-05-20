package com.leokongwq.codeinterview.arrayandmatrix;

/**
 * @author jiexiu
 * created 2019/1/27 - 10:35
 */
public class SpiralOrderPrinting {


    /**
     * 转圈打印矩阵
     * @param matrix 表示矩阵的二维数组
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * 思路：一个矩阵可以用左上角的坐标 和 右下角的坐标 来确定。
     */
    private static void spiralPrintMatirx(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int tr = 0;
        int tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;

        while (tr <= dr && tc <= dc) {
            printMatrix(matrix, tr++, tc++, dr--, dc--);
        }
    }

    private static void printMatrix(int[][] matrix, int tr, int tc, int dr, int dc) {
        //只有一行
        if (tr == dr) {
            for (int i = tc; i <= dc; i++) {
                System.out.print(matrix[tr][i] + ",");
            }
        }
        //只有一列
        if (tc == dc) {
            for (int i = tr; i <= dr; i++) {
                System.out.print(matrix[i][tr] + ",");
            }
        }
        //一般情况
        int curR = tr;
        int curC = tc;
        while (curC < dc) {
            System.out.print(matrix[tr][curC] + ",");
            curC++;
        }

        while (curR < dr) {
            System.out.print(matrix[curR][dc] + ",");
            curR++;
        }
        while (curC > tc) {
            System.out.print(matrix[dr][curC] + ",");
            curC--;
        }
        while (curR > tr) {
            System.out.print(matrix[curR][tc] + ",");
            curR--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13, 14, 15, 16}};
        spiralPrintMatirx(matrix);
    }
}
