package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/12 - 08:55
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 */
public class Question213HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int first = robRange(nums, 0, nums.length - 2);
        int second = robRange(nums, 1, nums.length - 1);

        return Math.max(first, second);
    }

    private int robRange(int[] nums, int start, int end) {
        int dp_i_1 = 0;
        int dp_i_2 = 0;
        int dp_i = 0;

        for (int i = end; i >= start; i--) {
            dp_i = Math.max(nums[i] + dp_i_2, dp_i);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    public static void main(String[] args) {
        Question213HouseRobber houseRobber = new Question213HouseRobber();

        System.out.println(houseRobber.rob(new int[]{2,3,2}));

        System.out.println(houseRobber.rob(new int[]{1,2,3,1}));
    }
}
