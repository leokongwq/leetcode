package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-11 09:50
 *
 * hard
 *
 * k 个一组翻转链表
 **/
public class Lc25 {

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k <= 0) {
			return head;
		}
		if (k == 1) {
			return rangeReverse(head, null);
		}
		ListNode p = head;
		int n = 0;
		while (p != null && n < k) {
			n++;
			p = p.next;
		}
		if (n < k) {
			return head;
		}
		ListNode newHead = rangeReverse(head, p);
		head.next = reverseKGroup(p, k);
		return newHead;
	}

	private ListNode rangeReverse(ListNode start, ListNode end) {
		ListNode pre = null;
		ListNode cur = start;
		while (cur != end) {
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		Lc25 lc25 = new Lc25();
		ListNode l = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

		ListNode head = lc25.reverseKGroup(l, 1);
//		ListNode head = lc25.reverse(l, 2);
//		ListNode head = lc25.reverse(l, 3);
		Printer.printList(head);
	}
}
