package com.leokongwq.algorithm.leetcode.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-09-09 16:01
 *
 * easy
 *
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * 例如，给定一个 3叉树 :
 *
 * 返回其前序遍历: [1,3,5,6,2,4]。
 *
 * 说明: 递归法很简单，你可以使用迭代法完成此题吗?
 *
 **/
public class Lc589 {

	static class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}

	private List<Integer> res = new LinkedList<>();

	public List<Integer> preorder(Node root) {
		doPreOrder(root);
		return res;
	}

	public List<Integer> preorderV1(Node root) {
		LinkedList<Node> stack = new LinkedList<>();
		LinkedList<Integer> output = new LinkedList<>();
		if (root == null) {
			return output;
		}

		stack.add(root);
		while (!stack.isEmpty()) {
			Node node = stack.pollLast();
			output.add(node.val);
			Collections.reverse(node.children);
			for (Node item : node.children) {
				stack.add(item);
			}
		}
		return output;
	}

	private void doPreOrder(Node root) {
		if (root == null) {
			return;
		}
		res.add(root.val);

		if (root.children != null) {
			for (Node child : root.children) {
				doPreOrder(child);
			}
		}
	}

	public static void main(String[] args) {
		Node n4 = new Node(5);
		Node n5 = new Node(6);

		List<Node> c2 = new LinkedList<>();
		c2.add(n4);
		c2.add(n5);

		Node n1 = new Node(3, c2);
		Node n2 = new Node(2);
		Node n3 = new Node(4);

		List<Node> c1 = new LinkedList<>();
		c1.add(n1);
		c1.add(n2);
		c1.add(n3);

		Node root = new Node(1, c1);

		Lc589 lc589 = new Lc589();
		List<Integer> result = lc589.preorder(root);
		result.stream().forEach(System.out::print);

		System.out.println();

		result = lc589.preorderV1(root);
		result.stream().forEach(System.out::print);

	}
}
