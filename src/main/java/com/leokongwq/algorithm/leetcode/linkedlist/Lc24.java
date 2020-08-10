package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 20:00
 *
 * medium
 *
 * 两两交互相邻的两个链表节点，返回结果链表
 *
 * 1->2->3->4  结果  2->1->4->3
 **/
public class Lc24 {

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dump = new ListNode(-1);
		ListNode tail = dump;

		while (head != null && head.next != null) {
			ListNode p = head;
			ListNode q = head.next;

			ListNode temp = q.next;
			q.next = p;
			p.next = null;

			tail.next = q;
			tail = p;

			head = temp;
		}

		if (head != null) {
			tail.next = head;
		}
		return dump.next;
	}

	public static void main(String[] args) {
		Lc24 lc24 = new Lc24();
//		ListNode h1 = new ListNode(1, new ListNode(2));
//		ListNode h1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
//		ListNode h1 = new ListNode(1, new ListNode(2, new ListNode(3)));
		ListNode h1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

		ListNode head = lc24.swapPairs(h1);
		Printer.printList(head);
	}
}
