package com.meiliinc.mls.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组arr 和 target ; 查找arr中两个数相加的和 == target  的 下标 index1  和 index2;
 * 邀请index1 和 index2 不能从0开始
 *
 * 分析:
 * 1: 不要使用双重for循环实现, 这个不是面试官想要的答案。应该时间复杂度为O(n2);
 * 2: 如果我们使用O(n)的时间复杂度来实现, 则需要牺牲空间复杂度。时间, 空间通常不可兼得。
 * 方法:
 * 1.遍历数组,将每个元素和元素的下标放入HashMap
 * 2. 遍历数组, 判断 target - arr[i] 是否在HashMap中( 注意下标不能相同 2 = 1 + 1, 你懂的);
 * 3. 优化点: 其实在第一次遍历数组建立HashMap的过程中, 可以同时进行第一步中的判断。这样我们确实只遍历了一遍数组。
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/28
 * Time: 下午12:52
 * Email:jiexiu@mogujie.com
 */
public class FindTwoNumAddToTargetInArray {

    /**
     *
     * @param arr
     * @param target
     * @return
     */
    public static int[] twoSum(int[] arr, int target){

        Map<Integer, Integer> numIndexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++){
            if (!numIndexMap.containsKey(arr[i])){
                numIndexMap.put(arr[i], i);
                //b = target - a
                if (numIndexMap.containsKey(target - arr[i]) && numIndexMap.get(target - arr[i]).intValue() != i){
                    //第一个数的下标
                    int index1 = numIndexMap.get(target - arr[i]).intValue();
                    return new int[]{index1 + 1, i+1};
                }
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2,7,11,5};
        int[] ret = twoSum(arr, 9);
        System.out.println(ret);
    }
}
