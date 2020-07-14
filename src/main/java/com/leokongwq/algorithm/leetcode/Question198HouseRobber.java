package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/11 - 22:36
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 */
public class Question198HouseRobber {

    /**
     * 回溯, 可以通过备忘录优化
     */
    public int rob(int[] nums) {
        return internalRob(nums, 0);
    }

    private int internalRob(int[] nums, int i) {
        if (i == nums.length) {
            return 0;
        }

        //每个房子有2中选择，抢 / 不抢，会分为2个决策树，取其结果的最大值即可
        return Math.max(
                //不抢
                internalRob(nums, i + 1),
                //抢，去下下家
                nums[i] + internalRob(nums, i + 2)
        );
    }

    /**
     * 动态规划
     * dp[i] 表示第i间屋子过后偷窃的最大总金额
     *
     * dp[0] = nums[0]
     * dp[1] = Math.max（nums[0], nums[1]）
     * dp[i] = Math.max（nums[i] + dp[i - 2], dp[i - 1]）
     *
     */
    public int robV1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int[] dp = new int[length];
        //基础状态
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        return dp[length - 1];
    }

    /**
     * 压缩状态数组
     */
    public int robV2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }

        return second;
    }

    public static void main(String[] args) {
        Question198HouseRobber houseRobber = new Question198HouseRobber();
        System.out.println(houseRobber.rob(new int[]{2, 1}));

        System.out.println(houseRobber.rob(new int[]{1, 2, 3, 1}));
        System.out.println(houseRobber.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(houseRobber.rob(new int[]{2, 1, 1, 2}));

        System.out.println(houseRobber.rob(new int[]{6, 3, 10, 8, 2, 10, 3, 5, 10, 5, 3}));
    }
}
