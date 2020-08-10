package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 20:26
 *
 * easy
 *
 * 删除链表中的重复元素
 **/
public class Lc83 {

	public ListNode removeDupNode(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode p = head;
		ListNode q = head.next;
		while (q != null) {
			if (p.val != q.val) {
				p = p.next;
				p.val = q.val;
			}
			q = q.next;
		}
		p.next = null;
		return head;
	}

	public static void main(String[] args) {
		Lc83 lc83 = new Lc83();
		ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
		lc83.removeDupNode(head);

		Printer.printList(head);
	}
}
