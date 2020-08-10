package com.leokongwq.algorithm.base;

import com.leokongwq.algorithm.leetcode.ListNode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2020/5/24 - 09:28
 */
public class Printer {

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println();
    }
}
