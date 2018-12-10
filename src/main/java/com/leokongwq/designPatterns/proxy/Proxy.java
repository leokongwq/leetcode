package com.leokongwq.designPatterns.proxy;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 下午12:11
 * Email:leokongwq@gmail.com
 */
public class Proxy implements Subject {

    private Subject subject;

    private void before(){
        System.out.println("before");
    }

    private void after(){
        System.out.println("after");
    }

    @Override
    public void request() {
        before();
        subject.request();
        after();
    }
}
