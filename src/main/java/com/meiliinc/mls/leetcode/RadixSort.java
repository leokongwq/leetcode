package com.meiliinc.mls.leetcode;

/**
 * 基数排序
 * 基数排序是非比较排序算法,算法的时间复杂度是O(n). 相比于快速排序的O(nlgn),
 * 从表面上看具有不小的优势.但事实上可能有些出入,因为基数排序的n可能具有比较大的系数K.
 * 因此在具体的应用中,应首先对这个排序函数的效率进行评估.基数排序的主要思路是,
 * 将所有待比较数值(注意,必须是正整数)统一为同样的数位长度,数位较短的数前面补零.
 * 然后, 从最低位开始, 依次进行一次稳定排序(我们常用上一篇blog介绍的计数排序算法,
 * 因为每个位可能的取值范围是固定的从0到9).这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列.
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/28
 * Time: 上午10:21
 * Email:jiexiu@mogujie.com
 */
public class RadixSort {

    private static int[] radixSort(int[] arr, int digit){
        for (int i = 1; i <= digit; i++){
            //用来保存每个数字的待排序位数的值
            int[] tempArr = new int[arr.length];
            //对digitArr 进行排序
            int[] sortDigit = new int[10];
            for (int j = 0; j < arr.length; j++){
                int bitNum = extractDigit(arr[j], i);
                sortDigit[bitNum] += 1;
            }

            for (int m = 1; m < 10; m++){
                sortDigit[m] += sortDigit[m - 1];
            }

            for (int n = arr.length - 1; n >= 0; n--){
                int tmpDigit = extractDigit(arr[n], i);
                tempArr[sortDigit[tmpDigit] - 1] = arr[n];
                sortDigit[tmpDigit] -= 1;
            }
            for (int p = 0; p < arr.length; p++) {
                arr[p] = tempArr[p];
            }
        }
        return arr;
    }

    /**
     * 提取数组 num 的 digit 位的数字. digit 从 1 开始
     * @param num
     * @param digit
     * @return
     */
    private static int extractDigit(int num, int digit){
        int k = digit - 1;
        int tmp = num;
        while (k > 0){
            tmp = tmp / 10;
            k--;
        }
        return tmp % 10;
    }

    public static void main(String[] args) {
        extractDigit(48, 3);

        int[] arr = new int[]{3,2,4,6,5};
        arr = radixSort(arr, 1);
        System.out.println(arr);

        arr = new int[]{332, 653, 632, 755, 433, 722, 48};
        arr = radixSort(arr, 3);
        System.out.println(arr);
    }
}
