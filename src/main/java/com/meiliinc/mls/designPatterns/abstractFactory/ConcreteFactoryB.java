package com.meiliinc.mls.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:56
 * Email:jiexiu@mogujie.com
 */
public class ConcreteFactoryB implements AbastractFactory {

    @Override
    public ProductA createProductA() {
        return new ConcreteProductAA();
    }

    @Override
    public ProductB createProductB() {
        return new ConcreteProductBB();
    }
}
