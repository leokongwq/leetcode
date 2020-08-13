package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author jiexiu
 * created 2020/8/13 - 23:02
 *
 * mediaum
 *
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 *
 * 示例:
 *
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 *
 */
public class Lc0204 {

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
        before.next = afterHead.next;
        after.next = null;
        return beforeHead.next;
    }
}
