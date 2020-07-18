package com.leokongwq.algorithm.leetcode;

/**
 * @author jiexiu
 * created 2020/7/18 - 11:17
 *
 * hard
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 */
public class Question42Trap {

    /**
     * 时间复杂度 O(N^2)
     * 空间复杂度 O(1)
     */
    public int trap(int[] height) {
        int n = height.length;
        int ans = 0;

        for (int i = 1; i < n - 1; i++) {
            int l_max = 0, r_max = 0;
            // 找右边最高的柱子
            for (int j = i; j < n; j++) {
                r_max = Math.max(r_max, height[j]);
            }
            // 找左边最高的柱子
            for (int j = i; j >= 0; j--) {
                l_max = Math.max(l_max, height[j]);
            }
            // 如果自己就是最高的话，
            // l_max == r_max == height[i]
            ans += Math.min(l_max, r_max) - height[i];
        }
        return ans;
    }

    /**
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public int trapV1(int[] height) {
        int n = height.length;
        int ans = 0;

        //leftMax[i] 表示i左边最高的柱子
        int[] leftMax = new int[height.length];
        leftMax[0] = height[0];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        //leftMax[i] 表示i左边最高的柱子
        int[] rightMax = new int[height.length];
        rightMax[n - 1] = height[n - 1];
        for (int i = n  - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        // 计算答案
        for (int i = 1; i < n - 1; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    /**
     * 双指针
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     */
    public int trapV2(int[] height) {
        if (height == null || height.length <= 2) {
            return 0;
        }
        int n = height.length;
        int left = 0, right = n - 1;
        int ans = 0;
        // l_max 是 height[0..left] 中最高柱子的高度
        int l_max = height[0];
        // r_max 是 height[right..end] 的最高柱子的高度。
        int r_max = height[n - 1];

        while (left <= right) {
            l_max = Integer.max(l_max, height[left]);
            r_max = Integer.max(r_max, height[right]);

            // ans += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                ans += l_max - height[left];
                left++;
            } else {
                ans += r_max - height[right];
                right--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Question42Trap trap = new Question42Trap();
        System.out.println(trap.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap.trapV1(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        System.out.println(trap.trapV2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }
}
