package com.leokongwq.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiexiu
 * created 2019/1/22 - 17:56
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public class Question448FindAllDisappearedNum {

    private static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int[] book = new int[nums.length + 1];
        for (int i = 0; i < book.length; i++) {
            book[i] = -1;
        }
        for (int num : nums) {
            book[num] = 1;
        }
        for (int i = 1; i < book.length; i++) {
            if (book[i] == -1) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}));
    }
}
