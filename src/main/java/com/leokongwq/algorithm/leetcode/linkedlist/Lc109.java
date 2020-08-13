package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.TreeNode;
import com.leokongwq.algorithm.leetcode.ListNode;

/**
 * @author jiexiu
 * created 2020/8/13 - 23:57
 */
public class Lc109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;

    }


    private ListNode findMiddleElement(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        return slow;
    }
}