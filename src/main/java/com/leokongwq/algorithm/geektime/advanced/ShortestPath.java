package com.leokongwq.algorithm.geektime.advanced;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-07-03 13:59
 * <p>
 * 最短路径算法
 * <p>
 * 单源最短路径 Dijkstra 算法（一个顶点到一个顶点）。提到最短路径算法，最出名的莫过于 Dijkstra 算法了
 **/
public class ShortestPath {
	/**
	 * 邻接表, 数组的下标表示顶点的编号
	 */
	private LinkedList<Edge> adj[];

	/**
	 * 顶点个数
	 */
	private int v;

	public ShortestPath(int v) {
		this.v = v;
		this.adj = new LinkedList[v];
		for (int i = 0; i < v; ++i) {
			this.adj[i] = new LinkedList<>();
		}
	}

	/**
	 * 添加一条边
	 *
	 * @param s 开始
	 * @param t 结束
	 * @param w 权重
	 */
	public void addEdge(int s, int t, int w) {
		this.adj[s].add(new Edge(s, t, w));
	}

	/**
	 * 边
	 */
	private class Edge {
		/**
		 * 边的起始顶点编号
		 */
		public int sid;
		/**
		 * 边的终止顶点编号
		 */
		public int tid;
		/**
		 * 权重
		 */
		public int w;

		public Edge(int sid, int tid, int w) {
			this.sid = sid;
			this.tid = tid;
			this.w = w;
		}
	}

	/**
	 * 下面这个类是为了dijkstra实现用的
	 */
	@Data
	private class Vertex {
		/**
		 * 顶点编号ID
		 */
		public int id;

		/**
		 * 从起始顶点到这个顶点的距离
		 */
		public int dist;

		public Vertex(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}
	}

	/**
	 * 因为Java提供的优先级队列，没有暴露更新数据的接口，所以我们需要重新实现一个
	 * 根据vertex.dist构建小顶堆
	 */
	private class PriorityQueue {

		private Vertex[] nodes;

		/**
		 * 堆的容量
		 */
		private int count;

		/**
		 * 当前堆的大小
		 */
		private int size;

		public PriorityQueue(int v) {
			this.nodes = new Vertex[v + 1];
			this.count = v;
		}

		public void add(Vertex vertex) {
			if (size >= count) {
				return;
			}
			size++;
			nodes[size] = vertex;

			int i = size;
			heapifyDownToUp(i);
		}

		public Vertex poll() {
			// 堆中没有数据
			if (isEmpty()) {
				return null;
			}
			Vertex v = nodes[1];
			nodes[1] = nodes[size];
			nodes[size] = null;
			//等价于删除元素，因为腾出来位置了
			--size;
			heapifyUpToDown(nodes, size, 1);

			return v;
		}

		// 自下往上堆化, 是一个大顶推。 父节点的下标 是 i / 2
		// 根节点下标是1，也就是堆定元素的下标是 1
		private void heapifyDownToUp(int i) {
			while (i / 2 > 0 && nodes[i].dist < nodes[i / 2].dist) {
				swap(nodes, i, i / 2);
				i = i / 2;
			}
		}

		private void heapifyUpToDown(Vertex[] nodes, int size, int i) {
			while (true) {
				//找到 i 的左右子节点中的最大值，并进行交换
				int maxPos = i;
				if (i * 2 <= size && nodes[i].dist > nodes[i * 2].dist) {
					maxPos = i * 2;
				}
				if (i * 2 + 1 <= size && nodes[maxPos].dist > nodes[i * 2 + 1].dist) {
					maxPos = i * 2 + 1;
				}
				//说明 i 就是合适位置， arr[i] 大于等于 arr[2 * i] 和 arr[2 * i + 1]
				if (maxPos == i) {
					break;
				}
				swap(nodes, i, maxPos);
				i = maxPos;
			}
		}

		// 更新结点的值，并且从下往上堆化，重新符合堆的定义。时间复杂度O(logn)。
		public void update(Vertex vertex) {
			//查找，并更新
			for (int i = 1; i <= size; i++) {
				if (nodes[i].id == vertex.id) {
					nodes[i].dist = vertex.dist;
					heapifyDownToUp(i);
				}
			}
		}

		public boolean isEmpty() {
			return size == 0;
		}

		private void swap(Vertex[] nodes, int i, int j) {
			Vertex temp = nodes[i];
			nodes[i] = nodes[j];
			nodes[j] = temp;
		}
	}

