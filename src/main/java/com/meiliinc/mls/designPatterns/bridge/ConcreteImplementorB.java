package com.meiliinc.mls.designPatterns.bridge;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:40
 * Email:jiexiu@mogujie.com
 */
public class ConcreteImplementorB implements Implementor {

    @Override
    public void operateImpl() {
        System.out.println("I am ConcreteImplementorB");
    }
}
