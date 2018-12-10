package com.leokongwq.designPatterns.factoryMethod;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 上午11:41
 * Email:leokongwq@gmail.com
 */
public class ConcreteProductA implements Product {
    @Override
    public void user() {
        System.out.println("I am ConcreteProductA");
    }
}
