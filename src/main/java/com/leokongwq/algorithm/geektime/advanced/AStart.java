package com.leokongwq.algorithm.geektime.advanced;

import lombok.Data;

import java.util.LinkedList;

/**
 * @author : jiexiu
 * @date : 2020-07-03 13:59
 * <p>
 * 最短路径算法
 * <p>
 * 单源最短路径 Dijkstra 算法（一个顶点到一个顶点）。提到最短路径算法，最出名的莫过于 Dijkstra 算法了
 **/
public class AStart {
	/**
	 * 邻接表, 数组的下标表示顶点的编号
	 */
	private LinkedList<Edge> adj[];

	/**
	 * 顶点个数
	 */
	private int v;

	// Graph类的成员变量，在构造函数中初始化
	private Vertex[] vertexes;

	public AStart(int v) {
		this.v = v;
		this.vertexes = new Vertex[this.v];
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

	// 新增一个方法，添加顶点的坐标
	public void addVetex(int id, int x, int y) {
		vertexes[id] = new Vertex(id, x, y);
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

		// 新增：f(i) = g(i) + h(i)
		public int f;

		// 新增：顶点在地图中的坐标（x, y）
		public int x, y;

		public Vertex(int id, int dist) {
			this.id = id;
			this.dist = dist;
		}

		public Vertex(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
			this.f = Integer.MAX_VALUE;
			this.dist = Integer.MAX_VALUE;
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
			while (i / 2 > 0 && nodes[i].f < nodes[i / 2].f) {
				swap(nodes, i, i / 2);
				i = i / 2;
			}
		}

		private void heapifyUpToDown(Vertex[] nodes, int size, int i) {
			while (true) {
				//找到 i 的左右子节点中的最大值，并进行交换
				int maxPos = i;
				if (i * 2 <= size && nodes[i].f > nodes[i * 2].f) {
					maxPos = i * 2;
				}
				if (i * 2 + 1 <= size && nodes[maxPos].f > nodes[i * 2 + 1].f) {
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
					nodes[i].f = vertex.f;
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

		public void clear() {
			this.size = 0;
		}
	}


	/**
	 * 从顶点s到顶点t的路径
	 */
	public void astar(int s, int t) {
		// 用来还原路径
		int[] predecessor = new int[this.v];
		// 按照vertex的 f 值构建的小顶堆，而不是按照dist
		PriorityQueue queue = new PriorityQueue(this.v);
		// 标记是否进入过队列
		boolean[] inqueue = new boolean[this.v];
		vertexes[s].dist = 0;
		vertexes[s].f = 0;
		queue.add(vertexes[s]);
		inqueue[s] = true;
		while (!queue.isEmpty()) {
			// 取堆顶元素并删除
			Vertex minVertex = queue.poll();
			for (int i = 0; i < adj[minVertex.id].size(); ++i) {
				// 取出一条minVetex相连的边
				Edge e = adj[minVertex.id].get(i);
				// minVertex-->nextVertex
				Vertex nextVertex = vertexes[e.tid];
				// 更新next的dist,f
				if (minVertex.dist + e.w < nextVertex.dist) {
					nextVertex.dist = minVertex.dist + e.w;
					nextVertex.f = nextVertex.dist + hManhattan(nextVertex, vertexes[t]);
					predecessor[nextVertex.id] = minVertex.id;
					if (inqueue[nextVertex.id]) {
						queue.update(nextVertex);
					} else {
						queue.add(nextVertex);
						inqueue[nextVertex.id] = true;
					}
				}
				// 只要到达t就可以结束while了
				if (nextVertex.id == t) {
					// 清空queue，才能推出while循环
					queue.clear();
					break;
				}
			}
		}
		// 输出路径
		System.out.print(s);
		print(s, t, predecessor);
	}

	private int hManhattan(Vertex v1, Vertex v2) {
		return Math.abs(v1.x - v2.x) + Math.abs(v1.y - v2.y);
	}

	private void print(int s, int t, int[] predecessor) {
		if (s == t) {
			return;
		}
		print(s, predecessor[t], predecessor);
		System.out.print("->" + t);
	}

	public static void main(String[] args) {
		AStart weightGraphDijkstra = new AStart(6);

		weightGraphDijkstra.addEdge(0, 1, 10);
		weightGraphDijkstra.addEdge(0, 4, 15);

		weightGraphDijkstra.addEdge(1, 2, 15);
		weightGraphDijkstra.addEdge(1, 3, 2);

		weightGraphDijkstra.addEdge(2, 5, 5);
		weightGraphDijkstra.addEdge(3, 5, 12);
		weightGraphDijkstra.addEdge(3, 2, 1);

		weightGraphDijkstra.addEdge(4, 5, 10);

		weightGraphDijkstra.astar(0, 5);
	}
}
