package com.meiliinc.mls.leetcode;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/8/20
 * Time: 下午8:43
 * Email:jiexiu@mogujie.com
 */
public class Cpu50Percent {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu cores = " + cpuCores);
        for (int i = 0; i < cpuCores / 2; i++){
            new Thread(new Runnable() {
                public void run() {
                    while (true){
                        //System.out.println("I am busy");
                    }
                }
            }).start();
        }
    }
}
