package com.leokongwq.algorithm.geektime.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : jiexiu
 * @date : 2020-06-22 09:23
 * <p>
 * 图的存储：
 * 1. 邻接矩阵 （通过二维数组）
 * 2. 邻接表（通过数组 + 链表）
 **/
public class Graph {
	/**
	 * 顶点的个数
	 */
	private int v;
	/**
	 * 邻接表
	 */
	private LinkedList<Integer>[] adj;

	public Graph(int v) {
		this.v = v;
		this.adj = new LinkedList[v];

		for (int i = 0; i < v; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	/**
	 * 添加边
	 * 无向图一条边存两次
	 */
	public void addEdge(int s, int t) {
		this.adj[s].add(t);
		adj[t].add(s);
	}

	/**
	 * 广度优先遍历（breadth first search）
	 */
	public void bfs(int s, int t) {
		// 是用来记录已经被访问的顶点，用来避免顶点被重复访问
		boolean[] visited = new boolean[v];
		// 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点
		// 因为广度优先搜索是逐层访问的，也就是说，我们只有把第 k 层的顶点都访问完成之后，才能访问第 k+1 层的顶点。
		// 当我们访问到第 k 层的顶点的时候，我们需要把第 k 层的顶点记录下来，稍后才能通过第 k 层的顶点来找第 k+1 层的顶点。
		// 所以，我们用这个队列来实现记录的功能。
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);

		// 用来记录搜索路径。当我们从顶点 s 开始，广度优先搜索到顶点 t 后，prev 数组中存储的就是搜索的路径。
		// 不过，这个路径是反向存储的。prev[w]存储的是，顶点 w 是从哪个前驱顶点遍历过来的
		int[] prev = new int[v];
		for (int i = 0; i < v; ++i) {
			prev[i] = -1;
		}

		while (!queue.isEmpty()) {
			int w = queue.poll();
			for (int i = 0; i < adj[w].size(); i++) {
				int q = adj[w].get(i);
				if (!visited[q]) {
					prev[q] = w;
					if (q == t) {
						printPath(prev, s, t);
						return;
					}
					visited[q] = true;
					queue.add(q);
				}
			}
		}
	}

	/**
	 * 递归打印 s->t的路径
	 */
	private void printPath(int[] prev, int s, int t) {
		if (prev[t] != -1 && t != s) {
			printPath(prev, s, prev[t]);
		}
		System.out.print(t + " ");
	}

	/**
	 * 全局变量或者类成员变量
	 */
	boolean found = false;

	/**
	 * 深度优先搜索 （depth first search）
	 */
	public void dfs(int s, int t) {
		found = false;
		boolean[] visited = new boolean[v];
		int[] prev = new int[v];
		for (int i = 0; i < v; ++i) {
			prev[i] = -1;
		}
		recurDfs(s, t, visited, prev);
		printPath(prev, s, t);
	}

	private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
		if (found) {
			return;
		}
		visited[w] = true;
		if (w == t) {
			found = true;
			return;
		}
		for (int i = 0; i < adj[w].size(); ++i) {
			int q = adj[w].get(i);
			if (!visited[q]) {
				prev[q] = w;
				recurDfs(q, t, visited, prev);
			}
		}
	}
}