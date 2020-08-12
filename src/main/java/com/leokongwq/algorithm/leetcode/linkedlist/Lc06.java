package com.leokongwq.algorithm.leetcode.linkedlist;

import com.leokongwq.algorithm.base.Printer;
import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiexiu
 * created 2020/8/9 - 12:34
 *
 * easy
 *
 * 倒序打印链表
 *
 */
public class Lc06 {
    List<Integer> result = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        tranverse(head, result);
        int[] ans = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }

    private void tranverse(ListNode head, List<Integer> result) {
        if (head == null) {
            return;
        }
        tranverse(head.next, result);
        result.add(head.val);
    }

    public static void main(String[] args) {
        Lc06 lc06 = new Lc06();
        ListNode head = new ListNode(1, new ListNode(3, new ListNode(2)));
        int [] res = lc06.reversePrint(head);
        Printer.printArray(res);
    }
}
