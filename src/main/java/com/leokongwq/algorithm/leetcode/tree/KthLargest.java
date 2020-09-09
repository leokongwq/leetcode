package com.leokongwq.algorithm.leetcode.tree;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-09-09 17:37
 *
 * easy
 *
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 **/
public class KthLargest {

	private List<TreeNode> inOrderList = new LinkedList<>();

	public int kthLargest(TreeNode root, int k) {
		inOrder(root);
		return inOrderList.get(inOrderList.size() - k).val;
	}

	private void inOrder(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrder(root.left);
		inOrderList.add(root);
		inOrder(root.right);
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
		KthLargest kthLargest = new KthLargest();

//		System.out.println(kthLargest.kthLargest(root, 1));
		System.out.println(kthLargest.kthLargest(root, 3));
	}
}