	/**
	 * 从顶点s到顶点t的最短路径
	 * 时间复杂度就是 O(E * logV)
	 * E 表示边的个数
	 * V 表示顶点的个数
	 *
	 * 比起 Dijkstra 算法，地图软件用的更多的是类似 A* 的启发式搜索算法
	 *
	 * 最短路径算法还有很多，比如 Bellford 算法、Floyd 算法等等
	 */
	public void dijkstra(int s, int t) {
		// 用来还原最短路径，数组的下标表示顶点的编号，值保存该顶点的前驱顶点，递归打印路径
		int[] predecessor = new int[this.v];

		Vertex[] vertexes = new Vertex[this.v];
		for (int i = 0; i < this.v; ++i) {
			vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
		}

		// 小顶堆
		PriorityQueue queue = new PriorityQueue(this.v);

		// 标记是否进入过队列，防止重复入队
		boolean[] inqueue = new boolean[this.v];

		//起始顶点 到 起始顶点 距离为 0
		vertexes[s].dist = 0;
		queue.add(vertexes[s]);
		inqueue[s] = true;

		while (!queue.isEmpty()) {
			// 取堆顶元素并删除
			Vertex minVertex = queue.poll();
			// 最短路径产生了
			if (minVertex.id == t) {
				break;
			}
			for (int i = 0; i < adj[minVertex.id].size(); ++i) {
				// 取出一条minVetex相连的边
				Edge e = adj[minVertex.id].get(i);
				// minVertex-->nextVertex
				Vertex nextVertex = vertexes[e.tid];

				//说明从 起始顶点 -> minVertex -> 的路径 比 起始顶点 -> nextVertex的距离短
				if (minVertex.dist + e.w < nextVertex.dist) {
					// 更新next的dist
					nextVertex.dist = minVertex.dist + e.w;
					//反向记录路径
					predecessor[nextVertex.id] = minVertex.id;

					//已经在队列中，则更新在队列中的位置（这里很关键，更新后会更早出队，提前发现最短路径）
					if (inqueue[nextVertex.id]) {
						// 更新队列中的dist值
						queue.update(nextVertex);
					} else {
						queue.add(nextVertex);
						inqueue[nextVertex.id] = true;
					}
				}
			}
		}
		// 输出最短路径
		System.out.print(s);
		print(s, t, predecessor);
	}

	private void print(int s, int t, int[] predecessor) {
		if (s == t) {
			return;
		}
		print(s, predecessor[t], predecessor);
		System.out.print("->" + t);
	}

	// 将多个DFS找到的路径的距离，取其最小值, 算法运行太慢了。
	private List<Edge> shortestPath;
	private int minWeight = Integer.MAX_VALUE;

	public void dfs(int s, int t) {
		boolean[] visited = new boolean[v];
		List<Edge> paths = new LinkedList<>();

		dfsRecur(s, t, visited, paths);

		System.out.println(minWeight);
	}

	private void dfsRecur(int s, int t, boolean[] visited, List<Edge> paths) {
		if (s == t) {
			//收集路径结果
			int weight = paths.stream().map(edge -> edge.w).reduce(Integer::sum).get();
			if (shortestPath == null || weight < minWeight) {
				shortestPath = new LinkedList<>(paths);
				minWeight = weight;
			}
			return;
		}
		visited[s] = true;
		for (Edge edge : adj[s]) {
			if (visited[edge.tid]) {
				continue;
			}
			paths.add(edge);
			dfsRecur(edge.tid, t, visited, paths);
			paths.remove(edge);
		}
	}

	public static void main(String[] args) {
		ShortestPath weightGraph = new ShortestPath(4);
		weightGraph.addEdge(0, 1, 5);
		weightGraph.addEdge(0, 2, 15);
		weightGraph.addEdge(1, 3, 5);
		weightGraph.addEdge(2, 3, 1);

		weightGraph.dfs(0, 3);
		weightGraph.dijkstra(0, 3);
		System.out.println();

		ShortestPath weightGraphDijkstra = new ShortestPath(6);

		weightGraphDijkstra.addEdge(0, 1, 10);
		weightGraphDijkstra.addEdge(0, 4, 15);

		weightGraphDijkstra.addEdge(1, 2, 15);
		weightGraphDijkstra.addEdge(1, 3, 2);

		weightGraphDijkstra.addEdge(2, 5, 5);
		weightGraphDijkstra.addEdge(3, 5, 12);
		weightGraphDijkstra.addEdge(3, 2, 1);

		weightGraphDijkstra.addEdge(4, 5, 10);

		weightGraphDijkstra.dijkstra(0, 5);
	}
}
