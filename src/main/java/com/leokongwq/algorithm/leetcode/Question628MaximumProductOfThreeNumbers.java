package com.leokongwq.algorithm.leetcode;

import java.util.*;

/**
 * @author jiexiu
 * created 2019/1/25 - 17:14
 *
 * 给定一个整数数组，找到乘积最大的三个数字并输出最大乘积。
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 * Example 2:
 * Input: [1,2,3,4]
 * Output: 24
 *
 * 注意:
 * 数组长度为 [3,10000] 并且所有元素大小为 [-1000, 1000].
 * 3个数的乘积最大不超过 32位有符号整数
 */
public class Question628MaximumProductOfThreeNumbers {

    private static int maximumProduct(int[] nums) {
        Arrays.sort(nums);
//        int maxProduct = nums[0] * nums[1] * nums[2];
//        if (nums.length == 3) {
//            return maxProduct;
//        }
        int len = nums.length;
        int negativeCnt = 0;
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] >= 0) {
                break;
            } else {
                negativeCnt++;
            }
        }
        //
        if (negativeCnt > 1) {
            if ( len - 3 >= i) {
                return Math.max(nums[0] * nums[1] * nums[len-1], nums[len-3] * nums[len-2] * nums[len-1]);
            } else {
                return nums[0] * nums[1] * nums[len-1];
            }
        }
        return nums[len-3] * nums[len-2] * nums[len-1];
    }

    public static void main(String[] args) {
        System.out.println(maximumProduct(new int[]{1,2,3}));
        System.out.println(maximumProduct(new int[]{2,3,4,5}));
        System.out.println(maximumProduct(new int[]{-1,-2,-3}));
        System.out.println(maximumProduct(new int[]{-4,-3,-2,-1,60}));
        // -976, -948, -939, -930, -902, -890, -859, -851, -850, -837, -822, -819, -787, -766, -749, -732, -710, -686, -651, -644, -640,
        // -563, -512, -509, -462, -459, -412, -385, -366, -350, -333, -329, -324, -316, -307, -287, -219, -207, -206, -186, -182, -169,
        // -159, -147, -118, -107, -94, -88, -73, -60, -60, -20, -14, -13, 62, 87, 93, 107, 115, 127, 143, 150, 160, 161, 202, 278,
        // 280, 303, 309, 338, 371, 404, 451, 459, 509, 516, 525, 532, 532, 611, 657, 683, 689, 694, 718, 721, 771, 826, 836, 844,
        // 870, 878, 893, 904, 904, 908, 969, 989, 990, 993
        System.out.println(maximumProduct(new int[]{-710,-107,-851,657,-14,-859,278,-182,-749,718,-640,127,-930,-462,694,969,143,309,904,-651,160,451,-159,-316,844,-60,611,-169,-73,721,-902,338,-20,-890,-819,-644,107,404,150,-219,459,-324,-385,-118,-307,993,202,-147,62,-94,-976,-329,689,870,532,-686,371,-850,-186,87,878,989,-822,-350,-948,-412,161,-88,-509,836,-207,-60,771,516,-287,-366,-512,509,904,-459,683,-563,-766,-837,-333,93,893,303,908,532,-206,990,280,826,-13,115,-732,525,-939,-787}));
    }
}
