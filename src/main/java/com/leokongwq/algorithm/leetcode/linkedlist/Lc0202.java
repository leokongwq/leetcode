package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author jiexiu
 * created 2020/8/12 - 08:11
 *
 * 返回倒数第 k 个节点
 *
 */
public class Lc0202 {

    public int kthToLast(ListNode head, int k) {
        if (head == null) {
            return 0;
        }
        ListNode pre = head;
        ListNode cur = head;

        while (cur != null && k > 0) {
            k--;
            cur = cur.next;
        }

        if (k > 0) {
            return 0;
        }

        while (cur != null) {
            cur = cur.next;
            pre = pre.next;
        }
        return pre.val;
    }

    public static void main(String[] args) {
        Lc0202 lc0202 = new Lc0202();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3)));

        System.out.println(lc0202.kthToLast(head, 1));
        System.out.println(lc0202.kthToLast(head, 2));
        System.out.println(lc0202.kthToLast(head, 3));
        System.out.println(lc0202.kthToLast(head, 4));
    }
}
