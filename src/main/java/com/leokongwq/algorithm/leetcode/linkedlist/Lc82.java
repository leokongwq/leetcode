package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.HashMap;
import java.util.Map;

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

	Map<Integer, Integer> numTimes = new HashMap<>();

	public ListNode deleteDuplicates(ListNode head) {
		if (head == null) {
			return null;
		}

		numTimes.put(head.val, numTimes.getOrDefault(head.val, 0) + 1);

		if (head.next == null) {
		    if (numTimes.get(head.val) > 1) {
		        return head.next;
            } else {
		        return head;
            }
        }
        ListNode node = deleteDuplicates(head.next);
		head.next = node;

		if (numTimes.get(head.val) > 1) {
            return head.next;
        } else {
		    return head;
        }
	}

	public static void main(String[] args) {
		Lc82 lc82 = new Lc82();
		ListNode head = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
		ListNode newHead = lc82.deleteDuplicates(head);

		Printer.printList(newHead);
	}
}