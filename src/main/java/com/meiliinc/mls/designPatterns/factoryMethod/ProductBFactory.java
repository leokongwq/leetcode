package com.meiliinc.mls.designPatterns.factoryMethod;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 上午11:43
 * Email:jiexiu@mogujie.com
 */
public class ProductBFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}
