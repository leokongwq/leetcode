package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-16 18:14
 *
 * medium
 *
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 *
 *  
 *
 * 插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 *
 * 示例 1：
 *
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 *
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 **/
public class Lc147 {

	public ListNode insertionSortList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(Integer.MIN_VALUE);
		dummy.next = head;

		ListNode pre = head;
		ListNode cur = head.next;
		while (cur != null) {
			if (cur.val < pre.val) {
				pre.next = null;
				ListNode next = cur.next;
				cur.next = null;
				//cur 插入排序链表中
				ListNode p = dummy;
				ListNode q = dummy.next;

				while (q.val <= cur.val) {
					q = q.next;
					p = p.next;
				}
				cur.next = q;
				p.next = cur;

				cur = next;
			} else {
				pre.next = cur;
				pre = cur;
				cur = cur.next;
			}
		}
		return dummy.next;
	}

	public static void main(String[] args) {
		Lc147 lc147 = new Lc147();
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(-3, new ListNode(-2)))));
//		ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));
//		ListNode head = new ListNode(3, new ListNode(2, new ListNode(1)));

//		ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));

		ListNode head = new ListNode(3, new ListNode(2, new ListNode(4)));


		ListNode h = lc147.insertionSortList(head);

		Printer.printList(h);
	}
}
