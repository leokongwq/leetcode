package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2018/12/14 - 09:22
 *
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class Question14IntegerToRoman {

    /**
     * 例如整数 1437 的罗马数字为 MCDXXXVII， 我们不难发现，千位，百位，十位和个位上的数分别用罗马数字表示了。
     * 1000 - M, 400 - CD, 30 - XXX, 7 - VII。所以我们要做的就是用取商法分别提取各个位上的数字，然后分别表示出来：
     * 100 - C
     * 200 - CC
     * 300 - CCC
     * 400 - CD
     * 500 - D
     * 600 - DC
     * 700 - DCC
     * 800 - DCCC
     * 900 - CM
     */
    private static String intToRoman(int num) {
        String res = "";
        char roman[] = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
        int value[] = {1000, 500, 100, 50, 10, 5, 1};

        for (int n = 0; n < 7; n += 2) {
            int x = num / value[n];
            if (x < 4) {
                for (int i = 1; i <= x; ++i) {
                    res += roman[n];
                }
            } else if (x == 4) {
                res = res + roman[n] + roman[n - 1];
            }
            else if (x < 9) {
                res += roman[n - 1];
                for (int i = 6; i <= x; ++i) {
                    res += roman[n];
                }
            }
            else if (x == 9) {
                res = res + roman[n] + roman[n - 2];
            }
            num %= value[n];
        }
        return res;
    }

    /**
     * 本题由于限制了输入数字范围这一特殊性，故而还有一种利用贪婪算法的解法，
     * 建立一个数表，每次通过查表找出当前最大的数，减去再继续查表。参见代码如下：
     */
    private static String intToRomanV2(int num) {
        String res = "";
        int[] val = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < val.length; ++i) {
            while (num >= val[i]) {
                num -= val[i];
                res += str[i];
            }
        }
        return res;
    }

    /**
     * 暴力查表
     */
    private static String intToRomanV3(int num) {
        String res = "";
        String[] v1 = {"", "M", "MM", "MMM"};
        String[] v2 = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] v3 = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] v4 = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return v1[num / 1000] + v2[(num % 1000) / 100] + v3[(num % 100) / 10] + v4[num % 10];
    }
}
