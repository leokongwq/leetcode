package com.leokongwq.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/31
 * Time: 下午6:38
 * Email:leokongwq@gmail.com
 * <p>
 * 计数排序（Counting sort）是一种稳定的线性时间排序算法。计数排序使用一个额外的数组  C ，
 * 其中第i个元素C[i]的值是待排序数组 A 中值等于 i 的元素的个数。然后根据数组  C 来将 A 中的元素排到正确的位置
 * 当输入的元素是 n个 0 到  k 之间的整数时，它的运行时间是 O(n+k)
 * 由于用来计数的数组 C 的长度取决于待排序数组中数据的范围（等于待排序数组的最大值与最小值的差加上1），
 * 这使得计数排序对于数据范围很大的数组，需要大量时间和内存。例如：计数排序是用来排序0到100之间的数字的最好的算法，但是它不适合按字母顺序排序人名。]
 * 但是，计数排序可以用在基数排序算法中，能够更有效的排序数据范围很大的数组。
 *
 * 通俗地理解，例如有10个年龄不同的人，统计出有8个人的年龄比A小，那A的年龄就排在第9位，用这个方法可以得到其他每个人的位置，也就排好了序。
 * 当然，年龄有重复时需要特殊处理（保证稳定性），这就是为什么最后要<p>反向填充目标数组，以及将每个数字的统计减去1</p>。
 */
public class CountingSort {

    /**
     * 计数排序
     *
     * @param arr 待排序数组
     * @param maxValue   最大的数
     * @return 排序后的数组
     */
    private static int[] countSort(int[] arr, int maxValue) {
        // 因为要以arr中元素的值作为buckets数组的下标值，所以最大值需要:+1
        int[] buckets = new int[maxValue + 1];
        for (int num : arr) {
            buckets[num]++;
        }
        //buckets中每个元素的值都等于：本身的值 + 前驱元素的值
        for (int i = 1; i < buckets.length; i++) {
            buckets[i] += buckets[i - 1];
        }
        //排序结果数组
        int[] sorted = new int[arr.length];
        //倒序遍历arr
        for (int i = arr.length - 1; i >= 0; i--) {
            //buckets[arr[i]] 表示该元素前面还有多少个元素(包括该元素)
            sorted[buckets[arr[i]] - 1] = arr[i];
            buckets[arr[i]]--;
        }
        return sorted;
    }

    /**
     * 优化后的计数排序， 减少内存空间占用
     * @param a
     * @return
     */
    public static int[] countSort(int []a){
        int b[] = new int[a.length];
        int max = a[0], min = a[0];
        for(int i : a){
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        //这里k的大小是要排序的数组中，元素大小的极值差+1
        int k = max - min + 1;
        int c[] = new int[k];
        for(int i = 0; i < a.length; ++i){
            c[a[i]-min] += 1;//优化过的地方，减小了数组c的大小
        }
        for(int i = 1; i < c.length; ++i){
            c[i] = c[i] + c[i-1];
        }
        for(int i = a.length-1; i >= 0; --i){
            b[--c[a[i]-min]] = a[i];//按存取的方式取出c的元素
        }
        return b;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 4, 6, 7, 2};
        arr = countSort(arr, 7);
        Arrays.stream(arr).forEach(n -> System.out.print(n + ","));
    }
}
