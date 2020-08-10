package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 20:37
 *
 * medium
 *
 * 删除链表中的重复元素，只保留原始链表中没有重复的元素
 *
 * 1->2->2->3  结果 1->3
 **/
public class Lc82 {

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
		Lc82 lc82 = new Lc82();
		ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
		lc82.removeDupNode(head);

		Printer.printList(head);
	}
}