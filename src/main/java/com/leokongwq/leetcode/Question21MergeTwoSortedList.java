package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2018/12/19 - 09:57
 * <p>
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together
 * the nodes of the first two lists.
 * <p>
 * Example:
 * <p>
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Question21MergeTwoSortedList {

    private static class ListNode {

        private int val;

        private ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode node1 = l1, node2 = l2;

        ListNode head = null, tail = null;
        while (node1 != null && node2 != null) {
            ListNode min;
            if (node1.val <= node2.val) {
                min = node1;
                node1 = node1.next;
            } else {
                min = node2;
                node2 = node2.next;
            }
            if (head == null) {
                head = new ListNode(min.val);
                tail = head;
            } else {
                ListNode newNode = new ListNode(min.val);
                tail.next = newNode;
                tail = newNode;
            }
        }
        if (node1 != null) {
            tail.next = node1;
        }
        if (node2 != null) {
            tail.next = node2;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}
