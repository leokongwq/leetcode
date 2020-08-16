package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-10 16:57
 *
 * medium
 *
 * 删除链表倒数第k个节点
 **/
public class Lc19 {

	public ListNode removeKthNode(ListNode head, int n) {
		ListNode slow = head;
		ListNode fast = head;
		int m = 0;
		while (fast != null && m < n) {
			m++;
			fast = fast.next;
		}
		if (fast == null) {
			//不够
			if (m < n) {
				return head;
			}
			//删除头节点
			if (m == n) {
				ListNode p = head.next;
				head.next = null;
				return p;
			}
		} else {
			while (fast.next != null) {
				fast = fast.next;
				slow = slow.next;
			}
			if (n == 1) {
				slow.next = null;
				return head;
			}
			ListNode del = slow.next;
			slow.next = del.next;
			del.next = null;
		}
		return head;
	}

	public static void main(String[] args) {
		Lc19 lc19 = new Lc19();
		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode newHead = lc19.removeKthNode(head, 1);
		ListNode newHead = lc19.removeKthNode(head, 2);
//		ListNode newHead = lc19.removeKthNode(head, 3);
		Printer.printList(newHead);
	}
}
