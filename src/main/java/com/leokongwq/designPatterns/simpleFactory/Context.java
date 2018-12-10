package com.leokongwq.designPatterns.simpleFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 下午10:58
 * Email:leokongwq@gmail.com
 */
public class Context {
    public static void main(String[] args) {
        Product product = SimpleFactory.produce(1);
        product.showMe();
    }
}
