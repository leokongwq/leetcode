package com.leokongwq.geektime.sort;

/**
 * @author jiexiu
 * created 2020/6/1 - 10:28
 * O(n) 时间复杂度内求无序数组中的第 K 大元素。比如: 4, 2, 5, 12, 3 这样一组数据，第 3 大元素就是 4。
 *
 * 选择数组区间 A[0…n-1]的最后一个元素 A[n-1]作为 pivot，对数组 A[0…n-1]原地分区，这样数组就分成了三部分，A[0…p-1]、A[p]、A[p+1…n-1]。
 * 如果 p+1=K，那 A[p]就是要求解的元素； 原因：整个数组按降序排列。如果要求第 k 个最小元素，那么数组升序排列即可
 * 如果 K>p+1,说明第 K 大元素出现在 A[p+1…n-1]区间，我们再按照上面的思路递归地在 A[p+1…n-1]这个区间内查找。
 * 同理，如果K<p+1，那我们就在 A[0…p-1]区间查找
 *
 */
public class FindKthBigElement {

    private static int findKthBig(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }
        int p = SortUtil.partition(arr, 0, arr.length - 1, true);
        while (p + 1 != k) {
            if (p + 1 < k) {
                p = SortUtil.partition(arr, p + 1, arr.length - 1, true);
            } else {
                p = SortUtil.partition(arr, 0, p - 1, true);
            }
        }
        return arr[p];
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{4, 2, 5, 12, 3};
        int[] arr = new int[]{1, 2, 3, 4, 5};

        System.out.println(findKthBig(arr, 1));
        System.out.println(findKthBig(arr, 2));
        System.out.println(findKthBig(arr, 3));
        System.out.println(findKthBig(arr, 5));
    }
}
