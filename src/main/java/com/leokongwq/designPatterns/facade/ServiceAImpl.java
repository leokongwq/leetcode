package com.leokongwq.designPatterns.facade;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:09
 * Email:leokongwq@gmail.com
 */
public class ServiceAImpl implements ServiceA {

    @Override
    public void methodA() {
        System.out.println("I am ServiceA");
    }
}
