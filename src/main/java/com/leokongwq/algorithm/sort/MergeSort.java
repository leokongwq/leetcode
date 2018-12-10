package com.leokongwq.algorithm.sort;

/**
 *
 * 归并排序
 *最好, 最坏, 平均时间复杂度 O(nlogn)
 * 空间复杂度 O(n)
 * 稳定排序
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/27
 * Time: 下午10:12
 * Email:leokongwq@gmail.com
 */
public class MergeSort {

    /**
     * //将有二个有序数列a[start ... mid]和a[mid ... end]合并。
     * @param sorted 已经排序好的数组
     * @param start 该排序数组的的前半部分起始位置
     * @param mid 该排序数组的的前半部分中间位置
     * @param end 该排序数组的的前半部分中点位置
     * @param sorted 合并后的结果数组
     */
    private static void merge(int[] unsorted, int start, int mid, int end, int[] sorted){
        int i = start, j = mid + 1;
        int m = mid,   n = end;
        int k = 0;
        while (i <= m && j <= n){
            if (unsorted[i] > unsorted[j]){
                sorted[k++] = unsorted[j++];
            }else {
                sorted[k++] = unsorted[i++];
            }
        }
        while (i <= m){
            sorted[k++] = unsorted[i++];
        }
        while (j <= n){
            sorted[k++] = unsorted[j++];
        }
        //将排序后的数组值赋值给未排序的数组
        for (int v = 0; v < k; v++){
            unsorted[start + v] = sorted[v];
        }
    }

    /**
     * 归并排序
     * @param unSorted
     * @param first
     * @param last
     * @param sorted
     */
    private static void mergeSort(int[] unSorted, int first, int last, int[] sorted){
        if (first < last){
            int mid = first + (last - first) / 2;
            //前半部分进行归并排序,
            mergeSort(unSorted, first, mid, sorted);
            //后半部分进行归并排序,
            mergeSort(unSorted, mid + 1, last, sorted);
            //合并
            merge(unSorted, first, mid, last, sorted);
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,4,6,5};
        int[] result = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, result);
        System.out.println(result);
    }
}
