package com.meiliinc.mls.designPatterns.facade;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:08
 * Email:jiexiu@mogujie.com
 */
public class Facade {

    private ServiceA serviceA;

    private ServiceB serviceB;

    /**
     * 统一的对外接口, 当然也可以提供多个对外接口
     */
    public void wrapOperation(){
        serviceA.methodA();
        serviceB.methodB();
    }
}
