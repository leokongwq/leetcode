package com.leokongwq.leetcode;

/**
 * @author jiexiu
 * created 2018/12/20 - 21:05
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 */
public class MaxRecArea {

    /**
     * 暴力解法
     */
    private static int maxAreaV1(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }
        int area = 0;
        for (int i = 0; i < heights.length -1; i++) {
            //高
            int height = heights[i];
            for (int j = i + 1; j < heights.length; j++) {
                area = Math.max(area, (j - i) * Math.min(height, heights[j]));
            }
        }

        return area;
    }

    /**
     * 双指针法
     *
     * 最初我们考虑由最外围两条线段构成的区域。现在，为了使面积最大化，我们需要考虑更长的两条线段之间的区域。
     * 如果我们试图将指向较长线段的指针向内侧移动，矩形区域的面积将受限于较短的线段而不会获得任何增加。
     * 但是，在同样的条件下，移动指向较短线段的指针尽管造成了矩形宽度的减小，但却可能会有助于面积的增大。
     * 因为移动较短线段的指针会得到一条相对较长的线段，这可以克服由宽度减小而引起的面积减小。
     */
    private static int maxAreaV2(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }
        int maxarea = 0, l = 0, r = heights.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(heights[l], heights[r]) * (r - l));
            if (heights[l] < heights[r]) {
                l++;
            } else {
                r--;
            }
        }
        return maxarea;
    }

    public static void main(String[] args) {
        System.out.println(maxAreaV1(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
