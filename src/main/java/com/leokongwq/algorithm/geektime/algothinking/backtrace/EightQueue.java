package com.leokongwq.algorithm.geektime.algothinking.backtrace;

/**
 * @author : jiexiu
 * @date : 2020-06-29 12:32
 **/
public class EightQueue {
	/**
	 * 下标表示行,值表示queen存储在哪一列
	 */
	private int[] result = new int[8];

	/**
	 * 经典的回溯例子: 八皇后问题
	 * <p>
	 * 我们有一个 8x8 的棋盘，希望往里放 8 个棋子（皇后），每个棋子所在的行、列、对角线都不能有另一个棋子。
	 * <p>
	 * 调用方式：cal8queens(0);
	 * row 可以表示当前是哪个阶段
	 */
	public void cal8queens(int row) {
		// 8个棋子都放置好了，打印结果
		if (row == 8) {
			printQueens(result);
			// 8行棋子都放好了，已经没法再往下递归了，所以就return
			return;
		}
		// 每一行都有8中放法
		for (int column = 0; column < 8; ++column) {
			// 有些放法不满足要求
			if (isOk(row, column)) {
				// 第row行的棋子放到了column列
				result[row] = column;
				// 考察下一行
				cal8queens(row + 1);
			}
		}
	}

	/**
	 * 判断row行column列放置是否合适
	 */
	private boolean isOk(int row, int column) {
		int leftup = column - 1;
		int rightup = column + 1;
		// 逐行往上考察每一行(因为下面的行还没有放呢)
		for (int i = row - 1; i >= 0; --i) {
			// 第i行的column列有棋子吗？
			if (result[i] == column) {
				return false;
			}
			// 考察左上对角线：第i行leftup列有棋子吗？
			if (leftup >= 0) {
				if (result[i] == leftup) {
					return false;
				}
			}
			// 考察右上对角线：第i行rightup列有棋子吗？
			if (rightup < 8) {
				if (result[i] == rightup) {
					return false;
				}
			}
			--leftup;
			++rightup;
		}
		return true;
	}

	/**
	 * 打印出一个二维矩阵
	 */
	private void printQueens(int[] result) {
		for (int row = 0; row < 8; ++row) {
			for (int column = 0; column < 8; ++column) {
				if (result[row] == column) {
					System.out.print("Q ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) {
		EightQueue eightQueue = new EightQueue();
		eightQueue.cal8queens(0);
	}
}
