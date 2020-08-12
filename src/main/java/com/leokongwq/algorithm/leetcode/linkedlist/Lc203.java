package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author jiexiu
 * created 2020/8/10 - 23:30
 *
 * easy
 *
 * 移除链表元素
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Lc203 {

    public ListNode removeElements(ListNode head, int val) {
        ListNode p = head;

        while (p != null) {
            if (p.val == val) {
                ListNode q = p.next;
                p.next = null;
                p = q;
                head = p;
            } else {
                if (p.next == null) {
                    return head;
                } else {
                    if (p.next.val == val) {
                        ListNode temp = p.next.next;
                        p.next.next = null;
                        p.next = temp;
                    } else {
                        p = p.next;
                    }
                }
            }
        }

        return head;
    }

    public ListNode removeElementsV1(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        Lc203 lc203 = new Lc203();
        ListNode head = new ListNode(2, new ListNode(1, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
        ListNode h = lc203.removeElements(head, 6);
        Printer.printList(h);
    }
}
