package com.leokongwq.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:57
 * Email:leokongwq@gmail.com
 */
public class ConcreteProductBB implements ProductB {

    @Override
    public void showMe() {
        System.out.println("I am ConcreteProductBB");
    }
}
