package com.leokongwq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2018/12/13 - 23:01
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as,
 * XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
 * Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 */
public class Question13RomanToInteger {

    private static Map<String, Integer> charToInteger = new HashMap<>();

    static {
        charToInteger.put("I", 1);
        charToInteger.put("V", 5);
        charToInteger.put("X", 10);
        charToInteger.put("L", 50);
        charToInteger.put("C", 100);
        charToInteger.put("D", 500);
        charToInteger.put("M", 1000);

        charToInteger.put("IV", 4);
        charToInteger.put("IX", 9);
        charToInteger.put("XL", 40);
        charToInteger.put("XC", 90);
        charToInteger.put("CD", 400);
        charToInteger.put("CM", 900);
    }

    private static String intToRoman(int num) {
        String str = "";
        String[] symbol = { "M", "CM", "D", "CD", "C",
                "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] value = { 1000, 900, 500, 400, 100,
                90, 50, 40, 10, 9, 5, 4, 1 };
        for (int i = 0; num != 0; i++) {
            while (num >= value[i]) {
                num -= value[i];
                str += symbol[i];
            }
        }
        return str;
    }

    /**
     * 罗马字符转为数字
     * 把罗马数字转化数字
     *
     *  基本字符：
     *  I、V、X、L、C、D、M
     *  相应的阿拉伯数字表示为：
     *  1、5、10、50、100、500、1000
     *  (1)相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
     *  (2)小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
     *  (3)小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9
     */
    private static int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int value = charToInteger.get(String.valueOf(s.charAt(0)));

        for (int i = 1; i < s.length(); i++) {
            if (charToInteger.get(String.valueOf(s.charAt(i))) > charToInteger.get(String.valueOf(s.charAt(i - 1)))) {
                value = value + charToInteger.get(String.valueOf(s.charAt(i)))
                        - 2 * charToInteger.get(String.valueOf(s.charAt(i - 1)));
            } else {
                value = value + charToInteger.get(String.valueOf(s.charAt(i)));
            }
        }
        return value;
    }

    /**
     * 1、相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
     * 2、小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
     * 3、小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
     * 4、正常使用时，连写的数字重复不得超过三次。（表盘上的四点钟“IIII”例外）
     * 5、在一个数的上面画一条横线，表示这个数扩大1000倍。
     *
     * 有几条须注意掌握：
     * 1、基本数字Ⅰ、X 、C 中的任何一个，自身连用构成数目，或者放在大数的右边连用构成数目，都不能超过三个；放在大数的左边只能用一个。
     * 2、不能把基本数字V 、L 、D 中的任何一个作为小数放在大数的左边采用相减的方法构成数目；放在大数的右边采用相加的方式构成数目，只能使用一个。
     * 3、V 和X 左边的小数字只能用Ⅰ。
     * 4、L 和C 左边的小数字只能用X。
     * 5、D 和M 左边的小数字只能用C。
     */
    private static int romanToIntV1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int value = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i == s.length() - 1 || charToInteger.get(String.valueOf(s.charAt(i))) >= charToInteger.get(String.valueOf(s.charAt(i + 1)))) {
                value = value + charToInteger.get(String.valueOf(s.charAt(i)));
            } else {
                value = value - charToInteger.get(String.valueOf(s.charAt(i)));
            }
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(romanToIntV1("XII"));
        System.out.println(romanToIntV1("XXVII"));
        //1994
        System.out.println(romanToIntV1("MCMXCIV"));
    }
}
