package com.leokongwq.leetcode;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2019/1/25 - 15:32
 *
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not. However,
 * flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 *
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
 * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 *
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 *
 * 注意:
 * 输入的数组也是满足规则的
 * 数组大小为 [1, 20000].
 * n 是正整数 并且不会超过数组大小
 */
public class Question605CanPlaceFlowers {

    private static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null) {
            return false;
        }
        int cnt = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            if (i == 0) {
                //[0]
                if (flowerbed.length == 1) {
                    cnt++;
                    flowerbed[i] = 1;
                    break;
                }
                //[0,0]
                if (flowerbed[i+1] == 0) {
                    flowerbed[i] = 1;
                    cnt++;
                }
            } else if (i < flowerbed.length - 1) {
                //range
                if (flowerbed[i-1] == 0 && flowerbed[i+1] == 0) {
                    flowerbed[i] = 1;
                    cnt++;
                }
            } else {
                //last
                if (flowerbed[i-1] == 0) {
                    flowerbed[i] = 1;
                    cnt++;
                }
            }
        }
        return n <= cnt;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        System.out.println(canPlaceFlowers(new int[]{1,0,0,0,1}, 2));

        System.out.println(canPlaceFlowers(new int[]{0,1,0}, 1));
        System.out.println(canPlaceFlowers(new int[]{0,0,1,0,1}, 1));

        System.out.println(canPlaceFlowers(new int[]{0,1,0,1,0,1,0,0}, 1));

    }
}
