package com.leokongwq.geektime.tree;

/**
 * @author : jiexiu
 * @date : 2020-06-18 16:11
 **/
public class BinaryTree {

	private TreeNode<Integer> tree;

	private static class TreeNode<T> {
		private T data;
		private TreeNode<T> left;
		private TreeNode<T> right;

		public TreeNode(T data) {
			this.data = data;
		}
	}

	/**
	 * 求二叉树节点的高度
	 * 确定二叉树高度有两种思路：
	 * 第一种是深度优先思想的递归，分别求左右子树的高度。当前节点的高度就是左右子树中较大的那个+1；
	 * 第二种可以采用层次遍历的方式，每一层记录都记录下当前队列的长度，这个是队尾，每一层队头从0开始。然后每遍历一个元素，队头下标+1。
	 * 直到队头下标等于队尾下标。这个时候表示当前层遍历完成。每一层刚开始遍历的时候，树的高度+1。最后队列为空，就能得到树的高度。
	 */
	private static int height(TreeNode<Integer> node, int height) {
		if (node == null) {
			return height;
		}
		return Math.max(height(node.left, height + 1), height(node.left, height + 1));
	}

	/**
	 * 二叉搜索树 - 查找
	 */
	private TreeNode<Integer> find(Integer target) {
		if (tree == null) {
			return null;
		}
		TreeNode<Integer> p = tree;
		while (p != null) {
			if (p.data > target) {
				p = p.left;
			} else if (p.data < target) {
				p = p.right;
			} else {
				return p;
			}
		}
		return null;
	}

	/**
	 * 二叉搜索树 - 插入
	 */
	private void insert(Integer data) {
		if (tree == null) {
			tree = new TreeNode<>(data);
			return;
		}
		TreeNode<Integer> p = tree;
		while (true) {
			if (p.data < data) {
				if (p.right == null) {
					p.right = new TreeNode<>(data);
					return;
				}
				p = p.right;
			} else {
				if (p.left == null) {
					p.left = new TreeNode<>(data);
					return;
				}
				p = p.left;
			}
		}
	}

	/**
	 * 二叉搜索树 - 删除
	 * 1. 删除叶子节点
	 * 2. 删除带有一个子节点的非叶子节点
	 * 3. 删除带有2个子节点的非叶子节点
	 */
	private void delete(Integer target) {
		TreeNode<Integer> p = tree;
		TreeNode<Integer> pp = null;

		//查到待删除的节点
		while (p != null && !p.data.equals(target)) {
			pp = p;
			if (p.data > target) {
				p = p.left;
			} else {
				p = p.right;
			}
		}

		if (p == null) {
			return;
		}

		//如果 节点 p 有2个子节点，则查找 p 的右子树的最小节点
		//找到会，用右子树的最小节点的值覆盖p的值，然后删除右子树的最小节点
		if (p.left != null && p.right != null) {
			TreeNode<Integer> minP = p.right;
			TreeNode<Integer> minPP = p;
			while (minP.left != null) {
				minPP = minP;
				minP = minP.left;
			}
			p.data = minP.data;

			p = minP;
			pp = minPP;
		}
		//到此处，问题化解为删除叶子节点 或 只有一个子节点的非叶子节点问题
		//这种实现方式比较优雅，最大程度的复用代码逻辑。副作用是理解起来困难一点点

		TreeNode<Integer> child = null;
		if (p.left != null) {
			child = p.left;
		} else if (p.right != null) {
			child = p.right;
		}
		//删除根节点 需要特别注意
		if (pp == null) {
			tree = child;
		} else if (pp.left == p) {
			pp.left = child;
		} else {
			pp.right = child;
		}
	}
}
