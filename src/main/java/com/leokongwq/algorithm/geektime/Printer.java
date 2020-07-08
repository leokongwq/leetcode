package com.leokongwq.algorithm.geektime;

import java.util.Arrays;

/**
 * @author jiexiu
 * created 2020/5/24 - 09:28
 */
public class Printer {

    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.stream(arr).forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++++++++++");
    }
}
