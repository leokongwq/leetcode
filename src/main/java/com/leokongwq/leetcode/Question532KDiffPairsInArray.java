package com.leokongwq.leetcode;

import java.util.*;

/**
 * @author jiexiu
 * created 2019/1/22 - 18:48
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array.
 * Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array
 * and their absolute difference is k.
 * <p>
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 * <p>
 * Example 2:
 * Input:[1, 2, 3, 4, 5], k = 1
 * Output: 4
 * Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * <p>
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0
 * Output: 1
 * Explanation: There is one 0-diff pair in the array, (1, 1).
 * <p>
 * Note:
 * The pairs (i, j) and (j, i) count as the same pair.
 * The length of the array won't exceed 10,000.
 * All the integers in the given input belong to the range: [-1e7, 1e7].
 * <p>
 * 题目：查找数组中
 * <p>
 * 注意：
 * 1. 数对 (i, j) 和 (j, i) 可以当做相同
 * 2. 数组长度不超过10000
 * 3. 所有数组元素 位于 [-1e7, 1e7]
 */
public class Question532KDiffPairsInArray {

    /**
     * 该提可以认为是 tow-sum 的变种问题
     * 1. 遍历数组，将每个元素和 k 计算 差值 和 和 ，结果已经在遍历过的元素中出现，则进行统计。
     * 2. 因为 (i, j) 和 (j, i) 只能计数一次，所有将满足条件的 Pari统一处理为升序对，
     * 3. 通过Hash的去重能力，获取最终的结果
     */
    private static int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Pair> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int sub = nums[i] - k;
            int sum = nums[i] + k;
            if (map.containsKey(sub)) {
                set.add(new Pair(nums[map.get(sub)], nums[i]));
            }
            if (map.containsKey(sum)) {
                set.add(new Pair(nums[map.get(sum)], nums[i]));
            }
            map.put(nums[i], i);
        }
        return set.size();
    }

    private static class Pair {
        private int start;
        private int end;

        public Pair(int start, int end) {
            if (start > end) {
                this.start = end;
                this.end = start;
            } else {
                this.start = start;
                this.end = end;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Pair pair = (Pair) o;
            return start == pair.start && end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    public static void main(String[] args) {
//        System.out.println(findPairs(new int[]{3, 1, 4, 1, 5}, 2));
//        System.out.println(findPairs(new int[]{1, 2, 3, 4, 5}, 1));
//        System.out.println(findPairs(new int[]{1, 3, 1, 5, 4}, 0));

        System.out.println(findPairs(new int[]{1,1,1,2,1}, 1));
    }
}
