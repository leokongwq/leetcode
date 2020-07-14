package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2019/1/24 - 10:33
 *
 * In MATLAB, there is a very useful function called 'reshape',
 * which can reshape a matrix into a new one with different size but keep its original val.
 *
 * You're given a matrix represented by a two-dimensional array,
 * and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
 *
 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
 *
 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise,
 * output the original matrix.
 *
 * Example 1:
 * Input:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 1, c = 4
 * Output:
 * [[1,2,3,4]]
 * Explanation:
 * The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
 * Example 2:
 * Input:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 2, c = 4
 * Output:
 * [[1,2],
 *  [3,4]]
 * Explanation:
 * There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
 * Note:
 * The height and width of the given matrix is in range [1, 100].
 * The given r and c are all positive.
 *
 */
public class Question566ReshapeTheMatrix {

    /**
     * 矩阵转换
     * @param nums 代表矩阵的二维数组
     * @param r 新的矩阵 行数
     * @param c 新的矩阵 列数
     * @return 新的矩阵
     *
     * 注意，如果不能进行转换，则返回原矩阵
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        //原数组的行
        int m = nums.length;
        //原数组的列
        int n = nums[0].length;
        //元素不匹配
        if (m * n < r * c) {
            return nums;
        }
        int[][] newNums = new int[r][c];
        for (int i = 0; i < r * c; i++) {
            newNums[i / c][i % c] = nums[i / n][i % n];
        }
        return newNums;
    }
}
