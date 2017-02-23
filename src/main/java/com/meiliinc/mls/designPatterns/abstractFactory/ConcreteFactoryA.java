package com.meiliinc.mls.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:55
 * Email:jiexiu@mogujie.com
 */
public class ConcreteFactoryA implements AbastractFactory {

    @Override
    public ProductA createProductA() {
        return new ConcreteProductA();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductB();
    }
}
