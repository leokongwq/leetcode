package com.meiliinc.mls.designPatterns.observer;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 上午11:03
 * Email:jiexiu@mogujie.com
 */
public class ConcreteObserver implements Observer {
    @Override
    public void update(Object event) {
        System.out.println("I am received the msg");
    }
}
