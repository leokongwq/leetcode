package com.meiliinc.mls.leetcode;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/17
 * Time: 下午12:17
 * Email:jiexiu@mogujie.com
 */
public class BigNumMutil {

    public static String bigMutil(long numA, long numB){
        String aStr = String.valueOf(numA);
        String bStr = String.valueOf(numB);
        int[] leftArr = toIntArray(aStr);
        int[] rightArr = toIntArray(bStr);
        int tmpLength = leftArr.length + rightArr.length - 1;
        int[] result = new int[tmpLength];

        for (int i = 0; i < leftArr.length; i++){
            for (int j = 0; j < rightArr.length; j++){
                result[i + j] += leftArr[i] * rightArr[j];
            }
        }
        //进位
        for (int i = result.length - 1; i >= 0; i--){
            if (result[i] >= 10 && (i - 1) >= 0){
                result[i - 1] = result[i - 1] + result[i] / 10;
                result[i] = result[i] % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmpLength; i++){
            sb.append(result[i]);
        }
        return sb.toString();
    }

    public static int[] toIntArray(String str){
        int len = str.length();
        int[] result = new int[len];
        for (int i = 0; i < len; i++){
            result[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return result;
    }

    public static Stack<Integer> toBinaryBitArray(int num, int d){
        Stack<Integer> stack = new Stack<Integer>();
        while (num != 0){
            int i = num % d;
            num = num / d;
            stack.push(i);
        }
        return stack;
    }

    public static void main(String[] args) {
        System.out.println(bigMutil(12, 34));
        Stack<Integer> stack = toBinaryBitArray(18, 16);
        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
        System.out.println();
    }
}
