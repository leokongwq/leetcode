package com.meiliinc.mls.designPatterns.bridge;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/12/3
 * Time: 上午11:39
 * Email:jiexiu@mogujie.com
 */
public abstract class Abstraction {

    private Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operate(){
        implementor.operateImpl();
    }
}
