package com.leokongwq.blog;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/16
 * Time: 下午3:36
 * Email:leokongwq@gmail.com
 */
public class PrimeTest {

    private final static int N = 100;
    private static List<Integer> primes = new LinkedList<Integer>();


    public static List<Integer> findPrime3(int n) {
        List<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        for(int i = 3; i <= n; i+=2) {
            for(int j = 0; j < primes.size(); j++) {
                if(i % primes.get(j) == 0) {
                    break;
                }
                if(j == primes.size() - 1) { primes.add(i); break; }
            }
        }
        return primes;
    }

    private static void test(int n){
        //假设所有的数都是素数
        int[] primes = new int[n + 1];
        //1不是素数
        primes[1] = 1;
        for (int i = 2; i <=n; i++){
            if (i == 2){
                continue;
            }
            //所有能被二整除的数都不是素数
            if (i % 2 == 0){
                primes[i] = 1;
            }
        }
        for (int i = 3; i <=n; i+=2){
            if (primes[i] == 0){ //是奇数下标, 把它的所有倍数下标置为1
                int k = 2;
                int m = k * i;
                while (m <= n){
                    primes[m] = 1;
                    m = i * (k++);
                }
            }
        }
        //
//        for (int i = 2; i < n; i++){
//            if (primes[i] == 0){
//                System.out.print(i + ",");
//            }
//        }
        System.out.println();
    }

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        //List<Integer> primes = findPrime3(2000000);
        System.out.println(System.currentTimeMillis() - s);
        //System.out.println(primes);
        long start = System.currentTimeMillis();
        test(2000000);
        System.out.println(System.currentTimeMillis() - start);


        Map<Integer, Integer> map = new HashMap<Integer, Integer>(1 << 30, 0.000075f);
        for (int i = 1; i < 80530; i++){
            map.put(i, i);
        }
        for (int i = 1; i < 10000; i++){
            map.put(i, i);
        }
        System.out.println("map size = " + map.size());
        System.out.println(Integer.MAX_VALUE);
        int a = Integer.MAX_VALUE;
        System.out.println(a);
    }
}
