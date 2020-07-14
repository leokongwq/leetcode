package com.leokongwq.algorithm.leetcode;

import com.leokongwq.algorithm.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiexiu
 * created 2020/7/12 - 09:22
 *
 * medium
 *
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 时间复杂度 O（n）
 */
public class Question337HouseRobber {

    private Map<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        //抢
        int do_it = root.val
                + (root.left == null ? 0 : (rob(root.left.left) + rob(root.left.right)))
                + (root.right == null ? 0 : (rob(root.right.left) + rob(root.right.right)));
        //不抢
        int not_do_it = rob(root.left) + rob(root.right);

        int res =  Math.max(do_it, not_do_it);
        memo.put(root, res);

        return res;
    }

    /**
     * 更巧妙的解法
     * res[0] 表示不抢root的最大值
     * res[1] 表示抢root的最大值
     */
    public int robV1(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * 这里本质上利用了分治的思想
     * 1. 假设左子树 和 右子树 代表的子问题解决了。
     * 2. 通过左子树 和 右子树 计算结果，合并解法根节点的计算
     *
     * 感悟：
     *
     * 1. 问题的求解过程中，可以假设子问题已经解决，并构造子问题计算结果的表示形式
     * 2. 基于子问题解决的前提下，构造原问题的解
     *
     */
    private int[] dp(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dp(root.left);
        int[] right = dp(root.right);

        int rob = root.val + left[0] + right[0];
        int notRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return new int[]{notRob, rob};
    }

    public static void main(String[] args) {
        Question337HouseRobber houseRobber = new Question337HouseRobber();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2, null, new TreeNode(3));
        root.right = new TreeNode(3, null, new TreeNode(1));
        System.out.println(houseRobber.rob(root));
    }
}
