package com.leokongwq.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiexiu
 * created 2019/1/17 - 13:58
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class Question118PascalTriangle {

    private static List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> result = new ArrayList<>(numRows);

        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                List<Integer> row = new ArrayList<>(i + 1);
                row.add(1);
                result.add(row);
            } else if (i == 1) {
                List<Integer> row = new ArrayList<>(i + 1);
                row.add(1);
                row.add(1);
                result.add(row);
            } else {
                List<Integer> row = new ArrayList<>(i + 1);
                //上一行
                List<Integer> preRow = result.get(i - 1);
                for (int j = 0; j < i+1; j++) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(j, preRow.get(j - 1) + preRow.get(j));
                    }
                }
                result.add(row);
            }
        }
        return result;
    }

    /**
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     *
     * Example:
     * *       [1],
     *  *     [1,1],
     *  *    [1,2,1],
     *  *   [1,3,3,1],
     *  *  [1,4,6,4,1]
     * Input: 3
     * Output: [1,3,3,1]
     *
     * Could you optimize your algorithm to use only O(k) extra space?
     */
    public List<Integer> getRow(int rowIndex) {
        // 行下标从 0 开始，第rowIndex行的元素个数就是 rowIndex + 1 个
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        for (int i = 0; i <= rowIndex; i++) {
            row.add(1);
            // From end to start
            for (int j = i - 1; j >= 1; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        System.out.println(result);
    }
}
