package com.leokongwq.offer;

import com.leokongwq.geektime.Printer;

import java.util.LinkedList;

/**
 * @author jiexiu
 * created 2020/5/31 - 21:47
 */
public class GetMaxWindow {

    /**
     * 大小为w的窗口从arr数组逐个元素划过，求得每个窗口内元素的最大值
     * @param arr 数组
     * @param w 窗口大小
     * @return 每个窗口中的最大值
     *
     * e.g arr = [4, 3, 5, 4, 3, 3, 6, 7]
     * result = [5, 5, 5, 4, 6, 7]
     */
    private static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        int[] result = new int[arr.length - w + 1];
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.removeLast();
            }
            qmax.addLast(i);
            //队首元素过期
            if (i - w == qmax.peekFirst()) {
                qmax.removeFirst();
            }
            if (i  >= w - 1) {
                result[k++] = arr[qmax.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] result = GetMaxWindow.getMaxWindow(new int[] {4, 3, 5, 4, 3, 3, 6, 7}, 3);
        Printer.printArray(result);
    }
}
