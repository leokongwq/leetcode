package com.meiliinc.mls.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/26
 * Time: 下午2:12
 * Email:jiexiu@mogujie.com
 */
public class PlusOne {

    /**
     * 给定一个用int数组表示的非负整数, 给给整数加一; 返回结果数组。
     * 数组的低位下标表示数字的高位
     * @param arr
     * @param n
     * @return
     */
    public static int[] plusOne(int[] arr, int n){
        int carry = 0;
        //最后一位+1
        arr[n - 1] += 1;
        for (int i = n -1; i > 0; i--){
            carry = arr[i] / 10;
            int num = arr[i] % 10;
            if (carry == 0){
                return arr;
            }
            arr[i] = num;
            arr[i - 1] += carry;
        }
        if (arr[0] < 10){
            return arr;
        }
        //处理最高位
        int[] result = new int[n + 1];
        for (int i = 0; i < n; i++){
            result[i+1] = arr[i];
        }
        result[0] = result[1] / 10;
        result[1] = result[1] % 10;
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,9,9,9};
        int[] result = plusOne(arr, arr.length);
        System.out.println(result);
    }
}
