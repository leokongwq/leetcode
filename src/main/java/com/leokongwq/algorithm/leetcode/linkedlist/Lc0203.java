package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.List;

/**
 * @author : jiexiu
 * @date : 2020-08-15 18:14
 *
 * easy
 *
 * 实现一种算法，删除单向链表中间的某个节点（即不是第一个或最后一个节点），假定你只能访问该节点。
 *
 * 示例：
 *
 * 输入：单向链表 a->b->c->d->e->f 中的节点c
 * 结果：不返回任何数据，但该链表变为a->b->d->e->f
 *
 **/
public class Lc0203 {

	public void deleteNode(ListNode node) {
		if (node == null) {
			return;
		}
		ListNode pre = null;
		ListNode p = node;
		while (p.next != null) {
			p.val = p.next.val;
			pre = p;
			p = p.next;
		}
		pre.next = null;
	}

	public static void main(String[] args) {
		Lc0203 lc0203 = new Lc0203();
		ListNode head = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3, new ListNode(4));
		head.next = node2;
		node2.next = node3;

		Printer.printList(head);
		lc0203.deleteNode(node2);
		Printer.printList(head);
	}
}
