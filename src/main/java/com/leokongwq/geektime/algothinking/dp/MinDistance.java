package com.leokongwq.geektime.algothinking.dp;

/**
 * @author : jiexiu
 * @date : 2020-06-30 11:02
 **/
public class MinDistance {

	// 全局变量或者成员变量
	public int minDist = Integer.MAX_VALUE;

	// 调用方式：minDistBacktracing(0, 0, 0, w, n);
	public void minDistBacktracing(int i, int j, int distance, int[][] w, int n) {
		System.out.println(i + " " + j);
		distance += w[i][j];
		if (i == n - 1 && j == n - 1) {
			if (distance < minDist) {
				minDist = distance;
			}
			return;
		}
		if (i < n - 1) {
			minDistBacktracing(i + 1, j, distance, w, n);
		}
		if (j < n - 1) {
			minDistBacktracing(i, j + 1, distance, w, n);
		}
	}

	public int minDistDP(int[][] matrix, int n) {
		int[][] states = new int[n][n];
		int sum = 0;
		// 初始化states的第一行数据
		for (int j = 0; j < n; ++j) {
			sum += matrix[0][j];
			states[0][j] = sum;
		}
		sum = 0;
		// 初始化states的第一列数据
		for (int i = 0; i < n; ++i) {
			sum += matrix[i][0];
			states[i][0] = sum;
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				states[i][j] =
						matrix[i][j] + Math.min(states[i][j - 1], states[i - 1][j]);
			}
		}
		return states[n - 1][n - 1];
	}

	public static void main(String[] args) {
		MinDistance minDistance = new MinDistance();
		int[][] martrix  = new int[][]{ {1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3} };
		minDistance.minDistBacktracing(0, 0, 0, martrix, 4);
		System.out.println(minDistance.minDist);
		System.out.println(minDistance.minDistDP(martrix, 4));
	}
}
