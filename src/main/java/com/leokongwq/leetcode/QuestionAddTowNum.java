package com.leokongwq.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午3:1
 */
public class QuestionAddTowNum {

    static class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 添加下一个节点
     *
     * @param listNode
     * @return
     */
    public static ListNode addNext(ListNode current, ListNode listNode) {
        ListNode lastNode = current;
        while (true) {
            if (lastNode.next == null) {
                break;
            }
            lastNode = lastNode.next;
        }
        lastNode.next = listNode;
        return current;
    }

    public static ListNode arrayToListNode(int[] arr) {
        ListNode listNode = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (i == 0 && arr[i] == 0){
                continue;
            }
            if (listNode == null) {
                listNode = new ListNode(arr[i]);
            } else {
                addNext(listNode, new ListNode(arr[i]));
            }
        }
        return listNode;
    }

    public static int[] stackToIntArray(Stack<Integer> integerStack) {
        int[] arr = new int[integerStack.size() + 1];
        int i = 1;
        while (!integerStack.empty()) {
            arr[i++] = integerStack.pop();
        }
        for (int j = arr.length - 1; j > 0; j--) {
            if (arr[j] >= 10) {
                int adder = arr[j] / 10;
                arr[j] = arr[j] % 10;
                arr[j - 1] = arr[j - 1] + adder;
            }
        }
        return arr;
    }

    public static Stack<Integer> convertToStack(ListNode listNode) {
        //转为顺序存储的数组, 高位从下标0开始
        int[] arr = listNodeToIntArray(listNode);
        Stack<Integer> stack = new Stack<Integer>();
        for (int num : arr){
            stack.push(num);
        }
        return stack;
    }

    public static int[] listNodeToIntArray(ListNode listNode){
        LinkedList<Integer> integers = new LinkedList<Integer>();
        ListNode lastNode = listNode;
        while (true) {
            if (lastNode == null) {
                break;
            }
            integers.addFirst(lastNode.val);
            lastNode = lastNode.next;
        }
        int size = integers.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++){
            result[i] = integers.removeFirst();
        }
        return result;
    }

    /**
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 0 -> 8
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = convertToStack(l1);
        Stack<Integer> stack2 = convertToStack(l2);

        Stack<Integer> result = new Stack<Integer>();
        //尾数出栈相加
        while (!stack1.empty() && !stack2.empty()) {
            result.push(stack1.pop() + stack2.pop());
        }
        while (!stack1.empty()) {
            result.push(stack1.pop());
        }
        while (!stack2.empty()) {
            result.push(stack2.pop());
        }
        //将相加的结果数组, 进行进位, 并存入数组
        int[] arr = stackToIntArray(result);
        return arrayToListNode(arr);
    }

    public static void printListNode(ListNode listNode){
        ListNode last = listNode;
        while (last != null){
            System.out.printf("%d,", last.val);
            last = last.next;
        }
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(8));
        ListNode listNode2 = new ListNode(0);
        ListNode res = addTwoNumbers(listNode1, listNode2);
        printListNode(res);
    }

}
