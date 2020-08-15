package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.TreeNode;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-15 17:24
 *
 * medium
 *
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 *
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 *
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 **/
public class Lc1367 {

	public boolean isSubPath(ListNode head, TreeNode root) {
		if (head == null) {
			return true;
		}
		if (root == null) {
			return false;
		}
		//先判断当前的节点，如果不对，再看左子树和右子树呗
		return doIsSubPath(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
	}

	private boolean doIsSubPath(ListNode node, TreeNode treeNode) {
		// 链表已经全部匹配完，匹配成功
		if (node == null) {
			return true;
		}
		// 二叉树访问到了空节点，匹配失败
		if (treeNode == null) {
			return false;
		}
		// 当前匹配的二叉树上节点的值与链表节点的值不相等，匹配失败
		if (node.val != treeNode.val) {
			return false;
		} else {
			return doIsSubPath(node.next, treeNode.left) || doIsSubPath(node.next, treeNode.right);
		}
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(4, new ListNode(2, new ListNode(8)));

		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(4, null, new TreeNode(2, new TreeNode(1), null));
		root.right = new TreeNode(4, new TreeNode(2, new TreeNode(6, null, null), new TreeNode(8, new TreeNode(1), new TreeNode(3))), null);

		Lc1367 lc1367 = new Lc1367();

		System.out.println(lc1367.isSubPath(head, root));
	}
}
