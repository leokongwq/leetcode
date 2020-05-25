package com.leokongwq.algorithm.recursive;

import java.util.Stack;

/**
 * @author jiexiu
 * created 2020/5/22 - 00:11
 */
public class ReverseStack {

    static Integer getAndDeleteLast(Stack<Integer> stack) {
        Integer value = stack.pop();
        if (stack.isEmpty()) {
            return value;
        }
        Integer last = getAndDeleteLast(stack);
        //按原有顺序入栈
        stack.push(value);
        return last;
    }

    static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Integer last = getAndDeleteLast(stack);
        reverse(stack);
        stack.push(last);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverse(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
