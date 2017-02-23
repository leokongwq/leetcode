package com.meiliinc.mls.designPatterns.bridge;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:42
 * Email:jiexiu@mogujie.com
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operate() {
        super.operate();
        System.out.println("RefinedAbstraction's operate");
    }
}
