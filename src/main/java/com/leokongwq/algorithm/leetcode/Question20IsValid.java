package com.leokongwq.algorithm.leetcode;

import java.util.Stack;

/**
 * @author jiexiu
 * created 2020/7/18 - 13:36
 * <p>
 * easy
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 */
public class Question20IsValid {

    public boolean isValid(String s) {
        if (s == null || s.length() == 1) {
            return false;
        }
        if ("".equals(s)) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (isRight(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char left = stack.peek();
                if (!isMatch(left, ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    private boolean isMatch(char left, char ch) {
        if (left == '(' && ch == ')') {
            return true;
        }
        if (left == '{' && ch == '}') {
            return true;
        }
        if (left == '[' && ch == ']') {
            return true;
        }
        return false;
    }

    private boolean isRight(char ch) {
        switch (ch) {
            case ')':
                return true;
            case '}':
                return true;
            case ']':
                return true;
            default:
                return false;

        }
    }

    public static void main(String[] args) {
        Question20IsValid isValid = new Question20IsValid();
        System.out.println(isValid.isValid(""));
        System.out.println(isValid.isValid("]"));
        System.out.println(isValid.isValid("["));
        System.out.println(isValid.isValid("){"));

        System.out.println(isValid.isValid("()"));
        System.out.println(isValid.isValid("()[]{}"));
        System.out.println(isValid.isValid("(]"));
        System.out.println(isValid.isValid("([)]"));
        System.out.println(isValid.isValid("{[]}"));
    }
}
