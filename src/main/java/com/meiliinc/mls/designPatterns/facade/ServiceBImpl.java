package com.meiliinc.mls.designPatterns.facade;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:10
 * Email:jiexiu@mogujie.com
 */
public class ServiceBImpl implements ServiceB {

    @Override
    public void methodB() {
        System.out.println("I am ServiceB");
    }
}
