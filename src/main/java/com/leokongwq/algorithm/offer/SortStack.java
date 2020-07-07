package com.leokongwq.algorithm.offer;

import java.util.Stack;

/**
 * @author jiexiu
 * created 2020/5/31 - 22:21
 */
public class SortStack {

    /**
     * 将参数stack指定的栈按从顶到底从大到小进行排序， 只能用另一个栈帮助排序
     * 也可以申请变量
     */
    private static Stack<Integer> sortStack(Stack<Integer> stack) {
        if (stack == null || stack.size() == 0) {
            return stack;
        }

        Stack<Integer> helper = new Stack<>();

        while (!stack.isEmpty()) {
            Integer temp = stack.pop();
            while (!helper.isEmpty() && helper.peek() < temp) {
                stack.push(helper.pop());
            }
            helper.push(temp);
        }
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
        return stack;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        sortStack(stack);

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }
}
