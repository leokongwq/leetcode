package com.leokongwq.algorithm.leetcode;

/**
 * @author : jiexiu
 * @date : 2020-07-13 20:15
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 **/
public class Question83DeleteDuplicates {

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null) {
			if (slow.val != fast.val) {
				slow = slow.next;
				slow.val = fast.val;
			}
			fast = fast.next;
		}
		slow.next = null;
		return head;
	}
}
