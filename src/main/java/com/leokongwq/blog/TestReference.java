package com.leokongwq.blog;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * -Xms20M -Xmx20M -Xmn5M
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/15
 * Time: 下午6:49
 * Email:leokongwq@gmail.com
 */
public class TestReference {

    private static int K = 1024;

    private static int M = 1024 * K;

    private static void testSoftReference(){
        byte[] a = new byte[10 * M];
        byte[] b = new byte[2 * M];
        ReferenceQueue queue = new ReferenceQueue();
        SoftReference softReference = new SoftReference(b, queue);
        System.out.println(softReference);
        b = null;
        try {
            byte[] c = new byte[5 * M];
        }catch (Error error){
            System.out.println(softReference.get());
            System.out.println(queue.poll());
        }
        System.out.println(softReference.get());
        System.out.println(queue.poll());
    }

    private static void testWeakReference(){
        byte[] a = new byte[10 * M];
        ReferenceQueue queue = new ReferenceQueue();
        WeakReference weakReference = new WeakReference(a, queue);
        System.out.println(weakReference);
        a = null;
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());
        System.out.println(queue.poll());
    }

    private static boolean isPrime(int n){
        if (n <= 1){
            return false;
        }
        if (n == 2){
            return true;
        }
        if (n % 2 == 0){
            return false;
        }
        //int max = n / 2;
        int max = (int) Math.sqrt((double)n);
        int i = 3;
        while (i <= max){
            if (n % i == 0){
                return false;
            }
            i += 2;
        }
        return true;
    }

    public static void main(String[] args) {
        //testSoftReference();
        //testWeakReference();
        System.out.println(isPrime(-1));
        System.out.println(isPrime(0));
        System.out.println(isPrime(1));
        System.out.println(isPrime(2));
        System.out.println(isPrime(3));
        System.out.println(isPrime(4));
        System.out.println(isPrime(5));
        System.out.println(isPrime(6));
        System.out.println(isPrime(9));
    }
}
