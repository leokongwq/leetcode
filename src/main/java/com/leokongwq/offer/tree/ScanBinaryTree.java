package com.leokongwq.offer.tree;

import org.w3c.dom.Node;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : jiexiu
 * @date : 2020-06-15 20:08
 * 二叉树的遍历
 * 前序：根左右
 * 中序：左根右
 * 后序：左右根
 **/
public class ScanBinaryTree {

	private static  class Node<T> {

		private Node<T> left;

		private Node<T> right;

		private T data;

		public Node(T data) {
			this.data = data;
		}
	}

	/**
	 * 先序遍历
	 */
	private <T> void preOrderRecursive(Node<T> root) {
		if (root == null) {
			return;
		}
		System.out.println(root.data);
		preOrderRecursive(root.left);
		preOrderRecursive(root.right);
	}

	/**
	 * 先序遍历-非递归
	 */
	private <T> void preOrder(Node<T> root) {
		if (root == null) {
			return;
		}
		Stack<Node<T>> stack = new Stack<>();
		stack.push(root);
		while (!stack.isEmpty()) {
			Node<T> node = stack.pop();
			System.out.println(node.data);
			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
	}

	/**
	 * 中序遍历
	 */
	private <T> void inOrderRecursive(Node<T> root) {
		if (root == null) {
			return;
		}
		inOrderRecursive(root.left);
		System.out.println(root);
		inOrderRecursive(root.right);
	}

	/**
	 * 中序遍历 - 非递归
	 */
	private <T> void inOrder(Node<T> root) {
		if (root == null) {
			return;
		}
		Stack<Node<T>> stack = new Stack<>();
		Node<T> node = root;
		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.println(node.data);
				node = node.right;
			}
		}
	}

	/**
	 * 后序遍历
	 */
	private <T> void postOrderRecursive(Node<T> root) {
		if (root == null) {
			return;
		}
		postOrderRecursive(root.left);
		postOrderRecursive(root.right);
		System.out.println(root);
	}

	/**
	 * 后序遍历 - 非递归
	 */
	private <T> void postOrder(Node<T> root) {
		if (root == null) {
			return;
		}
		Stack<Node<T>> s1 = new Stack<>();
		Stack<Node<T>> s2 = new Stack<>();
		s1.push(root);
		while (!s1.isEmpty()) {
			Node<T> node = s1.pop();
			s2.push(node);
			if (node.left != null) {
				s1.push(node.left);
			}
			//因为栈是后进先出，所以每次都是先处理右孩子，后孩子压入s2后，变为先进后出了
			//整体思路就是利用2个栈来实现顺序倒转和函数调用压栈操作
			if (node.right != null) {
				s1.push(node.right);
			}
		}
		while (!s2.isEmpty()) {
			System.out.println(s2.pop().data);
		}
	}

	/**
	 * 后序遍历 - 非递归 2
	 * h 代表最近一次弹出并打印的节点
	 * c 代码stack的栈顶节点
	 * 初始时 h 是根节点
	 */
	private <T> void postOrderV2(Node<T> h) {
		if (h == null) {
			return;
		}
		Stack<Node<T>> stack = new Stack<>();
		stack.push(h);
		Node<T> c = null;
		while (!stack.isEmpty()) {
			c = stack.peek();
			if (c.left != null && h != c.left && h != c.right) {
				stack.push(c.left);
			} else if (c.right != null && h != c.right) {
				stack.push(c.right);
			} else {
				System.out.println(stack.pop().data);
				h = c;
			}
		}
	}

	/**
	 * 按层遍历
	 */
	private <T> void levelPrintTree(Node<T> root) {
		if (root == null) {
			return;
		}

		LinkedList<Node<T>> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {
			Node<T> node = queue.removeFirst();
			System.out.println(node.data);
			if (node.left != null) {
				queue.add(node.left);
			}
			if (node.right != null) {
				queue.add(node.right);
			}
		}
	}

	public static void main(String[] args) {

	}
}
