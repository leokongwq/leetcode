package com.meiliinc.mls.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:55
 * Email:jiexiu@mogujie.com
 */
public class ConcreteProductB implements ProductB {
    @Override
    public void showMe() {
        System.out.println("I am ConcreteProductB");
    }
}
