package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-14 16:09
 *
 * medium
 *
 * 翻转链表 1 <= m <= n <= len
 **/
public class Lc92 {

	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || head.next == null || m == n) {
			return head;
		}

		ListNode dump = new ListNode(Integer.MIN_VALUE);
		dump.next = head;

		ListNode pre = dump;
		int c = m;
		while (c > 1) {
			c--;
			pre = pre.next;
		}
		ListNode p = pre.next;
		ListNode q = p;

		int step = n - m + 1;

		while (step > 0) {
			step--;
			q = q.next;
		}

		Lc25 lc25 = new Lc25();
		ListNode newHead = lc25.rangeReverse(p, q);
		pre.next = newHead;
		p.next = q;

		return dump.next;
	}

	public static void main(String[] args) {
		Lc92 lc92 = new Lc92();
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));

//		ListNode h = lc92.reverseBetween(head, 2, 3);
//		ListNode h = lc92.reverseBetween(head, 1, 1);
//		ListNode h = lc92.reverseBetween(head, 1, 2);
		ListNode h = lc92.reverseBetween(head, 4, 5);
		Printer.printList(h);
	}
}
