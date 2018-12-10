package com.leokongwq.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:55
 * Email:leokongwq@gmail.com
 */
public class ConcreteProductB implements ProductB {
    @Override
    public void showMe() {
        System.out.println("I am ConcreteProductB");
    }
}
