package com.meiliinc.mls.designPatterns.decorator;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 16/11/25
 * Time: 下午10:33
 * Email:jiexiu@mogujie.com
 */
public class SteamedMilk implements Beverage, Decotor {

    private double price = 0.5;

    private Beverage beverage;

    public SteamedMilk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return price + beverage.cost();
    }

    @Override
    public void cal() {

    }
}
