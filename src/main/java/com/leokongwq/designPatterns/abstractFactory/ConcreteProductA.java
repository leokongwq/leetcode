package com.leokongwq.designPatterns.abstractFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/26
 * Time: 下午12:54
 * Email:leokongwq@gmail.com
 */
public class ConcreteProductA implements ProductA {
    @Override
    public void use() {
        System.out.println("use ConcreteProductA");
    }
}
