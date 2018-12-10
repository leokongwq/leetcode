package com.leokongwq.designPatterns.bridge;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:41
 * Email:leokongwq@gmail.com
 */
public class ConcreteImplementorA implements Implementor {
    @Override
    public void operateImpl() {
        System.out.println("I am ConcreteImplementorA");
    }
}
