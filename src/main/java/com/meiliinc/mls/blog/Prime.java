package com.meiliinc.mls.blog;

public class Prime {
    // 返回n以内的素数列表
    public static int[] getPrimes(int n) {
        int[] a = new int[n];
        for(int i = 2; i < n; i ++) {
            a[i] = i;
        }
        // 筛法
        for(int i = 2; i < n; i ++) {
            if (a[i] != 0) {
                for(int j = i * 2; j < n; j = j + i) {
                    a[j] = 0;
                }
            }
        }
        int count = 0;
        for(int i = 2; i < n; i++) {
            if (a[i] != 0) {
                count ++;
            }
        }
        if (count > 0) {
            int[] primes  = new int[count];
            int j = 0;
            for (int i = 2; i < n; i ++) {
                if(a[i] != 0) {
                    primes[j] = a[i];
                    j ++;
                }
            }
            return primes;
        }
        return null;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] a = getPrimes(2000000);
        System.out.println(System.currentTimeMillis() - start);

//        for (int i = 0; i < a.length; i ++) {
//            System.out.print(a[i] + ",");
//        }
        System.out.println();
    }
}