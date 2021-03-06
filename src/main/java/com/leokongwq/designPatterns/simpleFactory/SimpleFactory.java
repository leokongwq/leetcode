package com.leokongwq.designPatterns.simpleFactory;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 下午10:56
 * Email:leokongwq@gmail.com
 */
public class SimpleFactory {

    public static final Product produce(int type){
        switch (type){
            case 1:
                return new ConcreteProductA();
            default:
                return null;
        }
    }
}
