package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author : jiexiu
 * @date : 2020-08-16 00:55
 *
 * easy
 *
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 *
 * 请你返回该链表所表示数字的 十进制值 。
 *
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 *
 **/
public class Lc1290 {

	int k = 0;

	public int getDecimalValue(ListNode head) {
		if (head == null) {
			return 0;
		}
		int res = getDecimalValue(head.next);
		res = res + (int)(head.val * Math.pow(2, k));
		k++;
		return res;
	}

	public static void main(String[] args) {
		Lc1290 lc1290 = new Lc1290();
		ListNode head = new ListNode(1, new ListNode(0, new ListNode(1)));

		System.out.println(lc1290.getDecimalValue(head));
	}
}
