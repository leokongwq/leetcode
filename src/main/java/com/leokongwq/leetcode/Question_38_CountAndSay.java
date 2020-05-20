package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2018/12/21 - 08:54
 * <p>
 * The count-and-say sequence is the sequence of integers with the first five terms as following:
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * <p>
 * Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 * <p>
 * Note: Each term of the sequence of integers will be represented as a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 1
 * Output: "1"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "1211"
 */
public class Question_38_CountAndSay {

    /**
     * 该为题的求解，很容易想到用递归解决。但前提是必须理解题目的意思。
     * 当 n >= 2时， 数组的字符串格式是： 前面字符串中每个数字连续出现的次数和该数字:
     * n = 2, 前面的字符串是 "1", 输出 count:数字 => 1个1 => 11
     * n = 3, 前面的字符串是 "11", 输出 count:数字 => 2个1 => 21
     * n = 4, 前面的字符串是 "21", 输出 count:数字 => 1个2  1个1 => 1211
     * n = 5, 前面的字符串是 "1211", 输出 count:数字 => 1个1 1个2 2个1 => 111221
     */
    private static String countAndSay(int n) {
        //递归结束条件
        if (n == 1) {
            return "1";
        }
        // 添加一个 `*` 方便编码处理
        String str = countAndSay(n - 1) + "*";
        //计数器，保存连续出现的个数
        int count = 1;
        //保存本次的结果字符串
        String s = "";
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                s = s + count + String.valueOf(str.charAt(i));
                //下一个 第一次出现的字符个数， 是1
                count = 1;
            }
        }
        return s;
    }

    /**
     * 非递归方式
     */
    public String countAndSayV1(int n) {
        if (n <= 1) {
            return "1";
        }
        //initialize result string
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            char[] chars = str.toCharArray();
            int count = 1;
            for (int j = 1; j < chars.length; j++) {
                if (chars[j] == chars[j - 1]) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(chars[j - 1]);
                    count = 1;
                }
            }

            //don't forget the last character
            sb.append(count);
            sb.append(chars[chars.length - 1]);
            str = sb.toString();
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
    }
}
