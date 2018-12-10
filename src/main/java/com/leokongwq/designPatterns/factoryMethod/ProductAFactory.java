package com.leokongwq.designPatterns.factoryMethod;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 上午11:42
 * Email:leokongwq@gmail.com
 */
public class ProductAFactory implements Factory {

    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}
