package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author jiexiu
 * created 2020/8/12 - 09:45
 *
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 *
 *提示：
 *
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 */
public class Lc237 {
    public void deleteNode(ListNode node) {
        ListNode pre = null;
        ListNode p = node;
        while (p.next != null) {
            p.val = p.next.val;
            if (pre == null) {
                pre = p;
            }
            pre = p;
            p = p.next;
        }
        pre.next = null;
    }

    public static void main(String[] args) {
        Lc237 lc237 = new Lc237();

        ListNode list = new ListNode(4);
        ListNode node = new ListNode(5, new ListNode(1, new ListNode(9)));
        list.next = node;
        lc237.deleteNode(node);

        Printer.printList(list);
    }
}
