package com.leokongwq.codeinterview.arrayandmatrix;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/27 - 11:06
 */
public class RotateMatrix {

    /**
     * 给定一个 N x N 的矩阵 顺时针旋转 90 度
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * 思路：一个矩阵可以用左上角的坐标 和 右下角的坐标 来确定。
     *
     * 从外到内分圈处理，每一圈，一个一个元素进行旋转
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     */
    private static void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int tr = 0;
        int tc = 0;
        int dr = matrix.length - 1;
        int dc = matrix[0].length - 1;

        while (tr < dr) {
            rotateEdge(matrix, tr++, tc++, dr--, dc--);
        }
    }

    private static void rotateEdge(int[][] matrix, int tr, int tc, int dr, int dc) {
        int times = dc - tc;

        for (int i = 0; i != times; i++) {
            //保留第一个要旋转的元素
            int temp = matrix[tr][tc + i];

            matrix[tr][tc + i] = matrix[dr - i][tc];
            matrix[dr - i][tc] = matrix[dr][dc - i];
            matrix[dr][dc - i] = matrix[tr + i][dc];
            matrix[tr + i][dc] = temp;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}, {13, 14, 15, 16}};
        rotate(matrix);
        System.out.println(matrix);
    }
}
