package com.meiliinc.mls.designPatterns.simpleFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 下午10:58
 * Email:jiexiu@mogujie.com
 */
public class ConcreteProductA implements Product {
    @Override
    public void showMe() {
        System.out.println("I am ConcreteProductA");
    }
}
