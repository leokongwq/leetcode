package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-15 23:26
 *
 * easy
 *
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 *
 *  
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 *
 **/
public class Lc22 {

	public ListNode getKthFromEnd(ListNode head, int k) {
		ListNode p = head;

		while (k > 0 && p != null) {
			k--;
			p = p.next;
		}
		//不够
		if (k > 0) {
			return null;
		}
		if (p == null) {
			return head;
		}
		//长度大于k
		ListNode temp = head;
		while (p != null) {
			temp = temp.next;
			p = p.next;
		}

		return temp;
	}

	public static void main(String[] args) {
		Lc22 lc22 = new Lc22();
		ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

//		ListNode kNode = lc22.getKthFromEnd(l, 3);
//		ListNode kNode = lc22.getKthFromEnd(l, 1);
//		ListNode kNode = lc22.getKthFromEnd(l, 5);
		ListNode kNode = lc22.getKthFromEnd(l, 6);
		if (kNode != null) {
			System.out.println(kNode.val);
		}
	}
}
