package com.leokongwq.algorithm.leetcode;

import com.leokongwq.algorithm.geektime.Printer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/21 - 07:43
 * <p>
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 * <p>
 * 给定数组的长度范围是 
 */
public class Question645FindErrorNums {


    public int[] findErrorNums(int[] nums) {
        if (nums == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(nums).forEach(num -> {
            map.compute(num, (key, oldValue) -> {
                if (oldValue == null) {
                    return 1;
                }
                return oldValue + 1;
            });
        });

        int[] res = new int[2];

        for (int i = 1; i <= nums.length; i++) {
            if (!map.containsKey(i)) {
                res[1] = i;
            } else if (map.get(i) > 1) {
                res[0] = i;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Question645FindErrorNums findErrorNums = new Question645FindErrorNums();

        int[] res = findErrorNums.findErrorNums(new int[]{1, 2, 2, 4});
        Printer.printArray(res);
    }
}
