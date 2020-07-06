package com.leokongwq.geektime.advanced;

import java.util.LinkedList;

/**
 * @author : jiexiu
 * @date : 2020-07-04 00:09
 **/
public class TopologicalSort {

	/**
	 * 顶点的个数
	 */
	private int v;
	/**
	 * 邻接表
	 */
	private LinkedList<Integer>[] adj;

	public TopologicalSort(int v) {
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

	/*
	 * 拓扑排序应用非常广泛，解决的问题的模型也非常一致。凡是需要通过局部顺序来推导全局顺序的，一般都能用拓扑排序来解决。
	 * 除此之外，拓扑排序还能检测图中环的存在。
	 * 对于 Kahn 算法来说，如果最后输出出来的顶点个数，少于图中顶点个数，图中还有入度不是 0 的顶点，那就说明，图中存在环。
	 */

	/**
	 * 拓扑排序 - Kahn 算法
	 * // s先于t，边s->t
	 * 从 Kahn 代码中可以看出来，每个顶点被访问了一次，每个边也都被访问了一次，所以，Kahn 算法的时间复杂度就是 O(V+E)（V 表示顶点个数，E 表示边的个数）
	 */
	public void topoSortByKahn() {
		// 统计每个顶点的入度, 目的是找到所有入度为0的顶点
		int[] inDegree = new int[v];
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < adj[i].size(); ++j) {
				// i->w
				int w = adj[i].get(j);
				inDegree[w]++;
			}
		}
		// 入度为0的顶点
		LinkedList<Integer> queue = new LinkedList<>();
		for (int i = 0; i < v; ++i) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int i = queue.remove();
			System.out.print("->" + i);
			for (int j = 0; j < adj[i].size(); ++j) {
				int k = adj[i].get(j);
				inDegree[k]--;
				if (inDegree[k] == 0) {
					queue.add(k);
				}
			}
		}
	}

	/**
	 * dfs 实现拓扑排序
	 * DFS 算法的时间复杂度我们之前分析过。每个顶点被访问两次，每条边都被访问一次，所以时间复杂度也是 O(V+E)。
	 */
	public void topoSortByDFS() {
		// 先构建逆邻接表，边s->t表示，s依赖于t，t先于s
		LinkedList<Integer> inverseAdj[] = new LinkedList[v];
		// 申请空间
		for (int i = 0; i < v; ++i) {
			inverseAdj[i] = new LinkedList<>();
		}
		// 通过邻接表生成逆邻接表
		for (int i = 0; i < v; ++i) {
			for (int j = 0; j < adj[i].size(); ++j) {
				// i->w
				int w = adj[i].get(j);
				// w->i
				inverseAdj[w].add(i);
			}
		}
		boolean[] visited = new boolean[v];
		// 深度优先遍历图
		for (int i = 0; i < v; ++i) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(i, inverseAdj, visited);
			}
		}
	}

	private void dfs(int vertex, LinkedList<Integer> inverseAdj[], boolean[] visited) {
		// 先把它依赖的所有的顶点输出了
		for (int i = 0; i < inverseAdj[vertex].size(); ++i) {
			int w = inverseAdj[vertex].get(i);
			if (visited[w]) {
				continue;
			}
			visited[w] = true;
			dfs(w, inverseAdj, visited);
		} // 先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
		System.out.print("->" + vertex);
	}
}
