package com.leokongwq.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author jiexiu
 * created 2018/12/18 - 22:37
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 */
public class Question20_ValidParentheses {

    private static Map<String, String> openCloseMap = new HashMap<>();

    private static Map<String, String> closeOpenMap = new HashMap<>();


    static {
        openCloseMap.put("(", ")");
        openCloseMap.put("[", "]");
        openCloseMap.put("{", "}");

        closeOpenMap.put(")", "(");
        closeOpenMap.put("]", "[");
        closeOpenMap.put("}", "{");
    }


    private static boolean isValid(String s) {
        if (s == null || s.trim().length() == 1) {
            return false;
        }

        if (s.trim().length() == 0) {
            return true;
        }

        if (s.trim().length() % 2 != 0) {
            return false;
        }

        //{}[{}]((){})(){}
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i < s.length()) {
            //左符号
            String currentChar = String.valueOf(s.charAt(i));
            if (openCloseMap.containsKey(currentChar)) {
                stack.push(currentChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String left = stack.pop();
                if (!left.equals(closeOpenMap.get(currentChar))) {
                    return false;
                }
            }
            i++;
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("([)]"));
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([)"));
        System.out.println(isValid("(([]){})"));
        System.out.println(isValid("{}{}()[]"));
        System.out.println(isValid("{}[{}]((){})(){}"));
    }
}
