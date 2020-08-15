package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-15 23:42
 *
 * easy
 **/
public class Lc18 {

	public ListNode deleteNode(ListNode head, int val) {
		if (head == null) {
			return null;
		}

		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;

		ListNode pre = dummy;
		ListNode p = dummy.next;

		while (p != null && p.val != val) {
			p = p.next;
			pre = pre.next;
		}
		//不存在
		if (p == null) {
			return null;
		}
		pre.next = p.next;
		p.next = null;

		return dummy.next;
	}

	public static void main(String[] args) {
		Lc18 lc18 = new Lc18();
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));

//		ListNode h = lc18.deleteNode(head, 1);
//		ListNode h = lc18.deleteNode(head, 2);
		ListNode h = lc18.deleteNode(head, 3);

		Printer.printList(h);
	}
}
