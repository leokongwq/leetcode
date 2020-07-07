package com.leokongwq.algorithm.leetcode;

import java.util.*;

/**
 *
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/9/12
 * Time: 下午2:43
 * Email:leokongwq@gmail.com
 */
public class ThreadNumSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2,-3, -9, 4, 5};
        List<Set<Integer>> result = threeSum(arr);
        System.out.println(result);
    }

    /**
     * 给定一个整数数组S,找出数组S中所有 a + b + c = 0 的组合, a, b, c  是顺序无关的。
     * @param num
     * @return
     */
    public static List<Set<Integer>> threeSum(int[] num){
        if(num == null || num.length <= 2){
            return null;
        }
        List<Set<Integer>> result = new ArrayList<Set<Integer>>();
        //sort
        Arrays.sort(num);

        for(int i = 0; i < num.length - 2; i++){
            int j = i + 1;
            int k = num.length - 1;
            while(j < k){
                int res = num[i] + num[j] + num[k];
                if(res == 0){
                    Set<Integer> set = new TreeSet<Integer>();
                    set.add(i);
                    set.add(j);
                    set.add(k);
                    result.add(set);
                    break;
                } else if (res < 0){
                    ++j;
                }else {
                    --k;
                }
            }
        }
        return result;
    }

    /**
     * 给定一个整数数组, 找出该数组中a , b, c 三个数相加的和 距离目标值: garget 最近的和
     * 注意点:
     * 1: 2个数的最近距离是0
     * 2: 需要对数组进行排序
     * @param arr 整数数组
     * @param target 目标值
     * @return int a + b + c 的和
     */
    public static int threeNumSumCloset(int[] arr, int target){
        if(arr == null || arr.length <= 2){
            return -1;
        }
        //排序
        Arrays.sort(arr);
        //假设最小距离为: Integer.MAX_VALUE
        int dis = Integer.MAX_VALUE;
        int ret = 0;
        for(int i = 0; i < arr.length - 2; i++){
            int j = i + 1;
            int k = arr.length - 1;
            int tmpDis = 0;
            while(j < k) {
                int sum = arr[i] + arr[j] + arr[k];
                if (sum < target) {
                    tmpDis = target - sum;
                    if (tmpDis < dis){
                        dis = tmpDis;
                        ++j;
                        ret = sum;
                    }
                } else if (sum > target){
                    tmpDis = sum - target;
                    if (tmpDis < dis){
                        dis = tmpDis;
                        ++j;
                        ret = sum;
                    }
                }else {
                    return sum;
                }
            }
        }
        return ret;
    }
}


