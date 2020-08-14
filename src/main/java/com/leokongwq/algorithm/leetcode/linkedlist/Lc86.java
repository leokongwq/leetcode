package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-12 16:31
 *
 * medium
 *
 * 分割链表：
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 **/
public class Lc86 {

	public ListNode partition(ListNode head, int x) {

		ListNode beforeHead = new ListNode(Integer.MIN_VALUE);
		ListNode before = beforeHead;

		ListNode afterHead = new ListNode(Integer.MIN_VALUE);
		ListNode after = afterHead;

		while (head != null) {
			if (head.val < x) {
				before.next = head;
				before = before.next;
			} else {
				after.next = head;
				after = after.next;
			}
			head = head.next;
		}
		//否则，有环，输出时，死循环。
		after.next = null;
		before.next = afterHead.next;
		return beforeHead.next;
	}

	public static void main(String[] args) {
		ListNode head = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2))))));

		Lc86 lc86 = new Lc86();
		ListNode node = lc86.partition(head, 3);
		Printer.printList(node);
	}
}
