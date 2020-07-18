package com.leokongwq.algorithm.datastruct;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/18
 * Time: 上午10:52
 * Email:leokongwq@gmail.com
 */
public class MyStack {

    public static void main(String[] args) {
        //后缀表达式
        String exp = "6523+8*+3+*";
        int result = calculatePostfixExp(exp);
        System.out.println("calculate result is : " + result);
    }

    /**
     * 用栈计算后缀表达式的值
     * @param exp
     * @return
     */
    private static int calculatePostfixExp(String exp){
        int n = exp.length();
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < n; i++){
            char c = exp.charAt(i);
            //如果是数字直接入栈
            if (Character.isDigit(c)){
                stack.push(Character.toString(c));
            }else {
                //操作符
                int c1 = Integer.parseInt(stack.pop());
                int c2 = Integer.parseInt(stack.pop());
                int opRet = oprateTwoNum(c1, c2, c);
                stack.push(String.valueOf(opRet));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private static int oprateTwoNum(int n, int m, char op){
        switch (op){
            case '+' :
                return n + m;
            case '-' :
                return n - m;
            case '*' :
                return n * m;
            case '/' :
                return n / m;
            default:
                return 0;
        }
    }
}
